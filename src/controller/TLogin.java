package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Traffic;

public class TLogin {

	@FXML
	private TextField beginField;

	@FXML
	private Button cancelClick;

	@FXML
	private TextField dailyField;

	@FXML
	private RadioButton downRB;

	@FXML
	private TextField endField;

	@FXML
	private TextField idField;

	@FXML
	private TextField nameField;

	@FXML
	private Button okClick;

	@FXML
	private TextField remarkField;

	@FXML
	private RadioButton upRB;
	
    @FXML
    private ToggleGroup type;


	private Stage dialogStage;
	private Traffic traffic;
	private boolean okClicked = false;
	private Main main = new Main();


	/**
	 * 设置stage
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * 设置输入框内容以便编辑
	 * @param traffic
	 */
	public void setTraffic(Traffic traffic) {
		this.traffic = traffic;

		nameField.setText(traffic.getName());
		remarkField.setText(traffic.getRemark());
		//beginField.setText(traffic.getBegin());
		dailyField.setText(traffic.getDail());
		endField.setText(traffic.getEnd());
		//idField.setText(traffic.getId());
	}

	/**
	 * 判断是否点击确认
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML
	private void handleOk() {
		if (isInputValid()) {

			traffic.setId(idField.getText());
			traffic.setName(nameField.getText());
			traffic.setAllName("");
			traffic.setBegin(beginField.getText());
			traffic.setEnd(endField.getText());
			traffic.setDail(dailyField.getText());
			traffic.setRemark(remarkField.getText());
			traffic.setType(((RadioButton) type.getSelectedToggle()).getText());

			okClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * 检查输入
	 * @return
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (nameField.getText() == null || nameField.getText().length() == 0) {
			errorMessage += "No valid name!\n";
		}

		if (idField.getText() == null || idField.getText().length() == 0) {
			errorMessage += "No valid id!\n";
		}

		if (beginField.getText() == null || beginField.getText().length() == 0) {
			errorMessage += "No valid begin!\n";
		}

		if (endField.getText() == null || endField.getText().length() == 0) {
			errorMessage += "No valid end!\n";
		}

		if (dailyField.getText() == null || dailyField.getText().length() == 0) {
			errorMessage += "No valid daily!\n";
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
