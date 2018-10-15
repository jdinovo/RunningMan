package Scene;


import Objects.Construct;
import Pane.RunningMan;
import javafx.scene.Scene;

public class GameScene extends Scene {
	
	/**
	 * @author Chris Dias
	 * This scene package contains all the scenes in the project.
	 * The scene is an organization method that carries their respective Pane
	 * and allows it to show to the screen.
	 * The GameScene brings up the actual game: RunningMan
	 */
	
	public GameScene(String name) {
		super(new RunningMan(name), Construct.SCREEN_WIDTH, Construct.SCREEN_HEIGHT);
	}
	
}
