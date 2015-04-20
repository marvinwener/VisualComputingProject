package nl.tue.win.vcp.virtualbreitenbergenvironment.model.vehicles;

import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.GL_CURRENT_BIT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_LIGHTING;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors.Sensor;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Rectangle;
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
    private final static float SIZE = 2;
    private final float wheelDistance = 1;

    public ThreeWheelVehicle(Vector initialPosition, float initialAngle) {
        super(initialPosition, initialAngle);
    }

    @Override
    public void draw(GL2 gl) {
        super.drawBoundingBox(gl);
        super.loadName(gl);

        gl.glPushMatrix();
        gl.glPushAttrib(GL_CURRENT_BIT);

        // Move / rotate to correct position
        double deg = Math.toDegrees(angle);
        gl.glTranslated(position.x(), position.y(), position.z());
        gl.glRotated(deg, 0, 0, 1);

        if (displayList == -1) {
            displayList = WavefrontObjectLoader_DisplayList.loadWavefrontObjectAsDisplayList(gl, OBJ_PATH, true);
        }
        gl.glEnable(GL_LIGHTING);
        gl.glColor3f(1, 0, 0);
        gl.glScaled(SIZE, SIZE, SIZE);
        gl.glTranslated(0.5, 0.5, 0);
        gl.glRotated(180, 0, 0, 1);
        gl.glCallList(displayList);
        gl.glDisable(GL_LIGHTING);

        gl.glPopAttrib();
        gl.glPopMatrix();

        for (Sensor s : slots) {
            s.draw(gl);
        }
        
        super.unloadName(gl);
    }
    
    @Override
    public final Vector[] getSensorLocations() {
        final Vector wheelDirection = INITIAL_DIRECTION.cross(Vector.Z);
        final Vector wheelVector = wheelDirection.normalized().scale(0.5 * wheelDistance).scale(0.5);
        final Vector toFront = new Vector(0, SIZE, 0).scale(0.35);
        return new Vector[]{wheelVector.plus(toFront),
            wheelVector.scale(-1).plus(toFront)};
    }
    
    @Override
    public Rectangle getBoundingBox() {
        final Vector direction = this.getDirection();
        final Vector wheelDirection = this.getDirection().cross(Vector.Z);
        
        final Vector upperRight = position.plus(direction.normalized().scale(0.52*SIZE)).plus(wheelDirection.normalized().scale(0.25*SIZE));
        final Vector upperLeft = position.plus(direction.normalized().scale(0.52*SIZE)).minus(wheelDirection.normalized().scale(0.25*SIZE));
        final Vector lowerRight = position.minus(direction.normalized().scale(0.52*SIZE)).plus(wheelDirection.normalized().scale(0.25*SIZE));
        final Vector lowerLeft = position.minus(direction.normalized().scale(0.52*SIZE)).minus(wheelDirection.normalized().scale(0.25*SIZE));
        
        return new Rectangle(direction, upperRight, upperLeft, lowerLeft, lowerRight);
    }
}
