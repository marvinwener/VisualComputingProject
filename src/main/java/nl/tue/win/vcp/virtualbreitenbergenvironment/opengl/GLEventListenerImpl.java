package nl.tue.win.vcp.virtualbreitenbergenvironment.opengl;

import com.jogamp.opengl.util.gl2.GLUT;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toDegrees;
import javax.media.opengl.GL;
import static javax.media.opengl.GL.GL_BLEND;
import static javax.media.opengl.GL.GL_COLOR_BUFFER_BIT;
import static javax.media.opengl.GL.GL_DEPTH_BUFFER_BIT;
import static javax.media.opengl.GL.GL_DEPTH_TEST;
import static javax.media.opengl.GL.GL_LESS;
import static javax.media.opengl.GL.GL_LINE_SMOOTH;
import static javax.media.opengl.GL.GL_LINE_SMOOTH_HINT;
import static javax.media.opengl.GL.GL_NICEST;
import static javax.media.opengl.GL.GL_ONE_MINUS_SRC_ALPHA;
import static javax.media.opengl.GL.GL_SRC_ALPHA;
import static javax.media.opengl.GL.GL_TEXTURE_2D;
import javax.media.opengl.GL2;
import static javax.media.opengl.GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_AMBIENT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_LIGHT0;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_LIGHTING;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_NORMALIZE;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_MODELVIEW;
import static javax.media.opengl.fixedfunc.GLMatrixFunc.GL_PROJECTION;
import javax.media.opengl.glu.GLU;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 *
 * @author maikel
 */
public class GLEventListenerImpl implements GLEventListener {

    private float tAnim = 0;
    private final float startTime;
    private final static GLU glu = new GLU();
    private final static GLUT glut = new GLUT();
    private final Vector cnt = new Vector(0, 0, 0);
    private int width;
    private int height;
    private float phi;
    private float theta;

    public GLEventListenerImpl() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

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
        gl.glEnable(GL_LIGHTING);
        gl.glEnable(GL_LIGHT0);
        gl.glEnable(GL_NORMALIZE);
        float[] ambient = {1f, 1f, 1f, 1f};
        gl.glLightfv(GL_LIGHT0, GL_AMBIENT, ambient, 0);

        //TODO: extend
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();

        tAnim = (float) (System.currentTimeMillis() - startTime) / 1000f;

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
        final int vDist = 10;
        final int vWidth = 10;

        // Calculate directional vector of the camera (view direction).
        Vector dir = new Vector(cos(this.phi) * cos(this.theta),
                sin(this.phi) * cos(this.theta),
                sin(this.theta));

        // Calculate position of the camera.
        final Vector eye = this.cnt.add(dir.scale(vDist));

        final float AR = this.width / (float) this.height; // aspect ratio
        float vHeight = vWidth / AR; // height of scene to be shown

        // We only compute the field of view once to prevent strange effects.
            /*
         * Consider a triangle with base vHeight and height vDist. The
         * vertical field of view angle we are looking for is the top angle
         * fovy. By adding the altitude from this corner to the base of the
         * triangle, we obtain two right triangles, in which tan(a/2) =
         * (vHeight / 2) / 2. Then fovy = arctan((vHeight / 2) / gs.vDist)
         */
        final double fovy = 2 * atan2(vHeight / 2, vDist);

        // Select part of window.
        gl.glViewport(0, 0, this.width, this.height);

        // Set projection matrix.
        gl.glMatrixMode(GL_PROJECTION);
        gl.glLoadIdentity();
        // Use perspective projection.
        glu.gluPerspective(toDegrees(fovy), AR, 0.1, 1000);

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

        //TODO: extend
    }

}
