package unitTests;

import static org.junit.Assert.*;
import javafx.scene.paint.Color;
import java.awt.geom.Rectangle2D;

import org.junit.Test;


import game.PhysicsObject;
import game.Vector2D;

// Evin Killian
public class TestPhysicsObjectKillian {

	@Test
	public void testObjectColor() {
		PhysicsObject wall = new PhysicsObject("FIELD", new Rectangle2D.Double(), new Vector2D(0, 0), new Vector2D(20, 30));
		Color color = wall.getColor();
		assertEquals(Color.BLACK, color);
	}
	
	@Test
	public void testObjectId() {
		PhysicsObject wall = new PhysicsObject("FIELD", new Rectangle2D.Double(), new Vector2D(0, 0), new Vector2D(20, 30));
		String Id = wall.getId();
		assertEquals("FIELD", Id);
	}
	
	

}
