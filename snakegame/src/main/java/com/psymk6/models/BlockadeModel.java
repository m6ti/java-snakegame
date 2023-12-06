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

public class BlockadeModel extends CanvasObjectModel {
    private List<Image> blockadeImages = new ArrayList<Image>();
    private List<Point2D> points = new ArrayList<Point2D>();
    private int blockadeSpeed = 1;
    private final double blockadeMoveChance = 0.3;
    private int blockadeNum = 0;
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
    public int getBlockadeNum() {
        return blockadeNum;
    }

    public Bounds getBlockadeBounds(int index) {
        int xCoord = (int) getBlockadePoint(index).getX();
        int yCoord = (int) getBlockadePoint(index).getY();
        // Return the bounds where the blockade is
        return new BoundingBox(xCoord, yCoord, width, height);
    }
    public void setBlockadePoints(int x, int y, int index){
        // Change the blockade position
        points.set(index,new Point2D(x,y));
    }
    public List<Image> getBlockadeImages() {
        return blockadeImages;
    }
    public Image getBlockadeImage(int index) {
        return blockadeImages.get(index);
    }
    public Point2D getBlockadePoint(int index) {
        return points.get(index);
    }
    public int getBlockadeSpeed() {
        return blockadeSpeed;
    }
    public void setBlockadeSpeed(int blockadeSpeed) {
        this.blockadeSpeed = blockadeSpeed;
    }
    public double getBlockadeMoveChance() {
        return blockadeMoveChance;
    }
}
