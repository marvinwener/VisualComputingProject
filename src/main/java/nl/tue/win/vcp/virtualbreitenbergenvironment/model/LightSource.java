package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Movable;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Drawable;
import com.jogamp.opengl.util.gl2.GLUT;
import java.io.Serializable;
import javax.media.opengl.GL2;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * A light source in the environment.
 *
 * @author maikel
 */
public class LightSource extends Movable implements Drawable, Serializable {

    private Vector position;

    public LightSource(Vector position) {
        this.position = position;
    }

    @Override
    public void draw(GL2 gl) {
        super.loadName(gl);
        gl.glPushMatrix();
        gl.glPushAttrib(GL2.GL_CURRENT_BIT);
        gl.glTranslated(position.x(), position.y(), position.z());
        gl.glColor3f(1, 1, 0);
        GLUT glut = new GLUT();
        glut.glutSolidCube(0.3f);
        gl.glPopAttrib();
        gl.glPopMatrix();
    }

    @Override
    public Vector getPosition() {
        return position;
    }

    public void setLocation(Vector location) {
        this.position = location;
    }

    @Override
    public void move(Vector movement) {
        this.position = this.position.plus(movement);
    }

    @Override
    public String toString() {
        return "Light Source at " + position;
    }
}
