package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import java.util.List;
import javax.media.opengl.GL2;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * Light sensor implementation.
 *
 * @author maikel
 */
public class LightSensor extends Sensor {

    private final Vector sensorPosition; // relative sensor position
    private final List<LightSource> lights;

    public LightSensor(Vector position, List<LightSource> lights) {
        this.sensorPosition = position;
        this.lights = lights;
    }

    @Override
    public float getValue(Vector location, Vector direction) {
        // take the maximum of all light sources
        float result = 0;
        for (LightSource light : lights) {
            final float val = getValue(location, direction, light.getPosition());
            result = (val > result) ? val : result;
        }
        return result;
    }

    public float getValue(Vector location, Vector direction, Vector lightPosition) {
        // take the direction from the sensor to the light
        // the dot product between these gives an indication of the extent to
        // which these point in the same direction
        // we only consider positive values, since the sensor will not pick up
        // anything from the reverse direction
        // we may need to use a lower bound
        final Vector sensorAbsolute = location.plus(sensorPosition);
        final Vector sensorDirection = lightPosition.minus(sensorAbsolute);
        double dot = Vector.dot(sensorDirection.normalized(), direction.normalized());
        return (dot > 0.8) ? (float) dot * 0.1f : 0;
    }

    @Override
    public void draw(GL2 gl) {
    }

}
