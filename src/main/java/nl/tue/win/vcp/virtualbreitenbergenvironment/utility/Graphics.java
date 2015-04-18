package nl.tue.win.vcp.virtualbreitenbergenvironment.utility;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javax.media.opengl.GL;
import static javax.media.opengl.GL.GL_LINE_LOOP;
import javax.media.opengl.GL2;

/**
 * Provides some simple methods to draw shapes.
 *
 * @author maikel
 */
public class Graphics {

    /**
     * Draws the unit circle in a 3D world in the Y and Z directions.
     *
     * @param gl the graphics object
     * @param radius the radius of the circle
     * @param slices the precision of the circle
     */
    public static void drawCircle(GL2 gl, float radius, int slices) {
        float angle; // angle of ray from origin to current point with y axis
        gl.glBegin(GL_LINE_LOOP);
        for (int i = 0; i < slices; i++) {
            angle = (float) (i * 2 * PI / slices); // compute angle
            // Define vertex by definition of the unit circle.
            gl.glVertex3d(0, (cos(angle) * radius), (sin(angle) * radius));
        }
        gl.glEnd();
    }

    /**
     * Draws the unit circle in a 3D world in the Y and Z directions.
     *
     * @param gl the graphics object
     * @param radius the radius of the circle
     * @param slices the precision of the circle
     */
    public static void drawDisk(GL2 gl, float radius, int slices) {
        float angle; // angle of ray from origin to current point with y axis
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glTexCoord1d(1);
        gl.glVertex3d(0, 0, 0);
        gl.glTexCoord1d(0);
        for (int i = 0; i < slices; i++) {
            angle = (float) (i * 2 * PI / slices); // compute angle
            // Define vertex by definition of the unit circle.
            gl.glVertex3d(0, (cos(angle) * radius), (sin(angle) * radius));
        }
        gl.glVertex3d(0, radius, 0);
        gl.glEnd();
    }
}
