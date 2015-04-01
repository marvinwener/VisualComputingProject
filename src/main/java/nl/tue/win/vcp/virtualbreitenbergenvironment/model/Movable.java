package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * A movable object. Classes implementing this interface must implement a move
 * object that changes their position.
 *
 * @author maikel
 */
public interface Movable {

    public static final Movable NULL = new Movable() {

        @Override
        public void move(Vector movement) {
        }
    };

    public void move(Vector movement);
}
