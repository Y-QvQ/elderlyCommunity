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
	 * ����stage
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * ��������������Ա�༭
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
	 * �ж��Ƿ���ȷ��
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	// ���ȷ�ϵ���,�����û��޸ĵ�����
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

	// ���ȡ���ǹرս���
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	// �������
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
				Integer.parseInt(ageField.getText());// �������Ϊ����
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
		 * �����������У�飬��Ϊ����洢��ʱMD5ֵ���޸�ʱ�޷�չʾԭ�����롣�����ϴ���޸�����ʱ��Ҫ��������ԭ�����롣
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
	 * ��ù���ҳ��Ŀ�����
	 * @return
	 */
	public Lists getControllerList() {
		return controllerList;
	}

	/**
	 * ���ù���ҳ��Ŀ�����
	 * @param controllerList
	 */
	public void setControllerList(Lists controllerList) {
		this.controllerList = controllerList;
	}

}
