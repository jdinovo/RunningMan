package Objects;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 * 
 * @author Dorian Harusha
 * This class takes care of all the Character functions
 * Such as, showing the player on the screen, getting the player to jump
 * And making the player duck.
 *
 */

public class Character extends Pane {
	
	//Link audio class with this class
	Audio audioPane = new Audio();
	
	// Variables for character animations
	private Image playerRunning = new Image("Graphics/player_running.gif");
	private Image playerDucking = new Image("Graphics/player_ducking.gif");
	private Image playerDying = new Image("Graphics/player_dying.gif");
	private Image playerStanding = new Image("Graphics/player_standing.png");
	
	public ImageView playerBox = new ImageView(playerRunning);
	
	private PathTransition playerJump;
	private int y = 435;
	
	//More variables that has to do with characters state of living
	
	//	private boolean alive = true;
	private int lives = 3;
	private Boolean dead;

	// Constructor
	public Character() {
		getPlayerBox();
		playerBox.setFitWidth(150);
		playerBox.setFitHeight(200);
		playerBox.setX(125);
		playerBox.setY(y);
		getChildren().addAll(playerBox);	
	}
	
	 /***********
	  * Methods *
	  ***********/
	
	//When the character jumps
	public void characterJump() {
		playerJump = new PathTransition(Duration.millis(200),
				new Line(200, 535, 200, 455), playerBox);
		setPlayerRunning(playerRunning);
		playerJump.setCycleCount(2);
		playerJump.setAutoReverse(true);
		audioPane.jumpEffect();
		playerJump.play();
	}
	
	//Method for when the character ducks
	public void characterDuck() {
		playerBox.setImage(playerDucking);
		audioPane.duckEffect();
	}
	
	//Getters and setters
	public Image getPlayerStanding() {
		return playerStanding;
	}

	public void setPlayerStanding(Image playerStanding) {
		this.playerStanding = playerStanding;
	}
	
	public Image getPlayerRunning() {
		return playerRunning;
	}

	public void setPlayerRunning(Image playerRunning) {
		this.playerRunning = playerRunning;
	}

	public Image getPlayerDying() {
		return playerDying;
	}

	public void setPlayerDying(Image playerDying) {
		this.playerDying = playerDying;
	}
	
	public Image getPlayerDucking() {
		return playerDucking;
	}

	public void setPlayerDucking(Image playerDucking) {
		this.playerDucking = playerDucking;
	}

	public ImageView getPlayerBox() {
		return playerBox;
	}

	public void setPlayerBox(ImageView playerBox) {
		this.playerBox = playerBox;
	}

	public int getLives() {
		return this.lives;
	}

	//Setter that determines number of lives character has 
	public void setLives(int lifeCount) {
		if (this.lives <= 0) {
			playerBox.setImage(playerDying);
			this.dead = false;
		} else {
			this.lives = lifeCount;
			fadeHit();
		}
	}
	
	public Boolean isNotDead() {
		return this.dead;
	}
	
	//Method to play hit animation - will cause the character to fade
	public void fadeHit() {
		
		FadeTransition fade1 = new FadeTransition(Duration.millis(100), this.playerBox);
		fade1.setFromValue(1);
		fade1.setToValue(.1);
		fade1.setAutoReverse(true);
		fade1.setCycleCount(4);
		fade1.play();
	}
	
	
}


