package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
/**
 * ��ʼ���棬��ѡ��Ա�������û���¼
 * @author YQQ
 *
 */
public class MorS {

	private Main main = new Main();

	@FXML
	private Button geisterS;

	@FXML
	private ImageView poster;// Ӧ�����½�ImageView���ڵײ�

	@FXML
	private Button registerM;

	/**
	 * ��ת���ڵ��û���¼
	 * @param event
	 */
	@FXML
	void toMember(ActionEvent event) {
		try {
			main.showPassword();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * ��ת���ڵ�Ա����¼
	 * @param event
	 */
	@FXML
	void toStaff(ActionEvent event) {
		try {
			main.showPassword();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
