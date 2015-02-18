
package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import com.jogamp.opengl.util.gl2.GLUT;
import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

/**
 *
 * @author maikel
 */
public class Environment {
    
    private final GL2 gl;
    private final GLU glu;
    private final GLUT glut;

    public Environment(GL2 gl, GLU glu, GLUT glut) {
        this.gl = gl;
        this.glu = glu;
        this.glut = glut;
    }
    public void draw(float time) {
        //TODO: extend
        gl.glColor3f(1, 0, 0);
        /*gl.glBegin(GL_QUADS);
        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(0, 1, 0);
        gl.glVertex3f(1, 1, 0);
        gl.glVertex3f(1, 0, 0);
        gl.glEnd();*/
        glut.glutWireCube(0.5f);
        gl.glColor3f(0, 0, 0);
        glut.glutSolidCube(0.5f);      
    }
}
