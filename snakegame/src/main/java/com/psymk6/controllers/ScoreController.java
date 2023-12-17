package com.psymk6.controllers;

import com.psymk6.interfaces.Drawable;
import com.psymk6.models.ScoreModel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * The ScoreController class manages the display and manipulation of the game score in SnakeGame.
 * It implements the Drawable interface, providing methods to draw the score on the screen and
 * increase the score when needed.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public class ScoreController implements Drawable {
    /**
     * The ScoreModel associated with this ScoreController.
     */
    ScoreModel scoreModel;

    /**
     * Constructs a ScoreController with the specified ScoreModel.
     *
     * @param scoreModel The ScoreModel instance associated with the score controller.
     */
    public ScoreController(ScoreModel scoreModel) {
        this.scoreModel = scoreModel;
    }

    /**
     * Draws the current score on the specified GraphicsContext.
     * The score is displayed in the top right corner of the screen.
     *
     * @param gc The GraphicsContext on which the score is drawn.
     */
    public void draw(GraphicsContext gc) {
        //Draw the score in top right corner of the screen.
        gc.fillText(scoreModel.getTextScore(), 700,50);
        gc.setFont(Font.font(scoreModel.getFont(), scoreModel.getFontWeight(), scoreModel.getFontSize()));
        gc.setFill(scoreModel.getFontColour());
    }

    /**
     * Increases the current score by 1.
     */
    public void increaseScore(){
        scoreModel.setScore(scoreModel.getIntScore() + 1);
    }
}
