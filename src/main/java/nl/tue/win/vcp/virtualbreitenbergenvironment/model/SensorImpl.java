package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import javax.media.opengl.GL2;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

public class SensorImpl extends Sensor {

    @Override
    public float getValue(Vector location, Vector direction) {
        return 1;
    }

    @Override
    public void draw(GL2 gl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
