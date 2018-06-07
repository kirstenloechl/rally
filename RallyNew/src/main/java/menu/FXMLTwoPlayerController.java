package menu;

import java.net.URL;
import java.util.ResourceBundle;

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

public class FXMLTwoPlayerController implements Initializable {

	 static final int MENU_WIDTH = 1024;
	 static final int MENU_HEIGHT = 664;

	@FXML
	Button btnReturn;
	
	@FXML
	SwingNode sw;
	
	@FXML
	StackPane anchor;
	
	@FXML
	protected void handleReturn(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLMainMenu.fxml"));
		Scene main = new Scene(root, MENU_WIDTH, MENU_HEIGHT);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Rally - Main Menu");
		
		
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
	    stage.setX((screenBounds.getWidth() - MENU_WIDTH) / 2);
		stage.setY((screenBounds.getHeight() - MENU_HEIGHT) / 2);
		
		stage.setScene(main);
		stage.show();
	}
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {

		// Unneeded

    }    

	
	
}
