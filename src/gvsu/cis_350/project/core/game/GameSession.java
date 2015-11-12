package gvsu.cis_350.project.core.game;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.Player;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public abstract class GameSession extends Observable implements Observer {

    protected Player sessionPlayer;

    public Player getSessionPlayer() {
        return sessionPlayer;
    }

    public GameSessionDifficulty getSessionDifficulty() {
        return sessionDifficulty;
    }

    protected int sessionMatches = 0;
    protected GameSessionDifficulty sessionDifficulty;
    protected boolean clickingEnabled = true;
    protected Card lastCardClicked;
    protected Map<JLabel, Card> cardMap = new HashMap<>();
    private String uiAction;

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

    public abstract boolean initialize(String sessionPlayerName, GameSessionDifficulty sessionDifficulty);

    public abstract boolean reset();

    public abstract void quit(boolean restart);

    public void endGameWithLoss() {
        int quit = JOptionPane.showConfirmDialog(null,
               "You lost the game. Do you wish to restart?", "Game Last!", JOptionPane.YES_NO_OPTION);
        quit(quit == JOptionPane.YES_OPTION);
    }

}
