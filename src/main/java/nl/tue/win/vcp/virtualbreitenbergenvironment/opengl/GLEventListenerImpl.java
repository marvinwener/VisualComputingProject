package nl.tue.win.vcp.virtualbreitenbergenvironment.opengl;

import nl.tue.win.vcp.virtualbreitenbergenvironment.model.Environment;
import com.jogamp.opengl.util.gl2.GLUT;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toDegrees;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2.*;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;
import javax.media.opengl.glu.GLU;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 *
 * @author maikel
 */
public class GLEventListenerImpl implements GLEventListener,
        MouseMotionListener,
        MouseListener,
        MouseWheelListener,
        KeyListener,
        EnvironmentContainer {

    private final static GLU glu = new GLU();
    private final static GLUT glut = new GLUT();
    private Vector cnt = new Vector(0, 0, 0);
    private int width;
    private int height;
    private float phi;
    private float theta = 0.4f;
    private int dragSourceX, dragSourceY;
    private int mouseButton;
    public static float DRAG_PIXEL_TO_RADIAN = 0.025f;
    final static private float EPS = 0.001f;
    static public float THETA_MIN = (-(float) Math.PI / 2f) + EPS;
    static public float THETA_MAX = ((float) Math.PI / 2f) - EPS;
    private float vDist = 30;
    static public float MIN_CAMERA_DISTANCE = 1f;
    static public float MOUSE_WHEEL_FACTOR = 1.2f;
    static public float CENTER_POINT_CHANGE = 1f;
    private Environment environment;
    private double fovy = -1;

    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        GLSingleton.provideGL(gl);
        environment = new Environment(gl, glu, glut);

        // Enable blending.
        gl.glEnable(GL_BLEND);
        gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

        // Enable anti-aliasing.
        gl.glEnable(GL_LINE_SMOOTH);
        //gl.glEnable(GL_POLYGON_SMOOTH);
        gl.glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
        //gl.glHint(GL_POLYGON_SMOOTH_HINT, GL_NICEST);

        // Enable depth testing.
        gl.glEnable(GL_DEPTH_TEST);
        gl.glDepthFunc(GL_LESS);

        // Enable textures. 
        gl.glEnable(GL_TEXTURE_2D);
        gl.glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);

        // Enable lighting.
        //gl.glEnable(GL_LIGHTING);
        //gl.glEnable(GL_LIGHT0);
        //gl.glEnable(GL_NORMALIZE);
        //float[] ambient = {1f, 1f, 1f, 1f};
        //gl.glLightfv(GL_LIGHT0, GL_AMBIENT, ambient, 0);
        //TODO: extend
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        setView(drawable);
        drawScene(drawable);
        reportErrors(gl);
    }

    private void reportErrors(final GL2 gl) {
        // Report OpenGL errors.
        int errorCode = gl.glGetError();
        while (errorCode != GL.GL_NO_ERROR) {
            System.err.println(errorCode + " "
                    + glu.gluErrorString(errorCode));
            errorCode = gl.glGetError();
        }
    }

    @Override
    public void reshape(GLAutoDrawable drawable,
            int x, int y,
            int width, int height) {
        // Update state.
        this.width = width;
        this.height = height;

        setView(drawable);
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    /**
     * Configures the viewing transform.
     */
    public void setView(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
        final int vWidth = 10;

        // Calculate directional vector of the camera (view direction).
        Vector dir = new Vector(cos(this.phi) * cos(this.theta),
                sin(this.phi) * cos(this.theta),
                sin(this.theta));

        // Calculate position of the camera.
        final Vector eye = this.cnt.plus(dir.scale(vDist));

        final float AR = this.width / (float) this.height; // aspect ratio
        float vHeight = vWidth / AR; // height of scene to be shown

        // We only compute the field of view once to prevent strange effects.
            /*
         * Consider a triangle with base vHeight and height vDist. The
         * vertical field of view angle we are looking for is the top angle
         * fovy. By adding the altitude from this corner to the base of the
         * triangle, we obtain two right triangles, in which tan(a/2) =
         * (vHeight / 2) / 2. Then fovy = arctan((vHeight / 2) / vDist)
         */
        if (fovy == -1) {
            final float vDist = 10;
            fovy = 2 * atan2(vHeight / 2, vDist);
        }

        // Select part of window.
        gl.glViewport(0, 0, this.width, this.height);

        // Set projection matrix.
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        // Use perspective projection.
        glu.gluPerspective(toDegrees(fovy), AR, 0.1, 1000);
        glu.gluLookAt(eye.x(), eye.y(), eye.z(), // eye point
                this.cnt.x(), this.cnt.y(), this.cnt.z(), // center point
                0, 0, 1); // up axis

        // Set camera.
        gl.glMatrixMode(GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    /**
     * Draws the entire scene.
     */
    public void drawScene(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        // Background color.
        gl.glClearColor(1f, 1f, 1f, 0f);

        // Clear background.
        gl.glClear(GL_COLOR_BUFFER_BIT);

        // Clear depth buffer.
        gl.glClear(GL_DEPTH_BUFFER_BIT);

        environment.draw();
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        float dX = me.getX() - dragSourceX;
        float dY = me.getY() - dragSourceY;

        // Change camera angle when left button is pressed.
        if (mouseButton == MouseEvent.BUTTON1) {
            this.phi += dX * DRAG_PIXEL_TO_RADIAN;
            this.theta = Math.max(THETA_MIN,
                    Math.min(THETA_MAX,
                            this.theta + dY * DRAG_PIXEL_TO_RADIAN));
        }
        // Change vWidth when right button is pressed.
            /*else if(mouseButton == MouseEvent.BUTTON3) {
         this.vWidth = Math.max(VWIDTH_MIN,
         Math.min(VWIDTH_MAX,
         this.vWidth + dY * DRAG_PIXEL_TO_VWIDTH));
         }*/

        dragSourceX = me.getX();
        dragSourceY = me.getY();
    }

    @Override
    public void mouseMoved(MouseEvent me) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent me) {
        dragSourceX = me.getX();
        dragSourceY = me.getY();
        mouseButton = me.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        vDist = (float) Math.max(MIN_CAMERA_DISTANCE,
                vDist
                * Math.pow(MOUSE_WHEEL_FACTOR,
                        mwe.getWheelRotation()));
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        // Move center point.
        double phiQ = phi + Math.PI / 2.0;

        switch (ke.getKeyChar()) {
            // Right.
            case 'a':
                cnt = cnt.minus(
                        new Vector(Math.cos(phiQ), Math.sin(phiQ), 0)
                        .scale(CENTER_POINT_CHANGE));
                break;
            // Left.
            case 'd':
                cnt = cnt.plus(
                        new Vector(Math.cos(phiQ), Math.sin(phiQ), 0)
                        .scale(CENTER_POINT_CHANGE));
                break;
            // Forwards.
            case 'w':
                cnt = cnt.minus(
                        new Vector(Math.cos(phi), Math.sin(phi), 0)
                        .scale(CENTER_POINT_CHANGE));
                break;
            // Backwards.
            case 's':
                cnt = cnt.plus(
                        new Vector(Math.cos(phi), Math.sin(phi), 0)
                        .scale(CENTER_POINT_CHANGE));
                break;
            // Up.
            case 'q':
                cnt = new Vector(cnt.x(),
                        cnt.y(),
                        cnt.z() + CENTER_POINT_CHANGE);
                break;
            // Down.
            case 'z':
                cnt = new Vector(cnt.x(),
                        cnt.y(),
                        cnt.z() - CENTER_POINT_CHANGE);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public void setEnvironment(Environment e) {
        this.environment = e;
    }

}
