package kuzombies;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;

public class Ku {
		  private Image image;
		  private int x;
		  private int y;
		  private Animation a;
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

		  public Ku(int x, int y) throws SlickException {
			  Image i = new Image("res/animation.png"); 
			  a = getAnimation( i, 9 , 8 , 1152, 1024, 130, 110 );
		    this.x = x;
		    this.y = y;
		  }
		  public void draw() throws SlickException {
			image.draw(this.x,this.y);
		  }
		  public void moveLeft() {
			  if(this.x > 0) {
			  this.x -= 4; 
			  }
		  }
		  public void moveRight() {
			  if(this.x < 700) {
			  this.x += 4;
			  }
		  }
		  public void moveUp() {
			  if(this.y > 0) {
			  this.y -= 4;
			  }
		  }
		  public void moveDown() {
			  if(this.y < 500) {
			  this.y += 4;
			  }
			  
		  }
	}