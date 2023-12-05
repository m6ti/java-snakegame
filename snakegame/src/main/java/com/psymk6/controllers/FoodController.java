package com.psymk6.controllers;

import com.psymk6.interfaces.Drawable;
import com.psymk6.models.BlockadeModel;
import com.psymk6.models.SnakeModel;
import javafx.scene.canvas.GraphicsContext;
import com.psymk6.models.FoodModel;

public class FoodController implements Drawable {
    FoodModel foodModel;
    public FoodController(FoodModel foodModel, BlockadeModel blockadeModel) {
        this.foodModel = foodModel;
        while(intersectsBlockade(blockadeModel)){
            foodModel.setRandomxCoord();
            foodModel.setRandomyCoord();
        }
    }
    public void draw(GraphicsContext gc) {
        gc.drawImage(foodModel.getFoodImage(),foodModel.getxCoord(), foodModel.getyCoord());
    }
    public boolean intersectsSnake(SnakeModel snakeModel) {
        if (snakeModel.getBounds().intersects(foodModel.getBounds()) && foodModel.isAlive() && snakeModel.isAlive()) {
            foodModel.setAlive(false);
            snakeModel.changeLength(snakeModel.getLength() + 1);
            snakeModel.setScore(snakeModel.getScore() + 1);
            return true;
        }else{
            return false;
        }
    }
    public boolean intersectsBlockade(BlockadeModel blockadeModel) {
        for(int i = 0; i < blockadeModel.getBlockadeNum(); i++){
            if (blockadeModel.getBounds(i).intersects(foodModel.getBounds())) {
                return true;
            }
        }
        return false;
    }
}
