package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.geom.RectangularShape;

public class RallyGame2 extends Application {

    private transient PhysicsSimulation p;

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private static final int BORDER = 20;

    public void start(Stage theStage)
    {
        theStage.setTitle( "RallyGame" );

        Group root = new Group();
        Scene theScene = new Scene( root );
        theStage.setScene( theScene );

        Canvas canvas = new Canvas( WIDTH, HEIGHT );
        root.getChildren().add( canvas );

        GraphicsContext gc = canvas.getGraphicsContext2D();
        p = new PhysicsSimulation(WIDTH, HEIGHT, BORDER);

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
            }
        }.start();

        theStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
