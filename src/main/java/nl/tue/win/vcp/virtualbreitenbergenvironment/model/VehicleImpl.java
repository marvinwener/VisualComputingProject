package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.*;
import static javax.media.opengl.GL2GL3.*;

/**
 *
 * @author maikel
 */
public class VehicleImpl extends Vehicle {

    @Override
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glPushAttrib(GL_CURRENT_BIT);
        gl.glBegin(GL_LINE);
        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(0, 0, 1);
        gl.glEnd();
        gl.glPopAttrib();
        gl.glPopMatrix();
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
