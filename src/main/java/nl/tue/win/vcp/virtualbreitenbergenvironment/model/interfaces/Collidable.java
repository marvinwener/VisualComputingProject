package nl.tue.win.vcp.virtualbreitenbergenvironment.model.interfaces;

import nl.tue.win.vcp.virtualbreitenbergenvironment.utility.CollisionDetection.Rectangle;

/**
 * An object that may collide. As such, it provides a method to retrieve its
 * bounding box to determine whether this is the case.
 *
 * @author maikel
 */
public interface Collidable {
    public Rectangle getBoundingBox();
}
