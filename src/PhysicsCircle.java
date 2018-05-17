import java.lang.Math;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;

public class PhysicsCircle extends Ellipse2D.Double implements PhysicsObject {

    protected double radius;

    protected double bounce;
    protected double mass;
    protected double inverseMass;

    protected double velocityX;
    protected double velocityY;

    protected double accelerationX;
    protected double accelerationY;

    public PhysicsCircle(double x, double y, double radius, double bounce, double mass, double velocityX, double velocityY, double accelerationX, double accelerationY) {

        super(x, y, radius * 2, radius * 2);

        setRadius(radius);

        setBounce(bounce);
        setMass(mass);
        setVelocityX(velocityX);
        setVelocityY(velocityY);
        setAccelerationX(accelerationX);
        setAccelerationY(accelerationY);

    }

    @Override
    public double getMass() {

        return this.mass;

    }

    @Override
    public double getInverseMass() {

        return this.inverseMass;

    }

    @Override
    public void setMass(double mass) {

        if(mass > 0) {
            this.mass = mass;
            this.inverseMass = 1.0 / mass;
        } else if(mass == 0) {
            this.mass = 0;
            this.inverseMass = 0;
        } else {
            throw new IllegalArgumentException("Mass must be greater or equal to zero.");
        }

    }

    @Override
    public double getBounce() {

        return this.bounce;

    }

    @Override
    public void setBounce(double bounce) {

        if(bounce >= 0) {
            this.bounce = bounce;
        } else {
            throw new IllegalArgumentException("Bounce must be greater or equal to zero.");
        }

    }

    @Override
    public double getVelocityX() {

        return velocityX;

    }

    @Override
    public double getVelocityY() {

        return velocityY;

    }

    @Override
    public void setVelocityX(double velocityX) {

        this.velocityX = velocityX;

    }

    @Override
    public void setVelocityY(double velocityY) {

        this.velocityY = velocityY;

    }

    @Override
    public double getAccelerationX() {

        return accelerationX;

    }

    @Override
    public double getAccelerationY() {

        return this.accelerationY;

    }

    @Override
    public void setAccelerationX(double accelerationX) {

        this.accelerationX = accelerationX;

    }

    @Override
    public void setAccelerationY(double accelerationY){

        this.accelerationY = accelerationY;

    }

    @Override
    public void performUpdate(double tick) {

        this.x += this.velocityX * tick;
        this.y += this.velocityY * tick;

        this.velocityX += this.accelerationX * tick;
        this.velocityY += this.accelerationY * tick;

    }

    public double getRadius() {

        return this.radius;

    }

    public void setRadius(double radius) {

        if(radius >= 0) {
            this.radius = radius;
        } else {
            throw new IllegalArgumentException("Radius must be greater or equal to zero.");
        }

    }

}
