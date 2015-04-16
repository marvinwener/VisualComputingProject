package nl.tue.win.vcp.virtualbreitenbergenvironment.utility;

import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.CollisionDetection.Rectangle;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author maikel
 */
public class CollisionDetectionTest {

    public CollisionDetectionTest() {
    }

    @Test
    public void testCollision1() {
        Rectangle a = new Rectangle(new Vector(0.6, 0.8),
                new Vector(-2, 7), new Vector(-8, -1), new Vector(-4, -4), new Vector(2, 4));
        Rectangle b = new Rectangle(new Vector(-0.6, 0.8),
                new Vector(3, 0), new Vector(-1, -3), new Vector(5, -11), new Vector(9, -8));
        boolean expResult = false;
        boolean result = CollisionDetection.collision(a, b);
        assertEquals(expResult, result);
    }

}
