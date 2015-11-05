package gvsu.cis_350.project.core.game.impl.event;

import gvsu.cis_350.project.core.game.GameSession;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public final class ObservableActionListener extends Observable implements ActionListener {

    /**
     * Game rules - how to play
     */
    private final String ABOUT_GAME = "How to Play...\n\n" +
            "1.) Click a card with a '?' picture.\n" +
            "	   The card will turn over and reveal a picture.\n" +
            "2.) Click another card that you think is a match.\n" +
            "    The card will turn over and reveal another\n" +
            "    picture that is the same or different.\n" +
            "3.) If the cards are a match, you gain a point and\n" +
            "    the cards will disappear.\n" +
            "4.) Continue trying to match all the pairs of cards\n" +
            "    until they are all gone.\n" +
            "5.) You win the game when all 8 pairs are matched.";
    /**
     * Game version - version, date, and authors
     */
    private final String VERSION_INFO = "Version: 1.0\n" +
            "Date: 10/12/15\n" +
            "Authors: Desmin Little, Emily Theis, Nick Spruit";
    private boolean resetFlag = false;

    public ObservableActionListener(GameSession session) {
        addObserver(session);
    }

    /**
     * @return the initialzeReset
     */
    public boolean getFlag() {
        return resetFlag;
    }

    public void flagChange(boolean flag) {
        this.resetFlag = flag;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Determines what to do when one of the menu items
     * is clicked.
     *
     * @param e - The action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem item = (JMenuItem) e.getSource();
        String name = item.getName();
        //Shutdown game and start over if new game is selected
        if (name.equals("new_game")) {
            if (quitGame(true))
                flagChange(true);
        }
        //Quits game and quit program if quit is selected
        if (name.equals("quit_game"))
            if (quitGame(false))
                flagChange(false);

        //Shows info about game rules
        if (name.equals("about")) {
            JOptionPane.showMessageDialog(item.getParent(), ABOUT_GAME, "HOW TO PLAY CONCENTRATION", 1);

        }
        //Shows info about version of game
        if (name.equals("version")) {
            JOptionPane.showMessageDialog(item.getParent(), VERSION_INFO, "VERSION", 1);
        }
    }

    /**
     * Asks user if they really want to exit the game.
     *
     * @return true or false
     */
    private boolean quitGame(boolean restart) {
        //Yes/No dialog asking user if they want to quit
        int quit = JOptionPane.showConfirmDialog(null,
                "Are you sure you wish to " + (restart ? "restart?" : "quit?"), "Quit Game?", JOptionPane.YES_NO_OPTION);
        return quit == JOptionPane.YES_OPTION;
    }

}
