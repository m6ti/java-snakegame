package com.psymk6.util;

import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import static javax.imageio.ImageIO.read;

public class GameUtil
{
	public static Image getImage(String imagePath)
	{
		URL u = GameUtil.class.getResource(imagePath);
		Image image = null;
		try{
			assert u != null;
			image = new Image(String.valueOf(u));
		}
		catch(Exception e){
			System.err.println("Error loading image path at: "+imagePath);
			e.printStackTrace();
		}
		return image;
	}

	public static Image rotateImage(final Image image, final int degree) {
		int width = (int) image.getWidth();
		int height = (int) image.getHeight();
		WritableImage rotatedImage = new WritableImage(width, height);
		PixelReader pixelReader = image.getPixelReader();
		PixelWriter pixelWriter = rotatedImage.getPixelWriter();

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

	public static Image getColoredImage(Image originalImage, Color color){
		ColorInput colorInput = new ColorInput(0, 0, originalImage.getWidth(), originalImage.getHeight(),
				color);
		Blend blend = new Blend(BlendMode.MULTIPLY);
		blend.setTopInput(colorInput);
		return applyEffect(originalImage, blend);
	}

	private static Image applyEffect(Image originalImage, Blend effect) {
		ImageView imageView = new ImageView(originalImage);
		imageView.setEffect(effect);
		WritableImage tintedImage = new WritableImage(
				(int) originalImage.getWidth(), (int) originalImage.getHeight());
		imageView.snapshot(null, tintedImage);
		return tintedImage;
	}
}
