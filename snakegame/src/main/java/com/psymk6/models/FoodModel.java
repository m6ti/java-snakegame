package com.psymk6.models;

import com.psymk6.abstracts.LiveCanvasObjectModel;
import com.psymk6.util.ImageUtil;
import javafx.scene.image.Image;

import java.util.Random;

public class FoodModel extends LiveCanvasObjectModel
{
	protected Image foodImage;
	public FoodModel() {
		// Set a random food image
		int randomIndex = new Random().nextInt(16);
		foodImage = ImageUtil.getImage(String.valueOf(randomIndex));
		// Initialise width and height
		this.width = foodImage.getWidth();
		this.height = foodImage.getHeight();
		// Set a random position within the canvas
		xCoord = ((int) (Math.random() * (870 - getWidth() + 10)));
		yCoord = ((int) (Math.random() * (560 - getHeight() - 40)));
		// Set to alive
		setAlive(true);
	}
	public void setRandomxCoord(){
		this.xCoord = ((int) (Math.random() * (870 - getWidth() + 10)));
	}
	public void setRandomyCoord(){
		yCoord = ((int) (Math.random() * (560 - getHeight() - 40)));
	}
	public Image getFoodImage() {
		return foodImage;
	}
	public void setFoodImage(Image foodImage) {
		this.foodImage = foodImage;
	}
}
