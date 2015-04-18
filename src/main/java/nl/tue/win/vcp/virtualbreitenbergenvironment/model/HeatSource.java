package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import com.jogamp.opengl.util.gl2.GLUT;
import javax.media.opengl.GL2;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors.TemperatureSensor;
import static nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Graphics.drawDisk;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * A heat source in the environment.
 *
 * @author maikel
 */
public class HeatSource extends LightSource {

    // all functionality is copied from light source, but draw() and toString() are overridden
    public HeatSource(Vector position) {
        super(position);
    }

    @Override
    public void draw(GL2 gl) {
        GLUT glut = new GLUT();
        gl.glPushMatrix();
        gl.glPushAttrib(GL2.GL_CURRENT_BIT);
        gl.glTranslated(getPosition().x(), getPosition().y(), getPosition().z());
        gl.glPushMatrix();
        gl.glPushAttrib(GL2.GL_CURRENT_BIT);
        gl.glColor4f(1, 0, 0, 0.3f);
        gl.glTranslatef(0, 0, 0.1f);
        gl.glRotatef(90, 0, 1, 0);
        drawDisk(gl, TemperatureSensor.DISTANCE_LIMIT, 50);
        gl.glPopAttrib();
        gl.glPopMatrix();
        super.loadName(gl);
        gl.glColor3f(1, 0, 0);
        glut.glutSolidCube(0.3f);
        gl.glPopAttrib();
        gl.glPopMatrix();
    }

    @Override
    public String toString() {
        return "Heat Source at " + getPosition();
    }
}
