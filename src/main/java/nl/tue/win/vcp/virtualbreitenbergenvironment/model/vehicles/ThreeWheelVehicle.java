package nl.tue.win.vcp.virtualbreitenbergenvironment.model.vehicles;

import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.GL_CURRENT_BIT;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors.Sensor;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.WavefrontObjectLoader_DisplayList;

/**
 * Three-wheel vehicle. Based on two-wheel vehicle, but with nicer graphics.
 *
 * @author maikel
 */
public class ThreeWheelVehicle extends TwoWheelVehicle {

    private static int displayList = -1;
    private final static String OBJ_PATH = "/graphics/threeWheelVehicle.obj";
    private final static float SIZE = 3;

    public ThreeWheelVehicle(Vector initialPosition, float initialAngle) {
        super(initialPosition, initialAngle);
    }

    @Override
    public void draw(GL2 gl) {
        // position is middle between wheels (behind)

        gl.glPushMatrix();
        gl.glPushAttrib(GL_CURRENT_BIT);

        // Move / rotate to correct position
        double deg = Math.toDegrees(angle);
        gl.glTranslated(position.x(), position.y(), position.z());
        gl.glRotated(deg, 0, 0, 1);

        if (displayList == -1) {
            displayList = WavefrontObjectLoader_DisplayList.loadWavefrontObjectAsDisplayList(gl, OBJ_PATH, true);
        }
        gl.glColor3f(1, 0, 0);
        gl.glScaled(SIZE, SIZE, SIZE);
        gl.glTranslated(0.5, 0.8f, 0);
        gl.glRotated(180, 0, 0, 1);
        gl.glCallList(displayList);

        gl.glPopAttrib();
        gl.glPopMatrix();

        for (Sensor s : slots) {
            s.draw(gl);
        }
    }
}
