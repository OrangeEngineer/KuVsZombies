package kuzombies;

import java.util.ArrayList;
import java.io.ObjectInputStream.GetField;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Deque;
import kuzombies.Entity;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;

public class KuZombies extends BasicGame {
	private Zombies[] zombiess;
	private LinkedList<Entity> entities;
	private ArrayList<Zombies> zombies = new ArrayList<Zombies>();
	private Ku ku;
	private float ZombiePositionX;
	private boolean isGameOver = false;
	private boolean isDying = false;
	private int default_bullet_delay = 200;
	private int timeBullet = 0;
	private int default_zombie_delay = 1000;
	private int timeZombie = 0;
	private int Walker = 0;
	
	public KuZombies(String title) {
		super(title);
		entities = new LinkedList<>();
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
		ku = new Ku(400, 500);
	}

	public void addZombies(GameContainer gc, int delta) throws SlickException {
		
			for (int i = 0; i < 4; i++) {
			timeZombie -= delta;
			if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)&& timeZombie<=0) {
			randomZombieX();
			if(Walker<=5) {
			zombies.add(new Zombies(ZombiePositionX,-100));
			} else if(Walker<=10) {
				zombies.add(new Zombies(ZombiePositionX,-100));
				randomZombieX();
				zombies.add(new Zombies(ZombiePositionX,-100));
				this.Walker++;
			} else {
				randomZombieX();
				zombies.add(new Zombies(ZombiePositionX,-100));
				randomZombieX();
				zombies.add(new Zombies(ZombiePositionX,-100));
				randomZombieX();
				zombies.add(new Zombies(ZombiePositionX,-100));
				this.Walker += 3;
			}
			this.Walker++;
			timeZombie = default_zombie_delay;
			}
			
		}
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
			timeBullet -= delta;
			if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && timeBullet<=0) {
				entities.add(new DirectionalBullet(ku.getX()+30,ku.getY()+10,10,positionMouseX,positionMouseY));
				timeBullet = default_bullet_delay;
			}
			for (Entity entity : entities) {
			      entity.update(delta);
			}
			addZombies(gc, delta);
			updateKuMovement(input, delta);
			try {
				updateZombie(gc, delta);
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else if (isDying == false) {
			ku.dead();
			ku.update(delta);
			isDying = true;
		}
	}
	private void updateZombie(GameContainer container, int delta) {
		for (int i = 0; i < Walker; i++) {
			zombies.get(i).update(container,delta);
		}
	}
	public void randomZombieX() {
		Random random = new Random();
		ZombiePositionX = 100 + random.nextInt(400);
	}

}
