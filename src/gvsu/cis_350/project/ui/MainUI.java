package gvsu.cis_350.project.ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.game.Game;
import gvsu.cis_350.project.core.game.GameDifficulty;
import gvsu.cis_350.project.core.game.MemoryGame;
import gvsu.cis_350.project.utils.Util;

@SuppressWarnings("serial")

/**
 * This class represents the main user interface
 * for the game of concentration. It creates a 
 * GUI that has a grid layout and menu bars extra
 * options.
 * 
 * @author Nick Spruit
 * @version 10/7/2015
 */
public class MainUI extends JFrame implements ActionListener {
	
	/** Main panel that holds top, middle, and bottom panels */
	private JPanel mainPanel;
	
	/** Top panel holds the title of the game */
	private JPanel topPanel;
	
	/** Bottom panel holds the player info */
	private JPanel bottomPanel;
	
	/** Middle panel holds the cards in a grid */
	private JPanel gridPanel;
	
	/** List of cards that get added to panel */
	private List<Card> cards;
	
	/** Label for player name */
	private JLabel playerNameLabel;
	
	/** Label for player score */
	private JLabel playerScoreLabel;
	
	/** Label for message to user */
	private JLabel messageLabel;
	
	/** Label for title*/
	private JLabel gameTitleLabel; 
	
	/** Menu bar */
	private JMenuBar menuBar;
	
	/** File menu */
	private JMenu fileMenu; 
	
	/** About menu */
	private JMenu aboutMenu;
	
	/** New game menu item */
	private JMenuItem newGameItem;
	
	/** Quit menu item */
	private JMenuItem quitItem;
	
	/** About game menu item - for info about rules*/
	private JMenuItem aboutItem;
	
	/** Version item - for info about version */
	private JMenuItem versionItem;
	
	/** Game rules - how to play */
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
	
	/** Game version - version, date, and authors */
	private final String VERSION_INFO = "Version: 1.0\n" +
										"Date: 10/12/15\n" +
										"Authors: Desmin Little, Emily Theis, Nick Spruit";
					
	/**
	 * Returns the player score label
	 * @return playerScoreLabel - the label that
	 * holds the player score
	 */
	public JLabel getScoreLabel() {
		return playerScoreLabel;
	}
	
	/**
	 * Constructor creates cards for game, creates panels to
	 * add to frame, sets up labels, and uses listeners for
	 * menu items
	 */
	public MainUI(String name, GameDifficulty difficulty){
		
		//Create main underlying panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.WHITE);
		//Creates panel to hold cards
		gridPanel = new JPanel(new GridLayout(4,4));
		gridPanel.setBackground(Color.WHITE);
		//Creates panel to hold title
		topPanel = new JPanel();
		topPanel.setBackground(Color.WHITE);
		//Creates panel to hold player info
		bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.WHITE);
		
		//creates menus and items for file, quit, about, and version
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		aboutMenu = new JMenu("About");
		newGameItem = new JMenuItem("New Game");
		quitItem = new JMenuItem("Quit");
		aboutItem = new JMenuItem("About Game");
		versionItem = new JMenuItem("Version");
		
		//Adds menus and items to menu bar
		this.setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(aboutMenu);
		fileMenu.add(newGameItem);
		fileMenu.add(quitItem);
		aboutMenu.add(aboutItem);
		aboutMenu.add(versionItem);
		
		//Adds action listeners to each menu item
		newGameItem.addActionListener(this);
		quitItem.addActionListener(this);
		aboutItem.addActionListener(this);
		versionItem.addActionListener(this);
		
		
		//Fills the list will two of each card type using the given
		//difficulty level and randomizes their order.
		 cards = Util.randomize(Util.fillList(difficulty));
		
		//Adds the cards onto the frame
		cards.forEach((card) -> {
			gridPanel.add(card);
		});
		
		//Sets up a font
		Font f = new Font("Courier", Font.BOLD, 20);
		
		//Creates player, message, and game title labels with new font
		playerNameLabel = new JLabel("Player Name: " + name, SwingConstants.LEFT);
		playerNameLabel.setFont(f);
		playerScoreLabel = new JLabel("    Player Score: 0", SwingConstants.LEFT);
		playerScoreLabel.setFont(f);
		messageLabel = new JLabel("", SwingConstants.LEFT);
		messageLabel.setFont(f);
		gameTitleLabel = new JLabel("The Game of Concentration", SwingConstants.CENTER);
		gameTitleLabel.setFont(f);
		
		//Adds label to bottom panel
		topPanel.add(gameTitleLabel);
		bottomPanel.add(playerNameLabel);
		bottomPanel.add(playerScoreLabel);
		bottomPanel.add(messageLabel);
		
		//Adds top, grid, and bottom panel to main
		mainPanel.add(topPanel);
		mainPanel.add(gridPanel);
		mainPanel.add(bottomPanel);
		
		//Sets title, adds main panel, sets size, etc.
		this.setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
		this.setTitle("Concentration");
		this.getContentPane().add(mainPanel);
		this.setVisible(true);
		this.setSize(750,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	/**
	 * Determines what to do when one of the menu items 
	 * is clicked. 
	 * @param e - The action event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Game game = MemoryGame.getInstance();
		//Shutdown game and start over if new game is selected
		if(e.getSource() == newGameItem){
			game.shutdown(true);
		}
		//Quits game and quit program if quit is selected
		if(e.getSource() == quitItem){
			if(quitGame())
				game.shutdown(false);
		}
		//Shows info about game rules
		if(e.getSource() == aboutItem){
			JOptionPane.showMessageDialog(this, ABOUT_GAME,"HOW TO PLAY CONCENTRATION",1);
			
		}
		//Shows info about version of game
		if(e.getSource() == versionItem){
			JOptionPane.showMessageDialog(this, VERSION_INFO, "VERSION",1);
		}
	}
	/**
	 * Asks user if they really want to exit the game.
	 * @return true or false
	 */
	private boolean quitGame(){
		//Yes/No dialog asking user if they want to quit
		int quit = JOptionPane.showConfirmDialog(null, 
		"Are you sure you wish to quit?", "Quit Game?",JOptionPane.YES_NO_OPTION);
		if(quit == JOptionPane.YES_OPTION){
			return true;
		}
		else
			return false;
	}
	
	
}
