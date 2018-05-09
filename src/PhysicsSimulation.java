import java.util.ArrayList;

public class PhysicsSimulation {

    public static final double FPS = 60;
    public static final double SPF = 1.0 / FPS;
    public static final double GRAVITY = 100;

    private double time = 0;

    public PhysicsCircle ball;
    private ArrayList<PhysicsRectangle> rectangles;

    public PhysicsSimulation() {
        this.ball = new PhysicsCircle(30, 10, 10, 300, 300, 0.95, 0.99);
        this.rectangles = new ArrayList<PhysicsRectangle>();
        rectangles.add(new PhysicsRectangle(1000, 20, 0, 000));
        rectangles.add(new PhysicsRectangle(1000, 20, 0, 500));
        rectangles.add(new PhysicsRectangle(20, 1000, 000, 0));
        rectangles.add(new PhysicsRectangle(20, 1000, 500, 0));

    }

    public void update() {

        this.time += this.SPF;
        this.ball.pos_x += this.ball.vel_x * this.SPF;
        this.ball.pos_y += this.ball.vel_y * this.SPF;
        this.ball.vel_y += this.GRAVITY * this.SPF;

        for(PhysicsRectangle r: this.rectangles) {

            this.ball.handleCollision(r);

        }

    }



}
