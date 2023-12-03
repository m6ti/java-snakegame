package com.psymk6.models;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ScoreModel {
    public int xCoord;
    public int yCoord;
    String score;
    int intScore;
    public ScoreModel() {
        this.intScore = 0;
        this.xCoord = 30;
        this.yCoord = 30;
        this.score = "Score: 0";
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
