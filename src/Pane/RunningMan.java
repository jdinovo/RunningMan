package Pane;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Menus.MainMenu;
import Objects.Audio;
import Objects.Character;
import Objects.ObjectGenerator;
import Objects.Player;
import Scene.WelcomeScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 
 * @author Dorian Harusha
 * @author James DiNovo
 * 
 * This is our main class for the project
 * 1) Here we load the game theme (background image)
 * 2) We load the character object
 * 3) We load the missile objects
 * 4) We use event handlers to check if the appropriate keys are pressed
 *    If they are, then make the player jump or duck the objects
 * 5) File I/O is implemented in this file as well
 * 
 */

public class RunningMan extends Pane {
	
	/**
	 * Declaring global variables
	 */
	private Boolean press = true;
	private Boolean down = true;
	public static Player user;
	public static Box keyboardNode;
	
	public static Character player;
	public static IntroPane introPane;
	
	public RunningMan(String name) {
		
		/**
		 * 1) Create a box to hold the keyboard node
		 * 2) Create an ObjectGenerator object, which will call it's default constructor 
		 * 	  which will load the objects on the screen
		 * 3) Call the Character class default constructor by creating a new Character object
		 *    named player. This will load the character into the game
		 * 4) Call the IntroPane class default constructor by creating a new IntroPane object
		 * 5) Call the GameWorld class default constructor by creating a new GameWorld object  
		 * 6) Create a new Player class object
		 * 7) Add world, player and obj objects to Pane (RunningMan) which extends Pane
		 */
		Box keyboardNode;
		ObjectGenerator obj = new ObjectGenerator();
		player = new Character();
		introPane = new IntroPane();
		GameWorld world = new GameWorld();
		Audio audioPane = new Audio();
		user = new Player(name);
		
		this.getChildren().addAll(world, player, obj);
		
		//Stop the theme song music
		if (Audio.player != null) {
			if (Audio.player.getStatus() == MediaPlayer.Status.PLAYING) {
				Audio.player.stop();
			}
		}
		
		/**
		 * 1) Create a node for the keyboard
		 * 2) Add the node to the Pane
		 * 3) Add an event handler to check if a key has been pressed
		 */
		keyboardNode = new Box();
		keyboardNode.setFocusTraversable(true);
		keyboardNode.requestFocus();
		
		this.getChildren().addAll(keyboardNode);
		
	    keyboardNode.setOnKeyPressed(e->{
    			// Arrow key UP to make player jump
	   
	    			/**
	    			 * 1) Check to see if the Key UP (Arrow Key Up) has been pressed
	    			 * 2) If Key UP is pressed, make the character on the screen play the jump animation
	    			 * 3) If Key DOWN is pressed, make the character on the screen play the duck animation
	    			 */
		    		if(press) {
		    			press = false;
			    		if (e.getCode() == KeyCode.UP && down) {
			    			player.characterJump();
				    		obj.setCollideTop(false);
			    		}
			    		
			    		// Arrow key DOWN to make player duck
			    		else if (e.getCode() == KeyCode.DOWN) {
			    			player.characterDuck();
			    			obj.setCollideBottom(false);
			    			down = false;
			    		}
		    		}
		    	
	    		/**
	    		 * 1) Check to see if the user has pressed the ESQ button
	    		 * 2) Create a new Dialogue Window
	    		 * 3) Add the Dialogue Window to a stage
	    		 * 4) Pause the game when the user has pressed ESQ
	    		 * 5) If the User presses No button on the Dialogue Window, switch back to the game scene
	    		 *    and resume the game
	    		 * 6) If the user presses Yes button on the Dialogue Window
	    		 *    Save the game and exit the game
	    		 */
		    		
		    	// Escape key closes the game   
	    		if ((e.getCode() == KeyCode.ESCAPE) && player.getLives() != 0) {
				
	    			/**
	    			 * @author Chris Dias
	    			 * This is the prompt screen
	    			 * When the escape key is pressed it asks the user
	    			 * to confirm if they really want to quit the game.
	    			 * It pauses the game in the background and they have
	    			 * the choice of yes or no.
	    			 * If yes, the game saves their score and returns to the WelcomeMenu
	    			 * If no, game continues and the prompt closes.
	    			 */
	    			
				//Create a new popup prompt to confirm if user wants to leave game
				Stage confirmQuit = new Stage();
				confirmQuit.initStyle(StageStyle.UNDECORATED);
				confirmQuit.initOwner(MainMenu.window);
				confirmQuit.initModality(Modality.APPLICATION_MODAL);
				confirmQuit.setWidth(400);
				confirmQuit.setHeight(300);
				confirmQuit.setAlwaysOnTop(true);
				confirmQuit.setResizable(false);
				
				//Stops the missiles from continuing
					obj.getBlocks().stop();
					obj.getBlockSpeed().stop();
				
				//Text within the stage and added properties to it
				Text quitText = new Text("Are you sure you want to quit?");
				Font quitFont = Font.font("Tahoma", FontWeight.BOLD, FontPosture.REGULAR, 30);
				quitText.setFont(quitFont);
				quitText.setWrappingWidth(200);
				quitText.setTextAlignment(TextAlignment.CENTER);
				
					//Give event handlers to the buttons
					Button quitYes = new Button("Yes");
					quitYes.setPrefSize(50, 50);
					quitYes.setOnAction(f->{
						
			    			confirmQuit.close();
			    			MainMenu.window.setScene(new WelcomeScene(name));
			    			audioPane.themeSong();
			    			user.setScore(obj.getScore());
			    			userSave(user);
					});
				
				Button quitNo = new Button("No");
				quitNo.setPrefSize(50, 50);
				quitNo.setOnAction(f->{
					//resumes game 
					obj.getBlocks().play();
					obj.getBlockSpeed().play();
					player.playerBox.setImage(player.getPlayerRunning());
					confirmQuit.close();
				});
				
				//Created a vbox that adds the text and buttons
				HBox quitBox = new HBox();
				quitBox.setPrefSize(300, 300);
				quitBox.setAlignment(Pos.CENTER);
				quitBox.setSpacing(10);
				quitBox.setPadding(new Insets(10,10,10,10));
				
				quitBox.getChildren().addAll(quitText, quitYes, quitNo);
				
				confirmQuit.setScene(new Scene(quitBox));
				confirmQuit.show();
	
	    		} else if (e.getCode() == KeyCode.ESCAPE) {
	    			MainMenu.window.setScene(new WelcomeScene(name));
	    			audioPane.themeSong();
	    		}
			
	    });
	    
	    	/**
	    	 * 1) Check if the player has released a Key
	    	 * 2) If the Key DOWN (Arrow Key Down) has been released, set the image
	    	 *    of the player to it's original position (running)
	    	 * 3) Enable collision detection
	    	 */
		    keyboardNode.setOnKeyReleased(e-> {
		    		if(player.getLives() != 0) {
		    			press = true;
			    		if (e.getCode() == KeyCode.DOWN) {
			    			player.playerBox.setImage(player.getPlayerRunning());
			    			obj.setCollideBottom(true);
			    			down = true;
			    		} 
		    		}
		    });
		
	}
	
