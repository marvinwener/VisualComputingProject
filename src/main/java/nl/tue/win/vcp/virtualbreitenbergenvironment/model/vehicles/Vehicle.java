package nl.tue.win.vcp.virtualbreitenbergenvironment.model.vehicles;

import java.io.Serializable;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors.DummySensor;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors.Sensor;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.Environment;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Drawable;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Movable;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * Represents a Breitenberg vehicle.
 *
 * @author maikel
 */
public abstract class Vehicle extends Movable implements Drawable, Serializable {

    protected Vector position;
    protected float angle;
    protected Sensor[] slots;
    protected static final Vector INITIAL_DIRECTION = Vector.Y;
    protected Environment environment;
    public static boolean RANDOM = false; // indicates whether random behaviour is enabled

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
        return getDirection(angle);
    }
    
    @Override
    public Vector getPosition() {
        return position;
    }
    
    public static Vector getDirection(float angle) {
        assert INITIAL_DIRECTION.length() == 1;
        return Vector.rotate(INITIAL_DIRECTION, Vector.O, angle);
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

    @Override
    public String toString() {
        return "Vehicle at" + position;
    }
}
