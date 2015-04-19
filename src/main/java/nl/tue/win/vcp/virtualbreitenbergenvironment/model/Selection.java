package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Drawable;
import com.jogamp.opengl.util.gl2.GLUT;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.GL_CURRENT_BIT;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * Arrow or cylinder to indicate which object is selected.
 *
 * @author maikel
 */
public class Selection implements Drawable {

    private final Vector position;
    private final float HEIGHT = Float.MAX_VALUE;
    private final float radius;

    public static enum SelectionMode {

        ARROW("Arrow"), CYLINDER("Cylinder");

        private final String name;

        private SelectionMode(String s) {
            name = s;
        }

        @Override
        public String toString() {
            return name;
        }
    };
    public static SelectionMode MODE = SelectionMode.CYLINDER;

    public Selection(Vector position) {
        this.position = position;
        this.radius = 1;
    }

    public Selection(Vector position, float radius) {
        this.position = position;
        this.radius = radius;
    }

    @Override
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glPushAttrib(GL_CURRENT_BIT);
        GLUT glut = new GLUT();
        gl.glTranslated(this.position.x(), this.position.y(), 0);
        switch (MODE) {
            case ARROW:
                gl.glColor3f(1, 0, 0);
                gl.glTranslated(0, 0, 5);
                gl.glRotated(180, 1, 0, 0);
                gl.glScaled(0.5, 0.5, 0.5);
                glut.glutSolidCone(1, 2, 100, 100);
                break;
            case CYLINDER:
                gl.glColor4f(1, 1, 0, 0.3f);
                glut.glutSolidCylinder(radius, HEIGHT, 50, 10);
                break;
        }
        gl.glPopAttrib();
        gl.glPopMatrix();
    }

}
