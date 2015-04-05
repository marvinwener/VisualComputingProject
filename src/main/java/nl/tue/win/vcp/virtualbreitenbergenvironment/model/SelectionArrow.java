package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

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

    public SelectionArrow(Vector position) {
        this.position = position;
    }
    
    @Override
    public void draw(GL2 gl) {
        gl.glPushMatrix();
        gl.glPushAttrib(GL_CURRENT_BIT);
        
        gl.glTranslated(position.x(), position.y(), position.z());
        
        GLUT glut = new GLUT();
        gl.glTranslated(0, 0, 5);
        gl.glRotated(180, 1, 0, 0);
        gl.glScaled(0.5, 0.5, 0.5);
        glut.glutSolidCone(1, 2, 100, 100);
        
        gl.glPopAttrib();
        gl.glPopMatrix();
    }

}
