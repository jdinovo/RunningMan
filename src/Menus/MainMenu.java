package Menus;

import Scene.IntroScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainMenu extends Application {
	//Externalize the stage from the start method
	public static Stage window;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		/**  
		 * @author Chris Dias
		 * This MainMenu stage extends the Application
		 * From here we create the stage and the scene
		 * and allows us to switch from scene to scene
		 * and pulls up the scene
		 */
		window = primaryStage;
		
		window.setScene(new IntroScene());
		window.setResizable(false);
		window.centerOnScreen();
		window.show();
	}

}
