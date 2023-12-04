package com.psymk6.models;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import static javafx.scene.text.FontWeight.BOLD;

public class ScoreModel {
    public int xCoord;
    public int yCoord;
    String score;
    int intScore;
    String font;
    FontWeight fontWeight;
    int fontSize;
    Color fontColour;

    public ScoreModel() {
        this.intScore = 0;
        this.xCoord = 30;
        this.yCoord = 40;
        this.score = "Score: 0";
        this.font = "Helvetica";
        this.fontWeight = BOLD;
        this.fontSize = 40;
        this.fontColour = Color.BLACK;
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

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
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
