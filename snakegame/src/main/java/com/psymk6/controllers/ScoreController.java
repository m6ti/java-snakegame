package com.psymk6.controllers;

import com.psymk6.interfaces.Drawable;
import com.psymk6.models.ScoreModel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ScoreController implements Drawable {
    ScoreModel scoreModel;
    Text text;

    public ScoreController(ScoreModel scoreModel) {
        this.scoreModel = scoreModel;
    }
    public void draw(GraphicsContext gc) {
        //Draw the score in top right corner of the screen.
        gc.fillText(scoreModel.getTextScore(), 700,50);
        gc.setFont(Font.font(scoreModel.getFont(), scoreModel.getFontWeight(), scoreModel.getFontSize()));
        gc.setFill(scoreModel.getFontColour());
    }
    public void increaseScore(){
        scoreModel.setScore(scoreModel.getIntScore() + 1);
    }
}
