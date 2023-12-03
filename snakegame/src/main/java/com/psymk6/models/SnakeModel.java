package com.psymk6.models;

import com.psymk6.util.ImageUtil;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SnakeModel
{
    public int xCoord;
    public int yCoord;
    public Image snakeBody, snakeHead;
    public double width;
    public double height;

    public boolean isAlive;

    // Leikjabreytan.
    public int speed_XY;
    public int length;
    public double num;
    public int score = 0;
    public final Image IMG_SNAKE_HEAD = ImageUtil.getImage("snake-head-right");
    public List<Point2D> bodyPoints = new LinkedList<>();
    public boolean up, down, left, right = true;

    public SnakeModel(int x, int y){
        this.isAlive = true;
        this.xCoord = x;
        this.yCoord = y;
        this.snakeBody = ImageUtil.getImage("snake-body");
        this.width = snakeBody.getWidth();
        this.height = snakeBody.getHeight();
        this.speed_XY = 5;
        this.length = 1;

        this.num = width / speed_XY;
        this.snakeHead = IMG_SNAKE_HEAD;
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


    public Bounds getBounds() {
        return new BoundingBox(xCoord, yCoord, width, height);
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

    public int getSpeed_XY() {
        return speed_XY;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }

    public void setSpeed_XY(int speed_XY) {
        this.speed_XY = speed_XY;
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
