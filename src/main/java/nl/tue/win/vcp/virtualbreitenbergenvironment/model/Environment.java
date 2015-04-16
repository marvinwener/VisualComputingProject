package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import nl.tue.win.vcp.virtualbreitenbergenvironment.model.vehicles.Vehicle;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Movable;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Drawable;
import com.jogamp.opengl.util.gl2.GLUT;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.*;
import static javax.media.opengl.GL2GL3.*;
import javax.media.opengl.glu.GLU;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Collidable;
import nl.tue.win.vcp.virtualbreitenbergenvironment.opengl.GLSingleton;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.CollisionDetection;

/**
 *
 * @author maikel
 */
public class Environment implements Serializable {

    transient private GL2 gl;
    transient private GLU glu;
    transient private GLUT glut;
    private final List<Vehicle> vehicles;
    private final List<LightSource> lights;
    private final List<HeatSource> heatSources;
    private Drawable preview = Drawable.NULL;

    public Environment() {
        this(GLSingleton.getGL());
    }

    public Environment(GL2 gl) {
        this(gl, new GLU(), new GLUT());
    }

    public Environment(GL2 gl, GLU glu, GLUT glut) {
        this.gl = gl;
        this.glu = glu;
        this.glut = glut;
        vehicles = new ArrayList<>();
        lights = new ArrayList<>();
        heatSources = new ArrayList<>();
    }

    public void draw() {
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
        gl.glLoadName(1);
        drawFloorAndWalls();
        preview.draw(gl);
        Set<Collidable> collidingVehicles
                = CollisionDetection.getCollidingObjects(this.getCollidables());
        for (Vehicle v : vehicles) {
            if (!collidingVehicles.contains(v)) {
                v.move();
            }
            v.draw(gl);
        }
        for (LightSource l : lights) {
            l.draw(gl);
        }
        for (HeatSource h : heatSources) {
            h.draw(gl);
        }
    }
    
    private Collidable[] getCollidables() {
        return vehicles.toArray(new Collidable[0]);
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
        v.setEnvironment(this);
        return this.vehicles.add(v);
    }

    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.gl = GLSingleton.getGL();
        this.glu = new GLU();
        this.glut = new GLUT();
    }

    public List<LightSource> getLights() {
        return this.lights;
    }

    public boolean addLight(LightSource l) {
        return this.lights.add(l);
    }

    public List<HeatSource> getHeatSources() {
        return this.heatSources;
    }

    public boolean addHeatSource(HeatSource h) {
        return this.heatSources.add(h);
    }

    public void clearPreview() {
        this.preview = Drawable.NULL;
    }

    public void preview(Drawable object) {
        this.preview = new Preview(object);
    }

    public Movable[] getMovables() {
        final List<Movable> result = new ArrayList<>();
        result.add(Movable.NULL); // in case of no selection
        result.addAll(vehicles); // TODO: add manual movement for vehicles
        result.addAll(lights);
        result.addAll(heatSources);
        return result.toArray(new Movable[0]);
    }

    public boolean removeObject(Movable selection) {
        return vehicles.remove(selection)
                || lights.remove(selection)
                || heatSources.remove(selection);
    }
}
