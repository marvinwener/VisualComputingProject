package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * Represents a Breitenberg vehicle.
 *
 * @author maikel
 */
public abstract class Vehicle implements Drawable {
    protected Vector position;
    protected float angle;
    protected Sensor[] slots;
    
    /**
     * Move the vehicle for one time unit. To be implemented by subclass.
     * 
     * A typical implementation will inspect the {@code slots} and use their
     * values to rotate and translate the vehicle.
     */
    public abstract void move();
    
    /**
     * Determines the direction based on the angle of the vehicle.
     * 
     * @return unit vector in the direction
     */
    public Vector getDirection() {
        throw new UnsupportedOperationException("Not supported yet."); // TODO: not yet implemented
    }
}
