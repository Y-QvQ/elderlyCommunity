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
 * ����Ϊ��̨�����࣬�ɹ����û���Ա�������ݣ����ݣ��೵��һ������Ϣ
 * @author YQQ
 *
 */
public class Lists {

	private Main main = new Main();
	private Lists controllerList;// ���ڴ���listҳ��Ŀ�������������֮�󵯳��Ĵ��ڽ���ʱ����init()�ﵽˢ�µ�Ŀ��
	private Stage dialogStage;

	/**
	 * ���Գ�ʼ�����ظ�������
	 */
	@FXML
	private void initialize() {

		loadDataMember(observable, "person.dat");// �����б�
		loadDataStaff(observableS, "staff.dat");
		loadDataHouse(observableH, "house.dat");
		loadDataStadium(observableA, "stadium.dat");
		loadDataTraffic("traffic.dat");

		loadData();

		tableM.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {// lambda�﷨
			showMember(null);
			showMember(newValue);
		});// ���ü�������observablse�����Ǿֲ�����
		tableS.getSelectionModel().selectedItemProperty().addListener((observableS, oldValue, newValue) -> {// lambda�﷨
			showStaff(null);
			showStaff(newValue);
		});
		tableT.getSelectionModel().selectedItemProperty().addListener((observableT, oldValue, newValue) -> {
			showTraffic(null);
			showTraffic(newValue);
		});
	}

	/**
	 * ��Ӹ��������ϵʱ����ˢ�����ݺͱ��
	 * @param str
	 * �����ж�ˢ���ĸ�����
	 */
	public void init(String str) {// ͨ��������������ˢ������
		if (str.equals("ס��")) {
			loadDataMember(observable, "person.dat");
			tableM.refresh();
		} else if (str.equals("Ա��")) {
			loadDataStaff(observableS, "staff.dat");
			tableS.refresh();
		} else if (str.equals("����")) {
			loadDataHouse(observableH, "house.dat");
			tableH.refresh();
		}

	}

	/**
	 * ���ڻ�õ�ǰҳ��stage
	 * @return
	 */
	public Stage getDialogStage() {
		return dialogStage;
	}

	/**
	 * ���õ�ǰҳ��stage
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	// ס������*********************************************
	/**
	 * �ɼ������ݵ�list��������tableView��϶�̬ˢ�±��
	 */
	ObservableList<Member> observable = FXCollections.observableArrayList();// ����Member����������Ķ���
	/**
	 * ��д�ļ�ʱ�õ���list����
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
	 * ˢ��lable������
	 * @param member
	 */
	@FXML
	private void showMember(Member member) {// ���ظ�����ϸ��Ϣ
		if (member != null) {
			nameeM.setText(member.getName());
			phoneeM.setText(member.getPhone());
			birthdayM.setText(member.getBirthday());
			genderM.setText(member.getGender());
			idCardM.setText(member.getIDCard());
			if (member.getStaff() != null) {// �Ǳ������ԣ�����δ���ã�ֱ����ʾĬ��"��"
				staffM.setText(member.getStaff().getName() + "," + member.getStaff().getPhone() + " ");
			}
			houseM.setText(member.getHouse().getId());
			typeM.setText(member.getType());
			remarkM.setText(member.getRemark());
			kinsfolkM.setText(member.getAllName());

			InputStream is = null;// ֱ����new Image(String dir)����ʾ�Ҳ�����Դ
			try {
				File testF = new File("picture/" + member.getPhone() + ".jpg");
				if (testF.exists()) {// �ж��ļ��Ƿ����Ȼ���ټ���ͷ��
					is = new FileInputStream("picture/" + member.getPhone() + ".jpg");
				} else {
					is = new FileInputStream("picture/0.jpg");
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (is != null) {
					pictureM.setPreserveRatio(true);// ���ֱ���
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
			nameeM.setText("��");
			phoneeM.setText("��");
			birthdayM.setText("��");
			genderM.setText("��");
			idCardM.setText("��");
			staffM.setText("��");
			houseM.setText("��");
			typeM.setText("��");
			remarkM.setText("��");
			kinsfolkM.setText("��");
			InputStream is = null;
			try {
				File testF = new File("picture/0.jpg");// ����Ĭ��ͼƬ
				if (testF.exists()) {// �ж��ļ��Ƿ����Ȼ���ټ���
					is = new FileInputStream("picture/0.jpg");
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (is != null) {
					pictureM.setPreserveRatio(true);// ���ֱ���
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
	 * ���ļ��ж�ȡlist���ݲ�ˢ������
	 * @param observable
	 * @param dir
	 */
	private void loadDataMember(ObservableList<Member> observable, String dir) {// ��ָ���ļ��м��ض���
		nameM.setCellValueFactory(new PropertyValueFactory<>("name"));// ������Ӧ���ݣ��������Ӧ���Ե�get��������getName
		phoneM.setCellValueFactory(new PropertyValueFactory<>("phone"));

		observable.clear();

		File file = new File(dir);
		if (file.exists()) {
			listM.readList(dir);
			for (int i = 0; i < listM.getSize(); i++) {
				observable.add((Member) listM.get(i));// ���ļ��ж�ȡ�Ķ�����ӵ�observable
			}
			tableM.setItems(observable);
		}

	}

	/**
	 * ��list����д���ļ�
	 */
	private void writeMember() {// д���ļ�
		listM = new PersonList();// ��ն���
		for (Member member : observable) {
			listM.addList(member);
			listM.writeList("person.dat");

		}
	}

	/**
	 * ɾ��ѡ�ж���
	 */
	@FXML
	private void removeMember() {
		int selectedIndex = tableM.getSelectionModel().getSelectedIndex();// �Ҳ�����������-1
		if (selectedIndex >= 0) {
			tableM.getItems().remove(selectedIndex);
			writeMember();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("����");
			alert.setHeaderText(null);
			alert.setContentText("��ɾ���˲����ڵĶ���!");

			alert.showAndWait();
		}
	}

	/**
	 * �����µĶ���
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
		} else {// �����ȷ��ɾ����ӵĶ���
			observable.remove(tempMember);
		}
	}


	/**
	 * �༭ָ������
	 */
	@FXML
	private void editMember() {
		Member member = tableM.getSelectionModel().getSelectedItem();// ��ȡѡ��Ķ���
		if (member != null) {
			boolean okClicked = main.showMemberEditDialog(member, controllerList);
			if (okClicked) {
				tableM.refresh();
				showMember(member);// �༭���չʾ���û�����ҳ��
				// writeMember();
			}

		} else {
			// û��ѡ�����
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

	// Ա��*******************************************************
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
	private void showStaff(Staff staff) {// ���ظ�����ϸ��Ϣ
		if (staff != null) {
			nameeS.setText(staff.getName());
			phoneeS.setText(staff.getPhone());
			birthdayS.setText(staff.getBirthday());
			genderS.setText(staff.getGender());
			idCardS.setText(staff.getIDCard());
			typeS.setText(staff.getType());
			memberS.setText(staff.getAllName());

			InputStream is = null;// ֱ����new Image(String dir)����ʾ�Ҳ�����Դ
			try {
				File testF = new File("picture/" + staff.getPhone() + ".jpg");
				if (testF.exists()) {// �ж��ļ��Ƿ����Ȼ���ټ���ͷ��
					is = new FileInputStream("picture/" + staff.getPhone() + ".jpg");
				} else {
					is = new FileInputStream("picture/0.jpg");
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (is != null) {
					pictureS.setPreserveRatio(true);// ���ֱ���
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
			nameeS.setText("��");
			phoneeS.setText("��");
			birthdayS.setText("��");
			genderS.setText("��");
			idCardS.setText("��");
			typeS.setText("��");
			memberS.setText("��");
			InputStream is = null;// ֱ����new Image(String dir)����ʾ�Ҳ�����Դ
			try {
				File testF = new File("picture/0.jpg");// ����Ĭ��ͼƬ
				if (testF.exists()) {// �ж��ļ��Ƿ����Ȼ���ټ���
					is = new FileInputStream("picture/0.jpg");
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (is != null) {
					pictureS.setPreserveRatio(true);// ���ֱ���
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

	private void loadDataStaff(ObservableList<Staff> observable, String dir) {// ��ָ���ļ��м��ض���
		nameS.setCellValueFactory(new PropertyValueFactory<>("name"));
		phoneS.setCellValueFactory(new PropertyValueFactory<>("phone"));

		observable.clear();

		File file = new File(dir);
		if (file.exists()) {
			listS.readList(dir);
			for (int i = 0; i < listS.getSize(); i++) {
				observable.add((Staff) listS.get(i));// ���ļ��ж�ȡ�Ķ�����ӵ�observable
			}
			tableS.setItems(observable);
		}

	}

	private void writeStaff() {// д���ļ�
		listS = new PersonList();// ��ն���
		for (Staff staff : observableS) {
			listS.addList(staff);
			listS.writeList("staff.dat");

		}
	}

	@FXML
	private void removeStaff() {
		int selectedIndex = tableS.getSelectionModel().getSelectedIndex();// �Ҳ�����������-1
		if (selectedIndex >= 0) {
			tableS.getItems().remove(selectedIndex);
			writeStaff();
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("����");
			alert.setHeaderText(null);
			alert.setContentText("��ɾ���˲����ڵĶ���!");

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
		Staff staff = tableS.getSelectionModel().getSelectedItem();// ��ȡѡ��Ķ���
		if (staff != null) {
			boolean okClicked = main.showStaffEditDialog(staff, controllerList);
			if (okClicked) {
				showStaff(staff);// �༭���չʾ���û�����ҳ��
				tableS.refresh();
				writeStaff();
			}

		} else {
			// û��ѡ�����
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Selection");
			alert.setHeaderText("No Person Selected");
			alert.setContentText("Please select a person in the table.");

			alert.showAndWait();
		}
	}

//����*************************************************
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

	private void loadDataHouse(ObservableList<House> observable, String dir) {// ��ָ���ļ��м��ض���
		locationH.setCellValueFactory(new PropertyValueFactory<>("id"));
		memberH.setCellValueFactory(new PropertyValueFactory<>("allName"));
		remarkH.setCellValueFactory(new PropertyValueFactory<>("remark"));
		stateH.setCellValueFactory(new PropertyValueFactory<>("state"));

		observable.clear();

		File file = new File(dir);
		if (file.exists()) {
			listH.readList(dir);
			for (int i = 0; i < listH.getSize(); i++) {
				observable.add((House) listH.get(i));// ���ļ��ж�ȡ�Ķ�����ӵ�observable
			}
			tableH.setItems(observable);
		}

	}

	private void writeHouse() {// д���ļ�
		listH = new ArchitectureList();// ��ն���
		for (House house : observableH) {
			listH.addList(house);
			listH.writeList("house.dat");
		}
	}

	@FXML
	private void editHouse(ActionEvent event) {
		House house = tableH.getSelectionModel().getSelectedItem();// ��ȡѡ��Ķ���
		if (house != null) {
			boolean okClicked = main.showHouseEditDialog(house, controllerList);
			if (okClicked) {
				tableH.refresh();
				writeHouse();
			}

		} else {
			// û��ѡ�����
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

//��ʩ***********************************************
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

	private void loadDataStadium(ObservableList<Stadium> observable, String dir) {// ��ָ���ļ��м��ض���
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
				observable.add((Stadium) listA.get(i));// ���ļ��ж�ȡ�Ķ�����ӵ�observable
			}
			tableA.setItems(observable);
		}

	}

	private void writeStadium() {// д���ļ�
		listA = new ArchitectureList();// ��ն���
		for (Stadium stadium : observableA) {
			listA.addList(stadium);
			listA.writeList("stadium.dat");
		}
	}

	@FXML
	private void editArchitecture(ActionEvent event) {
		Stadium stadium = tableA.getSelectionModel().getSelectedItem();// ��ȡѡ��Ķ���
		if (stadium != null) {
			boolean okClicked = main.showStadiumEditDialog(stadium, controllerList);
			if (okClicked) {
				tableA.refresh();
				writeStadium();
			}

		} else {
			// û��ѡ�����
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
	 * ��ù�����������
	 * @return
	 */
	public Lists getControllerList() {
		return controllerList;
	}

	/**
	 * ���ù�����������
	 * @param controllerList
	 */
	public void setControllerList(Lists controllerList) {
		this.controllerList = controllerList;
	}

	/**
	 * ���ó��ݳ�Ա��������־�ļ�
	 */
	@FXML
	private void restart() {// ���ó����ڳ�Ա
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String filedir = "stadium-daily/" + sdf.format(date) + ".txt";// ��־��Ϊ��ǰ����2022-5-26
		String temp = "";
		for (Stadium stadium : observableA) {

			temp += stadium.getName() + "(" + stadium.getBegin() + "-" + stadium.getEnd() + ")" + ":"
					+ stadium.getAllName();

			try {
				Writer writer = new OutputStreamWriter(new FileOutputStream(filedir, true), StandardCharsets.UTF_8);// ׷��д��
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

	// һ��********************************************************
	@FXML
	private PieChart pieChart1;

	@FXML
	private PieChart pieChart2;

	@FXML
	private PieChart pieChart3;

	@FXML
	private PieChart pieChart4;

	/**
	 * ���ڼ���һ��ͼ������
	 */
	ObservableList<Data> data1 = FXCollections.observableArrayList();
	/**
	 * ���ڼ���һ��ͼ������
	 */
	ObservableList<Data> data2 = FXCollections.observableArrayList();
	/**
	 * ���ڼ���һ��ͼ������
	 */
	ObservableList<Data> data3 = FXCollections.observableArrayList();
	/**
	 * ���ڼ���һ��ͼ������
	 */
	ObservableList<Data> data4 = FXCollections.observableArrayList();

	/**
	 * ����һ��ͼ����
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
		data1.addAll(new Data("δ�������û�", i), new Data("�ѹ������û�", j));

		i = 0;
		j = 0;
		for (Person person : listS.getPersonList()) {
			if (((Staff) person).getMember().size() == 0) {
				i++;
			}
		}
		j = listS.getSize() - i;
		data2.addAll(new Data("���е�Ա��", i), new Data("æµ��Ա��", j));

		i = 0;
		j = 0;
		for (Architecture architecture : listH.getList()) {
			if (((House) architecture).getMember().size() == 0) {
				i++;
			}
		}
		j = listH.getSize() - i;
		data3.addAll(new Data("���еķ���", i), new Data("�ѹ��ķ���", j));

		i = 0;
		j = 0;
		for (Architecture architecture : listA.getList()) {
			if (((Stadium) architecture).getMember().size() == 0) {
				i++;
			}
		}
		j = listA.getSize() - i;
		data4.addAll(new Data("���еĳ���", i), new Data("æµ�ĳ���", j));

		pieChart1.setTitle("ס�����");
		pieChart1.setData(data1);

		pieChart2.setTitle("Ա�����");
		pieChart2.setData(data2);

		pieChart3.setTitle("�������");
		pieChart3.setData(data3);

		pieChart4.setTitle("�������");
		pieChart4.setData(data4);
	}

	// ͷ���ϴ�************************************************

	@FXML
	private void dragOver(DragEvent event) {
		event.acceptTransferModes(TransferMode.ANY);// ����ģʽ
		// �鿴���̣��ж��Ƿ����ļ��������������·��
	}

	/**
	 * �����ק��ͷ���·�����������ѡ�����ĵ绰�����������ָ���ļ���
	 * @param event
	 */
	@FXML
	void dragDropped(DragEvent event) {
		Dragboard dragboard = event.getDragboard();
		String path = null;
		if (dragboard.hasFiles()) {
			path = dragboard.getFiles().get(0).getAbsolutePath();// ��þ���·��
		}

		int selectedIndex = tableM.getSelectionModel().getSelectedIndex();// �Ҳ�����������-1
		String str = null;
		if (selectedIndex >= 0) {
			str = "picture/" + tableM.getItems().get(selectedIndex).getPhone() + ".jpg";// ��ö���绰��
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("����");
			alert.setHeaderText(null);
			alert.setContentText("��ѡ�����!");

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

	// ��ͨ***********************************************
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
		String filedir = "traffic-daily/" + sdf.format(date) + ".txt";// ��־��Ϊ��ǰ����2022-5-26
		String temp = "";
		for (Traffic traffic : observableT) {

			temp += traffic.getName() + "(" + traffic.getBegin() + "-" + traffic.getEnd() + ")" + ":"
					+ traffic.getAllName();

			try {
				Writer writer = new OutputStreamWriter(new FileOutputStream(filedir, true), StandardCharsets.UTF_8);// ׷��д��
				writer.write(temp+"\r\n");
				writer.flush();
				
				traffic.setAllName("");//д����־�����
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
	 * ��ȡjson���ͱ���İ೵�����ļ�
	 * @param dir
	 * ��ȡ·��
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
					Traffic e=gson.fromJson(data, Traffic.class);//������json��ʽ�ַ���ת��ΪTraffic����
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
	 * ��json��ʽ�洢�೵����
	 * @param dir
	 * �洢·��
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
						bw.write(gson.toJson(traffic));//д���ļ�
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
	
	private void loadDataTraffic(String dir) {// ��ָ���ļ��м��ض���
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
	
	private void showTraffic(Traffic traffic) {// ���ظ�����ϸ��Ϣ
		if (traffic != null) {
			allnameT.setText(traffic.getAllName());
		} else {
			allnameT.setText("��");

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
		String regEx = ".*"+searchFieldS.getText()+"+?.*";//�ж��Ƿ�����������
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
