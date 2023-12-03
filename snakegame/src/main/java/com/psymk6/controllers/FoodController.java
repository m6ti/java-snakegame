package com.psymk6.controllers;

import com.psymk6.models.SnakeModel;
import javafx.scene.canvas.GraphicsContext;
import com.psymk6.models.FoodModel;

public class FoodController {
    FoodModel foodModel;

    public FoodController(FoodModel foodModel) {
        this.foodModel = foodModel;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(foodModel.getFoodImage(),foodModel.getxCoord(), foodModel.getyCoord());
    }

    public boolean eaten(SnakeModel snakeModel) {
        if (snakeModel.getBounds().intersects(foodModel.getBounds()) && foodModel.isAlive() && snakeModel.isAlive()) {
            foodModel.setAlive(false);
            snakeModel.changeLength(snakeModel.getLength() + 1);
            snakeModel.setScore(snakeModel.getScore() + 1);
            return true;
        }else{
            return false;
        }
    }

}
