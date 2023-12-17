package com.psymk6.interfaces;

import javafx.scene.canvas.GraphicsContext;

/**
 * The Drawable interface represents an object that can be drawn on a graphical context.
 * Classes implementing this interface should provide a method to draw the object on a specified GraphicsContext.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public interface Drawable {
    /**
     * Draws the object on the specified GraphicsContext.
     *
     * @param gc The GraphicsContext on which the object will be drawn.
     */
    void draw(GraphicsContext gc);
}
