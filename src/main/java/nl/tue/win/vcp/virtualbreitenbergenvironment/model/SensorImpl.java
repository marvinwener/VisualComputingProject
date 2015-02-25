package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import javax.media.opengl.GL2;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * Trivial implementation of a sensor for testing.
 *
 * @author maikel
 */
public class SensorImpl extends Sensor {

    final float val;

    SensorImpl() {
        this.val = 1;
    }

    SensorImpl(float val) {
        this.val = val;
    }

    @Override
    public float getValue(Vector location, Vector direction) {
        return val;
    }

    @Override
    public void draw(GL2 gl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
