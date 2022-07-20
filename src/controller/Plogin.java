package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Member;
import model.Person;

public class Plogin {

	@FXML
	private TextField ageField;

	@FXML
	private Button cancelClick;

	@FXML
	private Button houseButton;

	@FXML
	private TextField idCardField;

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

	private Stage dialogStage;
	private Member member;
	private boolean okClicked = false;
	private Main main = new Main();

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
	 * @param member
	 */
	public void setMember(Member member) {
		this.member = member;

		nameField.setText(member.getName());
		phoneField.setText(member.getPhone());
		ageField.setText(member.getAge().toString());
		idCardField.setText(member.getIDCard());
		remarkField.setText(member.getRemark());

	}

	/**
	 * �ж��Ƿ���ȷ��
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {

			member.setName(nameField.getText());
			member.setPhone(phoneField.getText());
			member.setAge(Integer.valueOf(ageField.getText()));
			member.setIDCard(idCardField.getText());
			member.setRemark(remarkField.getText());
			if (passwordField.getText() != null && passwordField.getText().length() != 0) {// �����Ϊ��ʱ�������벻��
				member.setPasswork(passwordField.getText());
			}

			okClicked = true;
			dialogStage.close();
		}
	}

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
		if (remarkField.getText() == null || remarkField.getText().length() == 0) {
			errorMessage += "No valid remark!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText("errorMessage");

			alert.showAndWait();
			return false;
		}
	}

}
