package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

/**
 * Class that provides a running time for animation use.
 *
 * @author maikel
 */
public class Time {

    private static long START = System.currentTimeMillis();
    private static long START_PAUSE = -1;

    public static float getTime() {
        if (isPaused()) {
            return (float) (START_PAUSE - START) / 1000f;
        }
        return (float) (System.currentTimeMillis() - START) / 1000f;
    }

    public static void pause() {
        if (!isPaused()) {
            START_PAUSE = System.currentTimeMillis();
        }
    }

    public static void play() {
        if (isPaused()) {
            final long diff = System.currentTimeMillis() - START_PAUSE;
            START += diff;
            START_PAUSE = -1;
        }
    }

    public static boolean isPaused() {
        return START_PAUSE != -1;
    }
}
