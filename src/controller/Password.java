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
 * �����¼���������
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
	 * ����stage
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * �ж��Ƿ���ȷ��
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * �ж��Ƿ�Ϊ����Ա�˻���Ϊ����Ա�˻���������ȷ���¼��̨����
	 * ���ǹ���Ա����û���Ѱ�ң�����������Ƿ���ȷ����ȷ���¼�����û�����
	 * @param id
	 * �˻���
	 * @param password
	 * ����
	 * @return
	 * �������������ж���ת�Ǹ�����
	 */
	private Integer checkPassword(String id, String password) {// �����¼�˻����ҵ�����indexֵ���Ҳ�������-1s����Ϊ����Ա�򷵻�-6�����ù���Ա�˻����룬id:����

		String passwordMD5 = new String();
		if (password != null) {
			if (id.equals("����") && password.equals("lifeiyv")) {
				return -6;
			}
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());// ����md5����
				String hashedPwd = new BigInteger(1, md.digest()).toString(16);
				passwordMD5 = hashedPwd;// ת��Ϊ�����MD5ֵ
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			File file = new File("person.dat");
			if (file.exists()) {
				list.readList("person.dat");

			} else {
				return -3;// �û����ݶ�ʧ
			}

			int i = list.search(id, "name");// ��ȷ���û�������������������������
			int j = list.search(id, "phone");
			if (i >= 0 || j >= 0) {// �����������򷵻ض�Ӧֵ
				try {

					if (i >= 0 && ((Member) list.get(i)).getPassword().equals(passwordMD5)) {
						return i;
					} else if (j >= 0 && ((Member) list.get(j)).getPassword().equals(passwordMD5)) {
						return j;
					} else {
						return -2;// -2�����������
					}
				} catch (Exception e) {

				}
			}
		}
		return -1;// -1�����Ҳ����û�
	}

	/**
	 * ���okˢ����Ϣ��д�����ݵ��ļ���
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
				alert.setTitle("����");
				alert.setContentText("�Ҳ����û�");

				alert.showAndWait();
			} else if (i == -2) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("����");
				alert.setContentText("�������");

				alert.showAndWait();
			} else if (i == -3) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("����");
				alert.setContentText("�û����ݶ�ʧ");

				alert.showAndWait();
			} else if (i >= 0) {// �������û������������������¼���˽���
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
	 * ���ȡ���رս���
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * �������
	 * @return
	 */
	// �������
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
