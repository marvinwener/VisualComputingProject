package nl.tue.win.vcp.virtualbreitenbergenvironment.gui;

import nl.tue.win.vcp.virtualbreitenbergenvironment.model.Environment;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.HeatSource;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.Preview;
import nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces.Drawable;
import nl.tue.win.vcp.virtualbreitenbergenvironment.opengl.EnvironmentMover;
import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.Vector;

/**
 *
 * @author maikel
 */
public class AddHeatFrame extends AddLightFrame {

    public AddHeatFrame(Environment environment, EnvironmentMover em) {
        super(environment, em);
        this.setTitle("Add heat source");
    }

    protected HeatSource getHeatSource() {
        final Vector position = new Vector((double) getXSpinner().getValue(),
                (double) getYSpinner().getValue(), (double) getZSpinner().getValue());
        return new HeatSource(position);
    }

    @Override
    protected void addLight() {
        Drawable preview = environment.getPreview();
        while (preview instanceof Preview) {
            preview = ((Preview) preview).getObject();
        }
        final HeatSource h;
        if (preview instanceof HeatSource) {
            h = (HeatSource) preview;
        } else {
            h = getHeatSource();
        }
        environment.addHeatSource(h);
        environment.clearPreview();
        em.select(h);
    }

    @Override
    protected void previewLight() {
        final HeatSource h = getHeatSource();
        environment.preview(h);
        em.select(h);
    }

}
