package Scene;

import Objects.Construct;
import Pane.CreditsPane;
import javafx.scene.Scene;

public class CreditsScene extends Scene {

	/**
	 * @author Chris Dias
	 * This scene package contains all the scenes in the project.
	 * The scene is an organization method that carries their respective Pane
	 * and allows it to show to the screen.
	 * The creditsScene brings up the CreditsPane
	 */
	
	public CreditsScene(String name) {
		super(new CreditsPane(name), Construct.SCREEN_WIDTH, Construct.SCREEN_HEIGHT);
	}
	
}
