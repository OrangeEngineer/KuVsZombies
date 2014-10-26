package kuzombies;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.GameContainer;

public interface Entity {
	void render(Graphics g);

	void update(int delta);
}
