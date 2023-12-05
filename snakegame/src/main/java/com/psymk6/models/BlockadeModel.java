package com.psymk6.models;

import com.psymk6.util.ImageUtil;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockadeModel {
    double width;
    double height;
    List<Image> blockadeImages = new ArrayList<Image>();
    private List<Point2D> points = new ArrayList<Point2D>();
    int blockadeSpeed = 1;
    double blockadeMoveChance = 0.3;
    public int getBlockadeNum() {
        return blockadeNum;
    }

    public void setBlockadeNum(int blockadeNum) {
        this.blockadeNum = blockadeNum;
    }
    int blockadeLoopNum = 0;
    int blockadeNum = 0;
    public BlockadeModel(int level) {
        if(level == 1){
            this.blockadeLoopNum = 3;
        }
        else{
            this.blockadeLoopNum = 4;
        }
        for(int i = 0; i<this.blockadeLoopNum;i++){
            addBlockade();
        }
        this.width = blockadeImages.get(0).getWidth();
        this.height = blockadeImages.get(0).getHeight();

    }
    public void addBlockade() {
        int tempxCoord = ((int) (Math.random() * (670 - getWidth() + 10)+200));
        int tempyCoord = ((int) (Math.random() * (360 - getHeight() - 40))+200);

        int randomIndex = new Random().nextInt(17,19);
        Image blockageImage = ImageUtil.getImage(String.valueOf(randomIndex));

        blockadeImages.add(blockageImage);
        points.add(new Point2D(tempxCoord,tempyCoord));
        blockadeNum += 1;
    }
    public Bounds getBounds(int index) {
        int xCoord = (int) getBlockadePoint(index).getX();
        int yCoord = (int) getBlockadePoint(index).getY();
        return new BoundingBox(xCoord, yCoord, width, height);
    }
    public void setBlockadePoints(int x, int y, int index){
        points.set(index,new Point2D(x,y));
    }

    public List<Image> getBlockadeImages() {
        return blockadeImages;
    }
    public List<Point2D> getBlockadePoints() {
        return points;
    }


    public Image getBlockadeImage(int index) {
        return blockadeImages.get(index);
    }
    public Point2D getBlockadePoint(int index) {
        return points.get(index);
    }
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
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
