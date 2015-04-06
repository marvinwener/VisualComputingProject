package nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors;

import nl.tue.win.vcp.virtualbreitenbergenvironment.model.abstractmodels.Sensor;
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
    private final float MAX = 0.5f; // maximum value
    private final float SCALE = 0.01f; // measure for how quickly value changes
    private float value = 0; // the value

    @Override
    public float getValue(Vector location, float angle) {
        float change = (generator.nextFloat() - 0.45f) * SCALE * 2;
        value += change;
        if (value > MAX) value = MAX;
        if (value < MIN) value = MIN;
        return value;
    }

    @Override
    public void draw(GL2 gl) {
    }

    public void reset() {
        this.value = 0;
    }

}
