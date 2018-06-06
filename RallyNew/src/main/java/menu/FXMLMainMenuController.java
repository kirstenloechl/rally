package menu;

import java.awt.geom.RectangularShape;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

import javafx.animation.AnimationTimer;
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
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import game.PhysicsObject;
import game.PhysicsSimulation;

public class FXMLMainMenuController {
	
	private  PhysicsSimulation p;
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
		getLeaderboard(stage);
	}
	
	@FXML
	protected void handleExit(ActionEvent event) throws IOException {
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.close();
		
	}
	
	public void launchGame() {

		Stage stage = new Stage();
		stage.setTitle( "Rally - Game" );

        Group root = new Group();
        Scene scene = new Scene( root );
        stage.setScene( scene );

        scene.getStylesheets().add("/styles/master.css");
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
   	    stage.setX((screenBounds.getWidth() - WIDTH + (2 * BORDER)) / 2); 
   	    stage.setY((screenBounds.getHeight() - HEIGHT + (2 * BORDER)) / 2);  
        
   	    Canvas canvas = new Canvas( WIDTH, HEIGHT );
        root.getChildren().add( canvas );

        final GraphicsContext gc = canvas.getGraphicsContext2D();
        p = new PhysicsSimulation(WIDTH, HEIGHT, BORDER);
        
        Button back = new Button("Menu");
        back.relocate(50, 25);
        root.getChildren().add(back);
        back.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent e) {
        		Parent root;
    	        try {
    	        	URL url = Paths.get("./src/main_menu/FXMLMainMenu.fxml").toUri().toURL();
    	        	root =  FXMLLoader.load(url);
    	            Scene homePageScene = new Scene(root, MENU_WIDTH, MENU_HEIGHT);
    	            Stage appStage = (Stage) ((Node)e.getSource()).getScene().getWindow();
    	                
    	            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    	       	    appStage.setX((screenBounds.getWidth() - MENU_WIDTH) / 2);
    	       	    appStage.setY((screenBounds.getHeight() - MENU_HEIGHT) / 2);
    	            appStage.hide();
    	            appStage.setScene(homePageScene);
    	            appStage.show();
    	            
        	        }
        	        catch (Exception ex) {
        	            ex.printStackTrace();
        	        }
        	}
        });
        

        new AnimationTimer()
        {
            long prevNanoTime = System.nanoTime();

            public void handle(long currentNanoTime)
            {
                double tick = (currentNanoTime - prevNanoTime) / 1000000000.0;
                prevNanoTime = currentNanoTime;
                p.update(tick);

                gc.setFill(Color.GREEN);
                gc.fillRect(0, 0, WIDTH, HEIGHT);

                for(PhysicsObject o: p.getBalls()) {
                    RectangularShape s = o.getShape();
                    gc.setFill(o.getColor());
                    gc.fillOval(s.getX(), s.getY(), s.getWidth(), s.getHeight());
                }

                for(PhysicsObject o: p.getWalls()) {
                    RectangularShape s = o.getShape();
                    gc.setFill(o.getColor());
                    gc.fillRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
                }

                for(PhysicsObject o: p.getPlayers()) {
                    RectangularShape s = o.getShape();
                    gc.setFill(o.getColor());
                    gc.fillRect(s.getX(), s.getY(), s.getWidth(), s.getHeight());
                }
                
                if (p.getTeamOneScore() > 3 || p.getTeamTwoScore() > 3) {
                	this.stop();
                }
            }
        }.start();

        stage.show();	
	}

}
