package menu;

import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.Parent;

public class MainMenu extends Application{

    static final int LOGIN_WIDTH = 600, LOGIN_HEIGHT = 400;

	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		stage.setTitle("Rally");
		stage.getIcons().add(new Image("/images/ball_icon.png"));
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLLogin.fxml"));
        Scene scene_login = new Scene(root, LOGIN_WIDTH, LOGIN_HEIGHT);
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((screenBounds.getWidth() - LOGIN_WIDTH) / 2); 
		stage.setY((screenBounds.getHeight() - LOGIN_HEIGHT) / 2);  
        
        stage.setScene(scene_login);
        stage.show();
	}

}
