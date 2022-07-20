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
 * ������������������ʼҳ���Լ���תҳ��
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
	 * ������ʼ����
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	// ��ʼ����**************************************************
	/**
	 * �÷������ڼ��ؿ�ʼ����
	 * @throws IOException
	 */
	public void showMain() throws IOException {
		pane = FXMLLoader.load(getClass().getResource("/view/MorS.fxml"));
		primaryStage.setScene(new Scene(pane));
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	// ��¼****************************************************
	/**
	 * �÷������ڼ����������
	 * @throws IOException
	 */
	public void showPassword() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/Password.fxml"));// ����view���µ�fxml�ļ�
		AnchorPane pane = (AnchorPane) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("��������-��¼");
		dialogStage.setResizable(false);
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.initOwner(primaryStage);
		Scene scene = new Scene(pane);
		dialogStage.setScene(scene);

		Password controller = loader.getController();
		controller.setDialogStage(dialogStage);

		dialogStage.showAndWait();

	}

	// �û�*********************************************************
	/**
	 * �÷������ڼ��ظ���ҳ��
	 * @param i
	 * �жϵ�¼���˺����
	 * iΪArrayList�е�����ֵ
	 * @throws IOException
	 */
	public void showPersonal(int i) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/Personal.fxml"));
		AnchorPane pane = (AnchorPane) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("��������-����ҳ��");
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
	 * �÷������ڼ����û�������Ϣ�޸Ľ���
	 * @param member
	 * Ҫ�޸ĵĶ���
	 * @return
	 * �ж��޸��Ƿ�ɹ�
	 */
	public boolean showPersonalEditDialog(Member member) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Plogin.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("��������-ס����Ϣ�޸�");
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

	// �б�****************************************************
	/**
	 * �÷������ڼ��ع������
	 * @throws IOException
	 */
	public void showList() throws IOException {
		// pane = FXMLLoader.load(getClass().getResource("/view/List.fxml"));

		FXMLLoader loaderList = new FXMLLoader(getClass().getResource("/view/List.fxml"));
		pane = loaderList.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("��������-��̨����");
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
	 * �÷������ڼ����û��༭����
	 * @param member
	 * Ҫ�༭���û�
	 * @param controllerList
	 * ����ҳ��Ŀ�����
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
			dialogStage.setTitle("��������-ס����Ϣ�޸�");
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
	 * �÷������ڼ���Ա���༭����
	 * @param staff
	 * Ҫ�޸ĵ�Ա��
	 * @param controllerList
	 * ����ҳ��Ŀ�����
	 * @return
	 * �ж��޸��Ƿ�ɹ�
	 */
	public boolean showStaffEditDialog(Staff staff, Lists controllerList) {
		try {
			// Load the fxml file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/SLogin.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("��������-Ա����Ϣ�޸�");
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
	 * ���ڼ��س��ݱ༭ҳ��
	 * @param stadium
	 * Ҫ�༭�ĳ���
	 * @param controllerList
	 * ����ҳ�������
	 * @return
	 * �ж��޸��Ƿ�ɹ�
	 */
	public boolean showStadiumEditDialog(Stadium stadium, Lists controllerList) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Alogin.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("��������-������Ϣ�޸�");
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
	 * �÷������ڼ��ط��ݱ༭ҳ��
	 * @param house
	 * Ҫ�༭�ķ���
	 * @param controllerList
	 * ����ҳ��Ŀ�����
	 * @return
	 * �ж��޸��Ƿ�ɹ�
	 */
	public boolean showHouseEditDialog(House house, Lists controllerList) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Hlogin.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("��������-ס����Ϣ�޸�");
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
	 * �÷������ڼ��ذ೵�༭ҳ��
	 * @param traffic
	 * Ҫ�༭�Ķ���
	 * @return
	 * �ж��޸��Ƿ�ɹ�
	 */
	public boolean showTrafficEditDialog(Traffic traffic) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/TLogin.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			Stage dialogStage = new Stage();
			dialogStage.setTitle("��������-��ͨ");
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

	// ����*********************************************
	/**
	 * �÷������ڼ�������ҳ��
	 * @param obj
	 * Ҫ�޸ĵĶ���
	 * @param controllerList
	 * ����ҳ�������
	 * @throws IOException
	 * 
	 */
	public void showSearch(Object obj, Lists controllerList) throws IOException {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/view/searchEdit.fxml"));
		AnchorPane pane = (AnchorPane) loader.load();

		Stage dialogStage = new Stage();
		dialogStage.setTitle("��������-����");
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
