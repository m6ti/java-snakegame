package com.psymk6.controllers;

import com.psymk6.interfaces.Drawable;
import com.psymk6.models.BlockadeModel;
import com.psymk6.models.SnakeModel;
import javafx.scene.canvas.GraphicsContext;

import static java.lang.Math.random;

/**
 * The BlockadeController class manages the functionality of blockades in the SnakeGame.
 * It handles drawing blockades on the canvas, their movement towards the snake, and checks
 * if the snake has collided with any blockades.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public class BlockadeController implements Drawable {
    /**
     * The BlockadeModel associated with this BlockadeController.
     */
    BlockadeModel blockadeModel;

    /**
     * Constructs a BlockadeController with the specified BlockadeModel.
     *
     * @param blockadeModel The BlockadeModel associated with this controller.
     */
    public BlockadeController(BlockadeModel blockadeModel) {
        this.blockadeModel = blockadeModel;
    }

    /**
     * Draws each blockade on the canvas.
     *
     * @param gc The GraphicsContext used for drawing on the canvas.
     */
    public void draw(GraphicsContext gc) {
        // Draw each blockade
        for(int i = 0; i<blockadeModel.getBlockadeNum(); i++){
            // Draw blockades onto the canvas
            gc.drawImage(blockadeModel.getBlockadeImages().get(i),
                    blockadeModel.getBlockadePoint(i).getX(),
                    blockadeModel.getBlockadePoint(i).getY());
        }
    }

    /**
     * Moves blockades towards the snake's position based on a random chance.
     *
     * @param snakeModel The SnakeModel representing the snake in the game.
     */
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

    /**
     * Checks if the snake has collided with any blockades, causing the snake to die.
     *
     * @param snakeModel The SnakeModel representing the snake in the game.
     */
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
