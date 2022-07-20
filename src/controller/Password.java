package controller;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.*;
/**
 * 密码登录界面控制器
 * @author YQQ
 *
 */
public class Password {

	@FXML
	private Button cancelClick;

	@FXML
	private TextField idField;

	@FXML
	private Button okClick;

	@FXML
	private TextField passwordField;

	@FXML
	private ImageView poster;

	private Stage dialogStage;
	private Main main = new Main();
	private boolean okClicked = false;
	private PersonList list = new PersonList();

	/**
	 * 设置stage
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * 判断是否点击确认
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * 判断是否为管理员账户，为管理员账户且密码正确则登录后台管理
	 * 不是管理员则从用户中寻找，并检查密码是否正确，正确则登录个人用户界面
	 * @param id
	 * 账户名
	 * @param password
	 * 密码
	 * @return
	 * 返回索引用于判断跳转那个界面
	 */
	private Integer checkPassword(String id, String password) {// 密码登录账户，找到返回index值，找不到返回-1s，若为管理员则返回-6。设置管理员账户密码，id:韩立

		String passwordMD5 = new String();
		if (password != null) {
			if (id.equals("韩立") && password.equals("lifeiyv")) {
				return -6;
			}
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());// 计算md5函数
				String hashedPwd = new BigInteger(1, md.digest()).toString(16);
				passwordMD5 = hashedPwd;// 转换为密码的MD5值
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			File file = new File("person.dat");
			if (file.exists()) {
				list.readList("person.dat");

			} else {
				return -3;// 用户数据丢失
			}

			int i = list.search(id, "name");// 不确定用户输入的情况下两种搜索都进行
			int j = list.search(id, "phone");
			if (i >= 0 || j >= 0) {// 若索引到，则返回对应值
				try {

					if (i >= 0 && ((Member) list.get(i)).getPassword().equals(passwordMD5)) {
						return i;
					} else if (j >= 0 && ((Member) list.get(j)).getPassword().equals(passwordMD5)) {
						return j;
					} else {
						return -2;// -2代表密码错误
					}
				} catch (Exception e) {

				}
			}
		}
		return -1;// -1代表找不到用户
	}

	/**
	 * 点击ok刷新信息，写入数据到文件中
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			int i = checkPassword(idField.getText(), passwordField.getText());

			if (i == -6) {
				try {
					main.showList();
					dialogStage.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (i == -1) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("错误");
				alert.setContentText("找不到用户");

				alert.showAndWait();
			} else if (i == -2) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("错误");
				alert.setContentText("密码错误");

				alert.showAndWait();
			} else if (i == -3) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("错误");
				alert.setContentText("用户数据丢失");

				alert.showAndWait();
			} else if (i >= 0) {// 如果获得用户索引，则根据索引登录个人界面
				try {
					dialogStage.close();
					main.showPersonal(i);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				okClicked = true;
			} else {

			}
		}
	}

	/**
	 * 点击取消关闭界面
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * 检查输入
	 * @return
	 */
	// 检查输入
	private boolean isInputValid() {
		String errorMessage = "";

		if (passwordField.getText() == null || passwordField.getText().length() == 0) {
			errorMessage += "No valid password!\n";
		}

		if (idField.getText() == null || idField.getText().length() == 0) {
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

}
