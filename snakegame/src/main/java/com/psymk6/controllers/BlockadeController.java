package com.psymk6.controllers;

import com.psymk6.interfaces.Drawable;
import com.psymk6.models.BlockadeModel;
import com.psymk6.models.SnakeModel;
import javafx.scene.canvas.GraphicsContext;

import static java.lang.Math.random;

public class BlockadeController implements Drawable {
    BlockadeModel blockadeModel;
    public BlockadeController(BlockadeModel blockadeModel) {
        this.blockadeModel = blockadeModel;
    }

    public void draw(GraphicsContext gc) {
        // Draw each blockade
        for(int i = 0; i<blockadeModel.getBlockadeNum(); i++){
            // Draw blockades onto the canvas
            gc.drawImage(blockadeModel.getBlockadeImages().get(i),
                    blockadeModel.getBlockadePoint(i).getX(),
                    blockadeModel.getBlockadePoint(i).getY());
        }
    }
    public void moveTowardsSnake(SnakeModel snakeModel) {
        for (int i = 0; i < blockadeModel.getBlockadeNum(); i++) {
            // Introduce a chance for each blockade to not move
            if (random() < blockadeModel.getBlockadeMoveChance()) {
                double deltaX = snakeModel.getxCoord() - blockadeModel.getBlockadePoint(i).getX();
                double deltaY = snakeModel.getyCoord() - blockadeModel.getBlockadePoint(i).getY();
                // Calculate the direction of movement
                double directionX = Math.signum(deltaX);
                double directionY = Math.signum(deltaY);
                // Update the positions of the blockade towards the direction of the snake
                int newX = (int) (blockadeModel.getBlockadePoint(i).getX() + directionX * blockadeModel.getBlockadeSpeed());
                int newY = (int) (blockadeModel.getBlockadePoint(i).getY() + directionY * blockadeModel.getBlockadeSpeed());
                blockadeModel.setBlockadePoints(newX, newY, i);
            }
        }
    }
    public void eaten(SnakeModel snakeModel) {
        // Check if snake has crossed into a blockade
        for(int i = 0; i<blockadeModel.getBlockadeNum(); i++){
            if (snakeModel.getBounds().intersects(blockadeModel.getBlockadeBounds(i)) && snakeModel.isAlive()) {
                // Snake dies if it touches blockade
                snakeModel.setAlive(false);
                break;
            }
        }

    }

}
