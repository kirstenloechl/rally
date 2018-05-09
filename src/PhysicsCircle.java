import java.lang.Math;


public class PhysicsCircle {

    public double dim_r;

    public double pos_x;
    public double pos_y;

    public double vel_x;
    public double vel_y;

    public double cor;
    public double muk;

    public PhysicsCircle(){

        this.dim_r = 0;

        this.pos_x = 0;
        this.pos_y = 0;

        this.vel_x = 0;
        this.vel_y = 0;

        this.cor = 1;
        this.muk = 1;

    }

    public PhysicsCircle(double dim_r, double pos_x, double pos_y, double vel_x, double vel_y, double cor, double muk){

        this.dim_r = dim_r;

        this.pos_x = pos_x;
        this.pos_y = pos_y;

        this.vel_x = vel_x;
        this.vel_y = vel_y;

        this.cor = cor;
        this.muk = muk;

    }

    public boolean handleCollision(PhysicsRectangle r) {

        double d_x = this.pos_x - r.pos_x;
        double d_y = this.pos_y - r.pos_y;

        double c_x = (Math.abs(d_x) > r.dim_x / 2) ? Math.copySign(r.dim_x / 2, d_x) : d_x;
        double c_y = (Math.abs(d_y) > r.dim_y / 2) ? Math.copySign(r.dim_y / 2, d_y) : d_y;

        if(d_x == c_x && d_y == c_y) {

            //TODO Circle center is inside rectangle

        }

        double n_x = d_x - c_x;
        double n_y = d_y - c_y;

        double n_d = Math.sqrt(n_x * n_x + n_y * n_y);

        // Bodies have not collided
        // TODO handle case where bodies moving too fast
        if(n_d > this.dim_r)
            return false;

        double n_v = (n_x * this.vel_x + n_y * this.vel_y) / n_d;

        // Bodies moving away from one another
        if(n_v > 0)
            return false;

        this.vel_x -= (1 + this.cor) * n_v * n_x / n_d;
        this.vel_y -= (1 + this.cor) * n_v * n_y / n_d;
        this.vel_x *= this.muk;
        this.vel_y *= this.muk;


        return true;
    }

    public void detectCollision(PhysicsRectangle r) {

    }

}
