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
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Circle;

public class Ku {
	private Image ku;
	private Shape KU;
	private float x;
	private float y;
	public float getX() {
		return this.x;
	}

	public float getY() {
		return this.y;
	}


	public Ku(float x, float y) throws SlickException {
		ku = new Image("res/Player.png");
		KU = new Circle(this.x+80,this.y+80, 20);
		this.x = x;
		this.y = y;
	}

	public void draw() throws SlickException {
		ku.draw(this.x, this.y);
		KU.setCenterX(this.x);
		KU.setCenterY(this.y);
	}

	public void moveLeft() {
		if (this.x > 0) {
			this.x -= 4;
		}
	}

	public void moveRight() {
		if (this.x < 1000) {
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
	
	public Shape getShape() {
		return KU;
	}

}