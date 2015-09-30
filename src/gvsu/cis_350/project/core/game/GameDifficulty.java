package gvsu.cis_350.project.core.game;

public enum GameDifficulty {
	EASY(16),
	AVERAGE(32),
	HARD(64);
	
	int associatedCardNumber = 0;
	GameDifficulty(int associatedCardNumber) {
		this.associatedCardNumber = associatedCardNumber;
	}
	
	public int getNumberOfCards() {
		return associatedCardNumber;
	}
	
	public int getPointsToWin() {
		return getNumberOfCards() / 2;
	}
}
