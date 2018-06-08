package menu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;

public class FXMLLeaderBoardController {

	static final int WINDOW_HEIGHT = 664;
	static final int WINDOW_WIDTH = 1024;
	
	@FXML
	Button btnReturn;
	@FXML
	Label user_1, user_2, user_3, user_4, user_5, score_1, score_2, score_3, score_4, score_5;
	
	@FXML
	protected void handleReturn(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLMainMenu.fxml"));
		Scene main = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT); 	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		stage.setScene(main);
		stage.show();

	}
	
	@FXML
	protected void getLeaderboard(Stage stage) {

	    int i = 1;
	    int y = 150;
	    int x1 = 300;
	    int x2 = 650;
	    try {
			Class.forName("org.sqlite.JDBC");
			try (Connection c = DriverManager.getConnection("jdbc:sqlite:database.db")) {
				c.setAutoCommit(false);
				try (Statement stmt = c.createStatement()){
					try(ResultSet rs = stmt.executeQuery("SELECT * FROM logins ORDER by highscore DESC LIMIT 5");) {
						Pane pane = new Pane();
						Pane newPane = FXMLLoader.load(getClass().getResource("/fxml/FXMLLeaderboard.fxml"));
						pane.getChildren().add(newPane);
						while (rs.next()) {
							Text users = new Text();
							Text scores = new Text();
							String user = rs.getString("username");
							String score = Integer.toString(rs.getInt("highscore"));
							users.setText(user);
							scores.setText(score);
							users.setY(y);
							scores.setY(y);
							users.setX(x1);
							scores.setX(x2);
							y+=100;
							users.setStyle("-fx-font: 36 arial;");
							scores.setStyle("-fx-font: 36 arial;");
							pane.getChildren().add(users);
							pane.getChildren().add(scores);

						}
						Scene scene2 = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
						stage.setScene(scene2);
						stage.show();
					}
				}
			}
			
		}
		catch (Exception e) {
            System.exit(0);  
		}	    
	}
}
