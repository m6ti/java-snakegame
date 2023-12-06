package com.psymk6.controllers;
import com.psymk6.abstracts.ClickableButton;
import com.psymk6.interfaces.Drawable;
import com.psymk6.util.ImageUtil;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class PauseController extends ClickableButton implements Drawable  {
    Image pauseImage = ImageUtil.getImage("pause");
    public void draw(GraphicsContext gc) {
        // Draw the pause image in 50% opacity
        gc.setGlobalAlpha(0.5);
        gc.drawImage(pauseImage, 0, 0);
        // Reset global opacity to 100%
        gc.setGlobalAlpha(1.0);
    }
    public void setClick(GameController gameController, double mouseX, double mouseY) {
        if (isMouseInsideButton(mouseX, mouseY, 0, 0, pauseImage.getWidth(), pauseImage.getHeight())) {
            // Button clicked, pause the game
            gameController.togglePauseButton();
        }
    }
}
