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
    private final Room room;
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
        room = new Room();
    }

    public void draw() {
        gl.glLoadName(1);
        room.draw(gl);
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
