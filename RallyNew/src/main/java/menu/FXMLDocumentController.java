package menu;

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
import javafx.stage.Screen;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {
    
	@FXML
	Button btnReturn;
	@FXML
	Button btnLogin;
    @FXML 
    private TextField usernameBox;
    @FXML 
    private TextField passwordText;
    @FXML
    private Label invalidLabel;
    
	static final int MENU_WIDTH = 1024;
	static final int MENU_HEIGHT = 664;
    static final int LOGIN_WIDTH = 600;
    static final int LOGIN_HEIGHT = 400;
 
	@FXML
    public void handleReturn(ActionEvent event) throws Exception {

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        boolean b = true;
		stage.setX((screenBounds.getWidth() - LOGIN_WIDTH) / 2);
		stage.setY((screenBounds.getHeight() - LOGIN_HEIGHT) / 2);
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLLogin.fxml"));
        while(b) { b = false; }
		Scene main = new Scene(root, LOGIN_WIDTH, LOGIN_HEIGHT);
		stage.setScene(main);
		stage.show();
    }
	
    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
    	if (!isValidCredentials()) {
    		usernameBox.clear();
            passwordText.clear();
    	}
    	else {
            Parent homePageParent =  FXMLLoader.load(getClass().getResource("/fxml/FXMLMainMenu.fxml"));
            Scene homePageScene = new Scene(homePageParent, MENU_WIDTH, MENU_HEIGHT);
            Stage appStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
       	    appStage.setX((screenBounds.getWidth() - MENU_WIDTH) / 2);
       	    appStage.setY((screenBounds.getHeight() - MENU_HEIGHT) / 2);
            appStage.hide();
            appStage.setScene(homePageScene);
            appStage.show();
    	}
    }
    
    private boolean isValidCredentials()
    {
        boolean letIn = false;

        try {
        	Class.forName("org.sqlite.JDBC");
            try(Connection c = DriverManager.getConnection("jdbc:sqlite:database.db")) {
                c.setAutoCommit(false);

                try(Statement stmt = c.createStatement()) {

                    try(ResultSet rs = stmt.executeQuery("SELECT * FROM logins WHERE username= " + "'" + usernameBox.getText() + "'"
                            + " AND password= " + "'" + passwordText.getText() + "'")) {

                        while (rs.next()) {
                            if (rs.getString("username") != null && rs.getString("password") != null) {
                                letIn = true;
                            }
                        }
                    }
                }
            }
        } catch ( Exception e ) {
        	System.exit(0);
            
        }
        return letIn;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

	    // Unused

    }
    
}
