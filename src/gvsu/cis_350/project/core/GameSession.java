package gvsu.cis_350.project.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import gvsu.cis_350.project.core.game.GameDifficulty;
import gvsu.cis_350.project.ui.GameJLabel;

public abstract class GameSession extends Observable implements Observer {
	
	private Player sessionPlayer;
	private int sessionMatches = 0;
	private GameDifficulty sessionDifficulty = GameDifficulty.UNSET;
	private boolean clickingEnabled = true;
	private Card lastCardClicked;
	private String uiAction;
	private Map<GameJLabel, Card> cardMap = new HashMap<>();
	
	/**
	 * @return the cardMap
	 */
	public Map<GameJLabel, Card> getCardMap() {
		return cardMap;
	}

	/**
	 * @param cardMap the cardMap to set
	 */
	public void setCardMap(Map<GameJLabel, Card> cardMap) {
		this.cardMap = cardMap;
	}

	/**
	 * @param sessionPlayer the sessionPlayer to set
	 */
	public void setSessionPlayer(Player sessionPlayer) {
		this.sessionPlayer = sessionPlayer;
	}

	/**
	 * @param sessionDifficulty the sessionDifficulty to set
	 */
	public void setSessionDifficulty(GameDifficulty sessionDifficulty) {
		this.sessionDifficulty = sessionDifficulty;
	}

	public String getUIAction() {
		return uiAction;
	}
	
	public void setUIAction(String action) {
		this.uiAction = action;
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * @param sessionMatches the sessionMatches to set
	 */
	public void setSessionMatches(int sessionMatches) {
		this.sessionMatches = sessionMatches;
	}

	/**
	 * @return the clickingEnabled
	 */
	public boolean isClickingEnabled() {
		return clickingEnabled;
	}

	/**
	 * @param clickingEnabled the clickingEnabled to set
	 */
	public void setClickingEnabled(boolean clickingEnabled) {
		this.clickingEnabled = clickingEnabled;
	}

	/**
	 * @return the lastCardClicked
	 */
	public Card getLastCardClicked() {
		return lastCardClicked;
	}

	/**
	 * @param lastCardClicked the lastCardClicked to set
	 */
	public void setLastCardClicked(Card lastCardClicked) {
		this.lastCardClicked = lastCardClicked;
	}

	/**
	 * @return the sessionDifficulty
	 */
	public GameDifficulty getSessionDifficulty() {
		return sessionDifficulty;
	}

	/**
	 * @return the sessionPlayer
	 */
	public Player getSessionPlayer() {
		return sessionPlayer;
	}

	/**
	 * @return the sessionMatches
	 */
	public int getSessionMatches() {
		return sessionMatches;
	}

	public void addMatch() {
		this.sessionMatches++;
	}

	public abstract boolean initialize(String sessionPlayerName, GameDifficulty sessionDifficulty);
	
	public abstract boolean reset();
	
	public abstract void quit(boolean restart);

}
