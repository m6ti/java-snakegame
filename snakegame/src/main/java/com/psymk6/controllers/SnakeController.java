package com.psymk6.controllers;

import com.psymk6.interfaces.Drawable;
import com.psymk6.models.ScoreModel;
import com.psymk6.models.SnakeModel;
import com.psymk6.util.GameUtil;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The SnakeController class manages the behavior and movement of the snake in the SnakeGame.
 * It handles user input for controlling the snake's direction and updates its position accordingly.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public class SnakeController implements Drawable {
    /**
     * The SnakeModel associated with this SnakeController.
     */
    SnakeModel snakeModel;

    /**
     * The Scene associated with the SnakeController.
     */
    Scene scene;

    /**
     * Constructs a SnakeController with the specified SnakeModel and Stage.
     *
     * @param snakeModel The SnakeModel associated with the snake.
     * @param stage      The Stage from which the Scene is obtained for handling key events.
     */
    public SnakeController( SnakeModel snakeModel, Stage stage) {
        this.snakeModel = snakeModel;
        this.scene = stage.getScene();
        keyPressed();
    }

    /**
     * Listens to key presses and updates the snake's direction accordingly.
     */
    public void keyPressed() {
        // Listen to key presses
        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    // Ensure snake cannot reverse direction in one go
                    if (!snakeModel.isDown()) {
                        // Change the direction of movement to up
                        snakeModel.setUp(true);
                        snakeModel.setLeft(false);
                        snakeModel.setRight(false);
                        snakeModel.setSnakeHead(GameUtil.rotateImage(snakeModel.getIMG_SNAKE_HEAD(), -90));
                    }
                    break;
                case DOWN:
                    // Ensure snake cannot reverse direction in one go
                    if (!snakeModel.isUp()) {
                        // Change the direction of movement to down
                        snakeModel.setDown(true);
                        snakeModel.setLeft(false);;
                        snakeModel.setRight(false);
                        snakeModel.setSnakeHead(GameUtil.rotateImage(snakeModel.getIMG_SNAKE_HEAD(), 90));
                    }
                    break;
                case LEFT:
                    // Ensure snake cannot reverse direction in one go
                    if (!snakeModel.isRight()) {
                        // Change the direction of movement to left
                        snakeModel.setUp(false);
                        snakeModel.setDown(false);
                        snakeModel.setLeft(true);
                        snakeModel.setSnakeHead(GameUtil.rotateImage(snakeModel.getIMG_SNAKE_HEAD(), -180));
                    }
                    break;

                case RIGHT:
                    // Ensure snake cannot reverse direction in one go
                    if (!snakeModel.isLeft()) {
                        // Change the direction of movement to right
                        snakeModel.setUp(false);
                        snakeModel.setDown(false);
                        snakeModel.setRight(true);
                        snakeModel.setSnakeHead(snakeModel.getIMG_SNAKE_HEAD());
                    }
                default: {
                    break;
                }
            }
        });
    }

    /**
     * Moves the snake by changing its coordinates based on its current direction and speed.
     */
    public void move() {
        // Move the snake by changing coordinates with the variable speed
        if (snakeModel.isUp()) {
            snakeModel.setyCoord(snakeModel.getyCoord()-snakeModel.getSpeed());
        } else if (snakeModel.isDown()) {
            snakeModel.setyCoord(snakeModel.getyCoord()+snakeModel.getSpeed());
        } else if (snakeModel.isLeft()) {
            snakeModel.setxCoord(snakeModel.getxCoord()-snakeModel.getSpeed());
        } else if (snakeModel.isRight()) {
            snakeModel.setxCoord(snakeModel.getxCoord()+snakeModel.getSpeed());
        }
    }

    /**
     * Draws the snake on the canvas, updating its position and appearance.
     *
     * @param gc The GraphicsContext used for drawing on the canvas.
     */
    @Override
    public void draw(GraphicsContext gc) {
        // Check if snake is out of bounds
        outOfBounds();
        // Check if snake has eaten its own body
        eatBody();
        // Shift the snake points along to the new coordinates.
        // Firstly add a new point to the snake
        snakeModel.getBodyPoints().add(new Point2D(snakeModel.getxCoord(), snakeModel.getyCoord()));
        // Secondly, remove last point if the snake has not eaten a food
        if (snakeModel.getBodyPointSize() == (snakeModel.getLength() + 1) * snakeModel.getNum()) {
            snakeModel.getBodyPoints().remove(0);
        }
        // Draw the snake head in the updated position
        gc.drawImage(snakeModel.getSnakeHead(), snakeModel.getxCoord(), snakeModel.getyCoord());
        // Draw the rest of the body
        drawBody(gc);
        // Move the snake
        move();
    }

    /**
     * Checks if the snake has hit a blockade and updates its state accordingly.
     *
     * @param blockadeController The BlockadeController to check for collisions with the snake.
     */
    public void checkHitBlockade(BlockadeController blockadeController) {
        // Check if snake has hit a blockade
        blockadeController.eaten(snakeModel);
    }

    /**
     * Checks if the snake has eaten its own body, and sets its state to not alive if true.
     */
    public void eatBody() {
        // Check if any points in snake body intersect
        for (Point2D point1 : snakeModel.getBodyPoints()) {
            for (Point2D point2 : snakeModel.getBodyPoints()) {
                if (point1.equals(point2) && point1 != point2) {
                    // If points intersect, then the snake dies
                    snakeModel.setAlive(false);
                }
            }
        }
    }

    /**
     * Draws the body of the snake on the canvas.
     *
     * @param gc The GraphicsContext used for drawing on the canvas.
     */
    public void drawBody(GraphicsContext gc) {
        // Set temporary length to the current length
        int tempLength = (snakeModel.getBodyPointSize() - 1 - ((int) snakeModel.getNum()));
        for (int i = tempLength; i >= snakeModel.getNum(); i -= (int) snakeModel.getNum()) {
            Point2D point = snakeModel.getBodyPoint(i);
            // Draw each point on the snake
            gc.drawImage(snakeModel.getSnakeBody(), point.getX(), point.getY());
        }
    }

    /**
     * Checks if the snake is out of bounds and sets its state to not alive if true.
     */
    private void outOfBounds() {
        // Check if snake has left the canvas.
        boolean xOut = (snakeModel.getxCoord() <= 0 || snakeModel.getxCoord() >= (870 - snakeModel.getWidth()));
        boolean yOut = (snakeModel.getyCoord() <= 0 || snakeModel.getyCoord() >= (560 - snakeModel.getHeight()));
        if (xOut || yOut) {
            snakeModel.setAlive(false);
        }
    }
}
