package game;

import java.awt.geom.RectangularShape;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class GameTimer extends AnimationTimer {

	private static GameTimer singleton = null;
	
	private long prevNanoTime;
	private PhysicsSimulation p;
	private GraphicsContext gc;
	private Canvas canvas;
	private Label scoreRed;
	private Label scoreBlue;
	private Button playAgain;
	private static final int WIDTH = 984;
    private static final int HEIGHT = 624;
	
	private GameTimer() {

		// Default constructor
		
	}

	public static GameTimer getInstance()
	{
		if (singleton == null)
			singleton = new GameTimer();

		return singleton;
	}

	public void setScoreRed(Label scoreRed) {
	    this.scoreRed = scoreRed;
    }

    public void setScoreBlue(Label scoreBlue) {
        this.scoreBlue = scoreBlue;
    }

    public void setPlayAgain(Button playAgain) {
	    this.playAgain = playAgain;
    }

    public void handle(long currentNanoTime) {
	 		 	
	    double tick = (currentNanoTime - prevNanoTime) / 1000000000.0;
	    prevNanoTime = currentNanoTime;
	    p.update(tick);
	    
		gc.setFill(Color.web("#8cff66"));
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
	    
		// Update scores
	    scoreBlue.setText(Integer.toString(p.getTeamTwoScore()));
	    scoreRed.setText(Integer.toString(p.getTeamOneScore()));
	    
	    // If either player wins, reset the scores and display the game over options
	    if (p.getTeamOneScore() == 2 || p.getTeamTwoScore() == 2) {
			p.resetScores();
			playAgain.setVisible(true);
			this.stop();
		}
	     
	}
	
	public void setPhysics(PhysicsSimulation p) {
		this.p = p;		
	}
	
	public PhysicsSimulation getPhysics() {
		return this.p;
	}
	
	public void setGraphicsContext(GraphicsContext gc) {
		this.gc = gc;		
	}
	public GraphicsContext getGraphicsContext() {
		return this.gc;
	}
	
	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;		
	}
	public Canvas getCanvas() {
		return this.canvas;
	}
	
	// CALL THIS BEFORE ANY CALLS TO .start() 
	public void updateNanoTime() {
		this.prevNanoTime = System.nanoTime();
	}
}
