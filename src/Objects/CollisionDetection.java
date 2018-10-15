package Objects;

import javafx.scene.image.ImageView;

/**
 * 
 * @author Dorian Harusha
 *  
 * This class creates the Collision Detection for the game
 * The theory behind the collision detection is that it checks if box1 (rect1)
 * Is touching box2 (rect2) if the boxes are touching (colliding) it returns true
 * 
 */

public class CollisionDetection {
	
	public CollisionDetection() {
	
	}
	
	public boolean collisionDetection(ImageView rect1, ImageView rect2) { 
		// if this statement is true the objects are not colliding
		if((rect1.getX() > (rect2.getX()+10) + rect2.getFitWidth() || rect1.getX() + rect1.getFitWidth() < (rect2.getX()+10)) || 
				(rect1.getY() > rect2.getY() + rect2.getFitWidth() || rect1.getY() + rect1.getFitWidth() < rect2.getY())) { 
			return false; 
		}
		// if the above is not true then the objects are colliding
		return true; 
	}
}
