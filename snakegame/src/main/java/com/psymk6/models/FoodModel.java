package com.psymk6.models;

import com.psymk6.abstracts.LiveCanvasObjectModel;
import com.psymk6.util.ImageUtil;
import javafx.scene.image.Image;

import java.util.Random;

/**
 * The FoodModel class represents the model for the food in the SnakeGame.
 * It manages the food's image, position, and other related attributes.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public class FoodModel extends LiveCanvasObjectModel
{
	/**
	 * The image representing the food.
	 */
	protected Image foodImage;

	/**
	 * Constructs a FoodModel instance, setting a random food image and position.
	 */
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

	/**
	 * Sets a new random x-coordinate for the food.
	 */
	public void setRandomxCoord(){
		this.xCoord = ((int) (Math.random() * (870 - getWidth() + 10)));
	}

	/**
	 * Sets a new random y-coordinate for the food.
	 */
	public void setRandomyCoord(){
		yCoord = ((int) (Math.random() * (560 - getHeight() - 40)));
	}

	/**
	 * Gets the image of the food.
	 *
	 * @return The image of the food.
	 */
	public Image getFoodImage() {
		return foodImage;
	}
}
