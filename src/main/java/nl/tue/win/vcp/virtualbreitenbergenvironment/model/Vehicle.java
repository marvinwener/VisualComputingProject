package nl.tue.win.vcp.virtualbreitenbergenvironment.model;

import java.io.Serializable;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * Represents a Breitenberg vehicle.
 *
 * @author maikel
 */
public abstract class Vehicle implements Drawable, Serializable {

    protected Vector position;
    protected float angle;
    protected Sensor[] slots;
    protected Vector initialDirection = Vector.Y;
    protected Environment environment;

    /**
     * Move the vehicle for one time unit. To be implemented by subclass.
     *
     * A typical implementation will inspect the {@code slots} and use their
     * values to rotate and translate the vehicle.
     */
    public abstract void move();

    /**
     * Gets the relative positions of the sensors on the vehicle.
     *
     * @return array {@code a} of locations such that slots[i] has location a[i]
     */
    public abstract Vector[] getSensorLocations();

    /**
     * Determines the direction based on the angle of the vehicle.
     *
     * @return unit vector in the direction
     */
    public Vector getDirection() {
        assert initialDirection.length() == 1;
        return Vector.rotate(initialDirection, Vector.O, angle);
    }

    /**
     * Removes null references from @code{slots} and replaces them by a dummy
     * sensor.
     *
     * @post \forall s : slots; s != null;
     */
    protected void removeNullSensors() {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == null) {
                slots[i] = DummySensor.instance;
            }
        }
    }

    /**
     * Sets the sensors for this vehicle.
     *
     * @param sensors the sensors
     */
    public void setSensors(Sensor... sensors) {
        if (sensors.length != slots.length) {
            throw new IllegalArgumentException("Incorrect number of sensors");
        }
        this.slots = sensors;
    }

    /**
     * Sets the environment this vehicle is part of.
     *
     * @param environment the environment
     */
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
