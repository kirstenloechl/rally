import java.lang.Math;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class PhysicsCircle extends Ellipse2D.Double {

    public double c;
    public double r;
    public double m;

    public double vel_x;
    public double vel_y;

    public PhysicsCircle(double x, double y, double r, double c, double m, double vel_x, double vel_y) {

        super(x, y, r * 2, r * 2);

        this.c = c;
        this.r = r;
        this.m = m;

        this.vel_x = vel_x;
        this.vel_y = vel_y;

    }

    public boolean handleCollision(PhysicsRectangle r) {

        double d_x = this.getCenterX() - r.getCenterX();
        double d_y = this.getCenterY() - r.getCenterY();

        double c_x = (Math.abs(d_x) > r.width / 2) ? Math.copySign(r.width / 2, d_x) : d_x;
        double c_y = (Math.abs(d_y) > r.height / 2) ? Math.copySign(r.height / 2, d_y) : d_y;

        if(d_x == c_x && d_y == c_y) {

            //TODO Circle center is inside rectangle

        }

        double n_x = d_x - c_x;
        double n_y = d_y - c_y;

        double n_d = Math.sqrt(n_x * n_x + n_y * n_y);

        // Bodies have not collided
        // TODO handle case where bodies moving too fast
        if(n_d > this.r)
            return false;

        double n_v = (n_x * this.vel_x + n_y * this.vel_y) / n_d;

        // Bodies moving away from one another
        if(n_v > 0)
            return false;

        this.vel_x -= (1 + this.c) * n_v * n_x / n_d;
        this.vel_y -= (1 + this.c) * n_v * n_y / n_d;
        this.vel_x *= this.m;
        this.vel_y *= this.m;


        return true;
    }
}
