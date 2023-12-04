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
    List<Image> blockageImages = new ArrayList<Image>();
    private List<Point2D> points = new ArrayList<Point2D>();

    public int getBlockadeNum() {
        return blockadeNum;
    }

    public void setBlockadeNum(int blockadeNum) {
        this.blockadeNum = blockadeNum;
    }

    int blockadeNum = 0;
    public BlockadeModel() {
        addBlockade();
        addBlockade();
        addBlockade();

        this.width = blockageImages.get(0).getWidth();
        this.height = blockageImages.get(0).getHeight();

    }
    public void addBlockade() {
        int tempxCoord = ((int) (Math.random() * (670 - getWidth() + 10)+200));
        int tempyCoord = ((int) (Math.random() * (360 - getHeight() - 40))+200);

        int randomIndex = new Random().nextInt(17,19);
        Image blockageImage = ImageUtil.getImage(String.valueOf(randomIndex));

        blockageImages.add(blockageImage);
        points.add(new Point2D(tempxCoord,tempyCoord));
        blockadeNum += 1;
    }
    public Bounds getBounds(int index) {
        int xCoord = (int) getBlockadePoint(index).getX();
        int yCoord = (int) getBlockadePoint(index).getY();
        return new BoundingBox(xCoord, yCoord, width, height);
    }

    public List<Image> getBlockadeImages() {
        return blockageImages;
    }
    public List<Point2D> getBlockadePoints() {
        return points;
    }

    public Image getBlockadeImage(int index) {
        return blockageImages.get(index);
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


}
