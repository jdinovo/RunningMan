package Scene;

import Menus.WelcomeMenu;
import Objects.Construct;
import javafx.scene.Scene;

public class WelcomeScene extends Scene  {
	
	/**
	 * 
	 * @author Chris Dias
	 * In the Scene package we carry all the scenes.
	 * The scenes carry a constructor that pulls up the WelcomeMenu Pane
	 * and brings the player name with it from the IntroPane.
	 */
	
	public WelcomeScene(String name) {
		super(new WelcomeMenu(name), Construct.SCREEN_WIDTH, Construct.SCREEN_HEIGHT);
	}
	
}
