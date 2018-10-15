package Objects;

import java.io.File;

import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Audio extends Pane {
	
	/**
	 * @author Chris Dias
	 * This is the Audio class
	 * It features all the sound files we use in our project
	 * Each of the file has it's own method where it's inserted
	 * into a media, and played into the media player.
	 * It's then used across the RunningGame class
	 */

	//Files - one for each sound effect
	File theSong = new File("src/Audio/Heroic_Demise.mp3");
	File theJump = new File("src/Audio/jump1.mp3");
	File theDuck = new File("src/Audio/duck1.mp3");
	File gotHit = new File("src/Audio/gotHit.mp3");
	
	//Publicly declare the media and media player
	public static Media media;
	public static MediaPlayer player;
	
	// METHODS //
	public void themeSong() {
		
		// Play the theme song
		media = new Media(new File(theSong.toString()).toURI().toString());
		player = new MediaPlayer(media);
		player.play();
		player.setAutoPlay(true);
			
		}
	
	public void jumpEffect() {
		//Play the jump sound whenever character jumps
		media = new Media(new File(theJump.toString()).toURI().toString());
		player = new MediaPlayer(media);
		player.play();
	}
	
	public void duckEffect() {
		//Play the duck whenever character ducks
		media = new Media(new File(theDuck.toString()).toURI().toString());
		player = new MediaPlayer(media);
		player.play();
	}
	
	public void hitEffect() {
		// Play the get hit sound when hit by missile
		media = new Media(new File(gotHit.toString()).toURI().toString());
		player = new MediaPlayer(media);
		player.play();
	}
	
	}

