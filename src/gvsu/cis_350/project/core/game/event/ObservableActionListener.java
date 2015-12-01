package gvsu.cis_350.project.core.game.event;

import gvsu.cis_350.project.core.game.GameSession;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;

public final class ObservableActionListener extends Observable implements ActionListener, WindowListener {

    public ObservableActionListener(GameSession session) {
        addObserver(session);
    }


    public void flagChange(boolean flag) {
        this.setChanged();
        this.notifyObservers(flag);
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
            /*
      Game rules - how to play
     */
            String ABOUT_GAME = "How to Play...\n\n" +
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
            JOptionPane.showMessageDialog(item.getParent(), ABOUT_GAME, "HOW TO PLAY CONCENTRATION", 1);

        }
        //Shows info about version of game
        if (name.equals("version")) {
            /*
      Game version - version, date, and authors
     */
            String VERSION_INFO = "Version: 2.0\n" +
                    "Date: 11/12/15\n" +
                    "Authors: Desmin Little, Emily Theis, Nick Spruit";
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

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        flagChange(false);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
