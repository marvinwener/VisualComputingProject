package nl.tue.win.vcp.virtualbreitenbergenvironment.utility;

import static java.lang.Math.sin;
import static java.lang.Math.cos;

/**
 * Class representing a matrix.
 *
 * @author maikel
 */
public class Matrix {

    double[][] numbers; // stores the numbers in the matrix

    /**
     * Construct a matrix.
     *
     * @param numbers numbers in the matrix
     */
    public Matrix(double[][] numbers) {
        this.numbers = numbers;
    }

    /**
     * Returns the result of the matrix-vector multiplication of {@code this}
     * and {@code v}.
     *
     * @param v the vector
     * @return the result of the multiplication
     */
    public Vector times(Vector v) {
        Vector h1 = new Vector(numbers[0][0], numbers[0][1], numbers[0][2]);
        double x = v.dot(h1); // dot product of first row and vector v

        Vector h2 = new Vector(numbers[1][0], numbers[1][1], numbers[1][2]);
        double y = v.dot(h2); // dot product of second row and vector v

        Vector h3 = new Vector(numbers[2][0], numbers[2][1], numbers[2][2]);
        double z = v.dot(h3); // dot product of third row and vector v

        return new Vector(x, y, z);
    }

    /**
     * Calculates the transposed matrix, that is, a matrix with the indices
     * reversed.
     *
     * @return the transposed matrix
     */
    public Matrix transposed() {
        double[][] result = new double[numbers.length][numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                result[j][i] = numbers[i][j];
            }
        }
        return new Matrix(result);
    }

    /**
     * Returns a rotation matrix around the Z axis over {@code angle}.
     *
     * @param angle the rotation angle in radians
     * @return the rotation matrix
     */
    public static Matrix rotationMatrix(float angle) {
        final double[][] contents = {
            {cos(angle), -sin(angle), 0},
            {sin(angle), cos(angle), 0},
            {0, 0, 1}
        };
        return new Matrix(contents);
    }

    /**
     * Returns a translation matrix that translates in XY plane.
     * 
     * @param translation translation vector (2D)
     * @return the translation matrix
     */
    public static Matrix translationMatrix(Vector translation) {
        final double[][] contents = {
            {1, 0, translation.x()},
            {0, 1, translation.y()},
            {0, 0, 1}
        };
        return new Matrix(contents);
    }
}
