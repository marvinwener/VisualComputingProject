package nl.tue.win.vcp.virtualbreitenbergenvironment.model.abstractmodels;

import java.util.HashMap;
import java.util.Map;
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
    private final static Map<Integer, Movable> NAMES = new HashMap<>();
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
        NAMES.put(ID, this);
    }
    
    /**
     * Method to be called before drawing to enable selection.
     * 
     * @param gl gl object to load name into
     */
    public void loadName(GL2 gl) {
        gl.glLoadName(ID);
    }
    
    /**
     * Get the Movable with the specified ID.
     * 
     * @param id the id (name) of the object
     * @return the object with the given id or else {@code NULL}
     */
    public static Movable getMovable(int id) {
        final Movable result = NAMES.get(id);
        return result != null ? result : NULL;
    }
    
    public abstract void move(Vector movement);
    public abstract Vector getPosition();
}
