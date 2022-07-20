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
 * �û��༭���������
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
		typeField.setText(member.getType());
		remarkField.setText(member.getRemark());
		kinsfolkLabel.setText(member.getAllName());
		houseLabel.setText(member.getHouse().getId());
		staffLabel.setText(member.getStaff().getName());
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

			member.setName(nameField.getText());
			member.setPhone(phoneField.getText());
			member.setAge(Integer.valueOf(ageField.getText()));
			member.setIDCard(idCardField.getText());
			member.setType(typeField.getText());
			member.setRemark(remarkField.getText());
			if (passwordField.getText() != null && passwordField.getText().length() != 0) {// �����Ϊ��ʱ�������벻��
				member.setPasswork(passwordField.getText());
			}

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
