package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.House;
import model.Member;
import model.Stadium;
import model.Staff;
import model.Traffic;
/**
 * 主函数，用于启动初始页面以及跳转页面
 */
public class Main extends Application {

	private Stage primaryStage;
	private AnchorPane pane;

	@Override
	public void start(Stage primaryStage) throws IOException {
		this.primaryStage = primaryStage;
		showMain();
	}

	/**
	 * 启动初始窗口
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	// 开始界面**************************************************
	/**
	 * 该方法用于加载开始界面
	 * @throws IOException
	 */
	public void showMain() throws IOException {
		pane = FXMLLoader.load(getClass().getResource("/view/MorS.fxml"));
		primaryStage.setScene(new Scene(pane));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	// 登录****************************************************
	/**
	 * 该方法用于加载密码界面
	 * @throws IOException
	 */
	public void showPassword() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/Password.fxml"));// 加载view包下的fxml文件
		AnchorPane pane = (AnchorPane) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("长者社区-登录");
		dialogStage.setResizable(false);
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(pane);
		dialogStage.setScene(scene);

		Password controller = loader.getController();
		controller.setDialogStage(dialogStage);

		dialogStage.showAndWait();

	}

	// 用户*********************************************************
	/**
	 * 该方法用于加载个人页面
	 * @param i
	 * 判断登录的账号身份
	 * i为ArrayList中的索引值
	 * @throws IOException
	 */
	public void showPersonal(int i) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/Personal.fxml"));
		AnchorPane pane = (AnchorPane) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("长者社区-个人页面");
		dialogStage.setResizable(false);
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(pane);
		dialogStage.setScene(scene);

		Personal controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setI(i);
		controller.init();

		dialogStage.show();

	}
	/**
	 * 该方法用于加载用户个人信息修改界面
	 * @param member
	 * 要修改的对象
	 * @return
	 * 判断修改是否成功
	 */
	public boolean showPersonalEditDialog(Member member) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Plogin.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("长者社区-住户信息修改");
			dialogStage.setResizable(false);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			Plogin controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMember(member);

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 列表****************************************************
	/**
	 * 该方法用于加载管理界面
	 * @throws IOException
	 */
	public void showList() throws IOException {
		// pane = FXMLLoader.load(getClass().getResource("/view/List.fxml"));

		FXMLLoader loaderList = new FXMLLoader(getClass().getResource("/view/List.fxml"));
		pane = loaderList.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("长者社区-后台管理");
		dialogStage.setResizable(false);
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(pane);
		dialogStage.setScene(scene);

		Lists controllerList = loaderList.getController();
		controllerList.setDialogStage(dialogStage);
		controllerList.setControllerList(controllerList);

		dialogStage.show();

	}
	/**
	 * 该方法用于加载用户编辑界面
	 * @param member
	 * 要编辑的用户
	 * @param controllerList
	 * 管理页面的控制器
	 * @return
	 */
	public boolean showMemberEditDialog(Member member, Lists controllerList) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/MLogin.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("长者社区-住户信息修改");
			dialogStage.setResizable(false);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			MLogin controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMember(member);
			controller.setControllerList(controllerList);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();
			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 该方法用于加载员工编辑界面
	 * @param staff
	 * 要修改的员工
	 * @param controllerList
	 * 管理页面的控制器
	 * @return
	 * 判断修改是否成功
	 */
	public boolean showStaffEditDialog(Staff staff, Lists controllerList) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/SLogin.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("长者社区-员工信息修改");
			dialogStage.setResizable(false);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			SLogin controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setStaff(staff);
			controller.setControllerList(controllerList);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 用于加载场馆编辑页面
	 * @param stadium
	 * 要编辑的场馆
	 * @param controllerList
	 * 管理页面控制器
	 * @return
	 * 判断修改是否成功
	 */
	public boolean showStadiumEditDialog(Stadium stadium, Lists controllerList) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Alogin.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("长者社区-场馆信息修改");
			dialogStage.setResizable(false);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			Alogin controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setStadium(stadium);
			controller.setControllerList(controllerList);

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 该方法用于加载房屋编辑页面
	 * @param house
	 * 要编辑的房屋
	 * @param controllerList
	 * 管理页面的控制器
	 * @return
	 * 判断修改是否成功
	 */
	public boolean showHouseEditDialog(House house, Lists controllerList) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Hlogin.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("长者社区-住房信息修改");
			dialogStage.setResizable(false);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			Hlogin controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setHouse(house);
			controller.setControllerList(controllerList);

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 该方法用于加载班车编辑页面
	 * @param traffic
	 * 要编辑的对象
	 * @return
	 * 判断修改是否成功
	 */
	public boolean showTrafficEditDialog(Traffic traffic) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/TLogin.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("长者社区-交通");
			dialogStage.setResizable(false);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			TLogin controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setTraffic(traffic);

			dialogStage.showAndWait();

			return controller.isOkClicked();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	// 搜索*********************************************
	/**
	 * 该方法用于加载搜索页面
	 * @param obj
	 * 要修改的对象
	 * @param controllerList
	 * 管理页面控制器
	 * @throws IOException
	 * 
	 */
	public void showSearch(Object obj, Lists controllerList) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/searchEdit.fxml"));
		AnchorPane pane = (AnchorPane) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("长者社区-搜索");
		dialogStage.setResizable(false);
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(pane);
		dialogStage.setScene(scene);

		SearchEdit controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setObj(obj);
		if (controllerList != null) {
			controller.init();
		} else {
			controller.initP();
		}

		dialogStage.showAndWait();

		if (controllerList != null) {
			controllerList.init(controller.getName2());
		} else {
			controller.writeP();
		}

	}

}
