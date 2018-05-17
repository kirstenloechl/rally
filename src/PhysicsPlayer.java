import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PhysicsPlayer extends PhysicsRectangle implements KeyListener {

    private int left;
    private int right;
    private double maxVelocity;

    private boolean held;

    public PhysicsPlayer(double x, double y, double w, double h, double bounce, double mass, double velocityX, double velocityY, double accelerationX, double accelerationY, int left, int right, double maxVelocity) {

        super(x, y, w, h, bounce, mass, velocityX, velocityY, accelerationX, accelerationY);

        this.left = left;
        this.right = right;
        this.maxVelocity = maxVelocity;

        this.held = false;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == this.left) {
            this.held = true;
            setVelocityX(-maxVelocity);
        }

        if (e.getKeyCode() == this.right) {
            this.held = true;
            setVelocityX(maxVelocity);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == this.left) {
            this.held = false;
            setVelocityX(0);
        }

        if (e.getKeyCode() == this.right) {
            this.held = false;
            setVelocityX(0);
        }

    }
}