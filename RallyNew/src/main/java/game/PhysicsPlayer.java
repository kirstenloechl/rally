package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PhysicsPlayer extends PhysicsObject implements KeyListener {

    private int left;
    private int right;

    private double minPosition;
    private double maxPosition;

    private double maxVelocity;

    private boolean heldLeft;
    private boolean heldRight;

    public PhysicsPlayer(PhysicsObject o, double minPosition, double maxPosition, double maxVelocity, int left, int right) {

        super(o);

        this.minPosition = minPosition;
        this.maxPosition = maxPosition;

        this.maxVelocity = maxVelocity;

        this.left = left;
        this.right = right;

        this.heldLeft = false;
        this.heldRight = false;
    }

    public void keyTyped(KeyEvent e) {

        // It is not necessary to track keyTyped events for this class.

    }

    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == this.left) {
            heldLeft = true;
        }

        if (e.getKeyCode() == this.right) {
            heldRight = true;
        }

        if(heldLeft && !heldRight) {
            setVelocity(-maxVelocity, 0);
        } else if(!heldLeft && heldRight) {
            setVelocity(maxVelocity, 0);
        } else {
            setVelocity(0, 0);
        }


    }

    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == this.left) {
            heldLeft = false;
        }

        if (e.getKeyCode() == this.right) {
            heldRight = false;
        }

        if(heldLeft && !heldRight) {
            setVelocity(-maxVelocity, 0);
        } else if(!heldLeft && heldRight) {
            setVelocity(maxVelocity, 0);
        } else {
            setVelocity(0, 0);
        }

    }

    @Override
    public void update(double tick) {

        if(position.getX() - radius.getX() < minPosition) {
            setVelocity(0, velocity.getY());
            setPosition(minPosition + radius.getX(), position.getY());
        }

        if(position.getX() + radius.getX() > maxPosition) {
            setVelocity(0, velocity.getY());
            setPosition(maxPosition - radius.getX(), position.getY());
        }

        position = position.add(velocity.scale(tick));
        velocity = velocity.add(acceleration.scale(tick));

        shape.setFrameFromCenter(position, position.add(radius));

    }

}