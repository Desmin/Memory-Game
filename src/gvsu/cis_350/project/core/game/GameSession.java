package gvsu.cis_350.project.core.game;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.game.difficulty.GameSessionDifficulty;
import gvsu.cis_350.project.core.game.event.ObservableActionListener;
import gvsu.cis_350.project.core.game.event.ObservableMouseListener;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public abstract class GameSession extends Observable implements Observer {

    protected boolean clickingEnabled = true;
    protected Card lastCardClicked;
    protected Map<JLabel, Card> cardMap = new HashMap<>();
    private String uiAction;

    public abstract void addPlayerToGame(String username);

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

    public abstract int getPlayerMatches();

    public abstract void addMatch();

    public abstract void onCardClick(JLabel label);

    public abstract void initialize(GameSessionDifficulty difficulty);

    public boolean reset() {
        this.setUIAction("dispose");
        return true;
    }

    public void quit(boolean restart) {
        this.setUIAction("dispose");
        if (!restart) {
            System.exit(1);
        } else
            reset();
    }

    @Override
    public void update(Observable ob, Object o) {
        if (ob instanceof ObservableActionListener) {
            quit((Boolean) o);
        } else if (ob instanceof ObservableMouseListener) {
            onCardClick((JLabel) o);
        }

    }

    public void endGameWithLoss() {
        int quit = JOptionPane.showConfirmDialog(null,
                "You lost the game. Do you wish to restart?", "Game Last!", JOptionPane.YES_NO_OPTION);
        quit(quit == JOptionPane.YES_OPTION);
    }


    public void resetLastCardClicked() {
        for (Map.Entry<JLabel, Card> e : cardMap.entrySet()) {
            if (e.getValue() == lastCardClicked) {
                JLabel label = e.getKey();
                if (e.getValue().hasBeenClicked())
                    label.setIcon(Card.BLANK);
                else
                    label.setIcon(Card.BACK);
                break;
            }
        }
        lastCardClicked = null;
    }

}
