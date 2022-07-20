package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import model.Stadium;
/**
 * ���ݱ༭����Ŀ�����
 * @author YQQ
 *
 */
public class Alogin {

	@FXML
	private TextField beginField;

	@FXML
	private Button cancle;

	@FXML
	private TextField endField;

	@FXML
	private TextField idField;

	@FXML
	private Label memberLabel;

	@FXML
	private TextField nameField;

	@FXML
	private Button ok;

	@FXML
	private TextField sizeField;

	private Stage dialogStage;
	private Stadium stadium;
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
	 * @param stadium
	 */
	public void setStadium(Stadium stadium) {
		this.stadium = stadium;

		idField.setText(stadium.getId());
		nameField.setText(stadium.getName());
		beginField.setText(stadium.getBegin().toString());
		endField.setText(stadium.getEnd().toString());
		sizeField.setText(stadium.getSize().toString());
		memberLabel.setText(stadium.getAllName());

	}

	/**
	 * �ж��Ƿ���ȷ��
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * ���ȷ�ϵ��ã������û��޸ĵ�����
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {

			stadium.setBegin(Integer.parseInt(beginField.getText()));
			stadium.setEnd(Integer.parseInt(endField.getText()));
			stadium.setSize(Integer.parseInt(sizeField.getText()));
			stadium.setID(idField.getText());
			stadium.setName(nameField.getText());

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
		if (idField.getText() == null || idField.getText().length() == 0) {
			errorMessage += "No valid location!\n";
		}
		if (beginField.getText() == null || beginField.getText().length() == 0) {
			errorMessage += "No valid begin!\n";
		} else {
			try {
				Integer.parseInt(beginField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid begin (must be an integer)!\n";
			}
		}

		if (endField.getText() == null || endField.getText().length() == 0) {
			errorMessage += "No valid end!\n";
		} else {
			try {
				Integer.parseInt(endField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid end (must be an integer)!\n";
			}
		}
		if (sizeField.getText() == null || sizeField.getText().length() == 0) {
			errorMessage += "No valid size!\n";
		} else {
			try {
				Integer.parseInt(sizeField.getText());
			} catch (NumberFormatException e) {
				errorMessage += "No valid size (must be an integer)!\n";
			}
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

	@FXML
	private void searchEditAM(ActionEvent event) {
		try {
			main.showSearch(stadium, controllerList);
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
