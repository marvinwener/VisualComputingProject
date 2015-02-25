package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import javax.media.opengl.GL2;

/**
 * A drawable object. Classes implementing this interface must provide a draw
 * method.
 *
 * @author maikel
 */
public interface Drawable {
    /**
     * Draw the object. To be implemented by subclass.
     * 
     * @param gl the gl object to draw on
     */
    public abstract void draw(GL2 gl);
}
