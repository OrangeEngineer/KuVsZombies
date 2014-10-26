package kuzombies;

import java.util.ArrayList;
import java.io.ObjectInputStream.GetField;
import java.util.Iterator;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.Sound;

public class KuZombies extends BasicGame {
	private ArrayList<Zombies> zombies = new ArrayList<Zombies>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<DyingZombies> dyingzombies = new ArrayList<DyingZombies>();
	private Ku ku;
	public static float KUhealth = 100;
	public static float KUKill = 0;
	private float ZombiePositionX;
	private int default_bullet_delay = 200;
	private int timeBullet = 0;
	private int default_zombie_delay = 400;
	private static int Magazine = 0;
	private int timeZombie = 0;
	private int timeDyingZombie = 0;
	private int default_hitt_delay = 1000;
	private int timeHitZombie = 0;
	public static boolean Isgameover = false;
	private int Walker = 0;
	private static int ZombieThrough = 0;
	private Image BackGround;
	private Image HealthFace1;
	private Image HealthFace2;
	private Image HealthFace3;
	private Image HealthFace4;
	private Sound GameOver;
	private Sound GunReload;
	private Sound Pain;
	private Sound BulletSound;
	private Sound ZombieDeadSound;
	private Sound ZombieAppearSound;

	public KuZombies(String title) {
		super(title);

	}

	public static void main(String[] args) throws SlickException {
		try {
			KuZombies game = new KuZombies("KU vs Zombie");
			AppGameContainer appgc = new AppGameContainer(game);
			appgc.setDisplayMode(1024, 720, false);
			appgc.setTargetFrameRate(60);
			appgc.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void init(GameContainer gc) throws SlickException {
		ku = new Ku(400, 500);
		SoundEffect();
	}

	private void SoundEffect() throws SlickException {
		GameOver = new Sound("res/Torture.wav");
		GunReload = new Sound("res/cocking.wav");
		Pain = new Sound("res/Pain.wav");
		BulletSound = new Sound("res/GUN_FIRE.wav");
		ZombieDeadSound = new Sound("res/ZombieWalk.wav");
		ZombieAppearSound = new Sound("res/ZombieAppear.wav");
	}

	private void Face(float KUHealth) throws SlickException {
		HealthFace1 = new Image("res/Health1.png");
		HealthFace2 = new Image("res/Health2.png");
		HealthFace3 = new Image("res/Health3.png");
		HealthFace4 = new Image("res/Health4.png");
		if (KUHealth >= 75) {
			HealthFace1.draw(20, 70);
		} else if (KUHealth >= 50) {
			HealthFace2.draw(20, 70);
		} else if (KUHealth >= 25) {
			HealthFace3.draw(20, 70);
		} else {
			HealthFace4.draw(20, 70);
		}
	}

	public void addZombies(GameContainer gc, int delta) throws SlickException {
		timeZombie -= delta;
		if (zombies.size() == 0 && timeZombie <= 0) {
			for (int i = 0; i < this.Walker; i++) {
				ZombieAppearSound.play();
				randomZombieX();
				zombies.add(new Zombies(ZombiePositionX, -100));
			}
			this.Walker ++;
			if (Walker > 3) {
				for (int j = 0; j < this.Walker-3; j++) {
					ZombieAppearSound.play();
					randomZombieX();
				zombies.add(new FastZombies(ZombiePositionX, -100));
				}
			}
			timeZombie = default_zombie_delay;
		}
	}

	public void addBullet(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		float positionMouseX = input.getMouseX();
		float positionMouseY = input.getMouseY();
		timeBullet -= delta;
		if (gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)
				&& timeBullet <= 0) {
			bullets.add(new DirectionalBullet(ku.getX() + 30, ku.getY() + 10,
					10, positionMouseX, positionMouseY));
			Magazine++;
			BulletSound.play();
			if(Magazine >= 25) {
				timeBullet = 1600;
				Magazine = 0;
				GunReload.play();
			} else {
			timeBullet = default_bullet_delay;
			}
		}
	}

	public void render(GameContainer gc, Graphics g) throws SlickException {
		BackGround = new Image("res/horrorBG.png");
		BackGround.draw();
		g.drawString("HP : " + (int) KuZombies.KUhealth, 20, 150);
		g.drawString("Kill : " + (int) KuZombies.KUKill, 20, 170);
		g.drawString("Zombies Pass : " + (int) (KuZombies.ZombieThrough), 20,
				190);
		Face(KUhealth);
		for (Bullet bullet : bullets) {
			bullet.render(g);
		}
		for (Zombies zombie : zombies) {
			zombie.render(g);
		}
		for (DyingZombies dyingzombie : dyingzombies) {
			dyingzombie.render(g);
		}
		ku.draw();
		if (Isgameover == true) {
			g.drawString("GAME OVER", 500, 360);
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
		if (Isgameover == false) {
			Input input = gc.getInput();
			addZombies(gc, delta);
			addBullet(gc, delta);
			updateKuMovement(input, delta);
			try {
				updateZombie(delta);
				updateBullet(delta);
				updateDyingZombie(gc, delta);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	protected void checkBulletHitZombie(int i, int delta) throws SlickException {
		for (int j = 0; j < bullets.size(); j++) {
			if (CollisionChecker.isBulletHitZombie(zombies.get(i).getShape(),
					bullets.get(j).getShape())) {
				dyingzombies.add(new DyingZombies(zombies.get(i).getX(),
						zombies.get(i).getY()));
				zombies.remove(i);
				bullets.remove(j);
				KUKill++;
				ZombieDeadSound.play();
			}
		}
	}

	private void checkKUHitZombie(int i, int delta) {
		timeHitZombie -= delta;
		if (CollisionChecker.isKUHitZombie(zombies.get(i).getShape(),
				ku.getShape())
				&& timeHitZombie <= 0) {
			Pain.play();
			KUhealth--;
			if (KUhealth <= 0) {
				GameOver.play();
				Isgameover = true;
			}
			timeHitZombie = default_hitt_delay;
		}
	}

	private void updateDyingZombie(GameContainer container, int delta)
			throws SlickException {

		for (int i = 0; i < dyingzombies.size(); i++) {
			timeDyingZombie += delta;
			dyingzombies.get(i).update(delta);
			if (timeDyingZombie >= 400) {
				dyingzombies.remove(i);
				timeDyingZombie = 0;
			}
		}
	}

	private void updateZombie(int delta) throws SlickException {
		for (int i = 0; i < zombies.size(); i++) {
			zombies.get(i).update(delta);
			checkBulletHitZombie(i, delta);
			checkKUHitZombie(i, delta);
			if (zombies.get(i).getY() >= 650) {
				zombies.remove(i);
				ZombieThrough++;
				if (ZombieThrough >= 20) {
					Isgameover = true;
				}
			}
		}
	}

	private void updateBullet(int delta) {
		for (int j = 0; j < bullets.size(); j++) {
			bullets.get(j).update(delta);
			if(bullets.get(j).getY()<=0) {
				bullets.remove(j);
			}
		}
	}

	public void randomZombieX() {
		Random random = new Random();
		ZombiePositionX = 100 + random.nextInt(600);
	}

}
