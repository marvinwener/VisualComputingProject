package nl.tue.win.vcp.virtualbreitenbergenvironment.model.abstractmodels;

import java.io.Serializable;
import javax.media.opengl.GL2;

/**
 * A drawable object. Classes implementing this interface must provide a draw
 * method.
 *
 * @author maikel
 */
public interface Drawable extends Serializable {

    public static final Drawable NULL = new Drawable() {

        @Override
        public void draw(GL2 gl) {
        }
    };

    /**
     * Draw the object. To be implemented by subclass.
     *
     * @param gl the gl object to draw on
     */
    public abstract void draw(GL2 gl);
}
