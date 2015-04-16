package nl.tue.win.vcp.virtualbreitenbergenvironment.utility;

/**
 * Class that stores a directional vector and four corners of a rectangle.
 *
 * @author maikel
 */
public class Rectangle {

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
