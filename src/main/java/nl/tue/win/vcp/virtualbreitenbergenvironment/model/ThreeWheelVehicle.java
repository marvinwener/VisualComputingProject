package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import static javax.media.opengl.GL.GL_LINES;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.GL_CURRENT_BIT;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.WavefrontObjectLoader_DisplayList;

/**
 *
 * @author maikel
 */
public class ThreeWheelVehicle extends VehicleImpl {
    static int displayList = -1;

    public ThreeWheelVehicle(Vector initialPosition, float initialAngle) {
        super(initialPosition, initialAngle);
    }
    
    @Override
    public void draw(GL2 gl) {
        // position is middle between wheels (behind)

        gl.glPushMatrix();
        gl.glPushAttrib(GL_CURRENT_BIT);

        // Line to indicate direction the vehicle points in
        /*gl.glColor3f(1, 0, 0);
        gl.glBegin(GL_LINES);
        gl.glVertex3dv(position.asBuffer());
        gl.glVertex3dv(position.plus(getDirection()).asBuffer());
        gl.glEnd();*/
        
        gl.glBegin(GL_LINES);
        gl.glVertex3dv(position.asBuffer());
        gl.glVertex3dv(position.plus(Vector.Z).asBuffer());
        gl.glEnd();

        // Move / rotate to correct position
        double deg = Math.toDegrees(angle);
        gl.glTranslated(position.x(), position.y(), position.z());
        gl.glRotated(deg, 0, 0, 1);
        
        if (displayList == -1)
            displayList = WavefrontObjectLoader_DisplayList.loadWavefrontObjectAsDisplayList(gl, "/home/maikel/Dropbox/2IV06 - Visual Computing Project/three_wheel/redcar_3wheel-obj/atorigin.obj", true);
        gl.glPushMatrix();
        gl.glPushAttrib(GL_CURRENT_BIT);
        
        gl.glColor3f(1, 0, 0);
        gl.glScaled(2, 2, 2);
        gl.glTranslated(0.5, 0.8f, 0);
        gl.glRotated(180, 0, 0, 1);
        gl.glCallList(displayList);
        
        gl.glPopAttrib();
        gl.glPopMatrix();

        /*// Draw wheels
        gl.glPushMatrix();
        gl.glColor3f(0, 0, 0);
        gl.glTranslatef(this.wheelDistance * -0.5f, 0, 0);
        Graphics.drawDisk(gl, 0.5f, 10);
        gl.glTranslatef(this.wheelDistance, 0, 0);
        Graphics.drawDisk(gl, 0.5f, 10);
        gl.glPopMatrix();*/

        gl.glPopAttrib();
        gl.glPopMatrix();

        for (Sensor s : slots) {
            s.draw(gl);
        }
    }
    
}
