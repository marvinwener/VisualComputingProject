package nl.tue.win.vcp.virtualbreitenbergenvironment.utility;

import static java.lang.Math.cos;

/**
 * Math functions that are not provided by Java.
 *
 * @author maikel
 */
public class MoreMath {

    /**
     * Computes cos^n(a).
     *
     * @param a value
     * @param n power
     * @return cos^n(a)
     */
    public static double powercos(double a, int n) {
        return (n > 0) ? cos(powercos(a, n - 1)) : a;
    }
}
