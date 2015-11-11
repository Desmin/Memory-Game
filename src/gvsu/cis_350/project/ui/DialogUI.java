package gvsu.cis_350.project.ui;
import gvsu.cis_350.project.core.game.GameSessionDifficulty;
import gvsu.cis_350.project.core.game.GameSessionSetting;
import gvsu.cis_350.project.core.game.GameSessionType;
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
 * 10/7/2015
 */
public class DialogUI extends JFrame {

	/** Combo box for difficulty level */
    private JComboBox<GameSessionSetting> difficultyLevel;
    
    /** Combo box for session type */
    private JComboBox<GameSessionType> gameSessionType;
    
    /** Combo box for name input */
    private JComboBox<String> userSelection;

    /**
     * Constructor adds panels, combo boxes, and labels to
     * dialog box 
     */
    public DialogUI() {
    	this.setTitle("Setup Memory Game");
        this.setBackground(Color.WHITE);
        
        //Creates combo boxes
        difficultyLevel = new JComboBox<>(GameSessionSetting.values());
        difficultyLevel.setBackground(Color.WHITE);
        gameSessionType = new JComboBox<>(GameSessionType.values());
        gameSessionType.setBackground(Color.WHITE);

        //Creates panels and buttons for dialog box
        JLabel background = new JLabel(new ImageIcon("resources/thinkingImg.jpg"));
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JPanel titlePanel = new JPanel(new FlowLayout());
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ButtonListener());

        //Creates labels and adds font type
        Font f = new Font("Courier", Font.BOLD, 20);
        JLabel name = new JLabel("Player Name:");
        JLabel title = new JLabel("Memory Game Setup");
        title.setFont(f);
        JLabel difficultyLabel = new JLabel("Choose Difficulty:");
        JLabel typeLabel = new JLabel("Choose Type:");

        //Sets up combo box for user name
        userSelection = new JComboBox<>(Util.formatUserSelection(new File("./data/").list()));
        userSelection.setEditable(true);

        //Adds panels and buttons to background
        background.setLayout(null);
        background.add(titlePanel);
        background.add(panel);
        background.add(startButton);

        //Size and location of panel
        panel.setSize(390, 70);
        panel.setLocation(200, 130);
        panel.setBackground(Color.WHITE);

        //Adds labels to main panel
        panel.add(name);
        panel.add(userSelection);
        panel.add(difficultyLabel);
        panel.add(difficultyLevel);
        panel.add(typeLabel);
        panel.add(gameSessionType);

        //Sets location and size of title panel
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setSize(300, 50);
        titlePanel.setLocation(235, 70);
        titlePanel.add(title);

        //Sets location and size of button
        startButton.setSize(100, 30);
        startButton.setLocation(350, 220);
        startButton.setBackground(Color.YELLOW);

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
        new DialogUI();
    }

    /**
     * Class is a button listener to start the game. Makes sure 
     * the user name is valid and creates a new game with the given
     * difficulty parameters
     */
    private class ButtonListener implements ActionListener {
    	/**
    	 * Method determines what to have done when the start button
    	 * is clicked
    	 * @param e - The action event
    	 */
        @Override
        public void actionPerformed(ActionEvent e) {
            //Creates difficulty
        	GameSessionDifficulty dif = new GameSessionDifficulty(
                    (GameSessionSetting) difficultyLevel.getSelectedItem(),
                    (GameSessionType) gameSessionType.getSelectedItem());
            String user = (String)userSelection.getSelectedItem();
            //Makes sure the name is not empty
            if (!user.trim().equals(""))
                new GameFrame(user, dif);
            else {
                userSelection.grabFocus();
                return;
            }
            //Disposes of the dialog frame
            dispose();
        }
    }
}