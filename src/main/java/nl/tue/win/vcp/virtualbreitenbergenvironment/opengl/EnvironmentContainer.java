package nl.tue.win.vcp.virtualbreitenbergenvironment.opengl;

import nl.tue.win.vcp.virtualbreitenbergenvironment.model.Environment;

/**
 *
 * @author maikel
 */
public interface EnvironmentContainer {
    public Environment getEnvironment();
    public void setEnvironment(Environment e);
}
