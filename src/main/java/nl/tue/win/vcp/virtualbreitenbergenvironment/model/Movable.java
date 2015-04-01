package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

/**
 * A movable object. Classes implementing this interface must implement a move
 * object that changes their position.
 *
 * @author maikel
 */
public interface Movable {

    public static final Movable NULL = new Movable() {

        @Override
        public void move(float dX, float dY, float dZ) {
        }
    };

    public void move(float dX, float dY, float dZ);
}
