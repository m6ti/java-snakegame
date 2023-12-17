package com.psymk6.controllers;
import com.psymk6.abstracts.ClickableButton;
import com.psymk6.interfaces.Drawable;
import com.psymk6.util.ImageUtil;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * The PauseController class handles the functionality of the pause button in the SnakeGame.
 * It extends ClickableButton and implements Drawable, providing methods to draw the pause button
 * and handle mouse clicks to pause the game.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public class PauseController extends ClickableButton implements Drawable  {
    /**
     * The image representing the pause button.
     */
    Image pauseImage = ImageUtil.getImage("pause");

    /**
     * Draws the pause button on the specified GraphicsContext.
     * The button is drawn with 50% opacity.
     *
     * @param gc The GraphicsContext on which the pause button is drawn.
     */
    public void draw(GraphicsContext gc) {
        // Draw the pause image in 50% opacity
        gc.setGlobalAlpha(0.5);
        gc.drawImage(pauseImage, 0, 0);
        // Reset global opacity to 100%
        gc.setGlobalAlpha(1.0);
    }

    /**
     * Handles the mouse click event on the pause button.
     * If the click occurs within the button bounds, it toggles the game's pause state.
     *
     * @param gameController The GameController instance associated with the game.
     * @param mouseX         The x-coordinate of the mouse click.
     * @param mouseY         The y-coordinate of the mouse click.
     */
    public void setClick(GameController gameController, double mouseX, double mouseY) {
        if (isMouseInsideButton(mouseX, mouseY, 0, 0, pauseImage.getWidth(), pauseImage.getHeight())) {
            // Button clicked, pause the game
            gameController.togglePauseButton();
        }
    }
}
