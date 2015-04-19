package nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces;

/**
 *
 * @author maikel
 */
public interface VirtualCamera {
    public void resetCamera();
    public void zoomIn();
    public void zoomOut();
    public void moveUp();
    public void moveDown();
    public void moveLeft();
    public void moveRight();
    public void moveForwards();
    public void moveBackwards();
}
