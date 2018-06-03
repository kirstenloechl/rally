package main_menu;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXMLInsertPageController {
    
	@FXML
	Button btnReturn;
    @FXML
    private Button btnLogin;
	@FXML
    private TextField username_text;
    @FXML
    private TextField password_text;
    @FXML
    private Label invalid_label;

    static final int LOGIN_WIDTH = 600, LOGIN_HEIGHT = 400;
	static final int MENU_WIDTH = 1024, MENU_HEIGHT = 664;
    
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
    	
    	if (userExists(username_text.getText())) {
    		System.out.println("INVALID USERNAME");
            username_text.clear();
            password_text.clear();
            
            invalid_label.setVisible(false);
            invalid_label.setLayoutX(100);
            invalid_label.setText("Username already exists");
            
    	}
    	else if(username_text.getText().isEmpty() || password_text.getText().isEmpty()) {
    		System.out.println("NO USERNAME || PASSWORD SPECIFIED");
    		username_text.clear();
            password_text.clear();

            invalid_label.setVisible(false);
            invalid_label.setLayoutX(50);
            invalid_label.setText("Please provide a username and password");

           
            
            
    	}
    	else {
    		 String query = "INSERT INTO logins (username, password) VALUES (" + "'" + username_text.getText() + 
    	                "'," + "'" + password_text.getText() + "');";
    	   
    	        
    	     System.out.println("Inserting\n" + query);
    	     insertStatement(query);
    	      
    	     Parent date_page_parent = FXMLLoader.load(getClass().getResource("FXMLMainMenu.fxml"));
    	     Scene date_page_scene = new Scene(date_page_parent, MENU_WIDTH, MENU_HEIGHT);
    	     Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	     
    	     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    	     app_stage.setX((screenBounds.getWidth() - MENU_WIDTH) / 2); 
    		 app_stage.setY((screenBounds.getHeight() - MENU_HEIGHT) / 2);  
    	     
    	     app_stage.hide(); //optional
    	     app_stage.setScene(date_page_scene);
    	     app_stage.show();     
    	} 
    	invalid_label.setVisible(true);
    }
     
    private void insertStatement(String insert_query){
    	Connection c = null;
    	Statement stmt = null;
    	try {
    		Class.forName("org.sqlite.JDBC");
    		c = DriverManager.getConnection("jdbc:sqlite:database.db");
    		c.setAutoCommit(false);
    		System.out.println("Opened database successfully");
    		stmt = c.createStatement(); 
    		System.out.println("Our query was: " + insert_query);
    		stmt.executeUpdate(insert_query);
    		stmt.close();
    		c.commit();
    		c.close();
    	}catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);  
        }    
    }
    
    private boolean userExists(String username) {
    	boolean un_exists = false;
    	String query = "SELECT * from logins WHERE username = ?";
		Connection c = null;
		try {
		     Class.forName("org.sqlite.JDBC");
		     c = DriverManager.getConnection("jdbc:sqlite:database.db");
		     c.setAutoCommit(false);		    		     
		     final PreparedStatement ps = c.prepareStatement(query);
		     ps.setString(1, username);
		     ResultSet rs = ps.executeQuery();
		     while (rs.next()) {
			     System.out.println("Found " + rs.getString(1) + ".");
			     un_exists = true;
		     }		     
		     c.commit();
		     c.close();
		}catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		    System.exit(0);  
		}  
		return un_exists;
	}   
}