package com.psymk6.abstracts;

import com.psymk6.controllers.GameController;

/**
 * This abstract class represents a clickable button that can be interacted with in a graphical user interface.
 * Subclasses must implement the setClick method to handle button clicks.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public abstract class ClickableButton {
    /**
     * Sets the action to be performed when the button is clicked.
     *
     * @param gameController The game controller managing the game state.
     * @param mouseX         The x-coordinate of the mouse click.
     * @param mouseY         The y-coordinate of the mouse click.
     */
    public abstract void setClick(GameController gameController, double mouseX, double mouseY);

    /**
     * Checks if the mouse is inside the button based on the provided coordinates and dimensions.
     *
     * @param mouseX The x-coordinate of the mouse.
     * @param mouseY The y-coordinate of the mouse.
     * @param x      The x-coordinate of the button.
     * @param y      The y-coordinate of the button.
     * @param width  The width of the button.
     * @param height The height of the button.
     * @return {@code true} if the mouse is inside the button, {@code false} otherwise.
     */
    protected boolean isMouseInsideButton(double mouseX, double mouseY, double x, double y, double width, double height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
