package menu;

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
    private TextField usernameText;
    @FXML
    private TextField passwordText;
    @FXML
    private Label invalidLabel;

    static final int LOGIN_WIDTH = 600;
    static final int LOGIN_HEIGHT = 400;
	static final int MENU_WIDTH = 1024;
	static final int MENU_HEIGHT = 664;
    
    @FXML
    public void handleReturn(ActionEvent event) throws IOException {

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((screenBounds.getWidth() - LOGIN_WIDTH) / 2); 
		stage.setY((screenBounds.getHeight() - LOGIN_HEIGHT) / 2);  
		 		
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLLogin.fxml"));
		Scene main = new Scene(root, LOGIN_WIDTH, LOGIN_HEIGHT); 	 
		
		stage.setScene(main);
		stage.show();	
    }
    
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
    	
    	if (userExists(usernameText.getText())) {
            usernameText.clear();
            passwordText.clear();
            
            invalidLabel.setVisible(false);
            invalidLabel.setLayoutX(100);
            invalidLabel.setText("Username already exists");
            
    	}
    	else if(usernameText.getText().isEmpty() || passwordText.getText().isEmpty()) {
    		usernameText.clear();
            passwordText.clear();

            invalidLabel.setVisible(false);
            invalidLabel.setLayoutX(50);
            invalidLabel.setText("Please provide a username and password");
    	}

    	else {
    		 String query = "INSERT INTO logins (username, password) VALUES (" + "'" + usernameText.getText() +
    	                "'," + "'" + passwordText.getText() + "');";

    	     insertStatement(query);
    	      
    	     Parent datePageParent = FXMLLoader.load(getClass().getResource("/fxml/FXMLMainMenu.fxml"));
    	     Scene datePageScene = new Scene(datePageParent, MENU_WIDTH, MENU_HEIGHT);
    	     Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	     
    	     Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    	     appStage.setX((screenBounds.getWidth() - MENU_WIDTH) / 2);
    		 appStage.setY((screenBounds.getHeight() - MENU_HEIGHT) / 2);
    	     
    	     appStage.hide(); //optional
    	     appStage.setScene(datePageScene);
    	     appStage.show();
    	} 
    	invalidLabel.setVisible(true);
    }
     
    private void insertStatement(String insertQuery){
    	Connection c = null;
    	Statement stmt = null;
    	try {
    		Class.forName("org.sqlite.JDBC");
    		c = DriverManager.getConnection("jdbc:sqlite:database.db");
    		c.setAutoCommit(false);
    		stmt = c.createStatement();
    		stmt.executeUpdate(insertQuery);
    		stmt.close();
    		c.commit();
    		c.close();
    	}catch ( Exception e ) {
            System.exit(0);  
        }
    }
    
    private boolean userExists(String username) {
    	boolean unExists = false;
    	String query = "SELECT * from logins WHERE username = ?";

		try {
		     Class.forName("org.sqlite.JDBC");
		     try(Connection c = DriverManager.getConnection("jdbc:sqlite:database.db")) {
				 c.setAutoCommit(false);
				 try (final PreparedStatement ps = c.prepareStatement(query);) {
					 ps.setString(1, username);
					 try (ResultSet rs = ps.executeQuery();) {
						 while (rs.next()) {
							 unExists = true;
						 }
					 }
				 }
				 c.commit();
			 }
		} catch ( Exception e ) {
		    System.exit(0);  
		}  
		return unExists;
	}   
}