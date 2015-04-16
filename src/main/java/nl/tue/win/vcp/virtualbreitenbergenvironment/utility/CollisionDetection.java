package nl.tue.win.vcp.virtualbreitenbergenvironment.utility;

/**
 * Provides methods that determines whether two 2D rectangles collide
 * (intersect).
 *
 * Based on http://www.euclideanspace.com/threed/games/examples/cars/collisions/
 *
 * @author maikel
 */
public class CollisionDetection {

    public static class Rectangle {

        public final Vector[] corners;
        public final Vector direction;

        public Rectangle(Vector direction, Vector... corners) {
            assert corners.length == 4;
            this.corners = corners;
            this.direction = direction;
        }
    }

    public static boolean collision(Rectangle a, Rectangle b) {
        Vector[] directions = new Vector[]{
            a.direction,
            rotated(a.direction),
            b.direction,
            rotated(b.direction)
        };
        // report a collision if we find a collisions for all of these directions
        boolean result = true;
        for (Vector direction : directions) {
            result &= collision(direction, a.corners, b.corners);
        }
        return result;
    }

    public static boolean collision(Vector direction, Vector[] a, Vector[] b) {
        return true; // TODO: implement
    }

    /**
     * Rotates a 2D vector by 90 degrees.
     *
     * This is a convenience method that is faster and more precise than the
     * more general {@link Vector.rotate}.
     *
     * @param v the vector to be rotated
     * @return the rotated vector
     */
    public static Vector rotated(Vector v) {
        return new Vector(-v.y(), v.x());
    }
}
