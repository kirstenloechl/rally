package integrationTests;

import static org.junit.Assert.assertEquals;

import game.PhysicsObject;
import game.Vector2D;
import javafx.scene.paint.Color;

import java.awt.geom.Ellipse2D;

import org.junit.Test;

// Christopher Scarborough
public class TestPhysicsObjectScarborough {

    @Test
    public void testUpdateStationary() {

        PhysicsObject p = new PhysicsObject("TEST", new Ellipse2D.Double(), new Vector2D(30, 30), new Vector2D(0, 0), 100, Color.WHITE);
        p.update(1);

        assertEquals(0, p.getPosition().getY(), 0.00001);

    }

    @Test
    public void testUpdateMoving() {

        PhysicsObject p = new PhysicsObject("TEST", new Ellipse2D.Double(), new Vector2D(30, 30), new Vector2D(0, 100), 100, Color.WHITE);
        p.update(1);

        assertEquals(100, p.getPosition().getY(), 0.00001);

    }

}
