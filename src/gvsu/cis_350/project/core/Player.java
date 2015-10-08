package gvsu.cis_350.project.core;

import java.io.Serializable;

/**
 * The {@link Player} class represents a single user in the game.
 * 
 * @author Desmin Little
 *
 */
public class Player implements Serializable {
	
	/**
	 * The default constructor defining a player given
	 * the player's name and wins.
	 * @param name The player's name.
	 * @param wins How many wins this player has.
	 */
	public Player(String name, int wins) {
		this.name = name;
		setWins(wins);
	}
	
	/**
	 * Sets this players wins to the given amount.
	 * @param wins
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * The serialization UID. Do not change!
	 */
	private static final long serialVersionUID = 9199972866695457612L;
	
	/**
	 * The name of this player.
	 */
	private String name;
	
	/**
	 * The number of wins associated with this player.
	 */
	private int wins;

	public int getWins() {
		return wins;
	}
	
	/**
	 * Adds a single win to this player.
	 */
	public void addWin() {
		wins++;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Player) {
			Player player = (Player)obj;
			return this.wins == player.wins && this.name == player.name;
		}
		
		return false;
	}
}
