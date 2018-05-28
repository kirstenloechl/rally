package main_menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FXMLMainMenuController {
	
	static final int WINDOW_HEIGHT = 800, WINDOW_WIDTH = 1200;
	
	@FXML
	Button btnOnePlayer, btnTwoPlayer, btnLeaderboard, btnSettings, btnExit;
	
	
	
	@FXML
	protected void handleOnePlayer(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLOnePlayer.fxml"));
		Scene main = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT); 	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		stage.setScene(main);
		stage.show();
	}
	
	@FXML
	protected void handleTwoPlayer(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLTwoPlayer.fxml"));
		Scene main = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT); 	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		stage.setScene(main);
		stage.show();
	}
	
	@FXML
	protected void handleSettings(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLSettings.fxml"));
		Scene main = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT); 	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		stage.setScene(main);
		stage.show();
	}
	
	@FXML
	protected void handleLeaderboard(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLLeaderboard.fxml"));
		Scene main = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT); 	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		stage.setScene(main);
		stage.show();
	}
	
	@FXML
	protected void handleExit(ActionEvent event) throws Exception {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

}
