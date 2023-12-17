package com.psymk6.abstracts;

/**
 * This abstract class extends the basic canvas object model to include a state indicating whether the object is alive or not.
 * Subclasses may utilize the 'isAlive' property to manage the lifecycle of the object.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public abstract class LiveCanvasObjectModel extends CanvasObjectModel{
    /**
     * Indicates whether the object is alive.
     */
    protected boolean isAlive;

    /**
     * Checks if the object is alive.
     *
     * @return {@code true} if the object is alive, {@code false} otherwise.
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * Sets the alive state of the object.
     *
     * @param alive The new alive state of the object.
     */
    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
