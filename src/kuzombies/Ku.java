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
	 private Image ku;
	 private float x;
	 private float y;
	 public Ku (float x,float y) throws SlickException {
		 ku = new Image("res/Player.png");
		 this.x = x;
		 this.y = y;
	 }
	 public void draw() throws SlickException {
			ku.draw(this.x,this.y);
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
		  if(this.y > 300) {
		  this.y -= 4;
		  }
	  }
	  public void moveDown() {
		  if(this.y < 500) {
		  this.y += 4;
		  }
		  
	  }
}