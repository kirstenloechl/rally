package main_menu;

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
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {
    
	@FXML
	Button btnReturn;
	@FXML
	Button btnLogin;
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
    
	static final int MENU_WIDTH = 1024, MENU_HEIGHT = 664;
    static final int LOGIN_WIDTH = 600, LOGIN_HEIGHT = 400;
 
	@FXML
    public void handleReturn(ActionEvent event) throws Exception {
    	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((screenBounds.getWidth() - LOGIN_WIDTH) / 2); 
		stage.setY((screenBounds.getHeight() - LOGIN_HEIGHT) / 2);  
		 		
		Parent root = FXMLLoader.load(getClass().getResource("FXMLLogin.fxml"));
		Scene main = new Scene(root, LOGIN_WIDTH, LOGIN_HEIGHT); 	 
		
		stage.setScene(main);
		stage.show();
    }
	
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
    	if (!isValidCredentials()) {
    		username_box.clear();
            password_box.clear();
            invalid_label.setText("Sorry, invalid credentials."); 	
    	}
    	else {
            System.out.println("DO IT");
            Parent home_page_parent =  FXMLLoader.load(getClass().getResource("FXMLMainMenu.fxml"));
            Scene home_page_scene = new Scene(home_page_parent, MENU_WIDTH, MENU_HEIGHT);
            Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
       	    app_stage.setX((screenBounds.getWidth() - MENU_WIDTH) / 2); 
       	    app_stage.setY((screenBounds.getHeight() - MENU_HEIGHT) / 2);  
            app_stage.hide();
            app_stage.setScene(home_page_scene);
            app_stage.show();  
    	}
    }
    
    private boolean isValidCredentials()
    {
        boolean let_in = false;
        Connection c = null;
        Statement stmt = null;
        try {
        	Class.forName("org.sqlite.JDBC");
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
