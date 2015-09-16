package gvsu.cis_350.project.core.game;

import gvsu.cis_350.project.core.Node;

/**
 * An interface containing necessary methods to run a memory game.
 * 
 * @author Desmin Little
 *
 */
public interface Game extends Node {
	
	/**
	 * Sets up and runs a new game.
	 * @return True if successful, false otherwise.
	 */
	public boolean initialize();
	
	/**
	 * Resets the current game and starts a new one.
	 */
	public void reset();
	
	/**
	 * Updates the user interface and other important variables when certain events
	 * take place in the game.
	 * 
	 * @return True if successful, false otherwise.
	 */
	public boolean update();
	
	/**
	 * Saves and shuts down the current game.
	 * @return True if successful, false otherwise.
	 */
	public boolean shutdown();

}
