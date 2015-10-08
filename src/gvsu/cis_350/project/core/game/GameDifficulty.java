package gvsu.cis_350.project.core.game;

/**
 * Represents the different levels of difficulty a game can have.
 * 
 * @author Desmin Little
 *
 */
public enum GameDifficulty {
	EASY(16),
	AVERAGE(32),
	HARD(64);
	
	/**
	 * Each difficulty has a specified number of cards, which will
	 * be placed on the UI.
	 */
	int associatedCardNumber = 0;
	
	/**
	 * Initializes a difficultly with the given number of cards.
	 * 
	 * @param associatedCardNumber
	 */
	GameDifficulty(int associatedCardNumber) {
		this.associatedCardNumber = associatedCardNumber;
	}
	
	public int getNumberOfCards() {
		return associatedCardNumber;
	}
	
	/**
	 * Tells us the number of matches needed to win at this
	 * difficulty, which will be 1/2 of the number of cards
	 * on the board.
	 * 
	 * @return The number of matches needed to win.
	 */
	public int getMatchesNeededToWin() {
		return getNumberOfCards() / 2;
	}
}
