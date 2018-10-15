package Objects;


import java.util.ArrayList;
import java.util.Random;

import Pane.RunningMan;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
/**
 * 
 * @author James DiNovo
 * ObjectGenerator Sends either a high or a low object character randomly directly across the screen
 * while the object is moving it is constantly being checked whether or not it has collided with the player object
 *
 */
public class ObjectGenerator extends Pane {
	/**
	 * @author James DiNovo
	 */
	
		//declaring variables
		private Timeline blocks;
		private Timeline blockSpeed;
		
		private Image missile = new Image("Graphics/missile.png");
		
		private double x = 840, y1 = 540, y2 = 460;
		
		private ImageView projectileBox1 = new ImageView(missile);
		private ImageView projectileBox2 = new ImageView(missile);
				 
		private ArrayList<ImageView> recs = new ArrayList<>();
		private Random r = new Random();
		private int i = 0;
		private int speedIncrementer = 50;
		
		private CollisionDetection collision = new CollisionDetection();
		private Character player = new Character();
	
		private boolean checkCollisionBottom = false;
		private boolean checkCollisionTop = false;
		public boolean collideTop = true;
		public boolean collideBottom = true;
		
		private Timeline timer;
		
		private Player user = new Player();
		Audio audioPane = new Audio();
		
		RunningMan test;
		
		Text scoreText = new Text();
		Text livesText = new Text();

		/**
		 * @author James DiNovo
		 */
		//constructor
		public ObjectGenerator() {
			
			getProjectileBox1();
			getProjectileBox2();
			
			projectileBox1.setX(x);
			projectileBox1.setY(y1);
			projectileBox1.setFitWidth(40);
			projectileBox1.setFitHeight(15);
			
			projectileBox2.setX(x);
			projectileBox2.setY(y2);
			projectileBox2.setFitWidth(40);
			projectileBox2.setFitHeight(15);
			
			recs.add(projectileBox1);
			recs.add(projectileBox2);
			
			/*
			 * setting score box
			 */
			scoreText.setText("Score: " + user.getScore());
			scoreText.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 40));
			scoreText.setFill(Color.WHITE);
			scoreText.setLayoutX(500);
			scoreText.setLayoutY(100);
			
			/*
			 * setting lives box
			 */
			livesText.setText("Lives: " + player.getLives());
			livesText.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 40));
			livesText.setFill(Color.WHITE);
			livesText.setLayoutX(100);
			livesText.setLayoutY(100);
			
			//add to pane
			this.getChildren().addAll(recs);
			this.getChildren().addAll(scoreText, livesText);
	        
			//creating timeLine that moves block
			blocks = new Timeline(new KeyFrame(Duration.millis(1), e-> {
				newObject();
			}));
			
			//creating timeline that adjusts block speed
			blockSpeed = new Timeline(new KeyFrame(Duration.millis(1), e-> {
				//set speed of blocks using this cycle count
				blocks.setCycleCount(speedIncrementer);
				blocks.play();
			}));
			
			blockSpeed.setCycleCount(Timeline.INDEFINITE);
			//blockSpeed.getKeyFrames().add(keyFrame);
			blockSpeed.play();
			
		}

		/**
		 * @author James DiNovo
		 */
		
		//method
		protected void newObject() {
			//move until passed screen
			if (recs.get(i).getX() >= -40 && !(checkCollisionTop || checkCollisionBottom)) {
				x -= 1;
				recs.get(i).setX(x);
				
				//check if collided
				if (collideTop) {
					checkCollisionTop = collision.collisionDetection(this.projectileBox1, player.playerBox);
				}
				if (collideBottom) {
					checkCollisionBottom = collision.collisionDetection(this.projectileBox2, player.playerBox);
				}	
		    		if (checkCollisionTop) {
		    			getChildren().remove(recs.get(i));	
		    			RunningMan.player.fadeHit();
		    			audioPane.hitEffect();
		    		} else if (checkCollisionBottom) {
		    			getChildren().remove(recs.get(i));
		    			RunningMan.player.fadeHit();
		    			audioPane.hitEffect();
		    		}
			//reset once passed
			} else {
				x = 840;
				recs.get(i).setX(x);
				
				if(checkCollisionTop || checkCollisionBottom) {
					getChildren().add(recs.get(i));
					checkCollisionTop = false;
					checkCollisionBottom = false;
					player.setLives(player.getLives() - 1);
					livesText.setText("Lives: " + player.getLives());
					
					/**
					 * @author Dorian Harusha
					 * This checks to if the player lives have reached 0
					 * If lives have reached 0, then play the die animation
					 */
					
					if (player.getLives() == 0) {
		    				// Kill the player
		    				RunningMan.player.playerBox.setImage(player.getPlayerDying());
		    				RunningMan.player.playerBox.setY(380);
		    				
		    				// Stop the objects
		    				this.getBlocks().stop();
		    				this.getBlockSpeed().stop();
		    				
		    				
		    				/**
		    				 * @author James DiNovo
		    				 * update stats
		    				 * save game 
		    				 * display game over 
		    				 * display press escape
		    				 * 
		    				 */
		    				//update game stats
		    				RunningMan.player.setLives(0);
		    				RunningMan.user.setScore(getScore());
						livesText.setText("Lives: " + RunningMan.player.getLives());
						
						//save game
		    				RunningMan.userSave(RunningMan.user); 
		    				
		    				//display game over
		    				livesText.setText("GAME OVER\nScore: " + RunningMan.user.getScore());
		    				livesText.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 100));
		    				livesText.setFill(Color.RED);
		    				livesText.setLayoutX(100);
		    				livesText.setLayoutY(300);
		    				
		    				//display press escape
		    				scoreText.setText("Press ESCAPE key to exit");
		    				scoreText.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 20));
		    				scoreText.setFill(Color.WHITE);
		    				scoreText.setLayoutX(150);
		    				scoreText.setLayoutY(450);
	    				
	    				}
				} else {
					scoreCounter();
				}
				i = r.nextInt(2);
				speedIncrementer += 5;
				
			}
		}

		public ImageView getProjectileBox1() {
			return projectileBox1;
		}

		public void setProjectile_box1(ImageView projectileBox1) {
			this.projectileBox1 = projectileBox1;
		}

		public ImageView getProjectileBox2() {
			return projectileBox2;
		}

		public void setProjectile_box2(ImageView projectileBox2) {
			this.projectileBox2 = projectileBox2;
		}	
		
		public void setCollideTop(Boolean x) {
			this.collideTop = x;
			timer = new Timeline(new KeyFrame(Duration.millis(400), e-> {
				this.collideTop = true;
			}));
			this.timer.setCycleCount(1);
			this.timer.play();
		}
		
		public void setCollideBottom(Boolean x) {
			this.collideBottom = x;
		}
		//Set getter/setter on both Timelines to stop the missiles
		//from playing when user quits back to the main menu
		public Timeline getBlocks() {
			return blocks;
		}

		public void setBlocks(Timeline blocks) {
			this.blocks = blocks;
		}

		public Timeline getBlockSpeed() {
			return blockSpeed;
		}

		public void setBlockSpeed(Timeline blockSpeed) {
			this.blockSpeed = blockSpeed;
		}
		
		public int getScore() {
			return user.getScore();
		}

		public void scoreCounter() {
			user.setScore(user.getScore() + 1);
			scoreText.setText("Score: " + user.getScore());
		}
	
}
