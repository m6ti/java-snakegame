package com.psymk6.controllers;

import com.psymk6.abstracts.ClickableButton;
import com.psymk6.interfaces.Drawable;
import com.psymk6.util.ImageUtil;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;

/**
 * The ExitController class manages the functionality of the exit button in the SnakeGame.
 * It extends ClickableButton and implements the Drawable interface.
 * It handles drawing the exit button and responding to clicks within its area when the game is paused.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public class ExitController extends ClickableButton implements Drawable {
    /**
     * The image representing the home button for exiting the game.
     */
    Image homeImage = ImageUtil.getImage("home");

    /**
     * The width of the pause image used to determine the position of the exit button.
     */
    int pauseImageWidth = (int) ImageUtil.getImage("pause").getWidth();

    /**
     * Draws the exit button on the canvas with 50% opacity.
     *
     * @param gc The GraphicsContext used for drawing on the canvas.
     */
    public void draw(GraphicsContext gc) {
        // Draw home image in 50% opacity
        gc.setGlobalAlpha(0.5);
        gc.drawImage(homeImage, pauseImageWidth, 0);
        // Reset global opacity to 100%
        gc.setGlobalAlpha(1.0);
    }

    /**
     * Sets up the click functionality for the exit button.
     * If the game is paused and the click is inside the exit button area, it triggers the homeButtonClicked method in GameController.
     *
     * @param gameController The GameController associated with the game.
     * @param mouseX         The x-coordinate of the mouse click.
     * @param mouseY         The y-coordinate of the mouse click.
     */
    public void setClick(GameController gameController,double mouseX, double mouseY) {
        // Only allow the click in the area if the game is paused
        if(gameController.isPaused()){
            if (isMouseInsideButton(mouseX, mouseY, pauseImageWidth, 0, pauseImageWidth+homeImage.getWidth(), homeImage.getHeight())) {
                // Button clicked, return to the menu
                try {
                    gameController.homeButtonClicked();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
