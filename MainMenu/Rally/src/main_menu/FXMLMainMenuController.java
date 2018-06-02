package main_menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXMLMainMenuController {
	
	static final int MENU_WIDTH = 1024, MENU_HEIGHT = 664;
	
	@FXML
	Button btnOnePlayer, btnTwoPlayer, btnLeaderboard, btnSettings, btnExit;
	
	
	
	@FXML
	protected void handleOnePlayer(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLOnePlayer.fxml"));
		Scene main = new Scene(root, MENU_WIDTH, MENU_HEIGHT); 	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
	    stage.setX((screenBounds.getWidth() - MENU_WIDTH) / 2); 
		stage.setY((screenBounds.getHeight() - MENU_HEIGHT) / 2);  
		
		stage.setScene(main);
		stage.show();
	}
	
	@FXML
	protected void handleTwoPlayer(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLTwoPlayer.fxml"));
		Scene main = new Scene(root, MENU_WIDTH, MENU_HEIGHT); 	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
	    stage.setX((screenBounds.getWidth() - MENU_WIDTH) / 2); 
		stage.setY((screenBounds.getHeight() - MENU_HEIGHT) / 2);  
		
		stage.setScene(main);
		stage.show();
	}
	
	@FXML
	protected void handleSettings(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLSettings.fxml"));
		Scene main = new Scene(root, MENU_WIDTH, MENU_HEIGHT); 	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
	    stage.setX((screenBounds.getWidth() - MENU_WIDTH) / 2); 
		stage.setY((screenBounds.getHeight() - MENU_HEIGHT) / 2);  
		
		stage.setScene(main);
		stage.show();
	}
	
	@FXML
	protected void handleLeaderboard(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLLeaderboard.fxml"));
		Scene main = new Scene(root, MENU_WIDTH, MENU_HEIGHT); 	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
	    stage.setX((screenBounds.getWidth() - MENU_WIDTH) / 2); 
		stage.setY((screenBounds.getHeight() - MENU_HEIGHT) / 2);  
		
		stage.setScene(main);
		stage.show();
	}
	
	@FXML
	protected void handleExit(ActionEvent event) throws Exception {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
	}

}
