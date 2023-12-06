package com.psymk6.models;

import com.psymk6.abstracts.LiveCanvasObjectModel;
import com.psymk6.util.ImageUtil;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;

public class SnakeModel extends LiveCanvasObjectModel
{
    public Image snakeBody, snakeHead;
    public int speed;
    public int length;
    public double num;
    public int score = 0;
    public final Image IMG_SNAKE_HEAD = ImageUtil.getImage("snake-head-right");
    public List<Point2D> bodyPoints = new LinkedList<>();
    public boolean up, down, left, right = true;

    public SnakeModel(int x, int y){
        // Initialise the snake image
        this.snakeBody = ImageUtil.getImage("snake-body");
        // Set the initial position
        setxCoord(x);
        setyCoord(y);
        // Initialise other details
        this.width = snakeBody.getWidth();
        this.height = snakeBody.getHeight();
        this.speed = 5;
        this.length = 1;
        this.num = width / speed;
        this.snakeHead = IMG_SNAKE_HEAD;
        // Set to alive
        setAlive(true);
    }

    public int getLength()
    {
        return length;
    }
    public void changeLength(int length)
    {
        this.length = length;
    }
    public int getBodyPointSize(){
        return bodyPoints.size();
    }
    public List<Point2D> getBodyPoints(){
        return bodyPoints;
    }
    public Point2D getBodyPoint(int i){
        return bodyPoints.get(i);
    }

    public int getSpeed() {
        return speed;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public Image getSnakeBody() {
        return snakeBody;
    }

    public void setSnakeBody(Image snakeBody) {
        this.snakeBody = snakeBody;
    }

    public Image getSnakeHead() {
        return snakeHead;
    }

    public void setSnakeHead(Image snakeHead) {
        this.snakeHead = snakeHead;
    }

    public Image getIMG_SNAKE_HEAD() {
        return IMG_SNAKE_HEAD;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
