package kuzombies;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;

public class KuZombies extends BasicGame {

	public KuZombies(String title) {
		super (title);
	}

	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer arg0, int arg1) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	public static void main(String[] args) {
		try {
			KuZombies game = new KuZombies("KU VS ZOBIES");
			AppGameContainer container = new AppGameContainer(game);
			container.setDisplayMode(800, 600, false);
		    container.setMinimumLogicUpdateInterval(1000 / 60);
			container.start();
		}catch (SlickException e) {
			e.printStackTrace();
		}

	}
}
