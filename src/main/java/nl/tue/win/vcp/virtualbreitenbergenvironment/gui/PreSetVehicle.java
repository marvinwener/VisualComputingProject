package nl.tue.win.vcp.virtualbreitenbergenvironment.gui;

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
        new PreSetVehicle("Two-wheel",
                "Light", true,
                "Light", true,
                "Sensor 1", "Sensor 2", 0,
                "Light-seeking vehicle"),
        new PreSetVehicle("Two-wheel",
                "Light", true,
                "Light", true,
                "Sensor 2", "Sensor 1", 0,
                "Light-fleeing vehicle"),
        new PreSetVehicle("Two-wheel",
                "Temperature", true,
                "Temperature", true,
                "Sensor 2", "Sensor 1", 0,
                "Heat-fleeing vehicle"),
        CUSTOM
    };

    private final String vehicleType;
    private final String sensor1;
    private final boolean fluctuation1;
    private final String sensor2;
    private final boolean fluctuation2;
    private final String leftWheel;
    private final String rightWheel;
    private final int angle;
    private final String name;

    private PreSetVehicle() {
        this(null, null, false, null, false, null, null, -1, "Custom");
    }

    private PreSetVehicle(String vehicle, String sensor1, boolean fluctuation1,
            String sensor2, boolean fluctuation2,
            String leftWheel, String rightWheel, int angle, String name) {
        this.vehicleType = vehicle;
        this.sensor1 = sensor1;
        this.fluctuation1 = fluctuation1;
        this.sensor2 = sensor2;
        this.fluctuation2 = fluctuation2;
        this.leftWheel = leftWheel;
        this.rightWheel = rightWheel;
        this.angle = angle;
        this.name = name;
    }

    private PreSetVehicle(JComboBox vehicleTypeComboBox,
            JComboBox sensor1ComboBox, JCheckBox fluctuation1CheckBox,
            JComboBox sensor2ComboBox, JCheckBox fluctuation2CheckBox,
            JComboBox leftWheelComboBox, JComboBox rightWheelComboBox,
            JSlider angleSlider) {
        this.vehicleType = (String) vehicleTypeComboBox.getSelectedItem();
        this.sensor1 = (String) sensor1ComboBox.getSelectedItem();
        this.fluctuation1 = fluctuation1CheckBox.isSelected();
        this.sensor2 = (String) sensor2ComboBox.getSelectedItem();
        this.fluctuation2 = fluctuation2CheckBox.isSelected();
        this.leftWheel = (String) leftWheelComboBox.getSelectedItem();
        this.rightWheel = (String) rightWheelComboBox.getSelectedItem();
        this.angle = angleSlider.getValue();
        this.name = null;
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
        if (this.fluctuation1 != other.fluctuation1) {
            return false;
        }
        if (!Objects.equals(this.sensor2, other.sensor2)) {
            return false;
        }
        if (this.fluctuation2 != other.fluctuation2) {
            return false;
        }
        if (!Objects.equals(this.leftWheel, other.leftWheel)) {
            return false;
        }
        if (!Objects.equals(this.rightWheel, other.rightWheel)) {
            return false;
        }
        if (this.angle != other.angle) {
            return false;
        }
        return true;
    }

    public void apply(JComboBox vehicleTypeComboBox,
            JComboBox sensor1ComboBox, JCheckBox fluctuation1CheckBox,
            JComboBox sensor2ComboBox, JCheckBox fluctuation2CheckBox,
            JComboBox leftWheelComboBox, JComboBox rightWheelComboBox,
            JSlider angleSlider) {
        if (this == CUSTOM) return;
        vehicleTypeComboBox.setSelectedItem(this.vehicleType);
        sensor1ComboBox.setSelectedItem(this.sensor1);
        fluctuation1CheckBox.setSelected(fluctuation1);
        sensor2ComboBox.setSelectedItem(this.sensor2);
        fluctuation2CheckBox.setSelected(fluctuation2);
        leftWheelComboBox.setSelectedItem(this.leftWheel);
        rightWheelComboBox.setSelectedItem(this.rightWheel);
        angleSlider.setValue(angle);
        assert this.equals(new PreSetVehicle(vehicleTypeComboBox,
                sensor1ComboBox, fluctuation1CheckBox, sensor2ComboBox,
                fluctuation2CheckBox, leftWheelComboBox, rightWheelComboBox,
                angleSlider)) :
                "Settings weren't successfully applied to all fields.";
    }

}
