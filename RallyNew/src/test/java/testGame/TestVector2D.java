package testGame;

import org.junit.Test;
import static org.junit.Assert.*;

import game.Vector2D;

// Christopher Scarborough
public class TestVector2D {

    @Test
    public void testVectorAddition() {

        Vector2D v = new Vector2D(11, 2);
        Vector2D u = new Vector2D(-1, 13);
        Vector2D w = u.add(v);

        assertEquals(10, w.getX(), 0.00001);
        assertEquals(15, w.getY(), 0.00001);

    }

    @Test
    public void testVectorSubtraction() {

        Vector2D v = new Vector2D(11, 2);
        Vector2D u = new Vector2D(-1, 13);
        Vector2D w = u.subtract(v);

        assertEquals(-12, w.getX(), 0.00001);
        assertEquals(11, w.getY(), 0.00001);

    }

    @Test
    public void testVectorSize() {

        Vector2D v = new Vector2D(3, 4);
        double s = v.size();

        assertEquals(5, s, 0.00001);

    }

    @Test
    public void testVectorUnit() {

        Vector2D v = new Vector2D(3, 4);
        Vector2D u = v.unit();

        assertEquals(3.0 / 5.0, u.getX(), 0.00001);
        assertEquals(4.0 / 5.0, u.getY(), 0.00001);
        assertEquals(1, u.size(), 0.00001);

    }

    @Test
    public void testVectorDotMin() {

        Vector2D v = new Vector2D(3, 4);
        Vector2D u = new Vector2D(4, -3);
        double d = v.dot(u);

        assertEquals(0, d, 0.00001);

    }

    @Test
    public void testVectorDotMax() {

        Vector2D v = new Vector2D(3, 4);
        Vector2D u = new Vector2D(3, 4);
        double d = v.dot(u);

        assertEquals(25, d, 0.00001);

    }

    @Test
    public void testVectorDotMid() {

        Vector2D v = new Vector2D(3, 4);
        Vector2D u = new Vector2D(3, 0);
        double d = v.dot(u);

        assertEquals(9, d, 0.00001);

    }

    @Test
    public void testVectorCrossMin() {

        Vector2D v = new Vector2D(3, 4);
        Vector2D u = new Vector2D(3, 4);
        double d = v.cross(u);

        assertEquals(0, d, 0.00001);

    }

    @Test
    public void testVectorCrossMax() {

        Vector2D v = new Vector2D(3, 4);
        Vector2D u = new Vector2D(4, -3);
        double d = v.cross(u);

        assertEquals(-25, d, 0.00001);

    }

    @Test
    public void testVectorCrossMid() {

        Vector2D v = new Vector2D(3, 4);
        Vector2D u = new Vector2D(3, 0);
        double d = v.cross(u);

        assertEquals( -12, d,  0.00001);

    }

}
