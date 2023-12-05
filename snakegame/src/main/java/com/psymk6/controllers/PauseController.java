package com.psymk6.controllers;

import com.psymk6.util.ImageUtil;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class PauseController {
    Image pauseImage = ImageUtil.getImage("pause");
    public void draw(GraphicsContext gc) {
        gc.drawImage(pauseImage, 0, 0);
    }
    public void setClick(Canvas canvas, GameController gameController) {
        canvas.setOnMouseClicked(event -> {
            double mouseX = event.getX();
            double mouseY = event.getY();
            System.out.println("clicked");
            if (isMouseInsideButton(mouseX, mouseY, 0, 0, pauseImage.getWidth(), pauseImage.getHeight())) {
                // Button clicked, perform action
                gameController.togglePauseButton();
                System.out.println("pause clicked");
            }
        });
    }
    private boolean isMouseInsideButton(double mouseX, double mouseY, double x, double y, double width, double height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
