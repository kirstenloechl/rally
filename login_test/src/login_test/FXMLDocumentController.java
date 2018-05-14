package login_test;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private AnchorPane home_page;
    @FXML 
    private TextField username_box;
    @FXML 
    private TextField password_box;
    @FXML
    private Label invalid_label;
    @FXML
    private Button back_button;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        
            System.out.println("DO IT");
            Parent home_page_parent =  FXMLLoader.load(getClass().getResource("FXMLHomePage.fxml"));
            home_page_parent.getStylesheets().add("style.css");            
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            
            if (isValidCredentials())
            {
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();  
            }
            else
            {
                username_box.clear();
                password_box.clear();
                invalid_label.setText("Sorry, invalid credentials."); 
            }
    }
    
    @FXML
    private void backButtonAction(ActionEvent event) throws IOException {
   
        Parent date_page_parent = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
        date_page_parent.setId("pane");
        date_page_parent.getStylesheets().add("style.css");
        Scene date_page_scene = new Scene(date_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(date_page_scene);
        app_stage.show();     
    }
    
    private boolean isValidCredentials()
    {
        boolean let_in = false;
    
        Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:sqlite:database.db");
            c.setAutoCommit(false);
            
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            
            ResultSet rs = stmt.executeQuery( "SELECT * FROM logins WHERE username= " + "'" + username_box.getText() + "'" 
            + " AND password= " + "'" + password_box.getText() + "'");
            
            while ( rs.next() ) {
                 if (rs.getString("username") != null && rs.getString("password") != null) { 
                     String  username = rs.getString("username");
                     System.out.println( "username = " + username );
                     String password = rs.getString("password");
                     System.out.println("password = " + password);
                     let_in = true;
                 }  
            }
            rs.close();
            stmt.close();
            c.close();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
            }
            System.out.println("Operation done successfully");
            return let_in;
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
