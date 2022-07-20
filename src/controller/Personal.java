package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;
import model.ArchitectureList;
import model.Member;
import model.Person;
import model.PersonList;
import model.Stadium;
import model.Traffic;
/**
 * 用户个人界面，支持基础信息修改，申请场馆和班车
 * @author YQQ
 *
 */

public class Personal {

	private Main main = new Main();

	// 住户界面*********************************************
	private ObservableList<Member> observable = FXCollections.observableArrayList();
	private PersonList listM = new PersonList();
	private Stage dialogStage;
	private int i = -1;

	@FXML
	private Label ageM;

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
	private Button editM;

	@FXML
	private ImageView pictureM;

	/**
	 * 设置stage
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * 设置用户索引
	 * @param i
	 * 用户信息索引值
	 */
	public void setI(int i) {
		this.i = i;
	}

	/**
	 * 初始化加载数据
	 */
	public void init() {// 用于初始化
		loadDataPerson(observable, "person.dat");
		loadDataStadium(observableA, "stadium.dat");
		loadDataTraffic("traffic.dat");
		showMember(observable.get(i));
	}

	/**
	 * 加载信息展示界面
	 * @param member
	 */
	@FXML
	private void showMember(Member member) {// 加载个人详细信息
		if (member != null) {
			nameeM.setText(member.getName());
			phoneeM.setText(member.getPhone());
			ageM.setText(member.getAge().toString());
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
			ageM.setText("无");
			genderM.setText("无");
			idCardM.setText("无");
			staffM.setText("无");
			houseM.setText("无");
			typeM.setText("无");
			remarkM.setText("无");
			kinsfolkM.setText("无");
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
	 * 加载文件数据
	 * @param observable
	 * @param dir
	 */
	private void loadDataPerson(ObservableList<Member> observable, String dir) {// 从指定文件中加载对象

		File file = new File(dir);
		if (file.exists()) {
			listM.readList(dir);
			for (int i = 0; i < listM.getSize(); i++) {
				observable.add((Member) listM.get(i));// 将文件中读取的对象添加到observable
			}
		}

	}

	/**
	 * 写入文件数据
	 */
	private void writeMember() {// 写入文件
		listM = new PersonList();// 清空对象
		for (Member member : observable) {
			listM.addList(member);
			listM.writeList("person.dat");

		}
	}

	/**
	 * 编辑用户
	 */
	@FXML
	private void editMember() {
		Member member = observable.get(i);
		if (member != null) {
			boolean okClicked = main.showPersonalEditDialog(member);
			if (okClicked) {
				showMember(member);// 编辑完后展示该用户个人页面
				writeMember();
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

	// 头像上传************************************************

	/**
	 * 设置托盘
	 * @param event
	 */
	@FXML
	void dragOver(DragEvent event) {
		event.acceptTransferModes(TransferMode.ANY);// 传输模式
		// 查看托盘，判断是否有文件，有则获得其绝对路径
	}

	/**
	 * 设置头像
	 * 获得图片路径并重命名后复制到指定文件夹
	 * @param event
	 */
	@FXML
	void dragDropped(DragEvent event) {
		Dragboard dragboard = event.getDragboard();
		String path = null;
		if (dragboard.hasFiles()) {
			path = dragboard.getFiles().get(0).getAbsolutePath();// 获得绝对路径
		}

		String str = null;
		if (i >= 0) {
			str = "picture/" + observable.get(i).getPhone() + ".jpg";// 获得对象电话名
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("错误");
			alert.setHeaderText(null);
			alert.setContentText("找不到对象!");

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

	// 设施***********************************************
	private ObservableList<Stadium> observableA = FXCollections.observableArrayList();
	private ArchitectureList listA = new ArchitectureList();

	@FXML
	private Button postA;

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

	/**
	 * 用户申请场馆，人满失败，有空位成功
	 * @param event
	 */
	@FXML
	void postArchitecture(ActionEvent event) {// 当前用户申请
		Stadium stadium = tableA.getSelectionModel().getSelectedItem();// 获取选择的对象
		if (stadium != null) {
			if (stadium.getMember().size() >= stadium.getSize()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("错误");
				alert.setContentText("该设施已满");

				alert.showAndWait();
			} else {
				stadium.getMember().add(observable.get(i));
				stadium.setAllName();
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
	//交通********************************************************
	ObservableList<Traffic> observableT = FXCollections.observableArrayList();

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

	/**
	 * 读取文件
	 * @param dir
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
	 * 写入文件
	 * @param dir
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
	
	/**
	 * 加载数据
	 * @param dir
	 */
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
	
	/**
	 * 申请班车
	 * @param event
	 */
	@FXML
	void postTraffic(ActionEvent event) {// 当前用户申请
		Traffic traffic= tableT.getSelectionModel().getSelectedItem();// 获取选择的对象
		if (traffic != null) {
				traffic.setAllName(traffic.getAllName()+observable.get(i).getName()+" ");
				tableT.refresh();
				writeT("traffic.dat");
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Invalid Fields");
				alert.setHeaderText("成功!");
				alert.showAndWait();
			

		} else {
			// 没有选择对象
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Selection");
			alert.setHeaderText("No Traffic Selected");
			alert.setContentText("Please select a traffic in the table.");

			alert.showAndWait();
		}
	}

}
