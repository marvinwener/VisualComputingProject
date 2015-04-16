package nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors;

import java.util.List;
import static java.lang.Math.*;
import static nl.tue.win.vcp.virtualbreitenbergenvironment.utility.MoreMath.*;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.LightSource;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 * Variation of the light sensor that uses a power of cosines instead of the dot
 * product (which is essentially a normal cosines.
 *
 * @author maikel
 */
public class SensitiveLightSensor extends LightSensor {

    final private int sensitivity;

    public SensitiveLightSensor(Vector position, List<LightSource> lights, int sensitivity) {
        super(position, lights);
        this.sensitivity = sensitivity;
    }

    @Override
    protected double valueFor(double dot) {
        return powercos(acos(dot), sensitivity);
    }

}
