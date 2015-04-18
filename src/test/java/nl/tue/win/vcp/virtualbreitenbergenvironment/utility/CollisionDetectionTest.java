package nl.tue.win.vcp.virtualbreitenbergenvironment.utility;

import java.util.HashSet;
import java.util.Set;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Collidable;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Rectangle.RectangleHolder;
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
    /*private final static Rectangle C = new Rectangle(new Vector(-1, 1),
     new Vector(2, -2), new Vector(-2, 2), new Vector(0, 4), new Vector(4, 0));*/
    private final static Rectangle C = new Rectangle(new Vector(-1, 1),
            new Vector(-6, 2), new Vector(-10, 6), new Vector(-8, 8), new Vector(-4, 4));
    private final static Rectangle D = new Rectangle(new Vector(0.7403435452793032, 0.6722287073335028, 0.0), new Vector(2.1250197833994022, 7.416767234773927, 0.5), new Vector(1.4527910760658995, 8.15711078005323, 0.5), new Vector(0.7124475307865963, 7.484882072719728, 0.5), new Vector(1.3846762381200992, 6.744538527440424, 0.5));
    private final static Rectangle E = new Rectangle(new Vector(0.10155671575798253, -0.9948297510048905, 0.0), new Vector(-7.804690103124905, 0.5144584993287216, 0.5), new Vector(-6.809860352120015, 0.6160152150867042, 0.5), new Vector(-6.9114170678779985, 1.6108449660915947, 0.5), new Vector(-7.906246818882888, 1.509288250333612, 0.5));

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
        final Rectangle c = C;
        final boolean expResult = true;
        final boolean result = CollisionDetection.collision(a, c);
        assertEquals(expResult, result);
    }

    @Test
    public void testCollision3() {
        final Rectangle b = B;
        final Rectangle c = C;
        final boolean expResult = false;
        final boolean result = CollisionDetection.collision(b, c);
        assertEquals(expResult, result);
    }

    @Test
    public void testCollision4() {
        final Rectangle a = A;
        final boolean expResult = true;
        final boolean result = CollisionDetection.collision(a, a);
        assertEquals(expResult, result);
    }

    @Test
    public void testCollision5() {
        final Rectangle d = D;
        final Rectangle e = E;
        final boolean expResult = false;
        final boolean result = CollisionDetection.collision(d, e);
        assertEquals(expResult, result);
    }

    @Test
    public void testDetermineDirection1() {
        final Rectangle a = new Rectangle(null, A.corners);
        final Vector expResult = A.direction;
        final Vector result = a.direction;
        assertTrue(expResult.normalized().equals(result.normalized()) || expResult.normalized().plus(result.normalized()).length() < EPS);
    }

    @Test
    public void testDetermineDirection2() {
        final Rectangle b = new Rectangle(null, B.corners);
        final Vector expResult = B.direction;
        final Vector result = b.direction;
        assertTrue(expResult.normalized().equals(result.normalized()) || expResult.normalized().plus(result.normalized()).length() < EPS);
    }

    @Test
    public void testDetermineDirection3() {
        final Rectangle c = new Rectangle(null, C.corners);
        final Vector expResult = C.direction;
        final Vector result = c.direction;
        assertTrue(expResult.normalized().equals(result.normalized()) || expResult.normalized().plus(result.normalized()).length() < EPS);
    }

    @Test
    public void testCollidingObjects1() {
        final Collidable a = new RectangleHolder(A, "A");
        final Collidable b = new RectangleHolder(B, "B");
        final Set<Collidable> expResult = new HashSet<>();
        final Set<Collidable> result = CollisionDetection.getCollidingObjects(a, b);
        assertEquals(expResult, result);
    }

    @Test
    public void testCollidingObjects2() {
        final Collidable a = new RectangleHolder(A, "A");
        final Collidable c = new RectangleHolder(C, "C");
        final Set<Collidable> expResult = new HashSet<>();
        expResult.add(a);
        expResult.add(c);
        final Set<Collidable> result = CollisionDetection.getCollidingObjects(a, c);
        assertEquals(expResult, result);
    }

    @Test
    public void testCollidingObjects3() {
        final Collidable a = new RectangleHolder(A, "A");
        final Collidable b = new RectangleHolder(B, "B");
        final Collidable c = new RectangleHolder(C, "C");
        final Set<Collidable> expResult = new HashSet<>();
        expResult.add(a);
        expResult.add(c);
        final Set<Collidable> result = CollisionDetection.getCollidingObjects(a, b, c);
        assertEquals(expResult, result);
    }
}
