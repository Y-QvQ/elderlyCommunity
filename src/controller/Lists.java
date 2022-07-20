package controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import model.Architecture;
import model.ArchitectureList;
import model.House;
import model.Member;
import model.Person;
import model.PersonList;
import model.Stadium;
import model.Staff;
import model.Traffic;
/**
 * 该类为后台管理类，可管理用户，员工，房屋，场馆，班车，一览等信息
 * @author YQQ
 *
 */
public class Lists {

	private Main main = new Main();
	private Lists controllerList;// 用于传递list页面的控制器，便于在之后弹出的窗口结束时调用init()达到刷新的目的
	private Stage dialogStage;

	/**
	 * 用以初始化加载各项数据
	 */
	@FXML
	private void initialize() {

		loadDataMember(observable, "person.dat");// 加载列表
		loadDataStaff(observableS, "staff.dat");
		loadDataHouse(observableH, "house.dat");
		loadDataStadium(observableA, "stadium.dat");
		loadDataTraffic("traffic.dat");

		loadData();

		tableM.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {// lambda语法
			showMember(null);
			showMember(newValue);
		});// 设置监听器，observablse不能是局部变量
		tableS.getSelectionModel().selectedItemProperty().addListener((observableS, oldValue, newValue) -> {// lambda语法
			showStaff(null);
			showStaff(newValue);
		});
		tableT.getSelectionModel().selectedItemProperty().addListener((observableT, oldValue, newValue) -> {
			showTraffic(null);
			showTraffic(newValue);
		});
	}

	/**
	 * 添加各个对象关系时用于刷新数据和表格
	 * @param str
	 * 用于判断刷新哪个数据
	 */
	public void init(String str) {// 通过控制器调用来刷新数据
		if (str.equals("住户")) {
			loadDataMember(observable, "person.dat");
			tableM.refresh();
		} else if (str.equals("员工")) {
			loadDataStaff(observableS, "staff.dat");
			tableS.refresh();
		} else if (str.equals("房屋")) {
			loadDataHouse(observableH, "house.dat");
			tableH.refresh();
		}

	}

	/**
	 * 用于获得当前页面stage
	 * @return
	 */
	public Stage getDialogStage() {
		return dialogStage;
	}

	/**
	 * 设置当前页面stage
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	// 住户界面*********************************************
	/**
	 * 可监听数据的list，用于与tableView配合动态刷新表格
	 */
	ObservableList<Member> observable = FXCollections.observableArrayList();// 读入Member并对其操作的对象
	/**
	 * 读写文件时用到的list对象
	 */
	PersonList listM = new PersonList();

	@FXML
	private Label birthdayM;

	@FXML
	private Label genderM;

	@FXML
	private Label houseM;

	@FXML
	private Label idCardM;

	@FXML
	private Label kinsfolkM;

	@FXML
	private Label nameeM;

	@FXML
	private Label phoneeM;

	@FXML
	private Label remarkM;

	@FXML
	private Label staffM;

	@FXML
	private Label typeM;

	@FXML
	private TableColumn<Member, String> nameM;

	@FXML
	private TableColumn<Member, String> phoneM;

	@FXML
	private TableView<Member> tableM;

	@FXML
	private Button newM;

	@FXML
	private Button deleteM;

	@FXML
	private Button editM;

	@FXML
	private ImageView pictureM;

	/**
	 * 刷新lable的数据
	 * @param member
	 */
	@FXML
	private void showMember(Member member) {// 加载个人详细信息
		if (member != null) {
			nameeM.setText(member.getName());
			phoneeM.setText(member.getPhone());
			birthdayM.setText(member.getBirthday());
			genderM.setText(member.getGender());
			idCardM.setText(member.getIDCard());
			if (member.getStaff() != null) {// 非必须属性，可以未设置，直接显示默认"无"
				staffM.setText(member.getStaff().getName() + "," + member.getStaff().getPhone() + " ");
			}
			houseM.setText(member.getHouse().getId());
			typeM.setText(member.getType());
			remarkM.setText(member.getRemark());
			kinsfolkM.setText(member.getAllName());

			InputStream is = null;// 直接用new Image(String dir)会显示找不到资源
			try {
				File testF = new File("picture/" + member.getPhone() + ".jpg");
				if (testF.exists()) {// 判断文件是否存在然后再加载头像
					is = new FileInputStream("picture/" + member.getPhone() + ".jpg");
				} else {
					is = new FileInputStream("picture/0.jpg");
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (is != null) {
					pictureM.setPreserveRatio(true);// 保持比例
					pictureM.setImage(new Image(is));
					try {
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		} else {
			nameeM.setText("无");
			phoneeM.setText("无");
			birthdayM.setText("无");
			genderM.setText("无");
			idCardM.setText("无");
			staffM.setText("无");
			houseM.setText("无");
			typeM.setText("无");
			remarkM.setText("无");
			kinsfolkM.setText("无");
			InputStream is = null;
			try {
				File testF = new File("picture/0.jpg");// 加载默认图片
				if (testF.exists()) {// 判断文件是否存在然后再加载
					is = new FileInputStream("picture/0.jpg");
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (is != null) {
					pictureM.setPreserveRatio(true);// 保持比例
					pictureM.setImage(new Image(is));
					try {
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	/**
	 * 从文件中读取list数据并刷新数据
	 * @param observable
	 * @param dir
	 */
	private void loadDataMember(ObservableList<Member> observable, String dir) {// 从指定文件中加载对象
		nameM.setCellValueFactory(new PropertyValueFactory<>("name"));// 监听对应数据，会调用相应属性的get方法，如getName
		phoneM.setCellValueFactory(new PropertyValueFactory<>("phone"));

		observable.clear();

		File file = new File(dir);
		if (file.exists()) {
			listM.readList(dir);
			for (int i = 0; i < listM.getSize(); i++) {
				observable.add((Member) listM.get(i));// 将文件中读取的对象添加到observable
			}
			tableM.setItems(observable);
		}

	}

	/**
	 * 将list数据写入文件
	 */
	private void writeMember() {// 写入文件
		listM = new PersonList();// 清空对象
		for (Member member : observable) {
			listM.addList(member);
			listM.writeList("person.dat");

		}
	}

	/**
	 * 删除选中对象
	 */
	@FXML
	private void removeMember() {
		int selectedIndex = tableM.getSelectionModel().getSelectedIndex();// 找不到索引返回-1
		if (selectedIndex >= 0) {
			tableM.getItems().remove(selectedIndex);
			writeMember();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("错误");
			alert.setHeaderText(null);
			alert.setContentText("您删除了不存在的对象!");

			alert.showAndWait();
		}
	}

	/**
	 * 创建新的对象
	 */
	@FXML
	private void newMember() {
		Member tempMember = new Member();
		observable.add(tempMember);
		boolean okClicked = main.showMemberEditDialog(tempMember, controllerList);
		if (okClicked) {
			tableM.refresh();
			writeMember();

			try {
				main.showList();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dialogStage.close();
		} else {// 不点击确认删除添加的对象
			observable.remove(tempMember);
		}
	}


	/**
	 * 编辑指定对象
	 */
	@FXML
	private void editMember() {
		Member member = tableM.getSelectionModel().getSelectedItem();// 获取选择的对象
		if (member != null) {
			boolean okClicked = main.showMemberEditDialog(member, controllerList);
			if (okClicked) {
				tableM.refresh();
				showMember(member);// 编辑完后展示该用户个人页面
				// writeMember();
			}

		} else {
			// 没有选择对象
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

	// 员工*******************************************************
	private ObservableList<Staff> observableS = FXCollections.observableArrayList();
	private PersonList listS = new PersonList();

	@FXML
	private Label birthdayS;

	@FXML
	private Label genderS;

	@FXML
	private Label houseS;

	@FXML
	private Label idCardS;

	@FXML
	private Label memberS;

	@FXML
	private Label nameeS;

	@FXML
	private Label phoneeS;

	@FXML
	private Label typeS;

	@FXML
	private TableColumn<Staff, String> nameS;

	@FXML
	private TableColumn<Staff, String> phoneS;

	@FXML
	private TableView<Staff> tableS;

	@FXML
	private Button newS;

	@FXML
	private Button deleteS;

	@FXML
	private Button editS;

	@FXML
	private ImageView pictureS;

	@FXML
	private void showStaff(Staff staff) {// 加载个人详细信息
		if (staff != null) {
			nameeS.setText(staff.getName());
			phoneeS.setText(staff.getPhone());
			birthdayS.setText(staff.getBirthday());
			genderS.setText(staff.getGender());
			idCardS.setText(staff.getIDCard());
			typeS.setText(staff.getType());
			memberS.setText(staff.getAllName());

			InputStream is = null;// 直接用new Image(String dir)会显示找不到资源
			try {
				File testF = new File("picture/" + staff.getPhone() + ".jpg");
				if (testF.exists()) {// 判断文件是否存在然后再加载头像
					is = new FileInputStream("picture/" + staff.getPhone() + ".jpg");
				} else {
					is = new FileInputStream("picture/0.jpg");
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (is != null) {
					pictureS.setPreserveRatio(true);// 保持比例
					pictureS.setImage(new Image(is));
					try {
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		} else {
			nameeS.setText("无");
			phoneeS.setText("无");
			birthdayS.setText("无");
			genderS.setText("无");
			idCardS.setText("无");
			typeS.setText("无");
			memberS.setText("无");
			InputStream is = null;// 直接用new Image(String dir)会显示找不到资源
			try {
				File testF = new File("picture/0.jpg");// 加载默认图片
				if (testF.exists()) {// 判断文件是否存在然后再加载
					is = new FileInputStream("picture/0.jpg");
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (is != null) {
					pictureS.setPreserveRatio(true);// 保持比例
					pictureS.setImage(new Image(is));
					try {
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

	}

	private void loadDataStaff(ObservableList<Staff> observable, String dir) {// 从指定文件中加载对象
		nameS.setCellValueFactory(new PropertyValueFactory<>("name"));
		phoneS.setCellValueFactory(new PropertyValueFactory<>("phone"));

		observable.clear();

		File file = new File(dir);
		if (file.exists()) {
			listS.readList(dir);
			for (int i = 0; i < listS.getSize(); i++) {
				observable.add((Staff) listS.get(i));// 将文件中读取的对象添加到observable
			}
			tableS.setItems(observable);
		}

	}

	private void writeStaff() {// 写入文件
		listS = new PersonList();// 清空对象
		for (Staff staff : observableS) {
			listS.addList(staff);
			listS.writeList("staff.dat");

		}
	}

	@FXML
	private void removeStaff() {
		int selectedIndex = tableS.getSelectionModel().getSelectedIndex();// 找不到索引返回-1
		if (selectedIndex >= 0) {
			tableS.getItems().remove(selectedIndex);
			writeStaff();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("错误");
			alert.setHeaderText(null);
			alert.setContentText("您删除了不存在的对象!");

			alert.showAndWait();
		}
	}

	@FXML
	private void newStaff() {
		Staff tempStaff = new Staff();
		observableS.add(tempStaff);
		boolean okClicked = main.showStaffEditDialog(tempStaff, controllerList);
		if (okClicked) {
			tableS.refresh();
			writeStaff();

			try {
				main.showList();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dialogStage.close();
		} else {
			observableS.remove(tempStaff);
		}
	}

	@FXML
	private void editStaff() {
		Staff staff = tableS.getSelectionModel().getSelectedItem();// 获取选择的对象
		if (staff != null) {
			boolean okClicked = main.showStaffEditDialog(staff, controllerList);
			if (okClicked) {
				showStaff(staff);// 编辑完后展示该用户个人页面
				tableS.refresh();
				writeStaff();
			}

		} else {
			// 没有选择对象
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

//房屋*************************************************
	private ObservableList<House> observableH = FXCollections.observableArrayList();
	private ArchitectureList listH = new ArchitectureList();
	@FXML
	private Button editH;

	@FXML
	private Button newH;

	@FXML
	private TableView<House> tableH;

	@FXML
	private TableColumn<House, String> locationH;

	@FXML
	private TableColumn<House, String> memberH;

	@FXML
	private TableColumn<House, String> remarkH;

	@FXML
	private TableColumn<House, Boolean> stateH;

	private void loadDataHouse(ObservableList<House> observable, String dir) {// 从指定文件中加载对象
		locationH.setCellValueFactory(new PropertyValueFactory<>("id"));
		memberH.setCellValueFactory(new PropertyValueFactory<>("allName"));
		remarkH.setCellValueFactory(new PropertyValueFactory<>("remark"));
		stateH.setCellValueFactory(new PropertyValueFactory<>("state"));

		observable.clear();

		File file = new File(dir);
		if (file.exists()) {
			listH.readList(dir);
			for (int i = 0; i < listH.getSize(); i++) {
				observable.add((House) listH.get(i));// 将文件中读取的对象添加到observable
			}
			tableH.setItems(observable);
		}

	}

	private void writeHouse() {// 写入文件
		listH = new ArchitectureList();// 清空对象
		for (House house : observableH) {
			listH.addList(house);
			listH.writeList("house.dat");
		}
	}

	@FXML
	private void editHouse(ActionEvent event) {
		House house = tableH.getSelectionModel().getSelectedItem();// 获取选择的对象
		if (house != null) {
			boolean okClicked = main.showHouseEditDialog(house, controllerList);
			if (okClicked) {
				tableH.refresh();
				writeHouse();
			}

		} else {
			// 没有选择对象
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Selection");
			alert.setHeaderText("No House Selected");
			alert.setContentText("Please select a house in the table.");

			alert.showAndWait();
		}
	}

	@FXML
	private void newHouse(ActionEvent event) {
		House tempHouse = new House();
		observableH.add(tempHouse);
		boolean okClicked = main.showHouseEditDialog(tempHouse, controllerList);
		if (okClicked) {
			tableH.refresh();
			writeHouse();
		} else {
			observableH.remove(tempHouse);
		}
	}

//设施***********************************************
	private ObservableList<Stadium> observableA = FXCollections.observableArrayList();
	private ArchitectureList listA = new ArchitectureList();

	@FXML
	private Button editA;

	@FXML
	private Button newA;

	@FXML
	private TableView<Stadium> tableA;

	@FXML
	private TableColumn<Stadium, Integer> beginA;

	@FXML
	private TableColumn<Stadium, Integer> endA;

	@FXML
	private TableColumn<Stadium, String> locationA;

	@FXML
	private TableColumn<Stadium, Integer> maxA;

	@FXML
	private TableColumn<Stadium, String> memberA;

	@FXML
	private TableColumn<Stadium, String> nameA;

	@FXML
	private TableColumn<Stadium, Boolean> stateA;

	private void loadDataStadium(ObservableList<Stadium> observable, String dir) {// 从指定文件中加载对象
		locationA.setCellValueFactory(new PropertyValueFactory<>("id"));
		memberA.setCellValueFactory(new PropertyValueFactory<>("allName"));
		beginA.setCellValueFactory(new PropertyValueFactory<>("begin"));
		endA.setCellValueFactory(new PropertyValueFactory<>("end"));
		maxA.setCellValueFactory(new PropertyValueFactory<>("size"));
		nameA.setCellValueFactory(new PropertyValueFactory<>("name"));
		stateA.setCellValueFactory(new PropertyValueFactory<>("state"));

		observable.clear();

		File file = new File(dir);
		if (file.exists()) {
			listA.readList(dir);
			for (int i = 0; i < listA.getSize(); i++) {
				observable.add((Stadium) listA.get(i));// 将文件中读取的对象添加到observable
			}
			tableA.setItems(observable);
		}

	}

	private void writeStadium() {// 写入文件
		listA = new ArchitectureList();// 清空对象
		for (Stadium stadium : observableA) {
			listA.addList(stadium);
			listA.writeList("stadium.dat");
		}
	}

	@FXML
	private void editArchitecture(ActionEvent event) {
		Stadium stadium = tableA.getSelectionModel().getSelectedItem();// 获取选择的对象
		if (stadium != null) {
			boolean okClicked = main.showStadiumEditDialog(stadium, controllerList);
			if (okClicked) {
				tableA.refresh();
				writeStadium();
			}

		} else {
			// 没有选择对象
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Selection");
			alert.setHeaderText("No Stadium Selected");
			alert.setContentText("Please select a stadium in the table.");

			alert.showAndWait();
		}
	}

	@FXML
	private void newArchitecture(ActionEvent event) {
		Stadium tempStadium = new Stadium();
		observableA.add(tempStadium);
		boolean okClicked = main.showStadiumEditDialog(tempStadium, controllerList);
		if (okClicked) {
			tableA.refresh();
			writeStadium();
		} else {
			observableA.remove(tempStadium);
		}
	}
	
	/**
	 * 获得管理界面控制器
	 * @return
	 */
	public Lists getControllerList() {
		return controllerList;
	}

	/**
	 * 设置管理界面控制器
	 * @param controllerList
	 */
	public void setControllerList(Lists controllerList) {
		this.controllerList = controllerList;
	}

	/**
	 * 重置场馆成员，创建日志文件
	 */
	@FXML
	private void restart() {// 重置场馆内成员
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String filedir = "stadium-daily/" + sdf.format(date) + ".txt";// 日志名为当前日期2022-5-26
		String temp = "";
		for (Stadium stadium : observableA) {

			temp += stadium.getName() + "(" + stadium.getBegin() + "-" + stadium.getEnd() + ")" + ":"
					+ stadium.getAllName();

			try {
				Writer writer = new OutputStreamWriter(new FileOutputStream(filedir, true), StandardCharsets.UTF_8);// 追加写入
				writer.write(temp+"\r\n");
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < listA.getSize(); i++) {
			observableA.get(i).getMember().clear();
			tableA.refresh();
			writeStadium();
		}

	}

	// 一览********************************************************
	@FXML
	private PieChart pieChart1;

	@FXML
	private PieChart pieChart2;

	@FXML
	private PieChart pieChart3;

	@FXML
	private PieChart pieChart4;

	/**
	 * 用于加载一览图的数据
	 */
	ObservableList<Data> data1 = FXCollections.observableArrayList();
	/**
	 * 用于加载一览图的数据
	 */
	ObservableList<Data> data2 = FXCollections.observableArrayList();
	/**
	 * 用于加载一览图的数据
	 */
	ObservableList<Data> data3 = FXCollections.observableArrayList();
	/**
	 * 用于加载一览图的数据
	 */
	ObservableList<Data> data4 = FXCollections.observableArrayList();

	/**
	 * 加载一览图数据
	 */
	public void loadData() {
		int i = 0;
		int j = 0;
		for (Person person : listM.getPersonList()) {
			if (((Member) person).getHouse().getId().equals((new House()).getId())) {
				i++;
			}
		}
		j = listM.getSize() - i;
		data1.addAll(new Data("未购房的用户", i), new Data("已购房的用户", j));

		i = 0;
		j = 0;
		for (Person person : listS.getPersonList()) {
			if (((Staff) person).getMember().size() == 0) {
				i++;
			}
		}
		j = listS.getSize() - i;
		data2.addAll(new Data("空闲的员工", i), new Data("忙碌的员工", j));

		i = 0;
		j = 0;
		for (Architecture architecture : listH.getList()) {
			if (((House) architecture).getMember().size() == 0) {
				i++;
			}
		}
		j = listH.getSize() - i;
		data3.addAll(new Data("空闲的房间", i), new Data("已购的房间", j));

		i = 0;
		j = 0;
		for (Architecture architecture : listA.getList()) {
			if (((Stadium) architecture).getMember().size() == 0) {
				i++;
			}
		}
		j = listA.getSize() - i;
		data4.addAll(new Data("空闲的场馆", i), new Data("忙碌的场馆", j));

		pieChart1.setTitle("住户情况");
		pieChart1.setData(data1);

		pieChart2.setTitle("员工情况");
		pieChart2.setData(data2);

		pieChart3.setTitle("房间情况");
		pieChart3.setData(data3);

		pieChart4.setTitle("场馆情况");
		pieChart4.setData(data4);
	}

	// 头像上传************************************************

	@FXML
	private void dragOver(DragEvent event) {
		event.acceptTransferModes(TransferMode.ANY);// 传输模式
		// 查看托盘，判断是否有文件，有则获得其绝对路径
	}

	/**
	 * 获得拖拽的头像的路径，将其根据选择对象的电话号命名后存入指定文件夹
	 * @param event
	 */
	@FXML
	void dragDropped(DragEvent event) {
		Dragboard dragboard = event.getDragboard();
		String path = null;
		if (dragboard.hasFiles()) {
			path = dragboard.getFiles().get(0).getAbsolutePath();// 获得绝对路径
		}

		int selectedIndex = tableM.getSelectionModel().getSelectedIndex();// 找不到索引返回-1
		String str = null;
		if (selectedIndex >= 0) {
			str = "picture/" + tableM.getItems().get(selectedIndex).getPhone() + ".jpg";// 获得对象电话名
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("错误");
			alert.setHeaderText(null);
			alert.setContentText("请选择对象!");

			alert.showAndWait();
		}
		if (path != null && str != null) {
			try {
				FileInputStream is = new FileInputStream(new File(path));
				FileOutputStream os = new FileOutputStream(new File(str));
				int len = 0;
				byte[] buffer = new byte[1024];
				while ((len = is.read(buffer)) != -1) {
					os.write(buffer, 0, len);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}

	// 交通***********************************************
	private ObservableList<Traffic> observableT = FXCollections.observableArrayList();

	@FXML
	private Label allnameT;

	@FXML
	private Button deleteT;

	@FXML
	private Button editT;

	@FXML
	private Button newT;

	@FXML
	private Button restartT;

	@FXML
	private TableView<Traffic> tableT;

	@FXML
	private TableColumn<Traffic, String> beginT;

	@FXML
	private TableColumn<Traffic, String> dailyT;

	@FXML
	private TableColumn<Traffic, String> endT;

	@FXML
	private TableColumn<Traffic, String> idT;

	@FXML
	private TableColumn<Traffic, String> nameT;

	@FXML
	private TableColumn<Traffic, String> remarkT;

	@FXML
	private TableColumn<Traffic, String> typeT;

	@FXML
	private void newTraffic(ActionEvent event) {
		Traffic traffic = new Traffic();
		observableT.add(traffic);


		boolean okClicked = main.showTrafficEditDialog(traffic);
		if (okClicked) {
			tableT.refresh();
			writeT("traffic.dat");
		} else {
			observableT.remove(traffic);
		}

	}

	@FXML
	private void editTraffic(ActionEvent event) {
		Traffic traffic = tableT.getSelectionModel().getSelectedItem();
		if(traffic!=null) {
			boolean okClicked = main.showTrafficEditDialog(traffic);
			if (okClicked) {
				tableT.refresh();
				writeT("traffic.dat");
			}
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Selection");
			alert.setHeaderText("No Traffic Selected");
			alert.setContentText("Please select a traffic in the table.");

			alert.showAndWait();
		}


	}

	@FXML
	private void deleteTraffic(ActionEvent event) {
		Traffic temp=tableT.getSelectionModel().getSelectedItem();
		observableT.remove(temp);
		writeT("traffic.dat");
		if(temp!=null) {
			observableT.remove(tableT.getSelectionModel().getSelectedItem());
			writeT("traffic.dat");
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Selection");
			alert.setHeaderText("No Traffic Selected");
			alert.setContentText("Please select a traffic in the table.");

			alert.showAndWait();
		}
	}

	@FXML
	private void restartTraffic(ActionEvent event) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String filedir = "traffic-daily/" + sdf.format(date) + ".txt";// 日志名为当前日期2022-5-26
		String temp = "";
		for (Traffic traffic : observableT) {

			temp += traffic.getName() + "(" + traffic.getBegin() + "-" + traffic.getEnd() + ")" + ":"
					+ traffic.getAllName();

			try {
				Writer writer = new OutputStreamWriter(new FileOutputStream(filedir, true), StandardCharsets.UTF_8);// 追加写入
				writer.write(temp+"\r\n");
				writer.flush();
				
				traffic.setAllName("");//写入日志后清空
				writer.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		writeT("traffic.dat");
		tableT.refresh();
		showTraffic(null);
	}

	/**
	 * 读取json类型保存的班车数据文件
	 * @param dir
	 * 读取路径
	 */
	public void readT(String dir) {
		Gson gson=new Gson();
		File file=new File(dir);
		
		if(file.exists()) {
			FileReader fr=null;
			BufferedReader br=null;
			try {

				try {
					fr = new FileReader(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				br=new BufferedReader(fr);
				String data=null;
				while((data=br.readLine())!=null) {
					Traffic e=gson.fromJson(data, Traffic.class);//将读入json格式字符串转化为Traffic对象
					observableT.add(e);
				}
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(br!=null) {
					try {
						br.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}


		}

	}

	/**
	 * 以json格式存储班车数据
	 * @param dir
	 * 存储路径
	 */
	public void writeT(String dir) {
		Gson gson=new Gson();
		File file=new File(dir);
		
		if(file.exists()) {
			FileWriter fw=null;
			BufferedWriter bw=null;
			try {

				try {
					fw = new FileWriter(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				bw=new BufferedWriter(fw);
				for(Traffic traffic:observableT) {
					try {
						bw.write(gson.toJson(traffic));//写入文件
						bw.newLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(bw!=null) {
					try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}


		}

	}
	
	private void loadDataTraffic(String dir) {// 从指定文件中加载对象
		nameT.setCellValueFactory(new PropertyValueFactory<>("name"));
		typeT.setCellValueFactory(new PropertyValueFactory<>("type"));
		beginT.setCellValueFactory(new PropertyValueFactory<>("begin"));
		endT.setCellValueFactory(new PropertyValueFactory<>("end"));
		remarkT.setCellValueFactory(new PropertyValueFactory<>("remark"));
		dailyT.setCellValueFactory(new PropertyValueFactory<>("dail"));
		idT.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		
		readT(dir);
		tableT.setItems(observableT);
		
	}
	
	private void showTraffic(Traffic traffic) {// 加载个人详细信息
		if (traffic != null) {
			allnameT.setText(traffic.getAllName());
		} else {
			allnameT.setText("无");

		}

	}
	
	private ObservableList<Staff> observableSS = FXCollections.observableArrayList();
	
	@FXML
	private Button searchS;
	
	@FXML
	private Button restartS;
	
	@FXML
	private TextField searchFieldS;
	
	@FXML
	private void searchStaff(ActionEvent event) {
		observableSS.clear();
		String regEx = ".*"+searchFieldS.getText()+"+?.*";//判断是否含有输入内容
		Pattern pattern = Pattern.compile(regEx);
		for (Staff staff: observableS) {

			Matcher matcher = pattern.matcher(staff.getPhone());
			Matcher matcher1 = pattern.matcher(staff.getName());

			if (matcher.find()) {
				observableSS.add(staff);
			}
			if (matcher1.find()) {
				observableSS.add(staff);
			}
		}
		tableS.setItems(observableSS);
		tableS.refresh();
	}
	
	@FXML
	private void restartStaff(ActionEvent event) {
		observableSS.clear();
		searchFieldS.setText("");
		tableS.setItems(observableS);
		tableS.refresh();
	}

}
