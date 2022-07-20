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
 * 房屋编辑界面的控制器
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
	 * 设置stage
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * 设置输入框内容以便编辑
	 * @param house
	 */
	public void setHouse(House house) {
		this.house = house;
		idField.setText(house.getId());
		remarkField.setText(house.getRemark());
		memberLabel.setText(house.getAllName());

	}

	/**
	 * 判断是否点击确定
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	// 点击确认调用,设置用户修改的内容
	@FXML
	private void handleOk() {
		if (isInputValid()) {

			house.setID(idField.getText());
			house.setRemark(remarkField.getText());

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
	 * 获得管理页面控制器
	 * @return
	 */
	public Lists getControllerList() {
		return controllerList;
	}

	/**
	 * 设置管理页面控制器
	 * @param controllerList
	 */
	public void setControllerList(Lists controllerList) {
		this.controllerList = controllerList;
	}

}
