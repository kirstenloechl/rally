package integrationTests;

import game.PhysicsObject;
import game.Vector2D;
import javafx.scene.paint.Color;
import org.junit.Test;

import java.awt.geom.Ellipse2D;

import static org.junit.Assert.assertEquals;

public class TestPhysicsObjectKillian {

    @Test
    public void testSetAccelerationX() {

        PhysicsObject p = new PhysicsObject("TEST", new Ellipse2D.Double(), new Vector2D(30, 30), new Vector2D(0, 0), 100, Color.WHITE);
        p.update(1);
        p.setAcceleration(1, 2);

        assertEquals(1, p.getAcceleration().getX(), 0.00001);

    }

    @Test
    public void testSetAccelerationY() {

        PhysicsObject p = new PhysicsObject("TEST", new Ellipse2D.Double(), new Vector2D(30, 30), new Vector2D(0, 100), 100, Color.WHITE);
        p.update(1);
        p.setAcceleration(1, 2);

        assertEquals(2, p.getAcceleration().getY(), 0.00001);

    }

}
