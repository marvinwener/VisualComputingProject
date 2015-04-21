package nl.tue.win.vcp.virtualbreitenbergenvironment.gui;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JColorChooser;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.Environment;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.Preview;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.*;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors.LightSensor;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors.Sensor;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.vehicles.Vehicle;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors.DummySensor;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors.RandomSensor;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors.TemperatureSensor;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.sensors.UnstableSensor;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.vehicles.ThreeWheelVehicle;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.vehicles.TwoWheelVehicle;
import nl.tue.win.vcp.virtualbreitenbergenvironment.opengl.EnvironmentMover;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 *
 * @author maikel
 */
public class AddVehicleFrame extends javax.swing.JFrame {

    /**
     * Creates new form AddVehicleFrame
     *
     * @param environment the environment to add the vehicle to
     * @param em environment mover that allows for selection of objects
     */
    public AddVehicleFrame(Environment environment, final EnvironmentMover em) {
        initComponents();
        this.environment = environment;
        this.em = em;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                AddVehicleFrame.this.environment.clearPreview();
                em.select(Movable.NULL);
            }
        });
        this.getRootPane().setDefaultButton(jButton1);
        jButton1.requestFocus();
        update();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vehicleTypeComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        sensor1ComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        sensor2ComboBox = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        leftWheelComboBox = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        rightWheelComboBox = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        angleSlider = new javax.swing.JSlider();
        jCheckBox1 = new javax.swing.JCheckBox();
        fluctuation1CheckBox = new javax.swing.JCheckBox();
        fluctuation2CheckBox = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add vehicle");
        setLocationByPlatform(true);

        vehicleTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Three-wheel", "Two-wheel" }));
        vehicleTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehicleTypeComboBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Type of vehicle");

        jLabel2.setText("Sensor 1");

        sensor1ComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Light", "Temperature", "Dummy", "Random" }));
        sensor1ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sensor1ComboBoxActionPerformed(evt);
            }
        });

        jLabel3.setText("Sensor 2");

        sensor2ComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Light", "Temperature", "Dummy", "Random" }));
        sensor2ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sensor2ComboBoxActionPerformed(evt);
            }
        });

        jLabel4.setText("Left wheel");

        leftWheelComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sensor 1", "Sensor 2" }));
        leftWheelComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftWheelComboBoxActionPerformed(evt);
            }
        });

        jLabel5.setText("Right wheel");

        rightWheelComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Sensor 1", "Sensor 2" }));
        rightWheelComboBox.setSelectedIndex(1);
        rightWheelComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightWheelComboBoxActionPerformed(evt);
            }
        });

        jButton1.setText("Add to environment and close");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Angle");

        angleSlider.setMaximum(359);
        angleSlider.setValue(0);
        angleSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                angleSliderStateChanged(evt);
            }
        });

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Preview");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        fluctuation1CheckBox.setSelected(true);
        fluctuation1CheckBox.setText("Add random fluctuation");

        fluctuation2CheckBox.setSelected(true);
        fluctuation2CheckBox.setText("Add random fluctuation");

        jButton2.setText("Reverse sensor positions");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setText("Pre-set vehicle");

        jComboBox6.setModel(presetsModel);
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });

        jButton3.setText("Customize color...");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(sensor2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fluctuation2CheckBox))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(sensor1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fluctuation1CheckBox))
                                    .addComponent(vehicleTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rightWheelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(leftWheelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton2))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(angleSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(vehicleTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(sensor1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fluctuation1CheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sensor2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(fluctuation2CheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(leftWheelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rightWheelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(angleSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jCheckBox1))
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addVehicle();
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void angleSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_angleSliderStateChanged
        update();
    }//GEN-LAST:event_angleSliderStateChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        update();
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void vehicleTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehicleTypeComboBoxActionPerformed
        update();
    }//GEN-LAST:event_vehicleTypeComboBoxActionPerformed

    private void sensor1ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sensor1ComboBoxActionPerformed
        update();
    }//GEN-LAST:event_sensor1ComboBoxActionPerformed

    private void sensor2ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sensor2ComboBoxActionPerformed
        update();
    }//GEN-LAST:event_sensor2ComboBoxActionPerformed

    private void leftWheelComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftWheelComboBoxActionPerformed
        update();
    }//GEN-LAST:event_leftWheelComboBoxActionPerformed

    private void rightWheelComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rightWheelComboBoxActionPerformed
        update();
    }//GEN-LAST:event_rightWheelComboBoxActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
        PreSetVehicle selectedItem = (PreSetVehicle) jComboBox6.getSelectedItem();
        selectedItem.apply(this.vehicleTypeComboBox,
                this.sensor1ComboBox, this.fluctuation1CheckBox,
                this.sensor2ComboBox, this.fluctuation2CheckBox,
                this.leftWheelComboBox,
                this.rightWheelComboBox,
                this.angleSlider
        );
        update();
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        final Object sel1 = leftWheelComboBox.getSelectedItem();
        final Object sel2 = rightWheelComboBox.getSelectedItem();
        rightWheelComboBox.setSelectedItem(sel1);
        leftWheelComboBox.setSelectedItem(sel2);
        update();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        final Color chosen = JColorChooser.showDialog(
                this,
                "Choose Vehicle Color",
                color);
        color = chosen == null ? color : chosen;
        update();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void update() {
        final boolean checked = this.jCheckBox1.isSelected();
        if (checked) {
            previewVehicle();
        } else {
            environment.clearPreview();
        }
        jButton1.setEnabled(this.isConsistent());
        PreSetVehicle preSetVehicle = getPreSetVehicle();
        jComboBox6.setSelectedItem(preSetVehicle);
        final boolean enabled = preSetVehicle.isSensorOrderMatters();
        leftWheelComboBox.setEnabled(enabled);
        rightWheelComboBox.setEnabled(enabled);
        jButton2.setEnabled(enabled);
    }

    public PreSetVehicle getPreSetVehicle() {
        for (PreSetVehicle psv : PreSetVehicle.PRESET_VEHICLES) {
            if (psv.isApplied(vehicleTypeComboBox, sensor1ComboBox, sensor2ComboBox, leftWheelComboBox, rightWheelComboBox)) {
                return psv;
            }
        }
        return PreSetVehicle.CUSTOM;
    }

    private void addVehicle() {
        Drawable preview = environment.getPreview();
        while (preview instanceof Preview) {
            preview = ((Preview) preview).getObject();
        }
        final Vehicle v;
        if (preview instanceof Vehicle) {
            v = (Vehicle) preview;
        } else {
            v = getVehicle();
        }
        environment.addVehicle(v);
        environment.clearPreview();
        em.select(v);
    }

    private void previewVehicle() {
        Vehicle v = getVehicle();
        environment.preview(v);
        em.select(v);
    }

    private Vehicle getVehicle() {
        final String vehicle = (String) vehicleTypeComboBox.getSelectedItem();
        switch (vehicle) {
            case "Two-wheel":
                return getVehicle(true);
            case "Three-wheel":
                return getVehicle(false);
            default:
                throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    private Vehicle getVehicle(boolean twoWheel) {
        Vector initialPosition = Vector.O;
        float initialAngle = (float) Math.toRadians(angleSlider.getValue());
        final String sensorType1 = (String) sensor1ComboBox.getSelectedItem();
        final String sensorType2 = (String) sensor2ComboBox.getSelectedItem();
        Vehicle v = twoWheel
                ? new TwoWheelVehicle(initialPosition, initialAngle)
                : new ThreeWheelVehicle(initialPosition, initialAngle, color);
        Vector[] sensorLocations = v.getSensorLocations();
        Sensor[] sensors = {getSensor(sensorType1, sensorLocations[0]),
            getSensor(sensorType2, sensorLocations[1])};
        if (fluctuation1CheckBox.isSelected()) {
            // enable fluctuation for first sensor
            sensors[0] = new UnstableSensor(sensors[0]);
        }
        if (fluctuation2CheckBox.isSelected()) {
            // enable fluctuation for second sensor
            sensors[1] = new UnstableSensor(sensors[1]);
        }
        v.setSensors(sensors[leftWheelComboBox.getSelectedIndex()],
                sensors[rightWheelComboBox.getSelectedIndex()]);
        return v;
    }

    private Sensor getSensor(String sensorType, Vector sensorLocation) {
        switch (sensorType) {
            case "Light":
                return new LightSensor(sensorLocation, environment.getLights());
            case "Temperature":
                return new TemperatureSensor(sensorLocation,
                        environment.getHeatSources());
            case "Dummy":
                return DummySensor.instance;
            case "Random":
                return new RandomSensor();
            default:
                throw new UnsupportedOperationException("Selected sensor type"
                        + " is not supported.");
        }
    }

    private boolean isConsistent() {
        return !leftWheelComboBox.getSelectedItem().equals(rightWheelComboBox.getSelectedItem());
    }

    private final Environment environment;
    private final EnvironmentMover em;
    private final ComboBoxModel<PreSetVehicle> presetsModel
            = new DefaultComboBoxModel<>(PreSetVehicle.PRESET_VEHICLES);
    private Color color = Color.RED;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider angleSlider;
    private javax.swing.JCheckBox fluctuation1CheckBox;
    private javax.swing.JCheckBox fluctuation2CheckBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox leftWheelComboBox;
    private javax.swing.JComboBox rightWheelComboBox;
    private javax.swing.JComboBox sensor1ComboBox;
    private javax.swing.JComboBox sensor2ComboBox;
    private javax.swing.JComboBox vehicleTypeComboBox;
    // End of variables declaration//GEN-END:variables
}
