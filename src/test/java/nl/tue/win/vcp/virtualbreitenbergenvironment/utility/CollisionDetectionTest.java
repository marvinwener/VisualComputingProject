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

    private final static Rectangle A = new Rectangle(new Vector(0.6, 0.8),
            new Vector(-2, 7), new Vector(-8, -1), new Vector(-4, -4), new Vector(2, 4));
    private final static Rectangle B = new Rectangle(new Vector(-0.6, 0.8),
            new Vector(3, 0), new Vector(-1, -3), new Vector(5, -11), new Vector(9, -8));
    private final static Rectangle C = new Rectangle(new Vector(-1, 1),
            new Vector(2, -2), new Vector(-2, 2), new Vector(0, 4), new Vector(4, 0));

    private final static float EPS = 0.0001f;

    @Test
    public void testCollision1() {
        final Rectangle a = A;
        final Rectangle b = B;
        final boolean expResult = false;
        final boolean result = CollisionDetection.collision(a, b);
        assertEquals(expResult, result);
    }

    @Test
    public void testCollision2() {
        final Rectangle a = A;
        final Rectangle b = C;
        final boolean expResult = true;
        final boolean result = CollisionDetection.collision(a, b);
        assertEquals(expResult, result);
    }

    @Test
    public void testDetermineDirection() {
        final Rectangle a = new Rectangle(null, A.corners);
        final Vector expResult = A.direction;
        final Vector result = a.direction;
        assertTrue(expResult.normalized().equals(result.normalized()) || expResult.normalized().plus(result.normalized()).length() < EPS);
    }

    @Test
    public void testDetermineDirection2() {
        final Rectangle a = new Rectangle(null, B.corners);
        final Vector expResult = B.direction;
        final Vector result = a.direction;
        assertTrue(expResult.normalized().equals(result.normalized()) || expResult.normalized().plus(result.normalized()).length() < EPS);
    }

    @Test
    public void testDetermineDirection3() {
        final Rectangle a = new Rectangle(null, C.corners);
        final Vector expResult = C.direction;
        final Vector result = a.direction;
        assertTrue(expResult.normalized().equals(result.normalized()) || expResult.normalized().plus(result.normalized()).length() < EPS);
    }
}
