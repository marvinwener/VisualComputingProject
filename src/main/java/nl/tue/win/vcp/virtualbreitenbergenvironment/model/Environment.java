package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import com.jogamp.opengl.util.gl2.GLUT;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.*;
import static javax.media.opengl.GL2GL3.*;
import javax.media.opengl.glu.GLU;
import nl.tue.win.vcp.virtualbreitenbergenvironment.opengl.GLSingleton;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 *
 * @author maikel
 */
public class Environment implements Serializable {

    transient private GL2 gl;
    transient private GLU glu;
    transient private GLUT glut;
    private final List<Vehicle> vs;

    public Environment(GL2 gl, GLU glu, GLUT glut) {
        this.gl = gl;
        this.glu = glu;
        this.glut = glut;
        vs = new ArrayList(Arrays.asList(
                new VehicleImpl(new Vector(0, 0, 0.5), 0),
                new VehicleImpl(new Vector(-5, -5, 0.5), 1.6f)
        ));
    }

    public void draw(float time) {
        drawFloorAndWalls();
        //TODO: extend
        gl.glColor3f(1, 0, 0);
        /*gl.glBegin(GL_QUADS);
         gl.glVertex3f(0, 0, 0);
         gl.glVertex3f(0, 1, 0);
         gl.glVertex3f(1, 1, 0);
         gl.glVertex3f(1, 0, 0);
         gl.glEnd();*/
        /*glut.glutWireCube(0.5f);
         gl.glColor3f(0, 0, 0);
         glut.glutSolidCube(0.5f);    
        
         gl.glTranslatef(0, 0, time);
         gl.glRotatef(time * 10,0,0,1);
         gl.glColor3f(1, 0, 0);
         glut.glutWireCube(0.5f);
         gl.glColor3f(0, 0, 0);
         glut.glutSolidCube(0.5f);*/
        for (Vehicle v : vs) {
            v.move();
            v.draw(gl);
        }
    }

    private void drawFloorAndWalls() {
        final float min = -10;
        final float max = 10;
        final float step = 0.5f;
        drawGrid(min, max, step);
        //drawWalls(min, max, step);
    }

    private void drawGrid(final float min, final float max, final float step) {
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

    private void drawWalls(float min, float max, float step) {
        gl.glPushAttrib(GL_CURRENT_BIT);
        gl.glPushMatrix();

        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslated(max, 0, 0);
        gl.glPushMatrix();
        gl.glRotated(90, 0, 0, 1);
        gl.glRotated(90, 1, 0, 0);
        drawGrid(0, max, step);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glRotated(-90, 0, 0, 1);
        gl.glRotated(90, 1, 0, 0);
        drawGrid(0, max, step);
        gl.glPopMatrix();

        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslated(0, max, 0);
        gl.glPushMatrix();
        gl.glRotated(90, 1, 0, 0);
        drawGrid(0, max, step);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glRotated(180, 0, 0, 1);
        gl.glRotated(90, 1, 0, 0);
        drawGrid(0, max, step);
        gl.glPopMatrix();

        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslated(min, 0, 0);
        gl.glPushMatrix();
        gl.glRotated(90, 0, 0, 1);
        gl.glRotated(90, 1, 0, 0);
        drawGrid(0, max, step);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glRotated(-90, 0, 0, 1);
        gl.glRotated(90, 1, 0, 0);
        drawGrid(0, max, step);
        gl.glPopMatrix();

        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glTranslated(0, min, 0);
        gl.glPushMatrix();
        gl.glRotated(90, 1, 0, 0);
        drawGrid(0, max, step);
        gl.glPopMatrix();
        gl.glPushMatrix();
        gl.glRotated(180, 0, 0, 1);
        gl.glRotated(90, 1, 0, 0);
        drawGrid(0, max, step);
        gl.glPopMatrix();

        gl.glPopMatrix();
        gl.glPopAttrib();
    }

    public boolean addVehicle(Vehicle v) {
        return this.vs.add(v);
    }

    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.gl = GLSingleton.getGL();
        this.glu = new GLU();
        this.glut = new GLUT();
    }
}
