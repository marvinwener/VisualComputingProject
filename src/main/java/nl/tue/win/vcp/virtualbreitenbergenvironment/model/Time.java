package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

/**
 * Class that provides a running time for animation use.
 *
 * @author maikel
 */
public class Time {

    private static Time instance;
    private final long START;

    private Time() {
        START = System.currentTimeMillis();
    }

    private float time() {
        return (float) (System.currentTimeMillis() - START) / 1000f;
    }

    public static float getTime() {
        if (instance == null) {
            instance = new Time();
        }
        return instance.time();
    }
    
}
