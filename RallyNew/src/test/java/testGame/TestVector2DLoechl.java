package testGame;

import game.Vector2D;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestVector2DLoechl {

    @Test
    public void testVectorAdditionX() {

        Vector2D v = new Vector2D(11, 2);
        Vector2D u = new Vector2D(-1, 13);
        Vector2D w = u.add(v);

        assertEquals(10, w.getX(), 0.00001);

    }

    @Test
    public void testVectorAdditionY() {

        Vector2D v = new Vector2D(11, 2);
        Vector2D u = new Vector2D(-1, 13);
        Vector2D w = u.add(v);

        assertEquals(15, w.getY(), 0.00001);

    }

}
