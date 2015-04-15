package nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors;

import java.util.Random;
import javax.media.opengl.GL2;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.abstractmodels.Sensor;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * Sensor that adds some "instability" (randomness) to an existing sensor.
 *
 * @author maikel
 */
public class UnstableSensor extends Sensor {
    
    final private Sensor sensor; // the sensor to add randomness to
    final private Random generator; // object that provides randomness
    final private static float SCALE = 0.001f; // extent of randomization
    private final float MIN = 0; // minimum value
    private final float MAX = 1; // maximum value

    public UnstableSensor(Sensor sensor) {
        this.sensor = sensor;
        this.generator = new Random();
    }
    
    @Override
    public float getValue(Vector location, float angle) {
        final float value = sensor.getValue(location, angle) + getRandomness() * SCALE;
        if (value > MAX) return MAX;
        if (value < MIN) return MIN;
        return value;
    }

    @Override
    public void draw(GL2 gl) {
        sensor.draw(gl);
    }

    protected float getRandomness() {
        return (generator.nextFloat() * 2) - 1;
    }
    
}
