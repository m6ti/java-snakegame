package com.psymk6.controllers;

import com.psymk6.models.ScoreModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ScoreController {
    ScoreModel scoreModel;

    public ScoreController(ScoreModel scoreModel) {
        this.scoreModel = scoreModel;

    }
    public void draw(GraphicsContext gc) {
        String scoreText = scoreModel.getTextScore();

        // Set the font and fill color
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        gc.setFill(Color.BLACK);

        // Draw the text at position (10, 10)
        gc.fillText(scoreText, scoreModel.getxCoord(), scoreModel.getyCoord());
    }
    public void scoreIncrease(){
        scoreModel.setScore(scoreModel.getIntScore() + 1);
    }
}
