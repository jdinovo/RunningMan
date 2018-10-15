package Pane;

import Menus.MainMenu;
import Objects.Construct;
import Scene.WelcomeScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class CreditsPane extends BorderPane {
	
	/**
	 * @author Chris Dias
	 * This creditsPane brings up the Credits screen.
	 * The creditsPane consists of multiple hyperlinks
	 * and dedicates part of the screen to open up the link in game.
	 * 
	 * @author Dorian Harusha
	 * Created the in-game browser
	 * The links can be clicked and the webpage will be opened inside the browser
	 * This will not open an application brower on the computer, instead it will
	 * open the browser inside the game.
	 */
	
	private Box keyboardNode;
	
	public CreditsPane(String name) {
		
		//Get game world
		GameWorld world = new GameWorld();
		world.getGame_world();
		
		this.getChildren().add(world);
		
		//Create a label as a title
		Label creditsTitle = new Label("Credits");
		
		//Create a font for the texts in the leaderboard
		Font font = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 30);
		creditsTitle.setFont(font);
		creditsTitle.setTextFill(Color.WHITE);
		
        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();
		
		//Create multiple hyperlinks - one for each source
		 
        // Open browser and dislay page
        // Background Image Source
		Hyperlink source1 = new Hyperlink();
		final String site1 = "https://edermunizz.itch.io/free-pixel-art-forest";
		source1.setText(site1);
		source1.setOnAction(new EventHandler<ActionEvent>() {
		     @Override
		    public void handle(ActionEvent e) {
		        webEngine.load(site1);
		    }
		 });
		//label
		Hyperlink source2 = new Hyperlink();
		final String site2 = "https://opengameart.org/content/missile-pack";
		source2.setText(site2);
		source2.setOnAction(new EventHandler<ActionEvent>() {
		     @Override
		    public void handle(ActionEvent e) {
		        webEngine.load(site2);
		    }
		 });
		//label
		Hyperlink source3 = new Hyperlink();
		final String site3 = "https://www.codester.com/items/4290/2d-game-character-sprites-3";
		source3.setText(site3);
		source3.setOnAction(new EventHandler<ActionEvent>() {
		     @Override
		    public void handle(ActionEvent e) {
		        webEngine.load(site3);
		    }
		 });
		//label
		Hyperlink source4 = new Hyperlink();
		final String site4 = "https://opengameart.org/content/heroic-demise-updated-version";
		source4.setText(site4);
		source4.setOnAction(new EventHandler<ActionEvent>() {
		     @Override
		    public void handle(ActionEvent e) {
		        webEngine.load(site4);
		    }
		 });
		//label
		Hyperlink source5 = new Hyperlink();
		final String site5 = "https://opengameart.org/content/jumping-man-sounds";
		source5.setText(site5);
		source5.setOnAction(new EventHandler<ActionEvent>() {
		     @Override
		    public void handle(ActionEvent e) {
		        webEngine.load(site5);
		    }
		 });
		
		//Create font for the links
		Font font2 = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 30);
		source1.setFont(font2);
		source2.setFont(font2);
		source3.setFont(font2);
		source4.setFont(font2);
		source5.setFont(font2);
		
		//Add a vbox with all the texts and add them to the scene
		VBox box = new VBox();
		box.setAlignment(Pos.CENTER);
		box.setPrefSize(Construct.SCREEN_WIDTH, Construct.SCREEN_HEIGHT);
		box.setSpacing(10);
		box.getChildren().addAll(creditsTitle, source1, source2, source3, source4, source5, browser);
		
		this.setCenter(box);
		
		// Added a keyboard node to press escape back to the WelcomeMenu
		keyboardNode = new Box();
		keyboardNode.setFocusTraversable(true);
		keyboardNode.requestFocus();
		
		this.setOnKeyPressed(e->{
	    	if(e.getCode() == KeyCode.ESCAPE) {
	    		MainMenu.window.setScene(new WelcomeScene(name));
	    	}
	    });
	    this.getChildren().addAll(keyboardNode);	
	}
}
