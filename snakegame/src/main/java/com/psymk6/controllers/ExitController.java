package com.psymk6.controllers;

import com.psymk6.abstracts.ClickableButton;
import com.psymk6.interfaces.Drawable;
import com.psymk6.util.ImageUtil;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.IOException;

public class ExitController extends ClickableButton implements Drawable {
    Image homeImage = ImageUtil.getImage("home");
    int pauseImageWidth = (int) ImageUtil.getImage("pause").getWidth();
    public void draw(GraphicsContext gc) {
        // Draw home image in 50% opacity
        gc.setGlobalAlpha(0.5);
        gc.drawImage(homeImage, pauseImageWidth, 0);
        // Reset global opacity to 100%
        gc.setGlobalAlpha(1.0);
    }
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
