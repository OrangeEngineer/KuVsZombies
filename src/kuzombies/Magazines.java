package kuzombies;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Magazines {
	private Image Bullet;
	private float x;
	private float y;
	
	public Magazines(float x, float y) throws SlickException {
		Bullet = new Image("res/Magazine.png");
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g) {
		Bullet.draw(this.x,this.y);
	}
	
	public void update() {
		Bullet.draw(this.x,this.y);
	}
}
