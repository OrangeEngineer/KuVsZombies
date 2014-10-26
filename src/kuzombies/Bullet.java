package kuzombies;

import java.util.Random;

import kuzombies.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Rectangle;

public class Bullet implements Entity {

	private static final float BULLET_SIZE = 9;
	protected float x;
	protected float y;
	protected Image bullet;
	protected Shape shapeBullet;
	
	
	
	public Bullet(float x, float y) throws SlickException {
		bullet = new Image("res/bullet.png");
		this.setXY(x, y);
		shapeBullet = new Rectangle(x, y, 3,3);
	}

	@Override
	public void render(Graphics g) {
		bullet.draw(getX(), getY(), BULLET_SIZE, BULLET_SIZE);
	}

	@Override
	public void update(int delta) {
		y -= 50;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public Shape getShape() {
		return shapeBullet;
	}

	protected void setXY(float x, float y) {
		this.x = x;
		this.y = y;
	}
}