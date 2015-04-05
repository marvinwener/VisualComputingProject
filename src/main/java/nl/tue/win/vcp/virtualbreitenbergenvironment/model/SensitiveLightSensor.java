package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import static java.lang.Math.*;
import java.util.List;
import static nl.tue.win.vcp.virtualbreitenbergenvironment.utility.MoreMath.*;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * Variation of the light sensor that uses a power of cosines instead of the dot
 * product (which is essentially a normal cosines.
 *
 * @author maikel
 */
public class SensitiveLightSensor extends LightSensor {

    final private int sensitivity;

    public SensitiveLightSensor(Vector position, List<LightSource> lights, int sensitivity) {
        super(position, lights);
        this.sensitivity = sensitivity;
    }

    @Override
    public float getValue(Vector location, Vector direction, Vector lightPosition, float angle) {
        // take the direction from the sensor to the light
        // the dot product between these gives an indication of the extent to
        // which these point in the same direction
        // we only consider positive values, since the sensor will not pick up
        // anything from the reverse direction
        // we may need to use a lower bound
        final Vector sensorAbsolute = location.plus(Vector.rotate(sensorPosition, Vector.O, angle));
        final Vector sensorDirection = lightPosition.minus(sensorAbsolute);
        final double dot = Vector.dot(sensorDirection.normalized(), direction.normalized());
        final double val = powercos(acos(dot), sensitivity);
        return (val > THRESHOLD) ? (float) val * SCALE : 0;
    }

}
