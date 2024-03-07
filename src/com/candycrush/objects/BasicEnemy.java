package com.candycrush.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.candycrush.main.GameWindow;

public class BasicEnemy extends GameObject{

	public BasicEnemy(Handler handler, ID id, Rectangle rectangle) {
		super(handler, id, rectangle);
		// TODO Auto-generated constructor stub
		
//		Set a random location and random speed
		rectangle.setLocation(
				(int)handler.getRandom().nextInt(GameWindow.GAMEWIDTH), 
				(int)handler.getRandom().nextInt(GameWindow.GAMEHEIGHT)
		);
		
		this.velX = (handler.getRandom().nextInt(2) * 2 - 1) * 5;
		this.velY = (handler.getRandom().nextInt(2) * 2 - 1) * 5;
	}

	@Override
	public void tick() {
		// Update the position
		rectangle.setLocation(
			(int)rectangle.getX() + velX, 
			(int)rectangle.getY() + velY
		);

		clamp();
		
		if(hit.get("left") || hit.get("right")) velX *= -1;
		if(hit.get("up") || hit.get("down")) velY *= -1;
	}

	@Override
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.red);
		g2d.fillRect(
			(int)rectangle.getX(), 
			(int)rectangle.getY(), 
			(int)rectangle.getWidth(), 
			(int)rectangle.getHeight()
		);
	}

	@Override
	public void collision(ID id) {
		// Temporary code to remove the BasicEnemy if it hits the player
		if(id == ID.PLAYER) {
			handler.removeObject(this);
		}
	}
	
}
