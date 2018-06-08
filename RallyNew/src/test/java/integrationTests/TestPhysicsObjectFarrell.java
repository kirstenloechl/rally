package integrationTests;

import game.PhysicsObject;
import game.Vector2D;
import javafx.scene.paint.Color;
import org.junit.Test;

import java.awt.geom.Ellipse2D;

import static org.junit.Assert.assertEquals;

public class TestPhysicsObjectFarrell {

    @Test
    public void testUpdateMovingAndAccelerating() {

        PhysicsObject p = new PhysicsObject("TEST", new Ellipse2D.Double(), new Vector2D(30, 30), new Vector2D(0, 0), 100, Color.WHITE);
        p.setVelocity(1, 1);
        p.setAcceleration(1, 1);
        p.update(1);

        assertEquals(1, p.getPosition().getY(), 0.00001);

    }

    @Test
    public void testUpdateMovingAndDecelerating() {

        PhysicsObject p = new PhysicsObject("TEST", new Ellipse2D.Double(), new Vector2D(30, 30), new Vector2D(0, 0), 100, Color.WHITE);
        p.setVelocity(1, 1);
        p.setAcceleration(-1, -1);
        p.update(1);

        assertEquals(1, p.getPosition().getY(), 0.00001);

    }

}
