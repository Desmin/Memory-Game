package gvsu.cis_350.project.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import gvsu.cis_350.project.core.game.GameDifficulty;

public abstract class GameSession extends Observable implements Observer {
	
	private String uiAction;
	
	protected Player sessionPlayer;
	protected int sessionMatches = 0;
	protected GameDifficulty sessionDifficulty = GameDifficulty.UNSET;
	protected boolean clickingEnabled = true;
	protected Card lastCardClicked;
	protected Map<JLabel, Card> cardMap = new HashMap<>();

	public String getUIAction() {
		return uiAction;
	}
	
	public void setUIAction(String action) {
		this.uiAction = action;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void setCardMap(Map<JLabel, Card> cardMap) {
		this.cardMap = cardMap;
	}
	
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
