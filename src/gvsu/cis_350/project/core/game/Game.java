package gvsu.cis_350.project.core.game;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.Player;

/**
 * An interface containing necessary methods to run a memory game.
 * 
 * @author Desmin Little
 *
 */
public interface Game {
	
	/**
	 * Sets up and runs a new game.
	 * 
	 * @param username The user we're initializing the game with.
	 */
	public void initialize(String username, GameDifficulty difficulty);
	
	/**
	 * Resets the current game and starts a new one.
	 */
	public void reset();
	
	/**
	 * Updates the user interface and other important variables when certain events
	 * take place in the game.
	 * 
	 */
	public void onCardClick(Card card);
	
	/**
	 * Shuts down the game with the intent to start over or exit the program.
	 * @param restarting Whether or not we'll be restarting the game.
	 */
	public void shutdown(boolean restarting);
	
	/**
	 * Gets the Player associated with this game.
	 * @return The Player.
	 */
	public Player getPlayer();

}
