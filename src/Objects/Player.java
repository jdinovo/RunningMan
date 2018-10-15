package Objects;

import java.io.Serializable;

public class Player implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @author James DiNovo
	 */

	
	//member variables
	private String name;
	private int score;
	
	/**
	 * 
	 * @param player
	 * The name is set when game is loaded
	 * this object stores all the player's data
	 */
	public Player(String name) {
		this.name = name;
		this.score = 0;
	}
	
	public Player(Player player) {
		this.name = player.getName();
		this.score = player.getScore();
	}
	
	public Player() {
		this.score = 0;
	}
	
	/**
	 * getters and setters
	 */

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
