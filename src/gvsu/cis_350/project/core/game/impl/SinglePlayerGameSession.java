package gvsu.cis_350.project.core.game.impl;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.Player;
import gvsu.cis_350.project.core.game.GameSession;
import gvsu.cis_350.project.core.game.difficulty.GameSessionDifficulty;
import gvsu.cis_350.project.core.game.difficulty.impl.SinglePlayerDifficulty;
import gvsu.cis_350.project.io.FileIO;
import gvsu.cis_350.project.ui.GameFrame;

import javax.swing.*;
import java.util.Objects;
import java.util.TimerTask;

public class SinglePlayerGameSession extends GameSession {

    private Player sessionPlayer;

    private SinglePlayerDifficulty sessionDifficulty;

    private int sessionMatches = 0;

    private TimerTask timeUpdate = new TimerTask() {
        long start = System.currentTimeMillis();

        @Override
        public void run() {
            long timeElapsed = start - System.currentTimeMillis();
            start = System.currentTimeMillis();
            long newTime = sessionDifficulty.getPlayerValue().getValue().longValue() + timeElapsed;
            if (newTime <= 0) {
                endGameWithLoss();
                this.cancel();
            }
            sessionDifficulty.setPlayerValue(newTime);
        }

    };

    public Player getSessionPlayer() {
        return sessionPlayer;
    }

    public SinglePlayerDifficulty getSessionDifficulty() {
        return sessionDifficulty;
    }

    @Override
    public boolean reset() {
        sessionMatches = 0;
        sessionDifficulty.reset();
        super.reset();
        new GameFrame(sessionDifficulty, getSessionPlayer().getName());
        return true;
    }

    @Override
    public void quit(boolean restart) {
        FileIO.savePlayerData(getSessionPlayer());
        super.quit(restart);
    }

    @Override
    public void addPlayerToGame(String username) {
        sessionPlayer = FileIO.loadPlayerData(username);
    }

    @Override
    public int getPlayerMatches() {
        return sessionMatches;
    }

    @Override
    public void addMatch() {
        sessionMatches++;
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
                        getSessionPlayer().addWin();
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
                        int matchesRemaining = sessionDifficulty.getPlayerValue().getValue().intValue();
                        matchesRemaining -= 1;
                        if (matchesRemaining <= 0)
                            endGameWithLoss();
                        sessionDifficulty.setPlayerValue(matchesRemaining);
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

    @Override
    public void initialize(GameSessionDifficulty sessionDifficulty) {
        if (sessionDifficulty instanceof SinglePlayerDifficulty) {
            this.sessionDifficulty = (SinglePlayerDifficulty) sessionDifficulty;
            if (this.sessionDifficulty.getSessionType().isTimed())
                new java.util.Timer(true).schedule(timeUpdate, 0, 1000);
        } else
            throw new IllegalArgumentException("Incorrect difficulty given! SinglePlayerDifficulty required.");
    }

}
