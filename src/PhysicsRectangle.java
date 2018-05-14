import java.lang.Math;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class PhysicsRectangle extends Rectangle2D.Double {

    public double c;

    public double vel_x;
    public double vel_y;

    public PhysicsRectangle(double x, double y, double w, double h, double c) {

        super(x, y, w, h);

        this.c = c;

        this.vel_x = 0;
        this.vel_y = 0;

    }

    public PhysicsRectangle(double x, double y, double w, double h, double c, double vel_x, double vel_y) {

        super(x, y, w, h);

        this.c = c;

        this.vel_x = vel_x;
        this.vel_y = vel_y;

    }

}
