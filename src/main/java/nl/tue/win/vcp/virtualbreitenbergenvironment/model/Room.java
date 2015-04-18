package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import java.util.Arrays;
import java.util.Collection;
import static javax.media.opengl.GL.GL_LINES;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.GL_CURRENT_BIT;
import static javax.media.opengl.GL2GL3.GL_QUADS;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.*;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Rectangle;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * Represents the walls around the vehicles.
 *
 * @author maikel
 */
public class Room implements Drawable {

    private final static float MIN = -10;
    private final static float MAX = 10;
    private final static float STEP = 0.5f;
    public static boolean DRAW_BOUNDING_BOX = true;
    public static boolean DRAW_WALLS = false;

    @Override
    public void draw(GL2 gl) {
        drawGrid(gl, MIN, MAX, STEP);
        drawWalls(gl, MIN, MAX, STEP);
    }

    private void drawGrid(GL2 gl, float min, float max, float step) {
        gl.glPushAttrib(GL_CURRENT_BIT);
        gl.glPushMatrix();
        gl.glColor3f(168f / 255, 163f / 255, 1);
        for (float i = min; i <= max; i += step) {
            gl.glBegin(GL_LINES);
            gl.glVertex3f(i, min, 0);
            gl.glVertex3f(i, max, 0);
            gl.glVertex3f(min, i, 0);
            gl.glVertex3f(max, i, 0);
            gl.glEnd();
        }
        gl.glColor3f(1, 1, 1);
        gl.glBegin(GL_QUADS);
        for (float i = min; i < max; i += step) {
            for (float j = min; j < max; j += step) {
                gl.glVertex3f(i, j, 0);
                gl.glVertex3f(i + step, j, 0);
                gl.glVertex3f(i + step, j + step, 0);
                gl.glVertex3f(i, j + step, 0);
            }
        }
        gl.glEnd();
        gl.glPopMatrix();
        gl.glPopAttrib();
    }

    private void drawWalls(GL2 gl, float min, float max, float step) {
        if (Room.DRAW_WALLS) {
            gl.glPushAttrib(GL_CURRENT_BIT);
            gl.glPushMatrix();

            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glTranslated(max, 0, 0);
            gl.glPushMatrix();
            gl.glRotated(90, 0, 0, 1);
            gl.glRotated(90, 1, 0, 0);
            drawGrid(gl, 0, max, step);
            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glRotated(-90, 0, 0, 1);
            gl.glRotated(90, 1, 0, 0);
            drawGrid(gl, 0, max, step);
            gl.glPopMatrix();

            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glTranslated(0, max, 0);
            gl.glPushMatrix();
            gl.glRotated(90, 1, 0, 0);
            drawGrid(gl, 0, max, step);
            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glRotated(180, 0, 0, 1);
            gl.glRotated(90, 1, 0, 0);
            drawGrid(gl, 0, max, step);
            gl.glPopMatrix();

            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glTranslated(min, 0, 0);
            gl.glPushMatrix();
            gl.glRotated(90, 0, 0, 1);
            gl.glRotated(90, 1, 0, 0);
            drawGrid(gl, 0, max, step);
            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glRotated(-90, 0, 0, 1);
            gl.glRotated(90, 1, 0, 0);
            drawGrid(gl, 0, max, step);
            gl.glPopMatrix();

            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glTranslated(0, min, 0);
            gl.glPushMatrix();
            gl.glRotated(90, 1, 0, 0);
            drawGrid(gl, 0, max, step);
            gl.glPopMatrix();
            gl.glPushMatrix();
            gl.glRotated(180, 0, 0, 1);
            gl.glRotated(90, 1, 0, 0);
            drawGrid(gl, 0, max, step);
            gl.glPopMatrix();

            gl.glPopMatrix();
            gl.glPopAttrib();
        }

        if (Room.DRAW_BOUNDING_BOX) {
            gl.glPushAttrib(GL_CURRENT_BIT);
            gl.glPushMatrix();
            gl.glColor3f(0, 1, 0);
            Collection<Collidable> walls = getWalls();
            for (Collidable wall : walls) {
                final Rectangle boundingBox = wall.getBoundingBox();
                gl.glBegin(GL_QUADS);
                for (Vector corner : boundingBox.corners) {
                    gl.glVertex2dv(corner.asBuffer());
                }
                gl.glEnd();
            }

            gl.glPopMatrix();
            gl.glPopAttrib();
        }
    }

    public Collection<Collidable> getWalls() {
        final Collidable[] result = {
            // left wall
            new Rectangle.RectangleHolder(new Rectangle(Vector.Y.as2DVector(),
            new Vector(-10, -10), new Vector(-10, 10), new Vector(-11, 10), new Vector(-11, -10))),
            // right wall
            new Rectangle.RectangleHolder(new Rectangle(Vector.Y.as2DVector(),
            new Vector(11, -10), new Vector(10, -10), new Vector(10, 10), new Vector(11, 10))),
            // bottom wall
            new Rectangle.RectangleHolder(new Rectangle(Vector.X.as2DVector(),
            new Vector(10, -11), new Vector(-10, -11), new Vector(-10, -10), new Vector(10, -10))),
            // upper wall
            new Rectangle.RectangleHolder(new Rectangle(Vector.X.as2DVector(),
            new Vector(10, 11), new Vector(-10, 11), new Vector(-10, 10), new Vector(10, 10)))
        };
        return Arrays.asList(result);
    }
}
