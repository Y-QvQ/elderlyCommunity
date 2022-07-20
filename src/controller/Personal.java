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
 * �û����˽��棬֧�ֻ�����Ϣ�޸ģ����볡�ݺͰ೵
 * @author YQQ
 *
 */

public class Personal {

	private Main main = new Main();

	// ס������*********************************************
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
	 * ����stage
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * �����û�����
	 * @param i
	 * �û���Ϣ����ֵ
	 */
	public void setI(int i) {
		this.i = i;
	}

	/**
	 * ��ʼ����������
	 */
	public void init() {// ���ڳ�ʼ��
		loadDataPerson(observable, "person.dat");
		loadDataStadium(observableA, "stadium.dat");
		loadDataTraffic("traffic.dat");
		showMember(observable.get(i));
	}

	/**
	 * ������Ϣչʾ����
	 * @param member
	 */
	@FXML
	private void showMember(Member member) {// ���ظ�����ϸ��Ϣ
		if (member != null) {
			nameeM.setText(member.getName());
			phoneeM.setText(member.getPhone());
			ageM.setText(member.getAge().toString());
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
			ageM.setText("��");
			genderM.setText("��");
			idCardM.setText("��");
			staffM.setText("��");
			houseM.setText("��");
			typeM.setText("��");
			remarkM.setText("��");
			kinsfolkM.setText("��");
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
	 * �����ļ�����
	 * @param observable
	 * @param dir
	 */
	private void loadDataPerson(ObservableList<Member> observable, String dir) {// ��ָ���ļ��м��ض���

		File file = new File(dir);
		if (file.exists()) {
			listM.readList(dir);
			for (int i = 0; i < listM.getSize(); i++) {
				observable.add((Member) listM.get(i));// ���ļ��ж�ȡ�Ķ�����ӵ�observable
			}
		}

	}

	/**
	 * д���ļ�����
	 */
	private void writeMember() {// д���ļ�
		listM = new PersonList();// ��ն���
		for (Member member : observable) {
			listM.addList(member);
			listM.writeList("person.dat");

		}
	}

	/**
	 * �༭�û�
	 */
	@FXML
	private void editMember() {
		Member member = observable.get(i);
		if (member != null) {
			boolean okClicked = main.showPersonalEditDialog(member);
			if (okClicked) {
				showMember(member);// �༭���չʾ���û�����ҳ��
				writeMember();
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

	// ͷ���ϴ�************************************************

	/**
	 * ��������
	 * @param event
	 */
	@FXML
	void dragOver(DragEvent event) {
		event.acceptTransferModes(TransferMode.ANY);// ����ģʽ
		// �鿴���̣��ж��Ƿ����ļ��������������·��
	}

	/**
	 * ����ͷ��
	 * ���ͼƬ·�������������Ƶ�ָ���ļ���
	 * @param event
	 */
	@FXML
	void dragDropped(DragEvent event) {
		Dragboard dragboard = event.getDragboard();
		String path = null;
		if (dragboard.hasFiles()) {
			path = dragboard.getFiles().get(0).getAbsolutePath();// ��þ���·��
		}

		String str = null;
		if (i >= 0) {
			str = "picture/" + observable.get(i).getPhone() + ".jpg";// ��ö���绰��
		} else {
			// Nothing selected.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("����");
			alert.setHeaderText(null);
			alert.setContentText("�Ҳ�������!");

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

	// ��ʩ***********************************************
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

	/**
	 * �û����볡�ݣ�����ʧ�ܣ��п�λ�ɹ�
	 * @param event
	 */
	@FXML
	void postArchitecture(ActionEvent event) {// ��ǰ�û�����
		Stadium stadium = tableA.getSelectionModel().getSelectedItem();// ��ȡѡ��Ķ���
		if (stadium != null) {
			if (stadium.getMember().size() >= stadium.getSize()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("����");
				alert.setContentText("����ʩ����");

				alert.showAndWait();
			} else {
				stadium.getMember().add(observable.get(i));
				stadium.setAllName();
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
	//��ͨ********************************************************
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
	 * ��ȡ�ļ�
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
	 * д���ļ�
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
	
	/**
	 * ��������
	 * @param dir
	 */
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
	
	/**
	 * ����೵
	 * @param event
	 */
	@FXML
	void postTraffic(ActionEvent event) {// ��ǰ�û�����
		Traffic traffic= tableT.getSelectionModel().getSelectedItem();// ��ȡѡ��Ķ���
		if (traffic != null) {
				traffic.setAllName(traffic.getAllName()+observable.get(i).getName()+" ");
				tableT.refresh();
				writeT("traffic.dat");
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Invalid Fields");
				alert.setHeaderText("�ɹ�!");
				alert.showAndWait();
			

		} else {
			// û��ѡ�����
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("No Selection");
			alert.setHeaderText("No Traffic Selected");
			alert.setContentText("Please select a traffic in the table.");

			alert.showAndWait();
		}
	}

}
