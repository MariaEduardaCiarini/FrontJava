package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;

	@FXML
	private MenuItem menuItemDepartment;

	@FXML
	private MenuItem menuItemAbout;

	@FXML
	public void onMenuItemSellerAction() {
		loadView("/gui/SellerList.fxml");
	}

	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartamentList.fxml");
	}

	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/DepartamentList.fxml");
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {

	}

	private synchronized void loadView(String absoluteName) {
		try {
			System.out.println("Tentando carregar: " + absoluteName);

			URL fxmlLocation = getClass().getResource(absoluteName);
			if (fxmlLocation == null) {
				throw new IOException("Arquivo FXML não encontrado: " + absoluteName);
			}

			FXMLLoader loader = new FXMLLoader(fxmlLocation);
			VBox newVBox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().isEmpty() ? null : mainVBox.getChildren().get(0);

			mainVBox.getChildren().clear();
			if (mainMenu != null) {
				mainVBox.getChildren().add(mainMenu);
			}
			mainVBox.getChildren().addAll(newVBox.getChildren());

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Erro ao carregar a view", e.getMessage(), AlertType.ERROR);
			e.printStackTrace();
		} catch (Exception ex) {
			Alerts.showAlert("Erro inesperado", "Erro ao carregar a view", ex.getMessage(), AlertType.ERROR);
			ex.printStackTrace();
		}
	}
}
