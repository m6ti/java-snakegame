package com.psymk6.controllers;

import com.psymk6.interfaces.Drawable;
import com.psymk6.util.ImageUtil;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;

public class ExitController implements Drawable {
    Image homeImage = ImageUtil.getImage("home");
    int pauseImageWidth = (int) ImageUtil.getImage("pause").getWidth();
    private GameController gameController;

    public ExitController(GameController gameController) {
        this.gameController = gameController;

    }
    public void draw(GraphicsContext gc) {
        gc.setGlobalAlpha(0.5); // Set global opacity to 50%
        gc.drawImage(homeImage, pauseImageWidth, 0);
        gc.setGlobalAlpha(1.0); // Reset global opacity to 100
    }
    public void setClick(GameController gameController,double mouseX, double mouseY) {
        if(gameController.isPaused()){
            System.out.println("clicked");
            if (isMouseInsideButton(mouseX, mouseY, pauseImageWidth, 0, pauseImageWidth+homeImage.getWidth(), homeImage.getHeight())) {
                // Button clicked, perform action
                System.out.println("home clicked");

                try {
                    gameController.homeButtonClicked();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
    private boolean isMouseInsideButton(double mouseX, double mouseY, double x, double y, double width, double height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }
}
