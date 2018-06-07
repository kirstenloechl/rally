package menu;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import game.GameTimer;
import game.PhysicsPlayer;
import game.PhysicsSimulation;

public class FXMLMainMenuController {
	
	private  PhysicsSimulation p;
	private GameTimer gt;
	static final int MENU_WIDTH = 1024;
	static final int MENU_HEIGHT = 664;
	private static final int WIDTH = 984;
    private static final int HEIGHT = 624;
    private static final int BORDER = 20;
	
	@FXML
	Button btnTwoPlayer;
	@FXML
	Button btnLeaderboard;
	@FXML
	Button btnSettings;
	@FXML
	Button btnExit;
	Button playAgain;
	Label scoreRed, scoreBlue;
	
	@FXML
	protected void handleTwoPlayer(ActionEvent event) throws IOException {
		launchGame();
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.hide();
		
	}
	
	@FXML
	protected void handleSettings(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLSettings.fxml"));
		Scene main = new Scene(root, MENU_WIDTH, MENU_HEIGHT); 	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
	    stage.setX((screenBounds.getWidth() - MENU_WIDTH) / 2); 
		stage.setY((screenBounds.getHeight() - MENU_HEIGHT) / 2);  
		
		stage.setScene(main);
		stage.setTitle("Rally - Settings");
		stage.show();
	}
	
	@FXML
	protected void handleLeaderboard(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLLeaderboard.fxml"));
		Scene main = new Scene(root, MENU_WIDTH, MENU_HEIGHT); 	
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
	    stage.setX((screenBounds.getWidth() - MENU_WIDTH) / 2); 
		stage.setY((screenBounds.getHeight() - MENU_HEIGHT) / 2);  
		
		stage.setScene(main);
		stage.setTitle("Rally - Leaderboard");
		stage.show();
		//getLeaderboard(stage);
	}
	
	@FXML
	protected void handleExit(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		
	}
	
	public void launchGame() {
		
		// Set up game window
		Stage stage = new Stage();
		stage.setTitle( "Rally - Game" );
		stage.getIcons().add(new Image("/images/ball_icon.png"));
        Group root = new Group();
        Scene scene = new Scene( root );
        scene.getStylesheets().add("/styles/game.css");
        stage.setScene( scene );
        
        
        // Set up the handlers for the player controls
        scene.setOnKeyPressed(
        		new EventHandler<KeyEvent>() {
        			public void handle(KeyEvent e) {
        				for (PhysicsPlayer player: p.getPlayers()) {
        					player.keyPressed(e);
        				}
        			}
		});
        scene.setOnKeyReleased(
        		new EventHandler<KeyEvent>() {
        			public void handle(KeyEvent e) {
        				for (PhysicsPlayer player: p.getPlayers()) {
        					player.keyReleased(e);
        				}
        			}
		});        
        
        // Center on screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
   	    stage.setX((screenBounds.getWidth() - WIDTH + (2 * BORDER)) / 2); 
   	    stage.setY((screenBounds.getHeight() - HEIGHT + (2 * BORDER)) / 2);  
        
   	    // Set up game screen
   	    Canvas canvas = new Canvas( WIDTH, HEIGHT );
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        p = new PhysicsSimulation(WIDTH, HEIGHT, BORDER);
        root.getChildren().add( canvas );
        
        addLabels(scene, root);
        addButtons(scene, root);

        // Initialize the global instance of the GameTimer
        gt = new GameTimer();
        gt.setCanvas(canvas);
        gt.setPhysics(p);
        gt.setGraphicsContext(gc);
        gt.updateNanoTime();
        gt.scoreRed = scoreRed;
        gt.scoreBlue = scoreBlue;
        gt.playAgain = playAgain;
     
        gt.start();
         
        stage.show();	

	}
	
	private void addLabels(Scene scene, Group root) {
		scoreBlue = new Label("0");
		scoreRed = new Label("0"); 
        
        scoreBlue.relocate( 350, 25);
        scoreRed.relocate(600, 25);
        
        root.getChildren().addAll(scoreBlue, scoreRed);
	}
	
	private void addButtons(Scene scene, Group root) {	 
		Button back = new Button("Menu");
        back.relocate(50, 25);
        root.getChildren().add(back);
        back.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		Parent root;
    	        try {
    	        	URL url = Paths.get("./src/main/resources/fxml/FXMLMainMenu.fxml").toUri().toURL();
    	        	root =  FXMLLoader.load(url);
    	            Scene homePageScene = new Scene(root, MENU_WIDTH, MENU_HEIGHT);
    	            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
    	            stage.setTitle("Rally - Menu");
    	            stage.getIcons().add(new Image("/images/ball_icon.png"));
    	            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    	       	    stage.setX((screenBounds.getWidth() - MENU_WIDTH) / 2);
    	       	    stage.setY((screenBounds.getHeight() - MENU_HEIGHT) / 2);
    	            stage.hide();
    	            stage.setScene(homePageScene);
    	            stage.show();
    	            
        	        }
        	        catch (Exception ex) {
        	            ex.printStackTrace();
        	        }
        		}
        });
        
        playAgain = new Button("Play Again?");
        playAgain.setLayoutX(425);
        playAgain.setLayoutY(200);
        playAgain.setVisible(false);
        root.getChildren().add(playAgain);
        playAgain.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		gt.start();
        		gt.updateNanoTime();
        		playAgain.setVisible(false);
        		
        		
        	}
        });
        
	}

}
