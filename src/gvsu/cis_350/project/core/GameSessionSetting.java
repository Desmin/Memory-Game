package gvsu.cis_350.project.core;

import gvsu.cis_350.project.utils.Util;

/**
 * GameSetting represents the size of a game, that is, how many
 * cards the game will contain and gives a general idea of how long
 * a game may take.
 * 
 * @author Desmin Little
 *
 */
public enum GameSessionSetting {
	SMALL(16),
	MEDIUM(32),
	LARGE(64);
	
	/**
	 * Each setting has a specified number of cards, which will
	 * be placed on the UI.
	 */
	int associatedCardNumber = 0;
	
	/**
	 * Initializes a setting with the given number of cards.
	 * 
	 * @param associatedCardNumber
	 */
	GameSessionSetting(int associatedCardNumber) {
		this.associatedCardNumber = associatedCardNumber;
	}
	
	public int getNumberOfCards() {
		return associatedCardNumber;
	}
	
	/**
	 * Tells us the number of matches needed to win at this
	 * setting, which will be 1/2 of the number of cards
	 * on the board.
	 * 
	 * @return The number of matches needed to win.
	 */
	public int getMatchesNeededToWin() {
		return getNumberOfCards() / 2;
	}
	
	public boolean isSmall() {
		return this.equals(SMALL);
	}
	
	public boolean isMedium() {
		return this.equals(MEDIUM);
	}
	
	public boolean isLarge() {
		return this.equals(LARGE);
	}
	
	@Override
	public String toString() {
		return Util.toLowerFirstUC(this.name());
	}
}
