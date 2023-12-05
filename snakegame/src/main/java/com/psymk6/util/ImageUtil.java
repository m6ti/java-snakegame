package com.psymk6.util;


import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;
public class ImageUtil
{
	private static final Map<String, Image> images = new HashMap<>();

	static
	{
		//Snake
		String prefix = "/assets/images/";
		images.put("snake-head-right", GameUtil.getImage(prefix+"snake-head-right.png"));
		images.put("snake-body", GameUtil.getImage(prefix+"snake-body.png"));
		images.put("snake-logo", GameUtil.getImage(prefix+"snake-logo.png"));
		//Obstacles
		images.put("0", GameUtil.getImage(prefix+"food-kiwi.png"));
		images.put("1", GameUtil.getImage(prefix+"food-lemon.png"));
		images.put("2", GameUtil.getImage(prefix+"food-litchi.png"));
		images.put("3", GameUtil.getImage(prefix+"food-mango.png"));
		images.put("4", GameUtil.getImage(prefix+"food-apple.png"));
		images.put("5", GameUtil.getImage(prefix+"food-banana.png"));
		images.put("6", GameUtil.getImage(prefix+"food-blueberry.png"));
		images.put("7", GameUtil.getImage(prefix+"food-cherry.png"));
		images.put("8", GameUtil.getImage(prefix+"food-durian.png"));
		images.put("9", GameUtil.getImage(prefix+"food-grape.png"));
		images.put("10", GameUtil.getImage(prefix+"food-grapefruit.png"));
		images.put("11", GameUtil.getImage(prefix+"food-peach.png"));
		images.put("12", GameUtil.getImage(prefix+"food-pear.png"));
		images.put("13", GameUtil.getImage(prefix+"food-orange.png"));
		images.put("14", GameUtil.getImage(prefix+"food-pineapple.png"));
		images.put("15", GameUtil.getImage(prefix+"food-strawberry.png"));
		images.put("16", GameUtil.getImage(prefix+"food-watermelon.png"));
		images.put("17", GameUtil.getImage(prefix+"dynamite_01.png"));
		images.put("18", GameUtil.getImage(prefix+"hand_grenade_01.png"));
		images.put("19", GameUtil.getImage(prefix+"sea_mine_02.png"));
		//Util
		images.put("pause", GameUtil.getImage(prefix+"pause.png"));
		images.put("home", GameUtil.getImage(prefix+"home.png"));

		//Backgrounds
		images.put("UI-background", GameUtil.getImage(prefix+"UI-background-new.jpg"));
		images.put("UI-background2", GameUtil.getImage(prefix+"UI-background-new2.jpg"));
		images.put("wasted", GameUtil.getImage(prefix+"wasted.jpg"));
	}
	public static Image getImage(String imageName){
		return images.get(imageName);
	}
}
