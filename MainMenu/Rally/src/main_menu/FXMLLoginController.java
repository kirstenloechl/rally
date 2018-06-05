package main_menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXMLLoginController implements Initializable {
    
	@FXML
    private Button loginbutton;
    
    @FXML
    private Button signupbutton;
    
    @FXML
    Label task_label;
 
    static final int SIGNUP_WIDTH = 320, SIGNUP_HEIGHT = 300;
    static final int LOGIN_WIDTH = 600, LOGIN_HEIGHT = 400;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException {
        
        Parent login_page_parent =  FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene login_page_scene = new Scene(login_page_parent, SIGNUP_WIDTH, SIGNUP_HEIGHT);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		app_stage.setX((screenBounds.getWidth() - SIGNUP_WIDTH) / 2); 
		app_stage.setY((screenBounds.getHeight() - SIGNUP_HEIGHT) / 2);  
        
        app_stage.hide();
        app_stage.setScene(login_page_scene);
        app_stage.show();  
            
    }
    
    @FXML
    private void signupButtonAction(ActionEvent event) throws IOException {
    	Parent signup_page_parent =  FXMLLoader.load(getClass().getResource("FXMLInsertPage.fxml"));
        Scene signup_page_scene = new Scene(signup_page_parent);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        app_stage.setX((screenBounds.getWidth() - SIGNUP_WIDTH) / 2); 
      	app_stage.setY((screenBounds.getHeight() - SIGNUP_HEIGHT) / 2);  
        
      	app_stage.hide(); 
        app_stage.setScene(signup_page_scene);
        app_stage.show();  
    }
    
}
