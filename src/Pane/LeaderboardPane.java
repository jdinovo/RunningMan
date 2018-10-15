package Pane;

import Menus.MainMenu;
import Objects.Player;
import Scene.WelcomeScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LeaderboardPane extends BorderPane {
	
	/**
	 * @author Chris Dias
	 * @author James DiNovo
	 * This is the leaderboardPane that brings up a
	 * leaderboard with the top 10 scores ever achieved
	 * within the save file. 
	 * The leaderboard features their player name and highest score.
	 */
	
	
	public static Player user;
	public static IntroPane introPane;
	
	private final Box keyboardNode;
	
	public LeaderboardPane(String name) {
		
		//Bring the gameworld into the background
		GameWorld world = new GameWorld();
		this.getChildren().add(world);
		
		//Create a font for the texts in the leaderboard
		Font font = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 30);
		
		//Create a label for player name and score
		final Text title = new Text("Player Name");
		title.setFont(font);
		title.setFill(Color.WHITE);
		
		final Text score = new Text("Highest Score");
		score.setFont(font);
		score.setFill(Color.WHITE);
		
		//Create a test Observable list array
		ObservableList<String> names = FXCollections.observableArrayList(RunningMan.nameOut());
		ObservableList<Integer> scores = FXCollections.observableArrayList(RunningMan.scoreOut());

		//Create a list view that will be the leaderboard
		ListView<String> listView = new ListView<String>(names);
		listView.setEditable(false);
		listView.setMaxHeight(252);
		
		ListView<Integer> listView2 = new ListView<Integer>(scores);
		listView2.setEditable(false);
		listView2.setMaxHeight(252);

		//Create a Vbox that store the label and table
		HBox box = new HBox();
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(listView, listView2);
		
		HBox titles = new HBox();
		titles.setLayoutX(150);
		titles.setLayoutY(100);
		titles.setSpacing(60);
		titles.getChildren().addAll(title,score);
		
		this.getChildren().add(titles);
		this.setCenter(box);
		
		//Created a keyboard node to leave the leaderboard pane
		keyboardNode = new Box();
		keyboardNode.setFocusTraversable(true);
		keyboardNode.requestFocus();
		
	    this.setOnKeyReleased(e->{
		    	if(e.getCode() == KeyCode.ESCAPE) {
		    		MainMenu.window.setScene(new WelcomeScene(name));
		    	}
	    });
	    this.getChildren().addAll(keyboardNode);
		
	}
	
}
