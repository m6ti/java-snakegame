package com.psymk6.controllers;

import com.psymk6.interfaces.Drawable;
import com.psymk6.models.BlockadeModel;
import com.psymk6.models.SnakeModel;
import javafx.scene.canvas.GraphicsContext;
import com.psymk6.models.FoodModel;

/**
 * The FoodController class manages the functionality of the food in the SnakeGame.
 * It implements the Drawable interface.
 * It handles drawing the food, checking for intersections with the snake and blockades,
 * and updating the game state when the snake eats the food.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public class FoodController implements Drawable {
    /**
     * The FoodModel associated with this FoodController.
     */
    FoodModel foodModel;

    /**
     * Constructs a FoodController with the specified FoodModel and BlockadeModel.
     * Ensures that the food does not initially intersect with any blockades.
     *
     * @param foodModel      The FoodModel associated with this controller.
     * @param blockadeModel  The BlockadeModel associated with this controller.
     */
    public FoodController(FoodModel foodModel, BlockadeModel blockadeModel) {
        this.foodModel = foodModel;
        // Ensure food does not initially cross the blockades
        while(intersectsBlockade(blockadeModel)){
            foodModel.setRandomxCoord();
            foodModel.setRandomyCoord();
        }
    }

    /**
     * Draws the food on the canvas using the provided GraphicsContext.
     *
     * @param gc The GraphicsContext used for drawing on the canvas.
     */
    public void draw(GraphicsContext gc) {
        gc.drawImage(foodModel.getFoodImage(),foodModel.getxCoord(), foodModel.getyCoord());
    }

    /**
     * Checks if the snake has intersected with the food.
     * If yes, updates the game state by increasing the snake's length and score.
     *
     * @param snakeModel The SnakeModel to check for intersection with the food.
     * @return True if the snake intersects with the food, false otherwise.
     */
    public boolean intersectsSnake(SnakeModel snakeModel) {
        // Check if the snake has eaten the food
        if (snakeModel.getBounds().intersects(foodModel.getBounds()) && foodModel.isAlive() && snakeModel.isAlive()) {
            foodModel.setAlive(false);
            snakeModel.changeLength(snakeModel.getLength() + 1);
            snakeModel.setScore(snakeModel.getScore() + 1);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Checks if the food intersects with any of the blockades.
     *
     * @param blockadeModel The BlockadeModel to check for intersection with the food.
     * @return True if the food intersects with any blockades, false otherwise.
     */
    public boolean intersectsBlockade(BlockadeModel blockadeModel) {
        // Check if food is touching any of the blockades
        for(int i = 0; i < blockadeModel.getBlockadeNum(); i++){
            if (blockadeModel.getBlockadeBounds(i).intersects(foodModel.getBounds())) {
                return true;
            }
        }
        return false;
    }
}
