package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import com.jogamp.opengl.util.gl2.GLUT;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL2;
import javax.media.opengl.GLException;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors.TemperatureSensor;
import static nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Graphics.drawDisk;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * A heat source in the environment.
 *
 * @author maikel
 */
public class HeatSource extends LightSource {

    private static Texture temperature;
    public static boolean DRAW_GRADIENT = true;
    final static String TEXTURE_PATH
            = "src/main/java/nl/tue/win/vcp/virtualbreitenbergenvironment/"
            + "graphics/textures/temperature.jpg";

    // all functionality is copied from light source, but draw() and toString() are overridden
    public HeatSource(Vector position) {
        super(position);
    }

    @Override
    public void draw(GL2 gl) {
        if (DRAW_GRADIENT && temperature == null) {
            try {
                temperature = TextureIO.newTexture(new File(TEXTURE_PATH), false);
            } catch (IOException | GLException ex) {
                Logger.getLogger(HeatSource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        GLUT glut = new GLUT();
        gl.glPushMatrix();
        gl.glPushAttrib(GL2.GL_CURRENT_BIT);
        gl.glTranslated(getPosition().x(), getPosition().y(), getPosition().z());
        gl.glPushMatrix();
        gl.glPushAttrib(GL2.GL_CURRENT_BIT);
        gl.glColor4f(1, 1, 1, 0.3f);
        gl.glTranslatef(0, 0, 0.1f);
        gl.glRotatef(90, 0, 1, 0);
        if (DRAW_GRADIENT) {
            temperature.enable(gl);
            temperature.bind(gl);
            drawDisk(gl, TemperatureSensor.DISTANCE_LIMIT, 50);
            temperature.disable(gl);
        }
        gl.glPopAttrib();
        gl.glPopMatrix();
        super.loadName(gl);
        gl.glColor3f(1, 0, 0);
        glut.glutSolidCube(0.3f);
        super.unloadName(gl);
        gl.glPopAttrib();
        gl.glPopMatrix();
    }

    @Override
    public String toString() {
        return "Heat Source at " + getPosition();
    }
}
