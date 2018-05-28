package main_menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FXMLTwoPlayerController {

	static final int WINDOW_HEIGHT = 800, WINDOW_WIDTH = 1200;
	
	@FXML
	Button btnReturn;
	
	@FXML
	protected void handleReturn(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLMainMenu.fxml"));
		Scene main = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT); 	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		stage.setScene(main);
		stage.show();
	}
}
