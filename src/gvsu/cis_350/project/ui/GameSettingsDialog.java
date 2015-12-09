package gvsu.cis_350.project.ui;

import gvsu.cis_350.project.core.game.difficulty.GameSessionDifficulty;
import gvsu.cis_350.project.core.game.difficulty.GameSessionSetting;
import gvsu.cis_350.project.core.game.difficulty.GameSessionType;
import gvsu.cis_350.project.core.game.difficulty.impl.SinglePlayerDifficulty;
import gvsu.cis_350.project.core.game.difficulty.impl.TwoPlayerDifficulty;
import gvsu.cis_350.project.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * This class represents a dialog that starts the game. It asks for the user's
 * name and then creates a main user interface for the game.
 *
 * @author Nick Spruit
 * @author Desmin Little
 *         10/7/2015
 */
public class GameSettingsDialog extends JFrame {

	/**
	 * Frame holds the main input settings
	 */
	JFrame frame;
    
	/**
     * Combo box for difficulty level
     */
    private JComboBox<GameSessionSetting> difficultyLevel;
    
    /**
     * Combo box for session type
     */
    private JComboBox<GameSessionType> gameSessionType;

    /**
     * Combo box for number of players input
     */
    private JComboBox<String> numberPlayers;
    
    /**
     * Panels for holding input fields
     */
    private JPanel panel;
    
    /**
     * Continue button
     */
    private JButton button;
    
    /**
     * Background label for image
     */
	private JLabel background;
	
    /**
     * Constructor adds panels, combo boxes, and labels to
     * dialog box
     */
    public GameSettingsDialog() {
    	frame = this;
        //Creates combo boxes
        difficultyLevel = new JComboBox<>(GameSessionSetting.values());
        difficultyLevel.setBackground(Color.WHITE);
        gameSessionType = new JComboBox<>(GameSessionType.values());
        gameSessionType.setBackground(Color.WHITE);
        numberPlayers = new JComboBox<>();
        numberPlayers.setBackground(Color.WHITE);
        numberPlayers.addItem("Single Player");
        numberPlayers.addItem("Two Player");
        
        //Creates panels and buttons for dialog box
        background = new JLabel(new ImageIcon("resources/thinkingImg.jpg"));
        JPanel titlePanel = new JPanel(new FlowLayout());
        Font f = new Font("Courier", Font.BOLD, 20);
        JLabel title = new JLabel("Memory Game Setup");
        title.setFont(f);
        background.setLayout(null);
        background.add(titlePanel);
        button = new JButton("CONTINUE...");
        button.addActionListener(new Listener());
        
        panel = new JPanel(new GridLayout(3, 2));        
        
        //Creates labels and adds font type
        JLabel numberOfPlayers = new JLabel("Number of Players:");
        JLabel difficultyLabel = new JLabel("Choose Difficulty:");
        JLabel typeLabel = new JLabel("Choose Type:");

          
        //Size and location of panel
        panel.setSize(390, 100);
        panel.setLocation(200, 130);
        panel.setBackground(Color.WHITE);

        //Adds labels to main panel
        panel.add(numberOfPlayers);
        panel.add(numberPlayers);
        panel.add(difficultyLabel);
        panel.add(difficultyLevel);
        panel.add(typeLabel);
        panel.add(gameSessionType);

        //Sets location and size of title panel
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setSize(300, 50);
        titlePanel.setLocation(235, 70);
        titlePanel.add(title);

        //Adds background and panel, sets location of button
        background.add(button);
        background.add(panel);
        button.setSize(150, 30);
        button.setLocation(350, 240);
        button.setBackground(Color.YELLOW);

        //Sets dialog size and location
        this.getContentPane().add(background);
        this.setSize(900, 575);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

        //Adds a window listener for exit
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });        
    }

    /**
     * Method starts creates a dialog to start the game
     *
     * @param args - An array of Strings
     */
    public static void main(String[] args) {
        new GameSettingsDialog();
    }

    /**
     * Class is a button listener to start the game. Makes sure
     * the user name is valid and creates a new game with the given
     * difficulty parameters
     */
    private class Listener implements ActionListener {
        /**
         * Method determines what to have done when the start button
         * is clicked
         *
         * @param e - The action event
         */
        @Override
        public void actionPerformed(ActionEvent e) {
        	frame.setVisible(false);
        	//Creates a dialog for player name(s) input
        	if((String)numberPlayers.getSelectedItem() == "Single Player")
        		new GameNameDialog(new SinglePlayerDifficulty(
        				(GameSessionSetting)difficultyLevel.getSelectedItem(),
                        (GameSessionType)gameSessionType.getSelectedItem()), frame);
        	
        	else
        		new GameNameDialog(new TwoPlayerDifficulty(
        				(GameSessionSetting)difficultyLevel.getSelectedItem(),
                        (GameSessionType)gameSessionType.getSelectedItem()),frame);
        }
    }
}