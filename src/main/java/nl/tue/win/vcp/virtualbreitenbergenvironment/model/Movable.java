package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import javax.media.opengl.GL2;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * A movable object. Classes implementing this interface must implement a move
 * object that changes their position.
 *
 * @author maikel
 */
public abstract class Movable {
    private static int NEXT_ID = 2;
    private final int ID;
    public static final Movable NULL = new Movable() {

        @Override
        public void move(Vector movement) {
        }

        @Override
        public String toString() {
            return "No selection";
        }

        @Override
        public Vector getPosition() {
            return null;
        }
    };

    public Movable() {
        this.ID = NEXT_ID++;
    }
    
    /**
     * Method to be called before drawing to enable selection.
     * 
     * @param gl gl object to load name into
     */
    public void loadName(GL2 gl) {
        gl.glLoadName(ID);
    }
    
    public abstract void move(Vector movement);
    public abstract Vector getPosition();
}
