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

    /**
     * Class that stores a directional vector and four corners of a rectangle.
     */
    public static class Rectangle {

        public final Vector[] corners;
        public final Vector direction;

        public Rectangle(Vector direction, Vector... corners) {
            assert corners.length == 4;
            this.corners = corners;
            this.direction = direction != null
                    ? direction : computeDirection(corners);
        }
        
        public final static Vector computeDirection(Vector... corners) {
            // assumption: corners are given in rotating order
            final Vector side1 = corners[0].minus(corners[1]);
            final Vector side2 = corners[1].minus(corners[2]);
            final Vector longestSide = side1.length() > side2.length()
                    ? side1 : side2;
            return longestSide.normalized();
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
        assert a.length == 4 && b.length == 4;
        double[] aValues = new double[a.length];
        double[] bValues = new double[b.length];
        for (int i = 0; i < aValues.length; i++) {
            aValues[i] = Vector.dot(direction, a[i]);
            bValues[i] = Vector.dot(direction, b[i]);
        }
        return overlap(aValues, bValues);
    }

    public static boolean overlap(double[] a, double[] b) {
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
