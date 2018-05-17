public interface PhysicsObject {

    double getMass();
    double getInverseMass();
    void setMass(double m);

    double getBounce();
    void setBounce(double b);

    double getCenterX();
    double getCenterY();

    double getVelocityX();
    double getVelocityY();

    void setVelocityX(double velocityX);
    void setVelocityY(double velocityY);

    double getAccelerationX();
    double getAccelerationY();

    void setAccelerationX(double accelerationX);
    void setAccelerationY(double accelerationY);

    void performUpdate(double tick);

}
