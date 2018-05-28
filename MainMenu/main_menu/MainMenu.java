package main_menu;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.Parent;

public class MainMenu extends Application{

	static final int WINDOW_HEIGHT = 800, WINDOW_WIDTH = 1200;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception {
		stage.setTitle("Rally");
		stage.getIcons().add(new Image("/images/ball_icon.png"));
		Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        Scene scene_login = new Scene(root);
        stage.setScene(scene_login);
        stage.show();
	}

}
