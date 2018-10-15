package Scene;

import Objects.Construct;
import Pane.IntroPane;
import javafx.scene.Scene;

public class IntroScene extends Scene {
	
	/**
	 * @author Chris Dias
	 * This scene package contains all the scenes in the project.
	 * The scene is an organization method that carries their respective Pane
	 * and allows it to show to the screen.
	 */
	
	public IntroScene() {
		super(new IntroPane(), Construct.SCREEN_WIDTH, Construct.SCREEN_HEIGHT);
	}
	
}
