package login_test;

import java.awt.Graphics;
import java.lang.Math;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import RallyGame;
/**
 * 
 * @author kirstenloechl7
 *
 */

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
    private void playButtonAction(ActionEvent event) throws IOException, InterruptedException {
    	Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        RallyGame game = new RallyGame();
        System.out.println("test1");
        game.play();
        System.out.println("test1"); 
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