	/**
	 * @author James DiNovo
	 * 1) Create a new function called userSave
	 * 2) Create local variables 
	 * 3) Check if the file exists, if it does load the user score file
	 *    if it doesn't create the file
	 * 4) 
	 *    
	 */
	public static void userSave(Player p) {
		ArrayList<Player> players;
		File file = new File("RunningManSave.txt");
		Boolean sameScore = false;
		
		if(file.exists()) {
			players = userLoad();
		} else {
			players = new ArrayList<>(10);
		}
		
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			//to keep array no larger than 10
			if(players.size() == 10) {
				for(int i = 0; i < players.size(); i++) {
					if(p.getScore() >= players.get(i).getScore()) {
						players.add(p);
						players.remove(9);
						break;
					} 
				}
			//if it is smaller than 10 do this
			} else {
				for(Player player : players) {
					if(player.getScore() == p.getScore()) {
						players.set(players.indexOf(player), p);
						sameScore = true;
					}
				}
				if(!sameScore) {
					players.add(p);
				}
				
			}
			
			out.writeObject(players);
			out.flush();
			out.close();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * @author James DiNovo
	 * 1) Create a function called userLoad
	 * 2) Declare local variables
	 * 3) Check to see if the file exists, if it does not exist
	 *    create a new file
	 */
	public static ArrayList<Player> userLoad(){
		ArrayList<Player> players = new ArrayList<>(10);
		File file = new File("RunningManSave.txt");
		Player temp;
		
		//if no file create one and write a blank array to it 
		if(!file.exists()) {
			try {
				file.createNewFile();
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
				out.writeObject(players);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				
				//read in object from file
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				ArrayList<Player> readObject = (ArrayList<Player>) in.readObject();
				
				if(!readObject.isEmpty()) {
					players = readObject;
				}
				
				if(players.size() > 1) {
					for(int x = 0; x < players.size(); x++){  
						for(int y = 1; y < (players.size() - x); y++){  
							if(players.get(y - 1).getScore() < players.get(y).getScore()) {  
		                         temp = players.get(y - 1);  
		                         players.set((y - 1), players.get(y));
		                         players.set(y, temp);
							}  
						}
					}
				}
				
				in.close();
			
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}
		return players;
	}
	
	/**
	 * @author James DiNovo
	 * Takes player array from file and converts it into Integer array of scores
	 */
	public static ArrayList<Integer> scoreOut() {
		ArrayList<Integer> list = new ArrayList<>();
		ArrayList<Player> p = userLoad();
		
		//add scores of players to list
		for(Player player : p){
			list.add(player.getScore());
		}
		
		return list;
	}
	
	/**
	 * @author James DiNovo
	 * Takes player array from file and converts it into String array of names
	 */
	public static ArrayList<String> nameOut() {
		ArrayList<String> list = new ArrayList<>();
		ArrayList<Player> p = userLoad();
		
		//add scores of players to list
		for(Player player : p){
			list.add(player.getName());
		}
		
		return list;
	}
	
}
