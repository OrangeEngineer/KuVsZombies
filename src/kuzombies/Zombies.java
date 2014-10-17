package kuzombies;

import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Zombies {
	private Animation animationZ;
	private Image z;
	private float x;
	private float y;
	private float vx;
	
	public Animation getAnimation ( Image i , int spritesX, int spritesY , int spriteWidth , int spriteHeight, int frames, int duration )
	{
		Animation a = new Animation(false);
		
		int c = 0;
		for( int y = 0 ; y < spritesY; y++)
		{
			for( int x = 0 ; x < spritesX; x++)
			{
				if( c < frames ) a.addFrame( i.getSubImage(x*spriteWidth, y*spriteHeight, spriteWidth, spriteHeight), duration);
				c++;
			}
		}
		
		return a;
	}

	public Zombies (float x,float y) throws SlickException {
		this.x = x ;
	    this.y = y - 250;
		Image z = new Image("res/animation.png");
		animationZ = getAnimation ( z, 9 , 3 , 128, 128, 30, 100 );
		

	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		animationZ.draw(this.x,this.y);
		
	}

	public void update(int delta) throws SlickException {
		this.y += 0.3;
		animationZ.draw(this.x,this.y);
		animationZ.update(delta);
		  }
		  
}
