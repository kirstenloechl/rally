import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PhysicsPlayer extends PhysicsRectangle implements KeyListener{

    private int left;
    private int right;
    public double speed;

    public PhysicsPlayer(double x, double y, double w, double h, double bounce, double mass, double velocityX, double velocityY, double accelerationX, double accelerationY, int left, int right, double speed) {

        super(x, y, w, h, bounce, mass, velocityX, velocityY, accelerationX, accelerationY);

        this.left = left;
        this.right = right;
        this.speed = speed;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == this.left) {
            setVelocityX(-speed);
            setAccelerationX(0);
        }

        if (e.getKeyCode() == this.right) {
            setVelocityX(speed);
            setAccelerationX(0);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == this.left) {
            setVelocityX(0);
        }

        if (e.getKeyCode() == this.right) {
            setVelocityX(0);
        }

    }

}
