import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PhysicsSimulation {

    public static final double FPS = 60;
    public static final double SPF = 1.0 / FPS;
    public static final double GRAVITY = 100;

    private double time = 0;

    public PhysicsCircle ball;
    public ArrayList<PhysicsRectangle> rectangles;
    public PhysicsPlayer player;

    public PhysicsSimulation() {
        this.ball = new PhysicsCircle(30, 30, 30, 0.95, 0.99, 100, 100);
        this.rectangles = new ArrayList<PhysicsRectangle>();
        rectangles.add(new PhysicsRectangle(0, 0,1000, 10, 1));
        rectangles.add(new PhysicsRectangle(0, 0, 10, 500, 1));
        rectangles.add(new PhysicsRectangle(0, 500, 1000, 10, 1));
        rectangles.add(new PhysicsRectangle(1000, 0, 10, 500, 1));
        this.player = new PhysicsPlayer(480, 250, 100, 20, 1.5, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, 200.0);
        rectangles.add(this.player);

    }

    public void update() {

        this.time += this.SPF;
        this.ball.x += this.ball.vel_x * this.SPF;
        this.ball.y += this.ball.vel_y * this.SPF;
        this.ball.vel_y += this.GRAVITY * this.SPF;

        for(PhysicsRectangle r: this.rectangles) {

            r.x += r.vel_x * this.SPF;
            r.y += r.vel_y * this.SPF;

            this.ball.handleCollision(r);

        }

    }



}
