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
import model.House;
/**
 * ���ݱ༭����Ŀ�����
 * @author YQQ
 *
 */
public class Hlogin {

	@FXML
	private Button cancle;

	@FXML
	private TextField idField;

	@FXML
	private Label memberLabel;

	@FXML
	private Button ok;

	@FXML
	private TextField remarkField;

	private Stage dialogStage;
	private House house;
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
	 * @param house
	 */
	public void setHouse(House house) {
		this.house = house;
		idField.setText(house.getId());
		remarkField.setText(house.getRemark());
		memberLabel.setText(house.getAllName());

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

			house.setID(idField.getText());
			house.setRemark(remarkField.getText());

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

		if (remarkField.getText() == null || remarkField.getText().length() == 0) {
			errorMessage += "No valid remark!\n";
		}
		if (idField.getText() == null || idField.getText().length() == 0) {
			errorMessage += "No valid location!\n";
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
	private void searchEditHM(ActionEvent event) {
		try {
			main.showSearch(house, controllerList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��ù���ҳ�������
	 * @return
	 */
	public Lists getControllerList() {
		return controllerList;
	}

	/**
	 * ���ù���ҳ�������
	 * @param controllerList
	 */
	public void setControllerList(Lists controllerList) {
		this.controllerList = controllerList;
	}

}
