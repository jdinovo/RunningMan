package Pane;

import Menus.MainMenu;
import Objects.Player;
import Scene.WelcomeScene;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class IntroPane extends BorderPane {
	
	/**
	 * @author Chris Dias
	 * This introPane is opened from the introScene.
	 * This pane brings up the intro menu with the animating game logo
	 * as well as the input for the character name.
	 * The character name is then saved to the Player class for future use.
	 */
	
	public TextField playerName;
	private final Box keyboardNode;
	public String playerNameString;
	public Player user;
	
	public IntroPane() {
		
		//Change background intro to black
		this.setStyle("-fx-background-color: #000");
		
		//Create Texts to use with transitions
		Text running = new Text("RUNNING");
		Text man = new Text("M  N");
		
		//Add images of missiles
		ImageView missile1 = new ImageView(new Image("Graphics/missile.png"));
		
		//Add font styles
		Font runningFont = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 70);
		Font manFont = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 90);
		
		//Set the font to the Texts
		running.setFont(runningFont);
		man.setFont(manFont);
		
		//Add properties to the Texts
		running.setFill(Color.FORESTGREEN);
		running.setStroke(Color.WHITE);
		man.setFill(Color.MEDIUMBLUE);
		man.setStroke(Color.WHITE);
		
		//Create a VBox for each text
		VBox manBox = new VBox();
		manBox.setAlignment(Pos.CENTER);
		manBox.getChildren().add(man);
		this.setCenter(manBox);
		
		HBox runningBox = new HBox();
		runningBox.getChildren().add(running);
		this.setLeft(runningBox);
		
		HBox missile1Box = new HBox();
		missile1Box.getChildren().add(missile1);
		this.setRight(missile1Box);
		
		//Add transitions to animate the 'Running' in logo
		RotateTransition rot = new RotateTransition(Duration.millis(1000), running);
		rot.setCycleCount(1);
		rot.setByAngle(-20);
		
		//Add rotate transition to the missile
		RotateTransition rotM1 = new RotateTransition(Duration.millis(1000), missile1);
		rotM1.setCycleCount(1);
		rotM1.setByAngle(90);
		
		//Translate transition the 'Running' logo
		TranslateTransition translate = new TranslateTransition(Duration.millis(300), running);
		translate.setFromX(-100);
		translate.setToX(160);
		translate.setFromY(-100);
		translate.setToY(140);
		
		//Scale for the missile
		ScaleTransition scaleM1 = new ScaleTransition(Duration.millis(10), missile1);
		scaleM1.setByX(-.60);
		scaleM1.setByY(-.60);
		
		//Add translate transition to the missile
		TranslateTransition translateM1 = new TranslateTransition(Duration.millis(500), missile1);
		translateM1.setFromX(-400);
		translateM1.setToX(-220);
		translateM1.setFromY(700);
		translateM1.setToY(170);
		
		//SequentialTransition puts it all together and animates the logo
		SequentialTransition parallel = new SequentialTransition();
		parallel.getChildren().addAll(rot, scaleM1, translate);
		parallel.setCycleCount(1);
		parallel.play();
		
		//Sequential Transition animates the missiles
		SequentialTransition parallelM1 = new SequentialTransition();
		parallelM1.getChildren().addAll(rotM1, translateM1);
		parallelM1.setCycleCount(1);
		parallelM1.play();
		
		//This is where you input your player name + color and font properties
		Label playerNameLabel = new Label("Enter your player name: (Max 15 characters)");
		Font playerNameFont = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 20);
		playerNameLabel.setFont(playerNameFont);
		playerNameLabel.setTextFill(Color.WHITE);
		playerNameLabel.setAlignment(Pos.TOP_CENTER);
		
		playerName = new TextField();
		playerName.setMaxWidth(300);
		playerName.setAlignment(Pos.BOTTOM_CENTER);
		
		//Create an Hbox for the label and text
		HBox bottom = new HBox();
		bottom.setAlignment(Pos.CENTER);
		bottom.setPadding(new Insets(10,10,100,10));
		bottom.setSpacing(10);
		bottom.getChildren().addAll(playerNameLabel, playerName);
		this.setBottom(bottom);
		
		//Create a keyboard node that will only accept 'enter' key
		keyboardNode = new Box();
		keyboardNode.setFocusTraversable(true);
		keyboardNode.requestFocus();
		
		playerName.setOnKeyPressed(e-> {
			if(e.getCode() == KeyCode.ENTER) {
				if ((playerName.getText().length() >= 1) && (playerName.getText().length() <= 15) && (!playerName.getText().contains(" "))) {
					playerNameString = playerName.getText();
					MainMenu.window.setScene(new WelcomeScene(playerNameString));
				}
			}
		});
		this.getChildren().addAll(keyboardNode);
	}
}
