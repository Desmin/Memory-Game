package gvsu.cis_350.project.ui;

import gvsu.cis_350.project.core.game.difficulty.GameSessionDifficulty;
import gvsu.cis_350.project.core.game.difficulty.impl.SinglePlayerDifficulty;
import gvsu.cis_350.project.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * This class creates a name panel for 1 or two players.
 * Either one or two players can enter their names, or select
 * from a combo box. The game starts once the user clicks the
 * "Start" button.
 *
 * @author Nick Spruit
 * @version 12/10/15
 */
public class GameNameDialog extends JFrame implements ActionListener {

    /**
     * Refers to the game settings dialog frame
     */
    JFrame f;
    /**
     * Panel for combobox and labels
     */
    private JPanel namePanel;
    /**
     * Buttons for canceling, starting game
     */
    private JButton startButton, cancelButton;

    /**
     * Combo boxes for at most two players
     */
    private JComboBox nameBox1, nameBox2;

    /**
     * Difficulty of game
     */
    private GameSessionDifficulty dif;

    /**
     * Keeps track of whether the game is single or double player
     */
    private boolean singlePlayer;

    /**
     * Constructor creates adds elements to the frame based on whether
     * the game is single or double player.
     *
     * @param d - game difficulty
     * @param f - frame that called this dialog
     */
    public GameNameDialog(GameSessionDifficulty d, JFrame f) {
        this.f = f;
        dif = d;

        //Creates panels and buttons
        namePanel = new JPanel();
        namePanel.setBackground(Color.WHITE);
        startButton = new JButton("START GAME");
        startButton.addActionListener(this);
        startButton.setBackground(Color.GREEN);
        cancelButton = new JButton("CANCEL");
        cancelButton.addActionListener(this);
        cancelButton.setBackground(Color.RED);

        //Single player game
        if (dif instanceof SinglePlayerDifficulty) {
            //Creates a panel to input a single player's name
            singlePlayer = true;
            namePanel.setLayout(new GridLayout(2, 2));
            JLabel playerLabel = new JLabel("Name of Player: ");
            nameBox1 = new JComboBox<>(Util.formatUserSelection(new File("./data/").list()));
            nameBox1.setEditable(true);
            nameBox1.setBackground(Color.WHITE);
            namePanel.add(playerLabel);
            namePanel.add(nameBox1);
        }
        //Two player game
        else {
            //Creates a panel to input two player's names (2 combo boxes)
            singlePlayer = false;
            namePanel.setLayout(new GridLayout(3, 2));
            String player1, player2;
            JLabel playerLabel1, playerLabel2;
            playerLabel1 = new JLabel("Name of Player 1: ");
            playerLabel2 = new JLabel("Name of Player 2: ");

            nameBox1 = new JComboBox<>(Util.formatUserSelection(new File("./data/").list()));
            nameBox2 = new JComboBox<>(Util.formatUserSelection(new File("./data/").list()));
            nameBox1.setEditable(true);
            nameBox2.setEditable(true);
            nameBox1.setBackground(Color.WHITE);
            nameBox2.setBackground(Color.WHITE);
            namePanel.add(playerLabel1);
            namePanel.add(nameBox1);
            namePanel.add(playerLabel2);
            namePanel.add(nameBox2);
        }
        //Adds buttons
        namePanel.add(cancelButton);
        namePanel.add(startButton);

        //Resizes panel
        this.getContentPane().add(namePanel);
        if (singlePlayer)
            this.setSize(400, 100);
        else
            this.setSize(400, 150);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * Method determines what is done when the use clicks
     * the start or cancel button
     *
     * @param e - the action event fired
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //User wants to start game
        if (e.getSource() == startButton) {
            f.setVisible(false);
            //Makes sure the single player's name is valid
            if (singlePlayer) {
                String player1 = (String) nameBox1.getSelectedItem();
                if (player1.trim().equals(""))
                    JOptionPane.showMessageDialog(null, "Enter player's name...");
                else {
                    new GameFrame(dif, player1);
                    this.dispose();
                    f.dispose();
                }
            }
            //Makes sure each of the player's names are valid
            else {
                String player1 = (String) nameBox1.getSelectedItem();
                String player2 = (String) nameBox2.getSelectedItem();
                if (player1.trim().equals(""))
                    JOptionPane.showMessageDialog(null, "Enter player 1's name...");
                else if (player2.trim().equals(""))
                    JOptionPane.showMessageDialog(null, "Enter player 2's name...");
                else if (player1.equals(player2))
                    JOptionPane.showMessageDialog(null, "Players cannot share the same name...");
                else {
                    new GameFrame(dif, player1, player2);
                    this.dispose();
                    f.dispose();
                }
            }
        }
        //Cancel button disposes of frame and sets the game setting dialog to visible
        else {
            this.dispose();
            f.setVisible(true);
        }
    }
}