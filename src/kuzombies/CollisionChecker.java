package kuzombies;

import org.newdawn.slick.geom.Shape;

public class CollisionChecker {

	public static boolean isBulletHitZombie(Shape zombie,Shape bullet) {
		if(zombie.intersects(bullet)){
			return true;
		} else{
			return false;
		}
	}
}
