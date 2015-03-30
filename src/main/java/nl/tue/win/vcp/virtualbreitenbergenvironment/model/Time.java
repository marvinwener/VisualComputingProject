package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

/**
 * Class that provides a running time for animation use.
 *
 * @author maikel
 */
public class Time {

    private final static long START = System.currentTimeMillis();

    public static float getTime() {
        return (float) (System.currentTimeMillis() - START) / 1000f;
    }
}
