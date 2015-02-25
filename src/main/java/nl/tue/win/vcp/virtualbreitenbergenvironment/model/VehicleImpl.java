package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.*;
import static javax.media.opengl.GL2GL3.*;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * Trivial implementation of a vehicle for testing.
 *
 * @author maikel
 */
public class VehicleImpl extends Vehicle {

    private float wheelDistance;

    public VehicleImpl(Vector initialPosition, float initialAngle) {
        this.position = initialPosition;
        this.angle = initialAngle;
        this.slots = new Sensor[2];
        slots[0] = new SensorImpl(1);
        slots[1] = new SensorImpl(0.5f);
    }

    @Override
    public void draw(GL2 gl) {
        // position is middle between wheels (behind)

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
        assert wheelDistance > 0 : "Non-positive wheel distance";

        // Determine how much the wheels turn
        final float leftWheel = slots[0].getValue(position, this.getDirection());
        final float rightWheel = slots[1].getValue(position, this.getDirection());

        if (leftWheel == rightWheel) {
            // move forward in a straight line by the amount leftWheel
            this.position = this.position.plus(this.getDirection().scale(leftWheel));
        } else if (leftWheel == 0) {
            final float rotationAngle = rightWheel / wheelDistance;
            final Vector direction = this.getDirection().cross(Vector.Z);
            final float magnitude = -0.5f * wheelDistance;
            final Vector leftWheelPosition = this.position.plus(direction.scale(magnitude));
            this.position = Vector.rotate(position, leftWheelPosition, rotationAngle);
        } else if (rightWheel == 0) {
            final float rotationAngle = leftWheel / wheelDistance;
            final Vector direction = this.getDirection().cross(Vector.Z);
            final float magnitude = 0.5f * wheelDistance;
            final Vector rightWheelPosition = this.position.plus(direction.scale(magnitude));
            this.position = Vector.rotate(position, rightWheelPosition, rotationAngle);
        } else {
            final float r; // radius
            final boolean left = (leftWheel > rightWheel);
            if (left) {
                // determine the radius of the turning circle
                r = determineRadius(leftWheel, rightWheel, wheelDistance);
            } else {
                // Switch a and b around and negate the result
                r = -determineRadius(rightWheel, leftWheel, wheelDistance);
            }

            // Now determine the point to rotate around.
            final int sign = left ? 1 : -1;
            final Vector direction = this.getDirection().cross(Vector.Z);
            final float magnitude = sign * (r + 0.5f * wheelDistance);
            final Vector centerPoint = this.position.plus(direction.scale(magnitude));
            final float rotationAngle = left ? rightWheel / r : leftWheel / r;
            this.position = Vector.rotate(this.position, centerPoint, rotationAngle);
        }
    }

    private static float determineRadius(float a, float b, float d) {
        // We know the the angle of a on a circle with radius r+d equals
        // the angle of b on a circle with radius r. Then a / (r+d) == b/r.
        // Therefore, r = bd / (a-b)
        // http://www.wolframalpha.com/input/?i=a%2F%28r%2Bd%29%3Db%2Fr
        assert a - b != 0 : "Wheels rotate by the same amount; infinite radius";
        assert a * b * d != 0 : "Wheel distance or rotation is 0.";
        return -b * d / (a - b);
    }

}
