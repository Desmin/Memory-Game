package gvsu.cis_350.project.core;

/**
 * The {@link Player} class represents a single user in the game.
 * 
 * @author Desmin Little
 *
 */
public class Player implements Node {

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

	@Override
	public boolean isPlayer() {
		return true;
	}
}
