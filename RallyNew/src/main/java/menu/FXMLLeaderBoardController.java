package menu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.*;
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
import javafx.scene.text.Text;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import javafx.geometry.*;
import javafx.scene.text.TextAlignment;

public class FXMLLeaderBoardController {

	static final int WINDOW_HEIGHT = 664, WINDOW_WIDTH = 1024;
	
	@FXML
	Button btnReturn;
	
	@FXML
	protected void handleReturn(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("FXMLMainMenu.fxml"));
		Scene main = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT); 	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		stage.setScene(main);
		stage.show();

	}
	
	@FXML
	protected void getLeaderboard(Stage stage) {
		// pane = FXMLLoader.load(getClass().getResource("FXMLLeaderboard.fxml"));
	    Connection c = null;
	    Statement stmt = null;
	    int i = 1;
	    int x = 10;
	    int y = 10;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:database.db");
	      c.setAutoCommit(false);      
	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM logins ORDER by highscore DESC LIMIT 5");
	      Pane pane = new Pane();
	      Pane newPane = FXMLLoader.load(getClass().getResource("FXMLLeaderboard.fxml"));
	      pane.getChildren().add(newPane);
	      while (rs.next()) {
	    	  Text t = new Text();
	    	  String user = rs.getString("username");
	    	  String score = Integer.toString(rs.getInt("highscore"));
	    	  String done = String.format("%-33s%d%-3s%-20s%4s\n"," ",i++,".",user,score);
	    	  t.setText(done);
	    	  t.setY(y+=100);
	    	  t.setStyle("-fx-font: 36 arial;");
	    	  pane.getChildren().add(t);

	      }
	      rs.close();
	      stmt.close();
	      c.close();
	      Scene scene2 = new Scene(pane,WINDOW_WIDTH,WINDOW_HEIGHT);
	      stage.setScene(scene2);
    	  stage.show();
		}
		catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);  
		}
	}
}
