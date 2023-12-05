package com.psymk6.controllers;

import com.psymk6.models.ScoreModel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ScoreController {
    private HBox hud;
    ScoreModel scoreModel;
    Text text;

    public ScoreController(ScoreModel scoreModel) {
        this.scoreModel = scoreModel;

//        text = new Text(scoreModel.getTextScore());
//        text.setFont(Font.font(scoreModel.getFont(), scoreModel.getFontWeight(), scoreModel.getFontSize()));
//        text.setFill(scoreModel.getFontColour());
//        text.setLayoutX(100);

    }

    public Text getText() {
        return text;
    }
    public void draw(GraphicsContext gc) {
        gc.fillText(scoreModel.getTextScore(), 650,50);
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
