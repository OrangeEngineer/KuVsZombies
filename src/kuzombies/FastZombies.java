package kuzombies;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class FastZombies extends Zombies {

	public FastZombies(float x, float y) throws SlickException {
		super(x, y);
		zombie = new Image("res/animationFast.png");
		animation_Zombie = getAnimation(zombie, 9, 3, 128, 128, 30, 100);	}
	public void update(int delta) {
		randomZombievVelocityY();
		//this.y += velocityY / 6;
		this.y += velocityY / 5;
		updateShape();
		if (this.x > 20 && this.x < 700) {
			this.x += (0.3) * ((float) Math.cos((angle * 2 * Math.PI) / 360));
		} else {
			angle = 90 - angle;
			this.x += ((float) Math.cos((angle * 2 * Math.PI) / 360));
		}
		animation_Zombie.update(delta);
	}
	public void DecreaseZombieHealth() {
		this.Health -= 100;
	}

}
