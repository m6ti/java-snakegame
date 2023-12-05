package com.psymk6.models;

import com.psymk6.util.ImageUtil;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;

import java.util.Random;

public class FoodModel
{
	public int xCoord;
	public int yCoord;
	public Image foodImage;
	double width;
	double height;
	public boolean isAlive;

	public FoodModel() {
		setAlive(true);

		int randomIndex = new Random().nextInt(16);
		foodImage = ImageUtil.getImage(String.valueOf(randomIndex));

		this.width = foodImage.getWidth();
		this.height = foodImage.getHeight();

		xCoord = ((int) (Math.random() * (870 - getWidth() + 10)));
		yCoord = ((int) (Math.random() * (560 - getHeight() - 40)));
	}

	public void setRandomxCoord(){
		this.xCoord = ((int) (Math.random() * (870 - getWidth() + 10)));
	}
	public void setRandomyCoord(){
		yCoord = ((int) (Math.random() * (560 - getHeight() - 40)));
	}
	public Bounds getBounds() {
		return new BoundingBox(xCoord, yCoord, width, height);
	}

	public int getxCoord() {
		return xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	public Image getFoodImage() {
		return foodImage;
	}

	public void setFoodImage(Image foodImage) {
		this.foodImage = foodImage;
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

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean alive) {
		isAlive = alive;
	}
}
