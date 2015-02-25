package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import com.jogamp.opengl.util.gl2.GLUT;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2GL3.*;
import javax.media.opengl.glu.GLU;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 *
 * @author maikel
 */
public class Environment {

    private final GL2 gl;
    private final GLU glu;
    private final GLUT glut;
    private final Vehicle v;

    public Environment(GL2 gl, GLU glu, GLUT glut) {
        this.gl = gl;
        this.glu = glu;
        this.glut = glut;
        v = new VehicleImpl(Vector.O, 0);
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
        /*glut.glutWireCube(0.5f);
         gl.glColor3f(0, 0, 0);
         glut.glutSolidCube(0.5f);    
        
         gl.glTranslatef(0, 0, time);
         gl.glRotatef(time * 10,0,0,1);
         gl.glColor3f(1, 0, 0);
         glut.glutWireCube(0.5f);
         gl.glColor3f(0, 0, 0);
         glut.glutSolidCube(0.5f);*/
        v.move();
        v.draw(gl);
    }
}
