package nl.tue.win.vcp.virtualbreitenbergenvironment.gui;

import nl.tue.win.vcp.virtualbreitenbergenvironment.model.Environment;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.HeatSource;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 *
 * @author maikel
 */
public class AddHeatFrame extends AddLightFrame {

    public AddHeatFrame(Environment environment) {
        super(environment);
        this.setTitle("Add heat source");
    }
    
    protected HeatSource getHeatSource() {
        final Vector position = new Vector((double) getXSpinner().getValue(),
                (double) getYSpinner().getValue(), (double) getZSpinner().getValue());
        return new HeatSource(position);
    }

    @Override
    protected void addLight() {
        environment.addHeatSource(getHeatSource());
        environment.clearPreview();
    }

    @Override
    protected void previewLight() {
        environment.preview(getHeatSource());
    }
    
}
