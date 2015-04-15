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

    public TemperatureSensor(Vector sensorPosition, List<HeatSource> heatSources) {
        this.sensorPosition = sensorPosition;
        this.heatSources = heatSources;
    }
    
    @Override
    public float getValue(Vector location, float angle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(GL2 gl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
