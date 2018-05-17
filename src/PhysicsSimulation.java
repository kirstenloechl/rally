import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PhysicsSimulation {

    public final double FPS = 60;
    public final double TICK = 1.0 / FPS;
    public final double GRAVITY = 100;

    private double time = 0;

    public PhysicsCircle ball;
    public ArrayList<PhysicsRectangle> rectangles;
    public PhysicsPlayer player;

    public PhysicsSimulation() {
        this.ball = new PhysicsCircle(30, 30, 30, 0.9, 1.0, 10.0, 10.0, 0.0, 9.81);

        this.rectangles = new ArrayList<PhysicsRectangle>();
        rectangles.add(new PhysicsRectangle(0, 0,1000, 10, 1, 0, 0, 0, 0, 0));
        rectangles.add(new PhysicsRectangle(0, 0, 10, 500, 1, 0, 0, 0, 0, 0));
        rectangles.add(new PhysicsRectangle(0, 490, 1000, 10, 1, 0, 0, 0, 0, 0));
        rectangles.add(new PhysicsRectangle(990, 0, 10, 500, 1, 0, 0, 0, 0, 0));
        this.player = new PhysicsPlayer(480, 250, 100, 20, 1.0, 0, 0, 0, 0, 0, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, 300.0);
        rectangles.add(this.player);

    }

    public void update() {

        this.time += this.TICK;

        ball.performUpdate(this.TICK);
        for(PhysicsRectangle r: this.rectangles) {
            r.performUpdate(this.TICK);
            handleCollision(this.ball, r);
        }

    }

    public void handleCollision(PhysicsCircle c, PhysicsRectangle r) {

        // Compute vector from the rectangle center to the circle center
        double deltaX = c.getCenterX() - r.getCenterX();
        double deltaY = c.getCenterY() - r.getCenterY();

        // Separating axis theorem applied in x
        if(deltaX > c.getRadius() + r.getWidth() / 2)
            return;

        // Separating axis theorem applied in y
        if(deltaY > c.getRadius() + r.getHeight() / 2)
            return;

        // Compute relative velocities
        double relativeVelocityX = c.getVelocityX() - r.getVelocityX();
        double relativeVelocityY = c.getVelocityY() - r.getVelocityY();

        // Compute vector from the point of collision to the circle center
        double closestX = (Math.abs(deltaX) > r.getWidth() / 2) ? Math.copySign(r.getWidth() / 2, deltaX) : deltaX;
        double closestY = (Math.abs(deltaY) > r.getHeight() / 2) ? Math.copySign(r.getHeight() / 2, deltaY) : deltaY;

        // Compute normal vector between objects
        double normalX = deltaX - closestX;
        double normalY = deltaY - closestY;

        // Circle center is inside the rectangle
        if(deltaX == closestX && deltaY == closestY) {

            // TODO Circle center is inside rectangle

            // Approximated solution
            normalX *= -1;
            normalY *= -1;

        }

        // Compute normal distance
        double normalDistance = Math.sqrt(normalX * normalX + normalY * normalY);

        // Bodies have not yet collided
        if(normalDistance > c.getRadius())
            return;

        // Compute normal velocity
        double normalVelocity = (normalX * relativeVelocityX + normalY * relativeVelocityY) / normalDistance;

        // Bodies do not collide if moving away from one another
        if(normalVelocity > 0)
            return;

        // Geometric mean of the coefficients of restitution
        double bounce = Math.sqrt(c.getBounce() * r.getBounce());

        // Compute impulse
        double impulse = -(1 + bounce) * normalVelocity;
        impulse /= c.getInverseMass() + r.getInverseMass();

        // Apply impulse
        c.setVelocityX(c.getVelocityX() + c.getInverseMass() * impulse * normalX / normalDistance);
        c.setVelocityY(c.getVelocityY() + c.getInverseMass() * impulse * normalY / normalDistance);

        r.setVelocityX(r.getVelocityX() -  r.getInverseMass() * impulse * normalX / normalDistance);
        r.setVelocityY(r.getVelocityY() -  r.getInverseMass() * impulse * normalY / normalDistance);

    }

}
