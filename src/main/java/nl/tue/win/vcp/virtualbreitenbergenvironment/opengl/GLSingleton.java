package nl.tue.win.vcp.virtualbreitenbergenvironment.opengl;

import javax.media.opengl.GL2;

/**
 *
 * @author maikel
 */
public class GLSingleton {
    private static GL2 gl;
    
    public static void provideGL(GL2 gl) {
        GLSingleton.gl = gl;
    }
    
    public static GL2 getGL() {
        return gl;
    }
}
