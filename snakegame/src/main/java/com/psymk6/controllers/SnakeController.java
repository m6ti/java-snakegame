package com.psymk6.controllers;

import com.psymk6.interfaces.Drawable;
import com.psymk6.models.SnakeModel;
import com.psymk6.util.GameUtil;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SnakeController implements Drawable {
    SnakeModel snakeModel;
    Scene scene;
    public SnakeController( SnakeModel snakeModel, Stage stage) {
        this.snakeModel = snakeModel;
        this.scene = stage.getScene();

        keyPressed();
    }
    public void keyPressed() {
        scene.setOnKeyPressed(e -> {
            switch (e.getCode())
            {
                case UP:
                    if (!snakeModel.isDown())
                    {
                        snakeModel.setUp(true);
                        snakeModel.setLeft(false);
                        snakeModel.setRight(false);
                        snakeModel.setSnakeHead(GameUtil.rotateImage(snakeModel.IMG_SNAKE_HEAD, -90));
                    }
                    break;

                case DOWN:
                    if (!snakeModel.isUp())
                    {
                        snakeModel.setDown(true);
                        snakeModel.setLeft(false);;
                        snakeModel.setRight(false);
                        snakeModel.setSnakeHead(GameUtil.rotateImage(snakeModel.IMG_SNAKE_HEAD, 90));
                    }
                    break;

                case LEFT:
                    if (!snakeModel.isRight())
                    {
                        snakeModel.setUp(false);
                        snakeModel.setDown(false);
                        snakeModel.setLeft(true);
                        snakeModel.setSnakeHead(GameUtil.rotateImage(snakeModel.IMG_SNAKE_HEAD, -180));
                    }
                    break;

                case RIGHT:
                    if (!snakeModel.isLeft())
                    {
                        snakeModel.setUp(false);
                        snakeModel.setDown(false);
                        snakeModel.setRight(true);
                        snakeModel.setSnakeHead(snakeModel.getIMG_SNAKE_HEAD());
                    }
                default:
                    break;
            }
        });
    }
    public void move() {
        if (snakeModel.isUp())
        {
            snakeModel.setyCoord(snakeModel.getyCoord()-snakeModel.getSpeed_XY());
        } else if (snakeModel.isDown())
        {
            snakeModel.setyCoord(snakeModel.getyCoord()+snakeModel.getSpeed_XY());
        } else if (snakeModel.isLeft())
        {
            snakeModel.setxCoord(snakeModel.getxCoord()-snakeModel.getSpeed_XY());
        } else if (snakeModel.isRight())
        {
            snakeModel.setxCoord(snakeModel.getxCoord()+snakeModel.getSpeed_XY());
        }
    }

    public void draw(GraphicsContext gc) {
        outOfBounds();
        eatBody();

        snakeModel.getBodyPoints().add(new Point2D(snakeModel.getxCoord(), snakeModel.getyCoord()));

        if (snakeModel.getBodyPointSize() == (snakeModel.getLength() + 1) * snakeModel.getNum())
        {
            snakeModel.getBodyPoints().remove(0);
        }
        gc.drawImage(snakeModel.getSnakeHead(), snakeModel.getxCoord(), snakeModel.getyCoord());

        drawBody(gc);
        move();
    }
    public void checkHitBlockade(BlockadeController blockadeController) {
        blockadeController.eaten(snakeModel);
    }
    public void eatBody() {
        for (Point2D point1 : snakeModel.getBodyPoints())
        {
            for (Point2D point2 : snakeModel.getBodyPoints())
            {
                if (point1.equals(point2) && point1 != point2)
                {
                    snakeModel.setAlive(false);
                }
            }
        }
    }

    public void drawBody(GraphicsContext gc) {
        int tempLength = (snakeModel.getBodyPointSize() - 1 - ((int) snakeModel.getNum()));

        for (int i = tempLength; i >= snakeModel.getNum(); i -= (int) snakeModel.getNum())
        {
            Point2D point = snakeModel.getBodyPoint(i);

            gc.drawImage(snakeModel.getSnakeBody(), point.getX(), point.getY());
        }
    }

    private void outOfBounds() {
        boolean xOut = (snakeModel.getxCoord() <= 0 || snakeModel.getxCoord() >= (870 - snakeModel.getWidth()));
        boolean yOut = (snakeModel.getyCoord() <= 0 || snakeModel.getyCoord() >= (560 - snakeModel.getHeight()));
        if (xOut || yOut)
        {
            snakeModel.setAlive(false);
        }
    }
}
