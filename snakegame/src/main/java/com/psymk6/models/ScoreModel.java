package com.psymk6.models;

import com.psymk6.abstracts.CanvasObjectModel;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static javafx.scene.text.FontWeight.BLACK;
import static javafx.scene.text.FontWeight.BOLD;

public class ScoreModel extends CanvasObjectModel {
    String score;
    int intScore;
    String font;
    FontWeight fontWeight;
    int fontSize;
    Color fontColour;
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
    public Color getFontColour() {
        return fontColour;
    }
    public void setFontColour(Color fontColour) {
        this.fontColour = fontColour;
    }
    public String getFont() {
        return font;
    }
    public void setFont(String font) {
        this.font = font;
    }
    public FontWeight getFontWeight() {
        return fontWeight;
    }
    public void setFontWeight(FontWeight fontWeight) {
        this.fontWeight = fontWeight;
    }
    public int getFontSize() {
        return fontSize;
    }
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
    public String getTextScore() {
        return score;
    }
    public void setScore(int score) {
        this.intScore = score;
        this.score = "Score: "+score;
    }
    public int getIntScore() {
        return intScore;
    }
}
