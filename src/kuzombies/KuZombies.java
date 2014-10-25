package kuzombies;

import java.io.ObjectInputStream.GetField;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.w3c.dom.Entity;

public class KuZombies extends BasicGame {
	private Zombies[] zombies;
	private LinkedList<Entity> entities;
	private Ku ku;
	private float ZombiePositionX;
	private boolean isGameOver = false;
	private boolean isDying = false;
	private int default_bullet_delay = 800;
	private int time = 0;
	
	public KuZombies(String title) {
		super(title);
	}

	public static void main(String[] args) throws SlickException {

		KuZombies game = new KuZombies("KU vs Zombie");
		AppGameContainer appgc = new AppGameContainer(game);
		appgc.setDisplayMode(800, 600, false);
		appgc.setTargetFrameRate(60);
		appgc.start();
	}

	public void init(GameContainer gc) throws SlickException {
		entities = new LinkedList<Entity>();
		initZombies();
		ku = new Ku(400, 500);
	}

	public void initZombies() throws SlickException {
		zombies = new Zombies[4];
		for (int i = 0; i < 4; i++) {
			randomZombieX();
			zombies[i] = new Zombies(ZombiePositionX, -100);
		}
	}
	public interface Entity {
		  void render(Graphics g);
		  void update(int delta);
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		if (isDying == false) {
			for (Entity entity : entities) {
			      entity.render(g);
			}
			for (Zombies zombie : zombies) {
				zombie.render(gc, g);
			}
			ku.draw();
		} else {
			ku.dead();
		}
	}

	void updateKuMovement(Input input, int Delta) {
		if (input.isKeyDown(input.KEY_A)) {
			ku.moveLeft();
		}
		if (input.isKeyDown(input.KEY_D)) {
			ku.moveRight();
		}
		if (input.isKeyDown(input.KEY_S)) {
			ku.moveDown();
		}
		if (input.isKeyDown(input.KEY_W)) {
			ku.moveUp();
		}
		float positionMouseX = input.getMouseX();
		float positionMouseY = input.getMouseY();
		ku.Rotate(positionMouseX, positionMouseY);
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		if (isGameOver == false) {
			float positionMouseX = input.getMouseX();
			float positionMouseY = input.getMouseY();
			time -= delta;
			if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && time<=0/*&& gameTime.getTimeMS() - shootTime > shootDelayInMilliseconds*/) {
				//shootTime = gameTime.getTimeMS();
				entities.add(new DirectionalBullet(ku.getX()+30,ku.getY()+30,10,positionMouseX,positionMouseY));
				time = default_bullet_delay;
			}
			for (Entity entity : entities) {
			      entity.update(delta);
			}
			for (Zombies zombie : zombies) {
				zombie.update(delta);
				if (ku.isAttacked(zombie)) {
					isGameOver = true;
				}
			}
			updateKuMovement(input, delta);
		} else if (isDying == false) {
			ku.dead();
			ku.update(delta);
			isDying = true;
		}
	}

	public void randomZombieX() {
		Random random = new Random();
		ZombiePositionX = 100 + random.nextInt(400);
	}

}
