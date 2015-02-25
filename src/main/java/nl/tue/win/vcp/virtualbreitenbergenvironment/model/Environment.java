
package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import com.jogamp.opengl.util.gl2.GLUT;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.*;
import javax.media.opengl.glu.GLU;
import nl.Wavefront;

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
    
    public void init() {
        gl.glNewList(1, GL_COMPILE);
        Wavefront wf = new Wavefront();
        try {
            wf.readWavefront("C:\\Users\\Maikel\\Documents\\NetBeansProjects\\tue-2io23\\src\\ogo\\spec\\game\\graphics\\models\\air.obj", gl);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Environment.class.getName()).log(Level.SEVERE, null, ex);
        }
        wf.normalize();
        wf.drawTriangles();
        gl.glEndList();
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
        //gl.glColor3f(0, 0, 0);
        //glut.glutSolidCube(0.5f);   
        gl.glColor3f(0, 1, 1);
        gl.glCallList(1);
    }
}
