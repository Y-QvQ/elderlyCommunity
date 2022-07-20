package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Member;
import model.Person;
/**
 * 用户编辑界面控制器
 * @author YQQ
 *
 */
public class MLogin {

	@FXML
	private TextField ageField;

	@FXML
	private Button cancelClick;

	@FXML
	private Button houseButton;

	@FXML
	private Label houseLabel;

	@FXML
	private TextField idCardField;

	@FXML
	private Button kinsfolkButton;

	@FXML
	private Label kinsfolkLabel;

	@FXML
	private TextField nameField;

	@FXML
	private Button okClick;

	@FXML
	private TextField passwordField;

	@FXML
	private TextField phoneField;

	@FXML
	private TextField remarkField;

	@FXML
	private Button staffButton;

	@FXML
	private Label staffLabel;

	@FXML
	private TextField typeField;

	private Stage dialogStage;
	private Member member;
	private boolean okClicked = false;
	private Main main = new Main();
	private Lists controllerList;

	@FXML
	private void initialize() {
	}

	/**
	 * 设置stage
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * 设置输入框内容以便编辑
	 * @param member
	 */
	public void setMember(Member member) {
		this.member = member;

		nameField.setText(member.getName());
		phoneField.setText(member.getPhone());
		ageField.setText(member.getAge().toString());
		idCardField.setText(member.getIDCard());
		typeField.setText(member.getType());
		remarkField.setText(member.getRemark());
		kinsfolkLabel.setText(member.getAllName());
		houseLabel.setText(member.getHouse().getId());
		staffLabel.setText(member.getStaff().getName());
	}

	/**
	 * 判断是否点击确认
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	// 点击确认调用,设置用户修改的内容
	@FXML
	private void handleOk() {
		if (isInputValid()) {

			member.setName(nameField.getText());
			member.setPhone(phoneField.getText());
			member.setAge(Integer.valueOf(ageField.getText()));
			member.setIDCard(idCardField.getText());
			member.setType(typeField.getText());
			member.setRemark(remarkField.getText());
			if (passwordField.getText() != null && passwordField.getText().length() != 0) {// 输入框为空时保持密码不变
				member.setPasswork(passwordField.getText());
			}

			okClicked = true;
			dialogStage.close();
		}
	}

	// 点击取消是关闭界面
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	// 检查输入
	private boolean isInputValid() {
		String errorMessage = "";

		if (nameField.getText() == null || nameField.getText().length() == 0) {
			errorMessage += "No valid name!\n";
		}
		if (phoneField.getText() == null || phoneField.getText().length() == 0) {
			errorMessage += "No valid phone!\n";
		}
		if (ageField.getText() == null || ageField.getText().length() == 0) {
			errorMessage += "No valid age!\n";
		} else {
			try {
				Integer.parseInt(ageField.getText());// 年龄必须为数字
			} catch (NumberFormatException e) {
				errorMessage += "No valid age (must be an integer)!\n";
			}
		}

		if (idCardField.getText() == null || idCardField.getText().length() == 0) {
			errorMessage += "No valid idCard!\n";
		}
		/*
		 * if (passwordField.getText() == null || passwordField.getText().length() == 0)
		 * { errorMessage += "No valid password!\n"; }
		 * 不对密码进行校验，因为密码存储的时MD5值，修改时无法展示原本密码。若加上此项不修改密码时需要重新输入原本密码。
		 */
		if (typeField.getText() == null || typeField.getText().length() == 0) {
			errorMessage += "No valid type!\n";
		}
		if (remarkField.getText() == null || remarkField.getText().length() == 0) {
			errorMessage += "No valid remark!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText("errorMessage");

			alert.showAndWait();
			return false;
		}
	}

	@FXML
	private void searchEditMM(ActionEvent event) {
		try {
			main.showSearch(member, controllerList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void searchEditMH(ActionEvent event) {
		try {
			main.showSearch(member, controllerList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void searchEditMS(ActionEvent event) {
		try {
			main.showSearch(member, controllerList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获得管理页面的控制器
	 * @return
	 */
	public Lists getControllerList() {
		return controllerList;
	}

	/**
	 * 设置管理页面的控制器
	 * @param controllerList
	 */
	public void setControllerList(Lists controllerList) {
		this.controllerList = controllerList;
	}
}
