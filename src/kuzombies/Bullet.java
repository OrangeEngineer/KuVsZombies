package kuzombies;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Image;


public class Bullet
{
	private Image mybullet;
	private Vector2f pos;
	private Vector2f speed;
	private int lived = 0;
	
	private boolean aktiv = true;
	
	private static int MAX_LIFETIME = 2000;
	
	public Bullet ( Vector2f pos, Vector2f speed )throws SlickException{
		this.pos = pos;
		this.speed = speed;
		mybullet = new Image("res/bullet.png");
	}
	
	public Bullet ()
	{
		aktiv = false;
	}
	
	public void update( int t )
	{
		if( aktiv )
		{
			Vector2f realSpeed = speed.copy();
			realSpeed.scale( (t/1000.0f) );
			pos.add( realSpeed );
			
			lived += t;
			if( lived > MAX_LIFETIME ) aktiv = false;
		}
	}
	
	public void render(GameContainer gc,Graphics g) throws SlickException 
	{
		if( aktiv )
		{
			mybullet.draw(pos.getX()-10, pos.getY()-10, 20, 20);
			//bullet.fillOval(pos.getX()-10, pos.getY()-10, 20, 20);
		}
	}
	
	public boolean isAktiv()
	{
		return aktiv;
	}
	
}