package nl.tue.win.vcp.virtualbreitenbergenvironment.utility;

import java.util.Arrays;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Collidable;

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

    @Override
    public String toString() {
        return "Rectangle{" + "corners=" + Arrays.toString(corners) + ", direction=" + direction + '}';
    }

    /**
     * Mock object for {@link Collidable}.
     */
    public static class RectangleHolder implements Collidable {

        final private Rectangle rectangle;
        final private String name;

        public RectangleHolder(Rectangle rectangle) {
            this.rectangle = rectangle;
            this.name = "RectangleHolder{" + "rectangle=" + rectangle + '}';
        }

        public RectangleHolder(Rectangle rectangle, String name) {
            this.rectangle = rectangle;
            this.name = name;
        }

        @Override
        public Rectangle getBoundingBox() {
            return rectangle;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
