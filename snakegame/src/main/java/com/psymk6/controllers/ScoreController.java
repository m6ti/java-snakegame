package com.psymk6.controllers;

import com.psymk6.interfaces.Drawable;
import com.psymk6.models.ScoreModel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ScoreController implements Drawable {
    private HBox hud;
    ScoreModel scoreModel;
    Text text;

    public ScoreController(ScoreModel scoreModel) {
        this.scoreModel = scoreModel;
    }
    public Text getText() {
        return text;
    }
    public void draw(GraphicsContext gc) {
        gc.fillText(scoreModel.getTextScore(), 700,50);
        gc.setFont(Font.font(scoreModel.getFont(), scoreModel.getFontWeight(), scoreModel.getFontSize()));
        gc.setFill(scoreModel.getFontColour());

    }
    public void drawInHud() {
        text.setText(scoreModel.getTextScore());
    }
    public void scoreIncrease(){
        scoreModel.setScore(scoreModel.getIntScore() + 1);
    }
}
