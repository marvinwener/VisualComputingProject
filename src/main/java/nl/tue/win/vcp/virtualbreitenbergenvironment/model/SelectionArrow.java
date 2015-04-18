package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Drawable;
import com.jogamp.opengl.util.gl2.GLUT;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.GL_CURRENT_BIT;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * Arrow to indicate which object is selected.
 *
 * @author maikel
 */
public class SelectionArrow implements Drawable {

    private final Vector position;
    private final float HEIGHT = Float.MAX_VALUE;
    private final float radius;
    
    public SelectionArrow(Vector position) {
        this.position = position;
        this.radius = 2;
    }
    
    public SelectionArrow(Vector position, float radius) {
        this.position = position;
        this.radius = radius;
    }

    private void oldDraw(GL2 gl) {
        gl.glPushMatrix();
        gl.glPushAttrib(GL_CURRENT_BIT);

        gl.glTranslated(position.x(), position.y(), position.z());
        gl.glColor3f(1, 0, 0);

        GLUT glut = new GLUT();
        gl.glTranslated(0, 0, 5);
        gl.glRotated(180, 1, 0, 0);
        gl.glScaled(0.5, 0.5, 0.5);
        glut.glutSolidCone(1, 2, 100, 100);

        gl.glPopAttrib();
        gl.glPopMatrix();
    }

    @Override
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glPushAttrib(GL_CURRENT_BIT);

        gl.glColor4f(0, 1, 1, 0.3f);
        GLUT glut = new GLUT();
        glut.glutSolidCylinder(radius, HEIGHT, 10, 10);
        
        gl.glPopAttrib();
        gl.glPopMatrix();
    }

}
