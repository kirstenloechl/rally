package login_test;

import java.lang.Math;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class PhysicsRectangle extends Rectangle2D.Double {

    public double c;

    public PhysicsRectangle(double x, double y, double w, double h, double c) {

        super(x, y, w, h);

        this.c = c;

    }

}
