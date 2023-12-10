package com.psymk6.models;

import com.psymk6.abstracts.LiveCanvasObjectModel;
import com.psymk6.util.ImageUtil;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.List;

/**
 * The SnakeModel class represents the model for the snake object in the game.
 * It extends the LiveCanvasObjectModel and provides properties such as speed, length, direction, and body points.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public class SnakeModel extends LiveCanvasObjectModel
{
    /**
     * The image representing the snake's body.
     */
    public Image snakeBody;

    /**
     * The image representing the snake's head.
     */
    public Image snakeHead;

    /**
     * The speed of the snake.
     */
    public int speed;

    /**
     * The length of the snake.
     */
    public int length;

    /**
     * The width/height ratio for the snake's body parts.
     */
    public double num;

    /**
     * The current score of the snake.
     */
    public int score = 0;

    /**
     * The constant image representing the snake's head facing right.
     */
    public final Image IMG_SNAKE_HEAD = ImageUtil.getImage("snake-head-right");

    /**
     * The list of body points representing the snake's body.
     */
    public List<Point2D> bodyPoints = new LinkedList<>();

    /**
     * Boolean indicating if the snake is moving up.
     */
    public boolean up;

    /**
     * Boolean indicating if the snake is moving down.
     */
    public boolean down;

    /**
     * Boolean indicating if the snake is moving left.
     */
    public boolean left;

    /**
     * Boolean indicating if the snake is moving right.
     */
    public boolean right = true;


    /**
     * Constructs a SnakeModel with the specified initial position.
     *
     * @param x The initial x-coordinate.
     * @param y The initial y-coordinate.
     */
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

    /**
     * Gets the length of the snake.
     *
     * @return The length of the snake.
     */
    public int getLength()
    {
        return length;
    }

    /**
     * Changes the length of the snake.
     *
     * @param length The new length.
     */
    public void changeLength(int length)
    {
        this.length = length;
    }

    /**
     * Gets the size of the body points list.
     *
     * @return The size of the body points list.
     */
    public int getBodyPointSize(){
        return bodyPoints.size();
    }

    /**
     * Gets the list of body points representing the snake's body.
     *
     * @return The list of body points.
     */
    public List<Point2D> getBodyPoints(){
        return bodyPoints;
    }

    /**
     * Gets a specific body point at the given index.
     *
     * @param i The index of the body point.
     * @return The body point at the given index.
     */
    public Point2D getBodyPoint(int i){
        return bodyPoints.get(i);
    }

    /**
     * Gets the speed of the snake.
     *
     * @return The speed of the snake.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the length of the snake.
     *
     * @param length The new length.
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Gets the width/height ratio for the snake's body parts.
     *
     * @return The width/height ratio.
     */
    public double getNum() {
        return num;
    }

    /**
     * Checks if the snake is moving up.
     *
     * @return True if the snake is moving up, false otherwise.
     */
    public boolean isUp() {
        return up;
    }

    /**
     * Sets the snake's upward movement direction.
     *
     * @param up True to set the upward movement, false otherwise.
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    /**
     * Checks if the snake is moving down.
     *
     * @return True if the snake is moving down, false otherwise.
     */
    public boolean isDown() {
        return down;
    }

    /**
     * Sets the snake's downward movement direction.
     *
     * @param down True to set the downward movement, false otherwise.
     */
    public void setDown(boolean down) {
        this.down = down;
    }

    /**
     * Checks if the snake is moving left.
     *
     * @return True if the snake is moving left, false otherwise.
     */
    public boolean isLeft() {
        return left;
    }

    /**
     * Sets the snake's leftward movement direction.
     *
     * @param left True to set the leftward movement, false otherwise.
     */
    public void setLeft(boolean left) {
        this.left = left;
    }

    /**
     * Checks if the snake is moving right.
     *
     * @return True if the snake is moving right, false otherwise.
     */
    public boolean isRight() {
        return right;
    }

    /**
     * Sets the snake's rightward movement direction.
     *
     * @param right True to set the rightward movement, false otherwise.
     */
    public void setRight(boolean right) {
        this.right = right;
    }

    /**
     * Gets the image representing the snake's body.
     *
     * @return The image representing the snake's body.
     */
    public Image getSnakeBody() {
        return snakeBody;
    }

    /**
     * Gets the image representing the snake's head.
     *
     * @return The image representing the snake's head.
     */
    public Image getSnakeHead() {
        return snakeHead;
    }

    /**
     * Sets the image representing the snake's head.
     *
     * @param snakeHead The new image for the snake's head.
     */
    public void setSnakeHead(Image snakeHead) {
        this.snakeHead = snakeHead;
    }

    /**
     * Gets the constant image representing the snake's head facing right.
     *
     * @return The constant image representing the snake's head facing right.
     */
    public Image getIMG_SNAKE_HEAD() {
        return IMG_SNAKE_HEAD;
    }

    /**
     * Gets the current score of the snake.
     *
     * @return The current score of the snake.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score of the snake.
     *
     * @param score The new score.
     */
    public void setScore(int score) {
        this.score = score;
    }
}
