package gvsu.cis_350.project.core.game.impl;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.Player;
import gvsu.cis_350.project.core.game.GameSession;
import gvsu.cis_350.project.core.game.difficulty.GameSessionDifficulty;
import gvsu.cis_350.project.core.game.difficulty.SessionDifficultyValue;
import gvsu.cis_350.project.core.game.difficulty.impl.TwoPlayerDifficulty;
import gvsu.cis_350.project.io.FileIO;
import gvsu.cis_350.project.ui.GameFrame;

import javax.swing.*;
import java.security.SecureRandom;
import java.util.Objects;
import java.util.TimerTask;

/**
 * Created by Desmin Little on 11/27/2015.
 */
public class TwoPlayerGameSession extends GameSession {

    private Player currentPlayer;

    private Player playerOne, playerTwo;

    private int playerOneMatches, playerTwoMatches;

    private TwoPlayerDifficulty sessionDifficulty;
    private TimerTask timeUpdate = new TimerTask() {
        long start = System.currentTimeMillis();

        @Override
        public void run() {
            long timeElapsed = start - System.currentTimeMillis();
            start = System.currentTimeMillis();
            long newTime = sessionDifficulty.getPlayerOneValue().getValue().longValue() + timeElapsed;
            if (newTime <= 0) {
                endGameWithLoss();
                this.cancel();
            }
            sessionDifficulty.setPlayerOneValue(newTime);
        }

    };

    public TwoPlayerDifficulty getSessionDifficulty() {
        return sessionDifficulty;
    }

    public SessionDifficultyValue getCurrentPlayerValue() {
        return getCurrentPlayer().equals(playerOne) ? sessionDifficulty.getPlayerOneValue() : sessionDifficulty.getPlayerTwoValue();
    }

    public void setCurrentPlayerValue(int value) {
        if (getCurrentPlayer().equals(playerOne))
            sessionDifficulty.setPlayerOneValue(value);
        else
            sessionDifficulty.setPlayerTwoValue(value);
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void addPlayerToGame(String username) {
        if (Objects.nonNull(playerOne) && Objects.nonNull(playerTwo))
            return;
        Player loaded = FileIO.loadPlayerData(username);
        if (Objects.isNull(playerOne))
            playerOne = loaded;
        else if (Objects.isNull(playerTwo))
            playerTwo = loaded;
    }

    @Override
    public int getPlayerMatches() {
        if (getCurrentPlayer().equals(playerOne))
            return playerOneMatches;
        else
            return playerTwoMatches;
    }

    public int getTotalMatches() {
        return playerOneMatches + playerTwoMatches;
    }

    @Override
    public void addMatch() {
        if (getCurrentPlayer().equals(playerOne))
            playerOneMatches++;
        else
            playerTwoMatches++;
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
                    if (getTotalMatches() == sessionDifficulty.getSessionSetting().getMatchesNeededToWin()) {
                        String winner = null;
                        if (playerOneMatches == playerTwoMatches) {
                            playerOne.addWin();
                            playerTwo.addWin();
                            winner = "A tie! You both";
                        } else {
                            Player p = playerOneMatches > playerTwoMatches ? playerOne : playerTwo;
                            p.addWin();
                            winner = p.getName();
                        }

                        int response = JOptionPane.showConfirmDialog(label.getParent(),
                                winner + " won! Do you wish to start again?", "Winner!", JOptionPane.YES_NO_OPTION,
                                JOptionPane.INFORMATION_MESSAGE);
                        boolean restart = response == JOptionPane.YES_OPTION;
                        quit(restart);
                        return;
                    }
                } else {
                    if (sessionDifficulty.getSessionType().hasLimitedMatchAttempts()) {
                        int matchesRemaining = getCurrentPlayerValue().getValue().intValue();
                        matchesRemaining -= 1;
                        if (matchesRemaining <= 0) {
                            endGameWithLoss();
                            return;
                        }
                        setCurrentPlayerValue(matchesRemaining);
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
                if (!match)
                    switchPlayer();
            }

        }.execute();
    }

    @Override
    public void initialize(GameSessionDifficulty sessionDifficulty) {
        if (sessionDifficulty instanceof TwoPlayerDifficulty) {
            int player = new SecureRandom().nextInt(1);
            currentPlayer = player == 0 ? playerOne : playerTwo;
            this.sessionDifficulty = (TwoPlayerDifficulty) sessionDifficulty;
            if (this.sessionDifficulty.getSessionType().isTimed())
                new java.util.Timer(true).schedule(timeUpdate, 0, 1000);
        } else
            throw new IllegalArgumentException("Incorrect difficulty given! TwoPlayerDifficulty required.");
    }

    @Override
    public boolean reset() {
        super.reset();
        playerOneMatches = 0;
        playerTwoMatches = 0;
        sessionDifficulty.reset();
        new GameFrame(sessionDifficulty, playerOne.getName(), playerTwo.getName());
        return true;
    }

    @Override
    public void quit(boolean restart) {
        FileIO.savePlayerData(playerOne);
        FileIO.savePlayerData(playerTwo);
        super.quit(restart);
    }

    private void switchPlayer() {
        if (getCurrentPlayer().equals(playerOne))
            currentPlayer = playerTwo;
        else
            currentPlayer = playerOne;
        setUIAction("switch_player");
        JOptionPane.showMessageDialog(null,
                "It's " + currentPlayer.getName() + "'s turn!");
    }
}
