package gvsu.cis_350.project.core;

import java.io.Serializable;

/**
 * A generic node class used to simplify saving and loading of data.
 * 
 * @author Desmin Little
 *
 */
public interface Node extends Serializable {
	
	/**
	 * Tells us if this class is a {@link Player} class.
	 * 
	 * @return True if the class is a player class, false otherwise.
	 */
	public boolean isPlayer();

}
