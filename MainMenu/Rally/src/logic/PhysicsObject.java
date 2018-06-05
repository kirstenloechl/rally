package logic;

import javafx.scene.paint.Color;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class PhysicsObject {

    private Color color;

    private String id;
    private double mass;
    private double inverseMass;
    private double bounce;

    protected Vector2D radius;
    protected Vector2D position;
    protected Vector2D velocity;
    protected Vector2D acceleration;

    protected RectangularShape shape;

    public PhysicsObject(String id, RectangularShape shape, Vector2D radius, Vector2D position, double mass, Color color) {

        setMass(mass);
        setBounce(0.9);

        this.id = id;
        this.color = color;
        this.shape = shape;
        this.radius = radius;
        this.position = position;
        this.velocity = new Vector2D(0, 0);
        this.acceleration = new Vector2D(0, 0);

        this.shape.setFrameFromCenter(position, position.add(radius));

    }

    public PhysicsObject(String id, Rectangle2D shape, Vector2D cornerOne, Vector2D cornerTwo) {

        setMass(mass);
        setBounce(0.9);

        this.id = id;
        this.color = Color.BLACK;
        this.shape = shape;
        this.radius = cornerOne.subtract(cornerTwo).scale(0.5);
        this.position = cornerTwo.add(radius);
        this.velocity = new Vector2D(0, 0);
        this.acceleration = new Vector2D(0, 0);

        this.shape.setFrameFromCenter(position, position.add(radius));

    }

    public PhysicsObject(PhysicsObject o) {

        this.id = o.id;
        this.color = o.color;
        this.bounce = o.bounce;
        this.mass = o.mass;
        this.inverseMass = o.inverseMass;

        this.radius = o.radius;
        this.position = o.position;
        this.velocity = o.velocity;
        this.acceleration = o.acceleration;

        this.shape = o.shape;

    }

    public Color getColor() {

        return color;

    }

    public String getId() {

        return id;

    }

    public double getMass() {

        return mass;

    }

    public double getInverseMass() {

        return inverseMass;

    }

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

    public double getBounce() {

        return this.bounce;

    }

    public void setBounce(double bounce) {

        if(bounce >= 0) {
            this.bounce = bounce;
        } else {
            throw new IllegalArgumentException("Bounce must be greater or equal to zero.");
        }

    }

    public Vector2D getRadius() {

        return radius;

    }

    public Vector2D getPosition() {

        return position;

    }

    public void setPosition(double x, double y) {

        position.setLocation(x, y);

    }

    public void setPosition(Vector2D position) {

        this.position = position;

    }

    public Vector2D getVelocity() {

        return velocity;

    }

    public void setVelocity(double x, double y) {

        velocity.setLocation(x, y);

    }

    public void setVelocity(Vector2D velocity) {

        this.velocity = velocity;

    }

    public Vector2D getAcceleration() {

        return acceleration;

    }

    public void setAcceleration(double x, double y) {

        acceleration.setLocation(x, y);

    }

    public void setAcceleration(Vector2D acceleration) {

        this.acceleration = acceleration;

    }

    public RectangularShape getShape() {

        return this.shape;

    }

    public void update(double tick) {

        position = position.add(velocity.scale(tick));
        velocity = velocity.add(acceleration.scale(tick));

        shape.setFrameFromCenter(position, position.add(radius));

    }

}
