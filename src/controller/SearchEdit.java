package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.*;
/**
 * ���������û������ݣ�Ա���������Ӧ��ϵ
 * @author YQQ
 *
 */
public class SearchEdit {

	@FXML
	private Button addButton;

	@FXML
	private ComboBox<String> choiceObj;

	@FXML
	private TextField getSearchObj;

	@FXML
	private Button removeButton;

	@FXML
	private Button search;

	@FXML
	private Label stateSearch;

	private PersonList listS = new PersonList();
	private PersonList listM = new PersonList();
	private ArchitectureList listH = new ArchitectureList();
	private ArchitectureList listA = new ArchitectureList();
	private Stage dialogStage;
	private Object obj = null;// ��ȡ��ǰ����
	private int j = -1;// �������������
	private String name2 = "";// �����ж�ִ��������ӻ�ɾ������
	private String searchName = "";

	ObservableList<String> options = FXCollections.observableArrayList("ס��", "Ա��", "����", "����");

	/**
	 * ����ҳ���ʼ��
	 */
	public void init() {
		choiceObj.setItems(options);
		listS.readList("staff.dat");
		listM.readList("person.dat");
		listH.readList("house.dat");
		listA.readList("stadium.dat");
	}

	/**
	 * ����ҳ���ʼ��
	 */
	public void initP() {// ΪPersonalҳ�涨�Ƶķ���
		choiceObj.getItems().add("ס��");
		listM.readList("person.dat");
	}

	/**
	 * ����ҳ��д���ļ�
	 */
	public void writeP() {
		listM.writeList("person.dat");
	}

