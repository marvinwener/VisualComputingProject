package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import java.util.Random;
import javax.media.opengl.GL2;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * This sensor is used to implement the random movement that a Vehicle follows
 * whenever no sensor input is received.
 * 
 * @author maikel
 */
public class RandomSensor extends Sensor {
    private final Random generator = new Random();
    private final float MIN = 0; // minimum value
    private final float MAX = 1; // maximum value
    private final float SCALE = 0.01f; // measure for how quickly value changes
    private float value = 0; // the value

    @Override
    public float getValue(Vector location, Vector direction) {
        float change = (generator.nextFloat() - 0.5f) * SCALE * 2;
        value += change;
        if (value > MAX) value = MAX;
        if (value < MIN) value = MIN;
        return value;
    }

    @Override
    public void draw(GL2 gl) {
    }

}
