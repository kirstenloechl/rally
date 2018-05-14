package login_test;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FXMLLoginController implements Initializable {
    
	@FXML
    private Button loginbutton;
    
    @FXML
    private Button signupbutton;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    }    
    
    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException {
        
        Parent login_page_parent =  FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        login_page_parent.setId("login");
        login_page_parent.getStylesheets().add("style.css");
        Scene login_page_scene = new Scene(login_page_parent);
        
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.hide();
        app_stage.setScene(login_page_scene);
        app_stage.show();  
            
    }
    
    @FXML
    private void signupButtonAction(ActionEvent event) throws IOException {
    	Parent signup_page_parent =  FXMLLoader.load(getClass().getResource("FXMLInsertPage.fxml"));
    	signup_page_parent.setId("login");
        signup_page_parent.getStylesheets().add("style.css");
    	Scene signup_page_scene = new Scene(signup_page_parent);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.hide(); 
        app_stage.setScene(signup_page_scene);
        app_stage.show();  
    }
    
}
