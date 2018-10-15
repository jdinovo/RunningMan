package Menus;

import Objects.Audio;
import Objects.Player;
import Pane.GameWorld;
import Scene.CreditsScene;
import Scene.GameScene;
import Scene.LeaderboardScene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class WelcomeMenu extends BorderPane {
	
	/**
	 * @author Chris Dias
	 * This is the WelcomeMenu
	 * It consists of the Game, Leaderboard, Credits button
	 * It also has the name of the player on the top left screen.
	 * It is the official main menu of the game.
	 */
	
	public static Player user;
	
	public WelcomeMenu(String name) {
		
		//Implement gameworld
		GameWorld world = new GameWorld();
		Audio audioPane = new Audio();
		
		user = new Player(name);
		
		//Plays the theme song when the character first enters the menu
		if (Audio.player == null) {
			audioPane.themeSong();
		}

		/**
		 * @author James DiNovo
		 */
		//Create ImageView for logo
		ImageView logo = new ImageView(new Image("Graphics/runningManLogo.png"));
		logo.setFitHeight(260);
		logo.setFitWidth(500);
		HBox logoBox = new HBox();
		logoBox.setAlignment(Pos.CENTER);
		logoBox.getChildren().add(logo);
		
		//Create the buttons
		Button startGameButton = new Button("Start Game!");
		Button leaderboardButton = new Button("Leaderboard");
		Button creditsButton = new Button("Credits");
		
		//Start Game Button
		startGameButton.setPrefSize(150, 100);
		startGameButton.setOnMouseClicked(e->{
			MainMenu.window.setScene(new GameScene(name));
		}); 
		
		//Leaderboard Button
		leaderboardButton.setPrefSize(150, 100);
		leaderboardButton.setOnMouseClicked(e->{
			MainMenu.window.setScene(new LeaderboardScene(name));
		});
		
		//credits Button
		creditsButton.setPrefSize(150,100);
		creditsButton.setAlignment(Pos.CENTER);
		creditsButton.setOnMouseClicked(e->{
			MainMenu.window.setScene(new CreditsScene(name));
		});
		
		//Create a VBox which will hold the buttons
		HBox mainMenuButtons = new HBox();
		mainMenuButtons.setSpacing(20);
		mainMenuButtons.setAlignment(Pos.CENTER);
		mainMenuButtons.getChildren().addAll(startGameButton, leaderboardButton);
		
		//Master pane 
		this.getChildren().add(world);
		this.setCenter(mainMenuButtons);
		this.setTop(logoBox);
		this.setBottom(creditsButton);
		
		//Externalized a CSS sheet to style the buttons
		this.getStylesheets().add("Styles/buttoncss.css");
		
	}

}
