package login_test;
import java.sql.Connection;
import java.sql.DriverManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class login_test extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        Scene scene_login = new Scene(root);
        stage.setScene(scene_login);
        stage.show();
        
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
