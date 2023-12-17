package com.psymk6.abstracts;

import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

/**
 * This abstract class represents a basic model for objects on a canvas,
 * providing properties such as coordinates, width, and height.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public abstract class CanvasObjectModel {
    /**
     * The x-coordinate of the object.
     */
    protected int xCoord;

    /**
     * The y-coordinate of the object.
     */
    protected int yCoord;

    /**
     * The width of the object.
     */
    protected double width;

    /**
     * The height of the object.
     */
    protected double height;

    /**
     * Gets the x-coordinate of the object.
     *
     * @return The x-coordinate of the object.
     */
    public int getxCoord() {
        return xCoord;
    }

    /**
     * Sets the x-coordinate of the object.
     *
     * @param xCoord The new x-coordinate of the object.
     */
    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * Gets the y-coordinate of the object.
     *
     * @return The y-coordinate of the object.
     */
    public int getyCoord() {
        return yCoord;
    }

    /**
     * Sets the y-coordinate of the object.
     *
     * @param yCoord The new y-coordinate of the object.
     */
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    /**
     * Gets the width of the object.
     *
     * @return The width of the object.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the width of the object.
     *
     * @param width The new width of the object.
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Gets the height of the object.
     *
     * @return The height of the object.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of the object.
     *
     * @param height The new height of the object.
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Gets the bounds of the object as a {@code Bounds} object.
     *
     * @return The bounds of the object.
     */
    public Bounds getBounds() {
        return new BoundingBox(xCoord, yCoord, width, height);
    }

}
