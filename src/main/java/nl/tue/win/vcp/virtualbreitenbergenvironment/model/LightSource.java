package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Movable;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Drawable;
import java.io.Serializable;
import javax.media.opengl.GL2;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.WavefrontObjectLoader_DisplayList;

/**
 * A light source in the environment.
 *
 * @author maikel
 */
public class LightSource extends Movable implements Drawable, Serializable {

    private Vector position;
    private static int innerDisplayList = -1;
    private final static String INNER_OBJ_PATH = "/graphics/bulbInner.obj";
    private static int outerDisplayList = -1;
    private final static String OUTER_OBJ_PATH = "/graphics/bulbOuter.obj";

    public LightSource(Vector position) {
        this.position = position;
    }

    @Override
    public void draw(GL2 gl) {
        super.loadName(gl);
        gl.glPushMatrix();
        gl.glPushAttrib(GL2.GL_CURRENT_BIT);
        gl.glTranslated(position.x(), position.y(), position.z());

        if (innerDisplayList == -1) {
            loadGraphics(gl);
        }

        gl.glTranslated(-0.45, 0.2, 0);
        gl.glRotated(90, 1, 0, 0);
        gl.glColor3d(0.7294117647058823, 0.4627450980392157, 0.15294117647058825);
        gl.glCallList(innerDisplayList);
        gl.glColor3f(1, 1, 0);
        gl.glCallList(outerDisplayList);
        gl.glPopAttrib();
        gl.glPopMatrix();
        super.unloadName(gl);
    }

    @Override
    public Vector getPosition() {
        return position;
    }

    public void setLocation(Vector location) {
        this.position = location;
    }

    @Override
    public void move(Vector movement) {
        this.position = this.position.plus(movement);
    }

    @Override
    public String toString() {
        return "Light Source at " + position;
    }

    public void loadGraphics(GL2 gl) {
        WavefrontObjectLoader_DisplayList tWaveFrontObjectModel = new WavefrontObjectLoader_DisplayList(INNER_OBJ_PATH);
        tWaveFrontObjectModel.normalizeVertices();
        WavefrontObjectLoader_DisplayList.ScalingConfiguration config = tWaveFrontObjectModel.getConfig();
        innerDisplayList = WavefrontObjectLoader_DisplayList.loadWavefrontObjectAsDisplayList(gl, INNER_OBJ_PATH, config);
        outerDisplayList = WavefrontObjectLoader_DisplayList.loadWavefrontObjectAsDisplayList(gl, OUTER_OBJ_PATH, config);
    }
}
