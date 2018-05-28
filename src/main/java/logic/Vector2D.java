package logic;

import java.awt.geom.Point2D;

public class Vector2D extends Point2D implements Cloneable {

    private double x;
    private double y;

    public Vector2D() {
        // Initializes this vector as the zero vector.

        this.x = 0;
        this.y = 0;

    }

    public Vector2D(double x, double y) {
        // Initializes this vector with the provided dimensions.

        this.x = x;
        this.y = y;

    }


    public double getX() {
        // Returns the X coordinate of this vector.

        return this.x;

    }

    public double getY() {
        // Returns the Y coordinate of this vector.

        return this.y;

    }


    public void setLocation(double x, double y) {
        // Sets the dimensions of this vector.

        this.x = x;
        this.y = y;

    }

    public String toString() {
        // Returns a string representation of this vector.

        return String.format("(%f, %f)", x, y);

    }

    @Override
    public boolean equals(Object o) {
        // Tests whether this vector equals a given object.

        if(! (o instanceof Vector2D))
            return false;

        Vector2D v = (Vector2D) o;

        if(v.x == this.x && v.y == this.y)
            return true;

        return false;

    }

    public double dot(Vector2D v) {
        // Returns the dot product of this vector and a given vector.

        return this.x * v.x + this.y * v.y;

    }

    public double cross(Vector2D v) {
        // Returns the cross product of this vector and a given vector.

        return this.x * v.y - this.y * v.x;

    }

    public double size() {
        // Returns the size of this vector.

        return Math.sqrt(this.dot(this));

    }

    public Vector2D unit() {
        // Returns a unit vector in the direction of this vector.

        double s = this.size();

        return new Vector2D(this.x / s, this.y / s);

    }

    public Vector2D scale(double factor) {
        // Returns a vector equal to this vector scaled by a given factor.

        return new Vector2D(this.x * factor,  this.y * factor);

    }

    public Vector2D add(Vector2D v) {
        // Returns a new vector equal to the sum of this vector and a given vector.

        return new Vector2D(x + v.x, y + v.y);

    }

    public Vector2D subtract(Vector2D v) {
        // Returns a new vector equal to the difference of this vector and a given vector.

        return new Vector2D(x - v.x, y - v.y);

    }

    public Vector2D clone() {
        // Returns a clone of this vector.

        return new Vector2D(x, y);
    }

}
