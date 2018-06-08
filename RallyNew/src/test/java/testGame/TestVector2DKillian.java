package testGame;

import game.Vector2D;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestVector2DKillian {

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

        assertEquals(1, u.size(), 0.00001);

    }

}
