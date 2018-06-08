package testGame;

import game.Vector2D;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestVector2DFarrell {

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

}
