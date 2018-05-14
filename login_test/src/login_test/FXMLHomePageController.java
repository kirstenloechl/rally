package login_test;

import java.io.IOException;
import java.net.URL;
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


public class FXMLHomePageController implements Initializable {

	@FXML
    private Button playgame;
    
    @FXML
    private Button viewleaders;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void playButtonAction(ActionEvent event) throws IOException {
        
        Parent login_page_parent =  FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene login_page_scene = new Scene(login_page_parent);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.hide();
        app_stage.setScene(login_page_scene);
        app_stage.show();  
            
    }  
    
    @FXML
    private void scoresButtonAction(ActionEvent event) throws IOException {
        
        Parent login_page_parent =  FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene login_page_scene = new Scene(login_page_parent);
        Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        
        app_stage.hide();
        app_stage.setScene(login_page_scene);
        app_stage.show();  
            
    }
    
}
