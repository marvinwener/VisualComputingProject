package nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors;

import java.util.List;
import javax.media.opengl.GL2;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.LightSource;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.vehicles.Vehicle;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * Light sensor implementation.
 *
 * @author maikel
 */
public class LightSensor extends Sensor {

    protected final Vector sensorPosition; // relative sensor position
    protected final List<LightSource> lights;
    protected static final double THRESHOLD = 0.8;
    protected static final float SCALE = 0.1f;
    //Vector sensorAbsolute;

    public LightSensor(Vector position, List<LightSource> lights) {
        this.sensorPosition = position;
        this.lights = lights;
        //this.sensorAbsolute = sensorPosition;
    }

    @Override
    public float getValue(Vector location, float angle) {
        final Vector direction = Vehicle.getDirection(angle);
        // take the maximum of all light sources
        float result = 0;
        for (LightSource light : lights) {
            final float val = getValue(location, direction, light.getPosition(), angle);
            result = (val > result) ? val : result;
        }
        return result;
    }

    private final float getValue(Vector location, Vector direction, Vector lightPosition, float angle) {
        // take the direction from the sensor to the light
        // the dot product between these gives an indication of the extent to
        // which these point in the same direction
        // we only consider positive values, since the sensor will not pick up
        // anything from the reverse direction
        // we may need to use a lower bound
        final Vector sensorAbsolute = location.plus(Vector.rotate(sensorPosition, Vector.O, angle));
        final Vector sensorDirection = lightPosition.minus(sensorAbsolute);
        final double dot = Vector.dot(sensorDirection.normalized(), direction.normalized());
        final double val = valueFor(dot);
        return (val > THRESHOLD) ? (float) val * SCALE : 0;
    }

    protected double valueFor(double dot) {
        return dot;
    }

    @Override
    public void draw(GL2 gl) {
        /*gl.glPushMatrix();
         gl.glPushAttrib(GL2.GL_CURRENT_BIT);
         gl.glTranslated(sensorAbsolute.x(), sensorAbsolute.y(), sensorAbsolute.z());
         com.jogamp.opengl.util.gl2.GLUT glut = new com.jogamp.opengl.util.gl2.GLUT();
         gl.glColor3f(0, 1, 0);
         glut.glutSolidSphere(0.1f, 10, 10);
         gl.glPopAttrib();
         gl.glPopMatrix();*/
    }

}
