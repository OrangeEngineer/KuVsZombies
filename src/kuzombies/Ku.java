package kuzombies;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;

public class Ku {
	private Animation dead;
	private Image ku;
	private Image dying;
	private float x;
	private float y;
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

	public Ku(float x, float y) throws SlickException {
		ku = new Image("res/Player.png");
		dying = new Image("res/dying.png");
		this.x = x;
		this.y = y;
		dead = getAnimation(dying, 2, 1, 128, 128, 2, 3);
	}

	public void draw() throws SlickException {
		ku.draw(this.x, this.y);
	}

	public void moveLeft() {
		if (this.x > 0) {
			this.x -= 4;
		}
	}

	public void moveRight() {
		if (this.x < 700) {
			this.x += 4;
		}
	}

	public void moveUp() {
		if (this.y > 300) {
			this.y -= 4;
		}
	}

	public void moveDown() {
		if (this.y < 500) {
			this.y += 4;
		}

	}

	public void Rotate(float positionMouseX, float positionMouseY) {
		double MouseAngle = Math.toDegrees(Math.atan2(positionMouseY - this.y,
				positionMouseX - this.x));
		ku.setRotation((float) MouseAngle + 90);
	}

	public boolean isAttacked(Zombies z) {
		return (this.x < z.getX() + 40 && this.x > z.getX() - 40
				&& this.y < z.getY() + 40 && this.y > z.getY() - 40);
	}

	public void dead() {
		dead.draw(this.x, this.y);
	}

	public void update(int delta) throws SlickException {
		dead.update(delta);
	}
}