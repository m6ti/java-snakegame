package com.psymk6.util;

import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import java.net.URL;

/**
 * The GameUtil class provides utility methods for handling game-related tasks, such as loading images,
 * rotating images, tinting images, and creating styled buttons.
 *
 * @author Mateusz Klocek
 * @version 1.0
 */
public class GameUtil
{
	/**
	 * Retrieves an image from the specified file path.
	 *
	 * @param imagePath The file path of the image.
	 * @return The loaded image.
	 */
	public static Image getImage(String imagePath)
	{
		// Locate the image
		URL u = GameUtil.class.getResource(imagePath);
		Image image = null;
		try{
			// Try to get the image
			assert u != null;
			image = new Image(String.valueOf(u));
		}
		catch(Exception e){
			// Catch exception if failed
			System.err.println("Error loading image path at: "+imagePath);
			e.printStackTrace();
		}
		return image;
	}

	/**
	 * Rotates the given image by the specified degree.
	 *
	 * @param image  The image to rotate.
	 * @param degree The rotation degree.
	 * @return The rotated image.
	 */
	public static Image rotateImage(final Image image, final int degree) {
		// Get the image details
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		// Create a new writeable image
		WritableImage rotatedImage = new WritableImage(width, height);
		PixelReader pixelReader = image.getPixelReader();
		PixelWriter pixelWriter = rotatedImage.getPixelWriter();
		// For each pixel, rotate the image
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int rotatedX = rotateX(x, y, width, height, degree);
				int rotatedY = rotateY(x, y, width, height, degree);
				pixelWriter.setArgb(rotatedX, rotatedY, pixelReader.getArgb(x, y));
			}
		}
		return rotatedImage;
	}
	private static int rotateX(int x, int y, int width, int height, int degree) {
		return (int) ((x - width / 2) * Math.cos(Math.toRadians(degree))
				- (y - height / 2) * Math.sin(Math.toRadians(degree)) + width / 2);
	}
	private static int rotateY(int x, int y, int width, int height, int degree) {
		return (int) ((x - width / 2) * Math.sin(Math.toRadians(degree))
				+ (y - height / 2) * Math.cos(Math.toRadians(degree)) + height / 2);
	}


	/**
	 * Tints the given image with the specified color.
	 *
	 * @param originalImage The original image.
	 * @param color         The color to apply.
	 * @return The tinted image.
	 */
	public static Image getColoredImage(Image originalImage, Color color){
		ColorInput colorInput = new ColorInput(0, 0, originalImage.getWidth(), originalImage.getHeight(),
				color);
		// Create a multiply blend
		Blend blend = new Blend(BlendMode.MULTIPLY);
		blend.setTopInput(colorInput);
		// Returned blended image
		return applyEffect(originalImage, blend);
	}

	/**
	 * Applies the given blend effect to the provided image.
	 *
	 * @param originalImage The original image.
	 * @param effect        The blend effect to apply.
	 * @return The image with the applied effect.
	 */
	private static Image applyEffect(Image originalImage, Blend effect) {
		// Create a new imageview
		ImageView imageView = new ImageView(originalImage);
		// Apply effect to imageview
		imageView.setEffect(effect);
		WritableImage tintedImage = new WritableImage(
				(int) originalImage.getWidth(), (int) originalImage.getHeight());
		imageView.snapshot(null, tintedImage);
		// Return a snapshot of the imageview
		return tintedImage;
	}

	/**
	 * Creates a styled button with the specified text.
	 *
	 * @param text The text to display on the button.
	 * @return The styled button.
	 */
	public static Button createStyledButton(String text) {
		// Return a red button with Consolas font
		Button button = new Button(text);
		button.setStyle("-fx-font-family: 'Consolas'; -fx-font-size: 16; -fx-background-color: #FF0000; -fx-text-fill: white;");
		return button;
	}
}
