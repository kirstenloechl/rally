public class PhysicsRectangle {

    double dim_x;
    double dim_y;

    public double pos_x;
    public double pos_y;


    public PhysicsRectangle() {

        this.dim_x = 0;
        this.dim_y = 0;
        this.pos_x = 0;
        this.pos_y = 0;

    }

    public PhysicsRectangle(double dim_x, double dim_y, double pos_x, double pos_y) {

        this.dim_x = dim_x;
        this.dim_y = dim_y;
        this.pos_x = pos_x;
        this.pos_y = pos_y;

    }


}
