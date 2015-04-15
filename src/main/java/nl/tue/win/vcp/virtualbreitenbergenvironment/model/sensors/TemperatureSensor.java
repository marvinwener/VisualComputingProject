package nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors;

import java.util.List;
import javax.media.opengl.GL2;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.HeatSource;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.abstractmodels.Sensor;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * Temperature sensor implementation.
 *
 * @author maikel
 */
public class TemperatureSensor extends Sensor {
    protected final Vector sensorPosition; // relative sensor position
    protected final List<HeatSource> heatSources;
    public final static float DISTANCE_LIMIT = 5f;
    protected static final float SCALE = 0.1f;

    public TemperatureSensor(Vector sensorPosition, List<HeatSource> heatSources) {
        this.sensorPosition = sensorPosition;
        this.heatSources = heatSources;
    }
    
    @Override
    public float getValue(Vector location, float angle) {
        float result = 0;
        for (HeatSource s : heatSources) result += getValue(location, s.getPosition(), angle);
        return result * SCALE;
    }
    
    private float getValue(Vector location, Vector heatPosition, float angle) {
        final Vector sensorAbsolute = location.plus(Vector.rotate(sensorPosition, Vector.O, angle));
        final double distance = heatPosition.minus(sensorAbsolute).length();
        final float value = (float) Math.max(DISTANCE_LIMIT - distance, 0);
        return value;
    }

    @Override
    public void draw(GL2 gl) {
    }
}
