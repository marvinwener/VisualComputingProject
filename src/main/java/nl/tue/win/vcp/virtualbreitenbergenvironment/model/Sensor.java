package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import java.io.Serializable;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * Represents a sensor on a vehicle.
 *
 * @author maikel
 */
public abstract class Sensor implements Drawable, Serializable {
    // TODO: add relative position?
    
    /**
     * Gets the value this particular sensor gives for the given position
     * and direction. To be implemented by subclasses.
     * 
     * @param location absolute location of the sensor in the coordinate system
     * @param direction direction vector the sensor points in
     * @return the value the sensor provides
     */
    public abstract float getValue(Vector location, Vector direction);
}
