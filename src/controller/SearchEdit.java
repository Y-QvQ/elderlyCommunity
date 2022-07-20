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
 * 用于搜索用户，房屋，员工并添加相应关系
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
	private Object obj = null;// 获取当前对象
	private int j = -1;// 搜索对象的索引
	private String name2 = "";// 用于判断执行那种添加或删除操作
	private String searchName = "";

	ObservableList<String> options = FXCollections.observableArrayList("住户", "员工", "房屋", "场馆");

	/**
	 * 管理页面初始化
	 */
	public void init() {
		choiceObj.setItems(options);
		listS.readList("staff.dat");
		listM.readList("person.dat");
		listH.readList("house.dat");
		listA.readList("stadium.dat");
	}

	/**
	 * 个人页面初始化
	 */
	public void initP() {// 为Personal页面定制的方法
		choiceObj.getItems().add("住户");
		listM.readList("person.dat");
	}

	/**
	 * 个人页面写入文件
	 */
	public void writeP() {
		listM.writeList("person.dat");
	}

	/**
	 * 添加对象间关系
	 * @param event
	 */
	@FXML
	void addObj(ActionEvent event) {
		int i = 0;
		if (obj instanceof Member && name2.equals("住户")) {
			addMM();
			i++;
		} else if (obj instanceof Stadium && name2.equals("住户")) {
			addAM();
			i++;
		} else if (obj instanceof House && name2.equals("住户")) {
			addHM();
			i++;
		} else if (obj instanceof Member && name2.equals("房屋")) {
			addMH();
			i++;
		} else if (obj instanceof Member && name2.equals("员工")) {
			addMS();
			i++;
		} else if (obj instanceof Staff && name2.equals("住户")) {
			addSM();
			i++;
		}
		if (i == 1) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("成功!");

			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("失败!");

			alert.showAndWait();
		}
	}

	/**
	 * 删除对象间关系
	 * @param event
	 */
	@FXML
	void removeObj(ActionEvent event) {
		int i = 0;
		if (obj instanceof Member && name2.equals("住户")) {
			removeMM();
			i++;
		} else if (obj instanceof Stadium && name2.equals("住户")) {
			removeAM();
			i++;
		} else if (obj instanceof House && name2.equals("住户")) {
			removeHM();
			i++;
		} else if (obj instanceof Member && name2.equals("房屋")) {
			removeMH();
			i++;
		} else if (obj instanceof Member && name2.equals("员工")) {
			removeMS();
			i++;
		} else if (obj instanceof Staff && name2.equals("住户")) {
			removeSM();
			i++;
		}
		if (i == 1) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("成功!");

			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("失败!");

			alert.showAndWait();
		}
	}

	/**
	 * 搜索对象
	 * @param event
	 */
	@FXML
	void searchObj(ActionEvent event) {
		name2 = choiceObj.getValue();
		setSearchName(getSearchObj.getText());
		if (name2 == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("请选择搜索对象!");
			alert.setContentText("errorMessage");

			alert.showAndWait();
		}
		if (searchName == null) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("请输入搜索内容!");
			alert.setContentText("errorMessage");

			alert.showAndWait();
		}
		if (name2.equals("住户")) {
			int x = listM.search(searchName, "name");// 不确定用户输入的情况下两种搜索都进行
			int y = listM.search(searchName, "phone");

			if (x >= 0) {
				setJ(x);
				stateSearch.setText("搜索成功");
			}
			if (y >= 0) {
				setJ(y);
				stateSearch.setText("搜索成功");
			}
		} else if (name2.equals("员工")) {
			setJ(listS.search(searchName, "name"));
			stateSearch.setText("搜索成功");
		} else if (name2.equals("房屋")) {
			setJ(listH.search(searchName, "ID"));
			stateSearch.setText("搜索成功");
		} else {
			stateSearch.setText("搜索失败");
		}
	}

	// 以下函数起名MS代表M对应索引为i，S索引为j

	private void addMS() {// 用户和员工
		listM.readList("person.dat");
		listS.readList("staff.dat");

		if (obj != null && j >= 0) {
			((Staff) listS.get(j)).setMember(((Member) obj));
			((Member) obj).setStaff((Staff) listS.get(j));
			listS.writeList("staff.dat");

		}

	}

	private void addSM() {// 用户和员工
		listM.readList("person.dat");
		listS.readList("staff.dat");

		if (obj != null && j >= 0) {
			// ((Staff) obj).getMember().add((Member) listM.get(j));
			((Staff) obj).setMember((Member) listM.get(j));
			((Member) listM.get(j)).setStaff((Staff) obj);

			listM.writeList("person.dat");
		}

	}

	private void addMM() {// 用户和用户,因为是一个list内的交互，用读入的obj找到要编辑的对象，在listM中让两个对象相互添加
		listM.readList("person.dat");

		if (obj != null && j >= 0) {
			int i;
			for (i = 0; i < listM.getSize(); i++) {
				if (((Person) obj).equals(listM.get(i))) {
					break;
				}
			}
			((Member) listM.get(i)).addKinsfolks((Member) listM.get(j));
			// ((Member)obj).addKinsfolks((Member)listM.get(j));因为都为Member所以后写入文件的操作会抵消前一个写入文件的操作
			((Member) listM.get(j)).addKinsfolks((Member) obj);
			listM.writeList("person.dat");
		}

	}

	private void addMH() {// 用户和房子
		listM.readList("person.dat");
		listH.readList("house.dat");

		if (obj != null && j >= 0) {
			((Member) obj).setHouse((House) listH.get(j));
			((House) listH.get(j)).getMember().add((Member) obj);

			listH.writeList("house.dat");
		}
	}

	private void addHM() {// 用户和房子
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
	 后面的删除坑死我了（哭了） list的remove(Object obj)是用equals比较两个对象的地址 找了好久原来是这个原因导致删除失败
	 解决方法:用下标删除或重写Person中的equals方法当idCard相等时则判断两个对象相等
	 */
	public void removeMS() {// 用户和员工
		listM.readList("person.dat");
		listS.readList("staff.dat");

		if (obj != null && j >= 0) {
			((Staff) listS.get(j)).removeMember((Member) obj);
			((Member) obj).setStaff(new Staff());
			listS.writeList("staff.dat");

		}

	}

	private void removeSM() {// 用户和员工
		listM.readList("person.dat");
		listS.readList("staff.dat");

		if (obj != null && j >= 0) {
			((Staff) obj).removeMember((Member) listM.get(j));
			((Member) listM.get(j)).setStaff(new Staff());

			listM.writeList("person.dat");
		}

	}

	private void removeMM() {// 用户和用户
		listM.readList("person.dat");

		if (obj != null && j >= 0) {
			int i;
			for (i = 0; i < listM.getSize(); i++) {
				if (((Person) obj).equals(listM.get(i))) {
					break;
				}
			}
			((Member) listM.get(i)).removeKinsfolks((Member) listM.get(j));
			// ((Member)obj).removeKinsfolks((Member)listM.get(j));因为都为Member所以后写入文件的操作会抵消前一个写入文件的操作
			((Member) listM.get(j)).removeKinsfolks((Member) obj);
			listM.writeList("person.dat");
		}

	}

	private void removeMH() {// 用户和房子
		listM.readList("person.dat");
		listH.readList("house.dat");

		if (obj != null && j >= 0) {
			((Member) obj).setHouse(new House());
			((House) listH.get(j)).remove((Member) obj);

			listH.writeList("house.dat");
		}
	}

	private void removeHM() {// 用户和房子
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
	 * 获取目标对象索引
	 * @return
	 */
	public int getJ() {
		return j;
	}

	/**
	 * 设目标对象索引
	 * @param j
	 */
	public void setJ(int j) {
		this.j = j;
	}

	/**
	 * 获得搜索模式名称
	 * @return
	 */
	public String getName2() {
		return name2;
	}
	/**
	 * 设置搜索模式名称
	 * @param name2
	 */
	public void setNameB(String name2) {
		this.name2 = name2;
	}

	/**
	 * 设置stage
	 * @return
	 */
	public Stage getDialogStage() {
		return dialogStage;
	}

	/**
	 * 获得stage
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * 获取当前对象
	 * @return
	 */
	public Object getObj() {
		return obj;
	}

	/**
	 * 设置当前对象
	 * @param obj
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}

	/**
	 * 设置搜索内容
	 * @param searchName
	 */
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	/**
	 * 获得搜索内容
	 * @return
	 */
	public String getSearchName() {
		return this.searchName;
	}

}
