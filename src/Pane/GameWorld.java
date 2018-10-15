package Pane;

import Objects.Construct;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * 
 * @author Dorian Harusha
 * This class creates the game world, which is the background image of the game.
 * 
 */

public class GameWorld extends Pane {
	private Image game = new Image("Graphics/BackgroundTrim.png");
	
	ImageView game_world = new ImageView(game);
	
	public GameWorld() {
		getGame_world();
		
		game_world.setFitHeight(Construct.SCREEN_HEIGHT);
		game_world.setFitWidth(Construct.SCREEN_WIDTH);
		
		getChildren().add(game_world);
	}

	public ImageView getGame_world() {
		return game_world;
	}

	public void setGame_world(ImageView game_world) {
		this.game_world = game_world;
	}
	
}
