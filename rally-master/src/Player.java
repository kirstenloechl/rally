

public class Player extends PhysicsRectangle {
	
	public int speed;
	
	public Player(double x, double y, double w, double h, double c, int speed) {
		
		super(x, y, w, h, c);
		
		this.speed = speed;
	}

}
