package kuzombies;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

public class DirectionalBullet extends Bullet {
	private float dir;
	private float velocity;
	private double MouseAngle;

	public DirectionalBullet(float x, float y, float velocity,
			float positionMouseX, float positionMouseY) throws SlickException {
		super(x, y);
		MouseAngle = Math.toDegrees(Math.atan2(positionMouseY - this.y,
				positionMouseX - this.x));
		this.dir = (float) MouseAngle + 90;
		this.velocity = velocity;
	}

	public void update(int delta) {
		this.y -= 20 * Math.cos((this.dir * 2 * Math.PI) / 360);
		this.x += 20 * Math.sin((this.dir * 2 * Math.PI) / 360);
		bullet.setRotation(this.dir);
		shapeBullet.setCenterX(this.x);
		shapeBullet.setCenterY(this.y);
	}

	public void bulletdirect() {

	}

	public float getVelocity() {
		return velocity;
	}

	public float getDir() {
		return dir;
	}
}


