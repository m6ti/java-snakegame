package com.psymk6.models;

import com.psymk6.interfaces.iSnake;
import com.psymk6.util.ImageUtil;

import java.awt.*;
import java.util.Random;

public class Food implements iSnake
{
	int x;
	int y;
	Image i;
	int w;
	int h;

	public boolean l;
	private static final long serialVersionUID = -3641221053272056036L;



	public Food()	{
		this.l = true;

		this.i = ImageUtil.images.get(String.valueOf(new Random().nextInt(10)));

		this.w = i.getWidth(null);
		this.h = i.getHeight(null);

		this.x = (int) (Math.random() * (870 - w + 10));
		this.y = (int) (Math.random() * (560 - h - 40));
	}

	public Rectangle getRectangle()
	{
		return new Rectangle(x, y, w, h);
	}

	public void eaten(SnakeModel mySnake)	{

		if (mySnake.getRectangle().intersects(this.getRectangle()) && l && mySnake.l)		{
			this.l = false;
			mySnake.changeLength(mySnake.getLength() + 1);
			mySnake.score += 521;
		}
	}
	public void draw(Graphics g)
	{
		g.drawImage(i, x, y, null);
	}
}
