package unitTests;

import game.Vector2D;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestVector2DGill {

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

}
