package Scene;

import Objects.Construct;
import Pane.LeaderboardPane;
import javafx.scene.Scene;

public class LeaderboardScene extends Scene {
	
	/**
	 * @author Chris Dias
	 * This scene package contains all the scenes in the project.
	 * The scene is an organization method that carries their respective Pane
	 * and allows it to show to the screen.
	 * The LeaderBoardScene will bring up the leaderboardPane
	 */
	
	public LeaderboardScene(String name) {
		super(new LeaderboardPane(name), Construct.SCREEN_WIDTH, Construct.SCREEN_HEIGHT);
	}

}
