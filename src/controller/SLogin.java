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
import model.Person;
import model.Staff;

public class SLogin {

	@FXML
	private TextField ageField;

	@FXML
	private Button cancle;

	@FXML
	private TextField idCardField;

	@FXML
	private Label memberLabel;

	@FXML
	private TextField nameField;

	@FXML
	private Button ok;

	@FXML
	private TextField phoneField;

	@FXML
	private TextField typeField;

	private Stage dialogStage;
	private Staff staff;
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
	 * @param staff
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;

		nameField.setText(staff.getName());
		phoneField.setText(staff.getPhone());
		ageField.setText(staff.getAge().toString());
		idCardField.setText(staff.getIDCard());
		typeField.setText(staff.getType());
		memberLabel.setText(staff.getAllName());

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

			staff.setName(nameField.getText());
			staff.setPhone(phoneField.getText());
			staff.setAge(Integer.valueOf(ageField.getText()));
			staff.setIDCard(idCardField.getText());
			staff.setType(typeField.getText());

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
	private void searchEditSM(ActionEvent event) {
		try {
			main.showSearch(staff, controllerList);
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
