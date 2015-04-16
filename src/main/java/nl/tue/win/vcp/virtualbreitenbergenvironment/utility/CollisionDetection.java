package nl.tue.win.vcp.virtualbreitenbergenvironment.utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Collidable;

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

    /**
     * Determines whether two rectangles overlap (collide).
     *
     * @param a the first rectangle
     * @param b the second rectangle
     * @return {@code a} and {@code b} overlap
     */
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

    /**
     * Determines whether the rectangles {@code a} and {@code b} overlap in
     * direction {@code direction}.
     *
     * This is essentially computing one column of the table and checking if the
     * ranges overlap.
     *
     * @param direction the direction
     * @param a corners of first rectangle
     * @param b corners of second rectangle
     * @return the ranges {@code aValues} and {@code bValues} overlap, where
     * xValues[i] == Vector.dot(direction, x[i])
     */
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

    /**
     * Check if the ranges in {@code a} overlap with {@code b}.
     *
     * @param a first range
     * @param b second range
     * @return {@code min(a) <= min(b) <= max(a) ||
     *                min(a) <= max(b) <= max(a)}
     */
    public static boolean overlap(double[] a, double[] b) {
        final double[] minMaxA = determineMinMax(a);
        final double[] minMaxB = determineMinMax(b);
        final double minA = minMaxA[0];
        final double minB = minMaxB[0];
        final double maxA = minMaxA[1];
        final double maxB = minMaxB[1];
        return (minA <= minB && minB <= maxA) || (minA <= maxB && maxB <= maxA);
    }
    
    /**
     * Determines the minimum and maximum value of an array.
     * 
     * @param a the array
     * @return an array r with r[0] = min(a) and r[1] = max(a)
     */
    public static double[] determineMinMax(double[] a) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (double v : a) {
            min = v < min ? v : min;
            max = v > max ? v : max;
        }
        return new double[] {min, max};
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
    
    /**
     * Determines which objects collide.
     * 
     * @param objects the input objects
     * @return a subset of {@code objects} that collide with some other object
     */
    public static Set<Collidable> getCollidingObjects(List<Collidable> objects) {
        for (Collidable object1 : objects) {
            for (Collidable object2 : objects) {
                if (object1 == object2) break;
                // TODO: compare
            }
        }
        return null; // TODO: return correct result
    }
}
