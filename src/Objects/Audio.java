package Objects;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

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
	 //new File("src/Audio/Heroic_Demise.mp3");
	
	 URL theSong = getClass().getResource("/Audio/Heroic_Demise.mp3");
	URL theJump = getClass().getResource("/Audio/jump1.mp3");
	URL theDuck = getClass().getResource("/Audio/duck1.mp3");
	URL gotHit = getClass().getResource("/Audio/gotHit.mp3");
	
	//Publicly declare the media and media player
	public static Media media;
	public static MediaPlayer player;
	
	// METHODS //
	public void themeSong() {
			// Play the theme song
			media = new Media(theSong.toString());
			
			player = new MediaPlayer(media);
			player.play();
			player.setAutoPlay(true);

	}
	
	public void jumpEffect() {

			//Play the jump sound whenever character jumps
			media = new Media(theJump.toString());
			player = new MediaPlayer(media);
			player.play();

	}
	
	public void duckEffect() {

			//Play the duck whenever character ducks
			media = new Media(theDuck.toString());
			player = new MediaPlayer(media);
			player.play();

	}
	
	public void hitEffect() {

			// Play the get hit sound when hit by missile
			media = new Media((gotHit.toString()));
			player = new MediaPlayer(media);
			player.play();

	}
	
}

