package main_menu;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import logic.PhysicsObject;
import logic.PhysicsPlayer;
import logic.PhysicsSimulation;
import logic.RallyGame;

public class FXMLTwoPlayerController implements Initializable{

	 static final int MENU_WIDTH = 1024, MENU_HEIGHT = 664;
	
	 private transient PhysicsSimulation p;

	 private static final double FPS = 100;
	 private static final double TICK = 1.0 / FPS;
	 private static final int WIDTH = 1000;
	 private static final int HEIGHT = 600;
	 private static final int BORDER = 20;
	
	@FXML
	Button btnReturn;
	
	@FXML
	SwingNode sw;
	
	@FXML
	StackPane anchor;
	
	@FXML
	protected void handleReturn(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLMainMenu.fxml"));
		Scene main = new Scene(root, MENU_WIDTH, MENU_HEIGHT); 	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Rally - Main Menu");
		
		
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
	    stage.setX((screenBounds.getWidth() - MENU_WIDTH) / 2); 
		stage.setY((screenBounds.getHeight() - MENU_HEIGHT) / 2);  
		
		stage.setScene(main);
		stage.show();
	}
	
	public void initialize(URL url, ResourceBundle rb) {
		setSwingContent(sw);		
       
	}
	
	private void setSwingContent(SwingNode swingNode) {
		 SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	RallyGame rg = new RallyGame();
	            	rg.setPhysicsSimulation(new PhysicsSimulation(TICK, WIDTH, HEIGHT, BORDER));
	                swingNode.setContent(rg);
	           
	                
	            }
	        });		
	}
	
}
