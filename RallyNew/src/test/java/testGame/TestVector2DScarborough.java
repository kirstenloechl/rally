package testGame;

import game.Vector2D;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestVector2DScarborough {

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
