package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
/**
 * 起始界面，可选择员工或者用户登录
 * @author YQQ
 *
 */
public class MorS {

	private Main main = new Main();

	@FXML
	private Button geisterS;

	@FXML
	private ImageView poster;// 应在坐下将ImageView放于底部

	@FXML
	private Button registerM;

	/**
	 * 跳转窗口到用户登录
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
	 * 跳转窗口到员工登录
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
