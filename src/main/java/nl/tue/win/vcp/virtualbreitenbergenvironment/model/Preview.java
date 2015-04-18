package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Drawable;
import javax.media.opengl.GL2;

/**
 * Represents a preview of some object that is somehow drawn differently.
 *
 * @author maikel
 */
public class Preview implements Drawable {

    private final Drawable object;
    private final static double PERIOD = 1;
    private final static boolean ENABLED = true;

    public Preview(Drawable object) {
        this.object = object;
    }

    public Drawable getObject() {
        return object;
    }

    @Override
    public void draw(GL2 gl) {
        float time = Time.getTime();
        if ((time % PERIOD <= PERIOD / 2) || !ENABLED) {
            object.draw(gl);
        }
    }

}
