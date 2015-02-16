package nl.tue.win.vcp.virtualbreitenbergenvironment.utility;

import static java.lang.Math.*;

/**
 * Class representing a point or vector.
 *
 * @author maikel
 */
public class Vector {

    private double[] coordinates;

    /**
     * Initializes a vector with the given coordinates.
     *
     * @param coordinates the coordinates
     */
    public Vector(double... coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Returns the x coordinate.
     *
     * @return the x coordinate.
     */
    public double x() {
        return coordinates[0];
    }

    /**
     * Returns the y coordinate.
     *
     * @return the y coordinate.
     */
    public double y() {
        return coordinates[1];
    }

    /**
     * Returns the z coordinate.
     *
     * @return the z coordinate.
     */
    public double z() {
        return coordinates[2];
    }

    /**
     * Returns a coordinate of this vector.
     *
     * @param index the index of the coordinate
     * @return the coordinate
     */
    public double getCoordinate(int index) {
        return coordinates[index];
    }

    /**
     * Returns the Euclidean length of the vector.
     *
     * @return the length
     */
    public double length() {
        double sum = 0;
        for (double c : coordinates) {
            sum += c * c;
        }
        return sqrt(sum);
    }

    /**
     * Normalizes a vector.
     *
     * @return vector with same direction and length 1
     */
    public Vector normalized() {
        final double length = this.length();

        double[] normalized = new double[coordinates.length];

        for (int i = 0; i < normalized.length; i++) {
            normalized[i] = coordinates[i] / length;
        }

        return new Vector(normalized);
    }

    /**
     * Calculates the dot product.
     *
     * @param that other vector
     * @return the dot product
     */
    public double dot(Vector that) {
        double result = 0;
        for (int i = 0; i < this.coordinates.length; i++) {
            result += this.getCoordinate(i) * that.getCoordinate(i);
        }
        return result;
    }

    /**
     * Calculates the dot product
     *
     * @param v1 first vector
     * @param v2 second vector
     * @return the dot product
     */
    public static double dot(Vector v1, Vector v2) {
        return v1.dot(v2);
    }

    /**
     * Computes the cross product.
     *
     * @param that the other vector
     * @return the cross product
     */
    public Vector cross(Vector that) {
        assert this.coordinates.length == 3 :
                "Cross product is only defined for 3D vectors";

        return new Vector(this.y() * that.z() - this.z() * that.y(),
                this.z() * that.x() - this.x() * that.z(),
                this.x() * that.y() - this.y() * that.x());
    }

    /**
     * Computes the cross product.
     *
     * @param v1 first vector
     * @param v2 second vector
     * @return the cross product
     */
    public Vector cross(Vector v1, Vector v2) {
        return v1.cross(v2);
    }

    /**
     * Multiply with the given scalar.
     *
     * @param scalar the scalar
     * @return the scaled vector
     */
    public Vector scale(double scalar) {
        double[] scaled = new double[this.coordinates.length];

        for (int i = 0; i < this.coordinates.length; i++) {
            scaled[i] = this.coordinates[i] * scalar;
        }

        return new Vector(scaled);
    }

    /**
     * Scales a vector.
     *
     * @param v the vector
     * @param scalar the scalar to multiply with
     * @return the scaled vector
     */
    public static Vector scaled(Vector v, double scalar) {
        return v.scale(scalar);
    }

    public Vector sum(Vector... vs) {
        double[] result = new double[this.coordinates.length];

        for (Vector v : vs) {
            for (int i = 0; i < result.length; i++) {
                result[i] += v.getCoordinate(i);
            }
        }
        
        return new Vector(result);
    }

    public Vector add(Vector that) {
        return sum(this, that);
    }

}
