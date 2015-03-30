package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import com.jogamp.opengl.util.gl2.GLUT;
import javax.media.opengl.GL2;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * Light sensor implementation.
 *
 * @author maikel
 */
public class LightSensor extends Sensor {

    private final Vector lightPosition = new Vector(0, 10, 0.5);
    private final Vector sensorPosition; // relative sensor position

    public LightSensor(Vector position) {
        this.sensorPosition = position;
    }

    @Override
    public float getValue(Vector location, Vector direction) {
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
        // for now, draw the light source itself (needs to be refactored)
        gl.glPushMatrix();
        gl.glPushAttrib(GL2.GL_CURRENT_BIT);
        gl.glTranslated(lightPosition.x(), lightPosition.y(), lightPosition.z());
        gl.glColor3f(1, 1, 0);
        GLUT glut = new GLUT();
        glut.glutSolidCube(0.3f);
        gl.glPopAttrib();
        gl.glPopMatrix();
    }

}
