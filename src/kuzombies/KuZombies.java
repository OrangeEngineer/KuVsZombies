package kuzombies;

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

public class KuZombies extends BasicGame {
	private Zombies[] zombies;
	private Ku ku;
	private float x;

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
		for (Zombies zombie : zombies) {
			zombie.render(gc, g);
		}
		ku.draw();
	}

	void updateKuMovement(Input input, int Delta) {
		if (input.isKeyDown(input.KEY_LEFT)) {
			ku.moveLeft();
		}
		if (input.isKeyDown(input.KEY_RIGHT)) {
			ku.moveRight();
		}
		if (input.isKeyDown(input.KEY_DOWN)) {
			ku.moveDown();
		}
		if (input.isKeyDown(input.KEY_UP)) {
			ku.moveUp();
		}
		float positionMouseX = input.getMouseX();
		float positionMouseY = input.getMouseY();
		ku.Rotate(positionMouseX ,positionMouseY);
	}

	public void update(GameContainer gc, int delta) throws SlickException {
		for (Zombies zombie : zombies) {
			zombie.update(delta);
		}
		Input input = gc.getInput();
		updateKuMovement(input, delta);
	}

	public void randomX() {
		Random random = new Random();
		x = 100 + random.nextInt(400);
	}

}
