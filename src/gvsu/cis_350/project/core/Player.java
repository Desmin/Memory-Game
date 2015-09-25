package gvsu.cis_350.project.core;

import java.io.Serializable;

/**
 * The {@link Player} class represents a single user in the game.
 * 
 * @author Desmin Little
 *
 */
public class Player implements Serializable {
	
	public Player(String name, int wins, int losses) {
		this.name = name;
		update(wins, losses);
	}
	
	public void update(int wins, int losses) {
		this.wins = wins;
		this.losses = losses;
	}

	/**
	 * The serialization UID. Do not change!
	 */
	private static final long serialVersionUID = 9199972866695457612L;
	
	private String name;
	
	private int wins, losses;

	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}
	
	public String getName() {
		return name;
	}
}
