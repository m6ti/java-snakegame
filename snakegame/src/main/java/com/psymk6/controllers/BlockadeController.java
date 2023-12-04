package com.psymk6.controllers;

import com.psymk6.models.BlockadeModel;
import com.psymk6.models.SnakeModel;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;

public class BlockadeController {
    BlockadeModel blockadeModel;
    public BlockadeController(BlockadeModel blockadeModel) {
        this.blockadeModel = blockadeModel;
    }

    public void draw(GraphicsContext gc) {
        for(int i = 0; i<blockadeModel.getBlockadeNum(); i++){
            gc.drawImage(blockadeModel.getBlockadeImages().get(i),blockadeModel.getBlockadePoint(i).getX(), blockadeModel.getBlockadePoint(i).getY());
        }
    }

    public void eaten(SnakeModel snakeModel) {
        for(int i = 0; i<blockadeModel.getBlockadeNum(); i++){
            if (snakeModel.getBounds().intersects(blockadeModel.getBounds(i)) && snakeModel.isAlive()) {
                snakeModel.setAlive(false);
                break;
            }
        }

    }
}
