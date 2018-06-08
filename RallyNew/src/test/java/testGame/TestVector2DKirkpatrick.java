package testGame;

import game.Vector2D;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestVector2DKirkpatrick {

    @Test
    public void testVectorSubtractionX() {

        Vector2D v = new Vector2D(11, 2);
        Vector2D u = new Vector2D(-1, 13);
        Vector2D w = u.subtract(v);

        assertEquals(-12, w.getX(), 0.00001);

    }


    @Test
    public void testVectorSubtractionY() {

        Vector2D v = new Vector2D(11, 2);
        Vector2D u = new Vector2D(-1, 13);
        Vector2D w = u.subtract(v);

        assertEquals(11, w.getY(), 0.00001);

    }

}
