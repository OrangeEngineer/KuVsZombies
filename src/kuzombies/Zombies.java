package kuzombies;

import java.util.Random;

import kuzombies.Entity;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Circle;

public class Zombies {
	private Animation animation_Zombie;
	private Image zombie;
	private float x;
	private float y;
	private float angle;
	private Shape ZombieShape;
	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}

	public Animation getAnimation(Image i, int spritesX, int spritesY,
			int spriteWidth, int spriteHeight, int frames, int duration) {
		Animation a = new Animation(false);

		int c = 0;
		for (int y = 0; y < spritesY; y++) {
			for (int x = 0; x < spritesX; x++) {
				if (c < frames)
					a.addFrame(i.getSubImage(x * spriteWidth, y * spriteHeight,
							spriteWidth, spriteHeight), duration);
				c++;
			}
		}

		return a;
	}

	public Zombies(float x, float y) throws SlickException {
		this.x = x;
		this.y = y;
		randomAngle();
		zombie = new Image("res/animation.png");
		animation_Zombie = getAnimation(zombie, 9, 3, 128, 128, 30, 100);
		ZombieShape = new Circle(this.x+64, this.y+64, 64);

	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		animation_Zombie.draw(this.x, this.y);

	}

	public void update(GameContainer container,int delta){
		this.y += 1;
		updateShape();
		if (this.x > 20 && this.x < 700) {
			this.x += (0.3) * ((float) Math.cos((angle*2*Math.PI)/360));
		} else {
			angle = 90 - angle;
			this.x += ((float) Math.cos((angle*2*Math.PI)/360));
		}
		animation_Zombie.update(delta);
	}
	private void updateShape() {
		ZombieShape.setCenterX(this.x+10);
		ZombieShape.setCenterY(this.y+10);
	}

	public boolean collision(float bulletX, float bulletY) {
		if(bulletX < this.x + 40 && bulletX > this.x
				&& bulletY < this.y + 40 && bulletY > this.y) {
			return true;
		}
		return false;
	}
	public Shape getShape() {
		return ZombieShape;
	}
	public void randomAngle() {
		Random random = new Random();
		angle = 30 + random.nextInt(120);
	}

}
