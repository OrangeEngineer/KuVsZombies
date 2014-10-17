package kuzombies;
import java.util.Random;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;



public class KuZombies extends BasicGame {
	private Zombies[] zombies;
	private float x;
	public KuZombies(String title) {
		super (title);
	}

	public void init(GameContainer gc) throws SlickException {
		initZombies();
	}
	public void initZombies() throws SlickException{
		zombies = new Zombies[4];
		randomX();
	    for (int i = 0; i < 4; i++) {
	      zombies[i] = new Zombies(x, 100);
	    }
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		for (Zombies zombie : zombies) {
		      zombie.render(gc, g);
		}
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		for (Zombies zombie : zombies) {
		      zombie.update(delta);
		}
	}
	public void randomX() {
		  Random random = new Random();
		  x = 0 + random.nextInt(400);
	}
	  

	public static void main(String[] args) {
		try {
			KuZombies game = new KuZombies("KU vs Zombie");
			AppGameContainer appgc = new AppGameContainer(game);
			appgc.setDisplayMode(800, 600,false);
			appgc.start();
		}catch (SlickException e) {
			e.printStackTrace();
		}
	}


}
