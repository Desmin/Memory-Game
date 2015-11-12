package gvsu.cis_350.project.core.game.impl;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.game.GameSession;
import gvsu.cis_350.project.core.game.GameSessionDifficulty;
import gvsu.cis_350.project.core.game.event.ObservableActionListener;
import gvsu.cis_350.project.core.game.event.ObservableMouseListener;
import gvsu.cis_350.project.io.FileIO;
import gvsu.cis_350.project.ui.GameFrame;

import javax.swing.*;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class SinglePlayerGameSession extends GameSession {

    private TimerTask timeUpdate = new TimerTask() {
        long start = System.currentTimeMillis();

        @Override
        public void run() {
            long timeElapsed = start - System.currentTimeMillis();
            start = System.currentTimeMillis();
            long newTime = sessionDifficulty.getSessionTypeValue().longValue() + timeElapsed;
            if (newTime <= 0) {
                endGameWithLoss();
                this.cancel();
            }
            sessionDifficulty.setSessionTypeValue(newTime);
        }

    };

    @Override
    public boolean initialize(String sessionPlayerName, GameSessionDifficulty sessionDifficulty) {
        this.sessionDifficulty = sessionDifficulty;
        this.sessionPlayer = FileIO.loadPlayerData(sessionPlayerName);
        if (this.sessionDifficulty.getSessionType().isTimed())
            new Timer(true).schedule(timeUpdate, 0, 1000);
        return true;
    }

    @Override
    public boolean reset() {
        sessionMatches = 0;
        sessionDifficulty.reset();
        this.setUIAction("dispose");
        new GameFrame(sessionPlayer.getName(), sessionDifficulty);
        return true;
    }

    @Override
    public void quit(boolean restart) {
        try {
            FileIO.savePlayerData(sessionPlayer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setUIAction("dispose");
        if (!restart) {
            System.exit(1);
        } else
            reset();
    }

    @Override
    public void update(Observable ob, Object o) {
        if (ob instanceof ObservableActionListener) {
            quit(((ObservableActionListener) ob).getFlag());
        } else if (ob instanceof ObservableMouseListener) {
            JLabel label = ((ObservableMouseListener) ob).getLabelClicked();
            onCardClick(label);
        }

    }

    public void onCardClick(JLabel label) {
        Card card = cardMap.get(label);
        if (card.hasBeenClicked() || !clickingEnabled) // If the card has
            // already been clicked
            // we return.
            return;
        label.setIcon(card.getCardType().getFace());// Changes the state of the
        // card.
        card.setHasBeenClicked(true);
        if (Objects.isNull(lastCardClicked)) { // If this is the first card to
            // be flipped over we set it as
            // the
            lastCardClicked = card; // last card to clicked.
            return;
        } else
            clickingEnabled = false; // Otherwise we disable clicking as we'll
        // be checking for a match.

        boolean match = card.equals(lastCardClicked); // Do we have a match?
        new SwingWorker<Void, Void>() { // SwingWorker to delay the
            // flipping/resetting of the clicked
            // cards.

            @Override
            protected Void doInBackground() throws Exception {
                if (match) { // If it's a match we update how many matches we've
                    // got and display that.
                    addMatch();
                    setUIAction("update_score");
                }
                Thread.sleep(match ? 500 : 1000); // Wait 500-1000ms to do
                // anything else so players
                // can
                return null; // see which cards they clicked.
            }

            @Override
            protected void done() {
                if (match) {
                    if (card.hasBeenClicked())
                        label.setIcon(Card.BLANK);
                    else
                        label.setIcon(Card.BACK);
                    resetLastCardClicked();
                    setUIAction("repaint");
                    if (sessionMatches == sessionDifficulty.getSessionSetting().getMatchesNeededToWin()) {
                        sessionPlayer.addWin();
                        int response = JOptionPane.showConfirmDialog(label.getParent(),
                                "You won! Do you wish to start again?", "Winner!", JOptionPane.YES_NO_OPTION,
                                JOptionPane.INFORMATION_MESSAGE);
                        boolean restart = false;
                        switch (response) {
                            case JOptionPane.YES_OPTION:
                                restart = true;
                                break;
                        }
                        quit(restart);
                    }
                } else {
                    if (sessionDifficulty.getSessionType().hasLimitedMatchAttempts()) {
                        int matchesRemaining = sessionDifficulty.getSessionTypeValue().intValue();
                        matchesRemaining -= 1;
                        if (matchesRemaining <= 0)
                            endGameWithLoss();
                        sessionDifficulty.setSessionTypeValue(matchesRemaining);
                    }
                    card.setHasBeenClicked(false);
                    lastCardClicked.setHasBeenClicked(false);
                    if (card.hasBeenClicked())
                        label.setIcon(Card.BLANK);
                    else
                        label.setIcon(Card.BACK);
                    resetLastCardClicked();
                    lastCardClicked = null;
                }
                clickingEnabled = true;
            }

        }.execute();
    }

    private void resetLastCardClicked() {
        for (Entry<JLabel, Card> e : cardMap.entrySet()) {
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
