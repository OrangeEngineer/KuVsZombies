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
	private LinkedList<Entity> entities;
	private ArrayList<Zombies> zombies = new ArrayList<Zombies>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private Ku ku;
	private float ZombiePositionX;
	private boolean isGameOver = false;
	private boolean isDying = false;
	private int default_bullet_delay = 100;
	private int timeBullet = 0;
	private int default_zombie_delay = 400;
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
			timeZombie -= delta;
			if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
					&& timeZombie <= 0) {
				randomZombieX();
				if (Walker <= 5) {
					zombies.add(new Zombies(ZombiePositionX, -100));
					Walker += 2;
				} else if (Walker  <= 10) {
					zombies.add(new Zombies(ZombiePositionX, -100));
					randomZombieX();
					zombies.add(new Zombies(ZombiePositionX, -100));
					Walker += 3;
				} else {
					randomZombieX();
					zombies.add(new Zombies(ZombiePositionX, -100));
					randomZombieX();
					zombies.add(new Zombies(ZombiePositionX, -100));
					randomZombieX();
					zombies.add(new Zombies(ZombiePositionX, -100));
					Walker += 4;
				}
				timeZombie = default_zombie_delay;
			}
	}
	
	public void addBullet(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		float positionMouseX = input.getMouseX();
		float positionMouseY = input.getMouseY();
		timeBullet -= delta;
		if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) && timeBullet<=0) {
				bullets.add(new DirectionalBullet(ku.getX()+30,ku.getY()+10,10,positionMouseX,positionMouseY));
				timeBullet = default_bullet_delay;
		}
	}
	
	public void render(GameContainer gc, Graphics g) throws SlickException {
		if (isDying == false) {
			for (Bullet bullet : bullets) {
			      bullet.render(g);
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
		addZombies(gc, delta);
		addBullet(gc, delta);
		updateKuMovement(input, delta);
		try {
			updateZombie(gc, delta);
			updateBullet(delta);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void checkBulletHitZombie(int i) {
		for (int j = 0; j < bullets.size(); j++) {
			if(CollisionChecker.isBulletHitZombie(zombies.get(i).getShape(),bullets.get(j).getShape())) {
				zombies.remove(i);
				bullets.remove(j);
			}
		}
	}
	
	private void updateZombie(GameContainer container, int delta) {
		for (int i = 0; i < zombies.size(); i++) {
			zombies.get(i).update(container,delta);
			checkBulletHitZombie(i);
		}
	}
	
	private void updateBullet(int delta) {
		for (int j = 0; j < bullets.size(); j++) {
			bullets.get(j).update(delta);
		}
	}
	
	public void randomZombieX() {
		Random random = new Random();
		ZombiePositionX = 100 + random.nextInt(400);
	}

}
