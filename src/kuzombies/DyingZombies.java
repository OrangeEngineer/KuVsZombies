package kuzombies;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class DyingZombies  {
	protected Animation Killed;
	protected Image zombieKilled;
	private float x;
	private float y;
	private int delay = 1000;
	private int ResetDelay = 1000;
	
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
	
	public DyingZombies(float x, float y) throws SlickException {
		this.x = x;
		this.y = y;
		zombieKilled = new Image("res/ZombieKilled2.png");
		Killed = getAnimation(zombieKilled, 9, 2, 132, 132, 17, 140);
		
	}
	public void render(Graphics g) {
		Killed.draw(this.x,this.y);
	}
	public void update(int delta) {
		
		do {
			delay -=delta;
			Killed.update(delta);
		} while (delay > 0);
		
	}
}
