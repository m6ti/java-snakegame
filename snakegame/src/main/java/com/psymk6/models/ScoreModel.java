package com.psymk6.models;

import com.psymk6.abstracts.CanvasObjectModel;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static javafx.scene.text.FontWeight.BLACK;
import static javafx.scene.text.FontWeight.BOLD;

/**
 * The ScoreModel class represents the model for the score in the SnakeGame.
 * It manages the score's value, font properties, and color.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public class ScoreModel extends CanvasObjectModel {
    /**
     * The textual representation of the score.
     */
    String score;

    /**
     * The numeric score value.
     */
    int intScore;

    /**
     * The font family of the score.
     */
    String font;

    /**
     * The font weight of the score.
     */
    FontWeight fontWeight;

    /**
     * The font size of the score.
     */
    int fontSize;

    /**
     * The font color of the score.
     */
    Color fontColour;

    /**
     * Constructs a ScoreModel instance, initializing the score to 0 and setting default font properties.
     */
    public ScoreModel() {
        // Initialise the score to 0
        this.intScore = 0;
        // Initialise the text
        this.score = "Score: 0";
        this.font = "Helvetica";
        this.fontWeight = BOLD;
        this.fontSize = 30;
        this.fontColour = new Color(0,0,0,0.5);
    }

    /**
     * Gets the font color of the score.
     *
     * @return The font color.
     */
    public Color getFontColour() {
        return fontColour;
    }

    /**
     * Gets the font family of the score.
     *
     * @return The font family.
     */
    public String getFont() {
        return font;
    }

    /**
     * Gets the font weight of the score.
     *
     * @return The font weight.
     */
    public FontWeight getFontWeight() {
        return fontWeight;
    }

    /**
     * Gets the font size of the score.
     *
     * @return The font size.
     */
    public int getFontSize() {
        return fontSize;
    }

    /**
     * Gets the textual representation of the score.
     *
     * @return The score as text.
     */
    public String getTextScore() {
        return score;
    }


    /**
     * Sets the numeric score value.
     *
     * @param score The new score value.
     */
    public void setScore(int score) {
        this.intScore = score;
        this.score = "Score: "+score;
    }


    /**
     * Gets the numeric score value.
     *
     * @return The numeric score value.
     */
    public int getIntScore() {
        return intScore;
    }
}
