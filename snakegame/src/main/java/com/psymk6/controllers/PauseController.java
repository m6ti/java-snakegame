package com.psymk6.controllers;

import com.psymk6.util.ImageUtil;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class PauseController {
    Image pauseImage = ImageUtil.getImage("pause");

    public void draw(GraphicsContext gc) {

        gc.setGlobalAlpha(0.5); // Set global opacity to 50%
        gc.drawImage(pauseImage, 0, 0);
        gc.setGlobalAlpha(1.0); // Reset global opacity to 100
    }
    public void setClick(GameController gameController, double mouseX, double mouseY) {
        System.out.println("clicked");
        if (isMouseInsideButton(mouseX, mouseY, 0, 0, pauseImage.getWidth(), pauseImage.getHeight())) {
            // Button clicked, perform action
            gameController.togglePauseButton();
            System.out.println("pause clicked");
        }
    }
    private boolean isMouseInsideButton(double mouseX, double mouseY, double x, double y, double width, double height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
