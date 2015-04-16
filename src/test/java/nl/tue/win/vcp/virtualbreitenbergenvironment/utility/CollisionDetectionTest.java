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
        final Rectangle a = new Rectangle(new Vector(0.6, 0.8),
                new Vector(-2, 7), new Vector(-8, -1), new Vector(-4, -4), new Vector(2, 4));
        final Rectangle b = new Rectangle(new Vector(-0.6, 0.8),
                new Vector(3, 0), new Vector(-1, -3), new Vector(5, -11), new Vector(9, -8));
        final boolean expResult = false;
        final boolean result = CollisionDetection.collision(a, b);
        assertEquals(expResult, result);
    }

    @Test
    public void testCollision2() {
        final Rectangle a = new Rectangle(new Vector(0.6, 0.8),
                new Vector(-2, 7), new Vector(-8, -1), new Vector(-4, -4), new Vector(2, 4));
        final Rectangle b = new Rectangle(null,
                new Vector(2, -2), new Vector(-2, 2), new Vector(0, 4), new Vector(4, 0));
        final boolean expResult = true;
        final boolean result = CollisionDetection.collision(a, b);
        assertEquals(expResult, result);
    }

    @Test
    public void testDetermineDirection() {
        final Rectangle a = new Rectangle(null,
                new Vector(-2, 7), new Vector(-8, -1), new Vector(-4, -4), new Vector(2, 4));
        final Vector expResult = new Vector(0.6, 0.8);
        final Vector result = a.direction;
        assertTrue(expResult.equals(result) || expResult.normalized().plus(result.normalized()).length() == 0);
    }

    @Test
    public void testDetermineDirection2() {
        final Rectangle a = new Rectangle(null,
                new Vector(3, 0), new Vector(-1, -3), new Vector(5, -11), new Vector(9, -8));
        final Vector expResult = new Vector(-0.6, 0.8);
        final Vector result = a.direction;
        assertTrue(expResult.equals(result) || expResult.normalized().plus(result.normalized()).length() == 0);
    }

}