	/**
	 * ��Ӷ�����ϵ
	 * @param event
	 */
	@FXML
	void addObj(ActionEvent event) {
		int i = 0;
		if (obj instanceof Member && name2.equals("ס��")) {
			addMM();
			i++;
		} else if (obj instanceof Stadium && name2.equals("ס��")) {
			addAM();
			i++;
		} else if (obj instanceof House && name2.equals("ס��")) {
			addHM();
			i++;
		} else if (obj instanceof Member && name2.equals("����")) {
			addMH();
			i++;
		} else if (obj instanceof Member && name2.equals("Ա��")) {
			addMS();
			i++;
		} else if (obj instanceof Staff && name2.equals("ס��")) {
			addSM();
			i++;
		}
		if (i == 1) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("�ɹ�!");

			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("ʧ��!");

			alert.showAndWait();
		}
	}

	/**
	 * ɾ��������ϵ
	 * @param event
	 */
	@FXML
	void removeObj(ActionEvent event) {
		int i = 0;
		if (obj instanceof Member && name2.equals("ס��")) {
			removeMM();
			i++;
		} else if (obj instanceof Stadium && name2.equals("ס��")) {
			removeAM();
			i++;
		} else if (obj instanceof House && name2.equals("ס��")) {
			removeHM();
			i++;
		} else if (obj instanceof Member && name2.equals("����")) {
			removeMH();
			i++;
		} else if (obj instanceof Member && name2.equals("Ա��")) {
			removeMS();
			i++;
		} else if (obj instanceof Staff && name2.equals("ס��")) {
			removeSM();
			i++;
		}
		if (i == 1) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("�ɹ�!");

			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("ʧ��!");

			alert.showAndWait();
		}
	}

	/**
	 * ��������
	 * @param event
	 */
	@FXML
	void searchObj(ActionEvent event) {
		name2 = choiceObj.getValue();
		setSearchName(getSearchObj.getText());
		if (name2 == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("��ѡ����������!");
			alert.setContentText("errorMessage");

			alert.showAndWait();
		}
		if (searchName == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("��������������!");
			alert.setContentText("errorMessage");

			alert.showAndWait();
		}
		if (name2.equals("ס��")) {
			int x = listM.search(searchName, "name");// ��ȷ���û�������������������������
			int y = listM.search(searchName, "phone");

			if (x >= 0) {
				setJ(x);
				stateSearch.setText("�����ɹ�");
			}
			if (y >= 0) {
				setJ(y);
				stateSearch.setText("�����ɹ�");
			}
		} else if (name2.equals("Ա��")) {
			setJ(listS.search(searchName, "name"));
			stateSearch.setText("�����ɹ�");
		} else if (name2.equals("����")) {
			setJ(listH.search(searchName, "ID"));
			stateSearch.setText("�����ɹ�");
		} else {
			stateSearch.setText("����ʧ��");
		}
	}

	// ���º�������MS����M��Ӧ����Ϊi��S����Ϊj

	private void addMS() {// �û���Ա��
		listM.readList("person.dat");
		listS.readList("staff.dat");

		if (obj != null && j >= 0) {
			((Staff) listS.get(j)).setMember(((Member) obj));
			((Member) obj).setStaff((Staff) listS.get(j));
			listS.writeList("staff.dat");

		}

	}

	private void addSM() {// �û���Ա��
		listM.readList("person.dat");
		listS.readList("staff.dat");

		if (obj != null && j >= 0) {
			// ((Staff) obj).getMember().add((Member) listM.get(j));
			((Staff) obj).setMember((Member) listM.get(j));
			((Member) listM.get(j)).setStaff((Staff) obj);

			listM.writeList("person.dat");
		}

	}

	private void addMM() {// �û����û�,��Ϊ��һ��list�ڵĽ������ö����obj�ҵ�Ҫ�༭�Ķ�����listM�������������໥���
		listM.readList("person.dat");

		if (obj != null && j >= 0) {
			int i;
			for (i = 0; i < listM.getSize(); i++) {
				if (((Person) obj).equals(listM.get(i))) {
					break;
				}
			}
			((Member) listM.get(i)).addKinsfolks((Member) listM.get(j));
			// ((Member)obj).addKinsfolks((Member)listM.get(j));��Ϊ��ΪMember���Ժ�д���ļ��Ĳ��������ǰһ��д���ļ��Ĳ���
			((Member) listM.get(j)).addKinsfolks((Member) obj);
			listM.writeList("person.dat");
		}

	}

	private void addMH() {// �û��ͷ���
		listM.readList("person.dat");
		listH.readList("house.dat");

		if (obj != null && j >= 0) {
			((Member) obj).setHouse((House) listH.get(j));
			((House) listH.get(j)).getMember().add((Member) obj);

			listH.writeList("house.dat");
		}
	}

	private void addHM() {// �û��ͷ���
		listM.readList("person.dat");
		listH.readList("house.dat");

		if (obj != null && j >= 0) {
			((Member) listM.get(j)).setHouse((House) obj);
			((House) obj).getMember().add((Member) listM.get(j));
			listM.writeList("person.dat");

		}
	}

	public void addAM() {
		listM.readList("person.dat");
		listA.readList("stadium.dat");

		if (obj != null && j >= 0) {
			((Stadium) obj).getMember().add((Member) listM.get(j));
			listM.writeList("person.dat");

		}
	}

	/*
	 �����ɾ���������ˣ����ˣ� list��remove(Object obj)����equals�Ƚ���������ĵ�ַ ���˺þ�ԭ�������ԭ����ɾ��ʧ��
	 �������:���±�ɾ������дPerson�е�equals������idCard���ʱ���ж������������
	 */
	public void removeMS() {// �û���Ա��
		listM.readList("person.dat");
		listS.readList("staff.dat");

		if (obj != null && j >= 0) {
			((Staff) listS.get(j)).removeMember((Member) obj);
			((Member) obj).setStaff(new Staff());
			listS.writeList("staff.dat");

		}

	}

	private void removeSM() {// �û���Ա��
		listM.readList("person.dat");
		listS.readList("staff.dat");

		if (obj != null && j >= 0) {
			((Staff) obj).removeMember((Member) listM.get(j));
			((Member) listM.get(j)).setStaff(new Staff());

			listM.writeList("person.dat");
		}

	}

	private void removeMM() {// �û����û�
		listM.readList("person.dat");

		if (obj != null && j >= 0) {
			int i;
			for (i = 0; i < listM.getSize(); i++) {
				if (((Person) obj).equals(listM.get(i))) {
					break;
				}
			}
			((Member) listM.get(i)).removeKinsfolks((Member) listM.get(j));
			// ((Member)obj).removeKinsfolks((Member)listM.get(j));��Ϊ��ΪMember���Ժ�д���ļ��Ĳ��������ǰһ��д���ļ��Ĳ���
			((Member) listM.get(j)).removeKinsfolks((Member) obj);
			listM.writeList("person.dat");
		}

	}

	private void removeMH() {// �û��ͷ���
		listM.readList("person.dat");
		listH.readList("house.dat");

		if (obj != null && j >= 0) {
			((Member) obj).setHouse(new House());
			((House) listH.get(j)).remove((Member) obj);

			listH.writeList("house.dat");
		}
	}

	private void removeHM() {// �û��ͷ���
		listM.readList("person.dat");
		listH.readList("house.dat");

		if (obj != null && j >= 0) {
			((Member) listM.get(j)).setHouse(new House());
			((House) obj).remove((Member) listM.get(j));
			listM.writeList("person.dat");

		}
	}

	private void removeAM() {
		listM.readList("person.dat");
		listA.readList("stadium.dat");

		if (obj != null && j >= 0) {
			((Stadium) obj).remove((Member) listM.get(j));
			listM.writeList("person.dat");

		}
	}

	/**
	 * ��ȡĿ���������
	 * @return
	 */
	public int getJ() {
		return j;
	}

	/**
	 * ��Ŀ���������
	 * @param j
	 */
	public void setJ(int j) {
		this.j = j;
	}

	/**
	 * �������ģʽ����
	 * @return
	 */
	public String getName2() {
		return name2;
	}
	/**
	 * ��������ģʽ����
	 * @param name2
	 */
	public void setNameB(String name2) {
		this.name2 = name2;
	}

	/**
	 * ����stage
	 * @return
	 */
	public Stage getDialogStage() {
		return dialogStage;
	}

	/**
	 * ���stage
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * ��ȡ��ǰ����
	 * @return
	 */
	public Object getObj() {
		return obj;
	}

	/**
	 * ���õ�ǰ����
	 * @param obj
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}

	/**
	 * ������������
	 * @param searchName
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	/**
	 * �����������
	 * @return
	 */
	public String getSearchName() {
		return this.searchName;
	}

}
