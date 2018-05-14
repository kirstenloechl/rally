package login_test;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLInsertPageController {
    
    @FXML
    private Button done_button;
    @FXML
    private Button back_button;
    
	@FXML
    private TextField username_text;
    
    
    @FXML
    private TextField password_text;
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
    
     @FXML
    private void doneButtonAction(ActionEvent event) throws IOException {
         System.out.println("test1");
        String query = "INSERT INTO logins (username, password) VALUES (" + "'" + username_text.getText() + 
                "'," + "'" + password_text.getText() + "');";
        System.out.println("test2");
        System.out.println("Inserting\n" + query);
        insertStatement(query);
        
        Parent date_page_parent = FXMLLoader.load(getClass().getResource("FXMLHomePage.fxml"));
        date_page_parent.setId("menu");
        date_page_parent.getStylesheets().add("style.css");
        Scene date_page_scene = new Scene(date_page_parent);
        
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(date_page_scene);
        app_stage.show();     
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
    
}