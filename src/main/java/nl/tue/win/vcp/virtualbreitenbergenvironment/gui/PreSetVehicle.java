package nl.tue.win.vcp.virtualbreitenbergenvironment.gui;

import java.awt.Color;
import java.util.Objects;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JSlider;

/**
 * Contains pre-set vehicles (type, sensors, etc.) for easy use in GUI.
 *
 * @author maikel
 */
public class PreSetVehicle {

    public final static PreSetVehicle CUSTOM = new PreSetVehicle();
    public final static PreSetVehicle[] PRESET_VEHICLES = new PreSetVehicle[]{
        new PreSetVehicle("Three-wheel",
        "Light", "Light",
        "Sensor 1", "Sensor 2", Color.RED,
        "Light-seeking three-wheel vehicle"),
        new PreSetVehicle("Three-wheel",
        "Light", "Light",
        "Sensor 2", "Sensor 1", Color.CYAN,
        "Light-fleeing three-wheel vehicle"),
        new PreSetVehicle("Three-wheel",
        "Temperature", "Temperature",
        "Sensor 2", "Sensor 1", Color.BLUE,
        "Heat-fleeing three-wheel vehicle"),
        new PreSetVehicle("Three-wheel",
        "Random", "Random",
        "Sensor 1", "Sensor 2", Color.GREEN,
        "Random three-wheel vehicle", false),
        new PreSetVehicle("Three-wheel",
        "Dummy", "Dummy",
        "Sensor 1", "Sensor 2", Color.GRAY,
        "Static three-wheel vehicle", false),
        new PreSetVehicle("Two-wheel",
        "Light", "Light",
        "Sensor 1", "Sensor 2", Color.RED,
        "Light-seeking two-wheel vehicle"),
        new PreSetVehicle("Two-wheel",
        "Light", "Light",
        "Sensor 2", "Sensor 1", Color.RED,
        "Light-fleeing two-wheel vehicle"),
        new PreSetVehicle("Two-wheel",
        "Temperature", "Temperature",
        "Sensor 2", "Sensor 1", Color.RED,
        "Heat-fleeing two-wheel vehicle"),
        new PreSetVehicle("Two-wheel",
        "Random", "Random",
        "Sensor 1", "Sensor 2", Color.RED,
        "Random two-wheel vehicle", false),
        new PreSetVehicle("Two-wheel",
        "Dummy", "Dummy",
        "Sensor 1", "Sensor 2", Color.RED,
        "Static two-wheel vehicle", false),
        CUSTOM
    };

    private final String vehicleType;
    private final String sensor1;
    private final String sensor2;
    private final String leftWheel;
    private final String rightWheel;
    private final String name;
    private final boolean sensorOrderMatters;
    private final Color color;

    private PreSetVehicle() {
        this(null, null, null, null, null, Color.RED, "Custom");
    }

    private PreSetVehicle(String vehicle, String sensor1, String sensor2,
            String leftWheel, String rightWheel, Color color, String name) {
        this(vehicle, sensor1, sensor2, leftWheel, rightWheel, color, name, true);
    }

    private PreSetVehicle(String vehicle, String sensor1, String sensor2,
            String leftWheel, String rightWheel, Color color, String name,
            boolean sensorOrderMatters) {
        this.vehicleType = vehicle;
        this.sensor1 = sensor1;
        this.sensor2 = sensor2;
        this.leftWheel = leftWheel;
        this.rightWheel = rightWheel;
        this.name = name;
        this.sensorOrderMatters = sensorOrderMatters;
        this.color = color;
    }

    private PreSetVehicle(JComboBox vehicleTypeComboBox,
            JComboBox sensor1ComboBox, JComboBox sensor2ComboBox,
            JComboBox leftWheelComboBox, JComboBox rightWheelComboBox) {
        this.vehicleType = (String) vehicleTypeComboBox.getSelectedItem();
        this.sensor1 = (String) sensor1ComboBox.getSelectedItem();
        this.sensor2 = (String) sensor2ComboBox.getSelectedItem();
        this.leftWheel = (String) leftWheelComboBox.getSelectedItem();
        this.rightWheel = (String) rightWheelComboBox.getSelectedItem();
        this.name = null;
        this.sensorOrderMatters = true;
        this.color = Color.RED;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PreSetVehicle other = (PreSetVehicle) obj;
        if (!Objects.equals(this.vehicleType, other.vehicleType)) {
            return false;
        }
        if (!Objects.equals(this.sensor1, other.sensor1)) {
            return false;
        }
        if (!Objects.equals(this.sensor2, other.sensor2)) {
            return false;
        }
        if (this.sensorOrderMatters && other.sensorOrderMatters) {
            if (!Objects.equals(this.leftWheel, other.leftWheel)) {
                return false;
            }
            if (!Objects.equals(this.rightWheel, other.rightWheel)) {
                return false;
            }
        }
        return true;
    }

    public boolean isApplied(JComboBox vehicleTypeComboBox,
            JComboBox sensor1ComboBox, JComboBox sensor2ComboBox,
            JComboBox leftWheelComboBox, JComboBox rightWheelComboBox) {
        return this.equals(new PreSetVehicle(vehicleTypeComboBox,
                sensor1ComboBox, sensor2ComboBox,
                leftWheelComboBox, rightWheelComboBox));
    }

    public Color apply(JComboBox vehicleTypeComboBox,
            JComboBox sensor1ComboBox, JCheckBox fluctuation1CheckBox,
            JComboBox sensor2ComboBox, JCheckBox fluctuation2CheckBox,
            JComboBox leftWheelComboBox, JComboBox rightWheelComboBox,
            JSlider angleSlider) {
        if (this == CUSTOM) {
            return null;
        }
        vehicleTypeComboBox.setSelectedItem(this.vehicleType);
        sensor1ComboBox.setSelectedItem(this.sensor1);
        sensor2ComboBox.setSelectedItem(this.sensor2);
        leftWheelComboBox.setSelectedItem(this.leftWheel);
        rightWheelComboBox.setSelectedItem(this.rightWheel);
        assert isApplied(vehicleTypeComboBox, sensor1ComboBox, sensor2ComboBox,
                leftWheelComboBox, rightWheelComboBox) :
                "Settings weren't successfully applied to all fields.";
        return color;
    }

    public boolean isSensorOrderMatters() {
        return sensorOrderMatters;
    }
}
