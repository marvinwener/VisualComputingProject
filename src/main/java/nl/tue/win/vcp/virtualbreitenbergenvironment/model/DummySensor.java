package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import javax.media.opengl.GL2;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * A sensor that never provides any input. This may be used to essentially
 * disable a component.
 *
 * @author maikel
 */
public class DummySensor extends Sensor {
    final static public Sensor instance = new DummySensor();

    @Override
    public float getValue(Vector location, Vector direction) {
        return 0;
    }

    @Override
    public void draw(GL2 gl) {
    }

}
