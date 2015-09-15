package gvsu.cis_350.project.core.game;

import java.util.logging.Level;
import java.util.logging.Logger;

import gvsu.cis_350.project.core.Player;

public class MemoryGame implements Game {

	/**
	 * Generated UID for serialization. Do not remove!
	 */
	private static final long serialVersionUID = -612405127231085808L;
	
	private Logger log = Logger.getLogger(this.toString());
	
	private int points = 0;
	
	@Override
	public boolean initialize() {
		//check for saved game, load if available
		//build UI
		log.log(Level.INFO, "UI successfully built.");
		return true;
	}

	@Override
	public void reset() {
		log.log(Level.INFO, "Resetting game...");
		points = 0;
		if (initialize())
			log.log(Level.INFO, "Successfully reset game!");
		else
			log.log(Level.SEVERE, "Something went wrong!");
	}

	@Override
	public boolean update() {
		//check for matches
		//add points if a match occurs
		//update UI
		return true;
	}

	@Override
	public boolean shutdown() {
		//if the game isn't finished prompt to save it
			//if yes, save it
		//if the game was loaded from a save and is now finished, delete the useless save
		//save player data (wins/losses)
		//clean and close UI
		//terminate program
		return true;
	}

	@Override
	public boolean isPlayer() {
		return false;
	}


}
