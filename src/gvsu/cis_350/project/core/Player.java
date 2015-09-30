package gvsu.cis_350.project.core;

import java.io.Serializable;

/**
 * The {@link Player} class represents a single user in the game.
 * 
 * @author Desmin Little
 *
 */
public class Player implements Serializable {
	
	public Player(String name, int wins) {
		this.name = name;
		update(wins);
	}
	
	public void update(int wins) {
		this.wins = wins;
	}

	/**
	 * The serialization UID. Do not change!
	 */
	private static final long serialVersionUID = 9199972866695457612L;
	
	private String name;
	
	private int wins;

	public int getWins() {
		return wins;
	}
	
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
