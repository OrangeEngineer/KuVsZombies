package kuzombies;

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
import org.newdawn.slick.geom.Vector2f;

public class KuZombies extends BasicGame {
	private Zombies[] zombies;
	private LinkedList<Bullet> bullets;
	private Ku ku;
	private float x;
	private boolean isGameOver = false;
	private boolean isDying = false;

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
		bullets = new LinkedList<Bullet>();
		initZombies();
		ku = new Ku(400, 500);
	}

	public void initZombies() throws SlickException {
		zombies = new Zombies[4];
		for (int i = 0; i < 4; i++) {
			randomX();
			zombies[i] = new Zombies(x, -100);
		}
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		if (isDying == false) {
			for (Bullet bullet : bullets) {
				bullet.render(gc, g);
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
		if (isGameOver == false) {
			
			Iterator<Bullet> i = bullets.iterator();
			while (i.hasNext()) {
				Bullet b = i.next();
				if (b.isAktiv()) {
					b.update(delta);
				} else {
					i.remove();
				}
			}
			

			System.out.println(bullets.size());
			if(bullets.size()<1) {
			if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				bullets.add(new Bullet(new Vector2f(ku.getX() + 25,
						ku.getY() + 38), new Vector2f((Mouse.getX() - ku.getX()),
						(Mouse.getY() - ku.getY()))));
			}
			}
			for (Zombies zombie : zombies) {
				zombie.update(delta);
				if (ku.isAttacked(zombie)) {
					isGameOver = true;
				}
			}
			Input input = gc.getInput();
			updateKuMovement(input, delta);
		} else if (isDying == false) {
			ku.dead();
			ku.update(delta);
			isDying = true;
		}
	}

	public void randomX() {
		Random random = new Random();
		x = 100 + random.nextInt(400);
	}

}
