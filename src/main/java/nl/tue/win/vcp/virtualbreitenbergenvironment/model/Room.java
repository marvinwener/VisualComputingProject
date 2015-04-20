package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.media.opengl.GL.GL_LINES;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.GL_CURRENT_BIT;
import static javax.media.opengl.GL2GL3.GL_QUADS;
import javax.media.opengl.GLException;
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
    private final static float STEP = 5;
    public static boolean DRAW_BOUNDING_BOX = false;
    public static boolean DRAW_WALLS = true;
    static private Texture FLOOR_TEXTURE = null;
    final static private String FLOOR_TEXTURE_PATH = "/textures/woodenFloor.jpg";
    // source: http://commons.wikimedia.org/wiki/File:Wood_pattern_parquet_floor_tiles.jpg
    static private Texture WALL_TEXTURE = null;
    final static private String WALL_TEXTURE_PATH = "/textures/wall.png";
    // source: http://www.pd4pic.com/stripes-grey-pattern.html

    @Override
    public void draw(GL2 gl) {
        if (FLOOR_TEXTURE == null) {
            try {
                FLOOR_TEXTURE = TextureIO.newTexture(new Object().getClass().getResourceAsStream(FLOOR_TEXTURE_PATH), false, "jpg");
            } catch (IOException | GLException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (WALL_TEXTURE == null) {
            try {
                WALL_TEXTURE = TextureIO.newTexture(new Object().getClass().getResourceAsStream(WALL_TEXTURE_PATH), false, "png");
            } catch (IOException | GLException ex) {
                Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        FLOOR_TEXTURE.enable(gl);
        FLOOR_TEXTURE.bind(gl);
        drawGrid(gl, MIN, MAX, STEP);
        FLOOR_TEXTURE.disable(gl);
        WALL_TEXTURE.enable(gl);
        WALL_TEXTURE.bind(gl);
        drawWalls(gl, MIN, MAX, STEP);
        FLOOR_TEXTURE.disable(gl);
    }

    private void drawGrid(GL2 gl, float min, float max, float step) {
        gl.glPushAttrib(GL_CURRENT_BIT);
        gl.glPushMatrix();
        gl.glColor3f(168f / 255, 163f / 255, 1);
        gl.glColor3f(1, 1, 1);
        gl.glBegin(GL_QUADS);
        for (float i = min; i < max; i += step) {
            for (float j = min; j < max; j += step) {
                gl.glTexCoord2f(0, 0);
                gl.glVertex3f(i, j, 0);
                gl.glTexCoord2f(1, 0);
                gl.glVertex3f(i + step, j, 0);
                gl.glTexCoord2f(1, 1);
                gl.glVertex3f(i + step, j + step, 0);
                gl.glTexCoord2f(0, 1);
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
