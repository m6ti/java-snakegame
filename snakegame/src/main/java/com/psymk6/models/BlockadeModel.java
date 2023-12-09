package com.psymk6.models;

import com.psymk6.abstracts.CanvasObjectModel;
import com.psymk6.util.ImageUtil;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The BlockadeModel class represents the model for the blockades in the SnakeGame.
 * It manages the blockades' images, positions, and other related attributes.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public class BlockadeModel extends CanvasObjectModel {
    /**
     * The list of images representing different blockades.
     */
    private List<Image> blockadeImages = new ArrayList<Image>();

    /**
     * The list of positions (coordinates) for each blockade.
     */
    private List<Point2D> points = new ArrayList<>();

    /**
     * The speed of the blockades.
     */
    private int blockadeSpeed = 1;

    /**
     * The chance for each blockade to move.
     */
    private final double blockadeMoveChance = 0.3;

    /**
     * The number of blockades.
     */
    private int blockadeNum = 0;

    /**
     * Constructs a BlockadeModel instance based on the specified game level.
     *
     * @param level The level of the game (1 or 2).
     */
    public BlockadeModel(int level) {
        int blockadeLoopNum = 0;
        if(level == 1){
            // Level one has three blockades
            blockadeLoopNum = 3;
        } else{
            // Level two has four blockades
            blockadeLoopNum = 4;
        }
        for(int i = 0; i < blockadeLoopNum; i++){
            // Add the correct amount of blockades
            addBlockade();
        }
        // Set width and height to that of a blockade
        this.width = blockadeImages.get(0).getWidth();
        this.height = blockadeImages.get(0).getHeight();

    }

    /**
     * Adds a new blockade with a random image and position.
     */
    public void addBlockade() {
        // Create random coordinates that are not in the top left corner of the canvas
        int tempxCoord = ((int) (Math.random() * (670 - getWidth() + 10)+200));
        int tempyCoord = ((int) (Math.random() * (360 - getHeight() - 40))+200);
        // Assign a random image to the blockade
        int randomIndex = new Random().nextInt(17,19);
        Image blockageImage = ImageUtil.getImage(String.valueOf(randomIndex));
        blockadeImages.add(blockageImage);
        // Add to the list of blockades
        points.add(new Point2D(tempxCoord,tempyCoord));
        // Increase the number of blockades
        blockadeNum += 1;
    }

    /**
     * Gets the number of blockades.
     *
     * @return The number of blockades.
     */
    public int getBlockadeNum() {
        return blockadeNum;
    }

    /**
     * Gets the bounds of a specific blockade based on its index.
     *
     * @param index The index of the blockade.
     * @return The Bounds of the blockade.
     */
    public Bounds getBlockadeBounds(int index) {
        int xCoord = (int) getBlockadePoint(index).getX();
        int yCoord = (int) getBlockadePoint(index).getY();
        // Return the bounds where the blockade is
        return new BoundingBox(xCoord, yCoord, width, height);
    }

    /**
     * Sets the position of a specific blockade based on its index.
     *
     * @param x     The x-coordinate.
     * @param y     The y-coordinate.
     * @param index The index of the blockade.
     */
    public void setBlockadePoints(int x, int y, int index){
        // Change the blockade position
        points.set(index,new Point2D(x,y));
    }

    /**
     * Gets the list of blockade images.
     *
     * @return The list of blockade images.
     */
    public List<Image> getBlockadeImages() {
        return blockadeImages;
    }

    /**
     * Gets the position of a specific blockade based on its index.
     *
     * @param index The index of the blockade.
     * @return The position of the blockade.
     */
    public Point2D getBlockadePoint(int index) {
        return points.get(index);
    }

    /**
     * Gets the speed of the blockades.
     *
     * @return The speed of the blockades.
     */
    public int getBlockadeSpeed() {
        return blockadeSpeed;
    }

    /**
     * Gets the chance for each blockade to move.
     *
     * @return The chance for each blockade to move.
     */
    public double getBlockadeMoveChance() {
        return blockadeMoveChance;
    }
}
