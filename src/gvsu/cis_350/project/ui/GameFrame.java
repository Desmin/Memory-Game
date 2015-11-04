package gvsu.cis_350.project.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.GameSession;
import gvsu.cis_350.project.core.GameSessionDifficulty;
import gvsu.cis_350.project.core.GameSessionSetting;
import gvsu.cis_350.project.core.game.SinglePlayerGameSession;
import gvsu.cis_350.project.core.game.event.ObservableActionListener;
import gvsu.cis_350.project.core.game.event.ObservableMouseListener;
import gvsu.cis_350.project.utils.Util;

@SuppressWarnings("serial")

/**
 * This class represents the main user interface for the game of concentration.
 * It creates a GUI that has a grid layout and menu bars extra options.
 * 
 * @author Nick Spruit
 * @author Desmin Little
 * @version 10/7/2015
 */
public class GameFrame extends JFrame implements Observer {

	/** Main panel that holds top, middle, and bottom panels */
	private JPanel mainPanel;

	/** Top panel holds the title of the game */
	private JPanel topPanel;

	/** Bottom panel holds the player info */
	private JPanel bottomPanel;

	/** Middle panel holds the cards in a grid */
	private JPanel gridPanel;

	/** Label for player name */
	private JLabel playerNameLabel;

	/** Label for player score */
	private JLabel playerScoreLabel;

	/** Label for message to user */
	private JLabel messageLabel;

	/** Label for title */
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

	/** About game menu item - for info about rules */
	private JMenuItem aboutItem;

	/** Version item - for info about version */
	private JMenuItem versionItem;

	/**
	 * Returns the player score label
	 * 
	 * @return playerScoreLabel - the label that holds the player score
	 */
	public JLabel getScoreLabel() {
		return playerScoreLabel;
	}

	/**
	 * Constructor creates cards for game, creates panels to add to frame, sets
	 * up labels, and uses listeners for menu items
	 */
	public GameFrame(String name, GameSessionDifficulty difficulty) {

		SinglePlayerGameSession session = new SinglePlayerGameSession();
		session.initialize(name, difficulty);
		session.addObserver(this);
		difficulty.addObserver(this);

		// Create main underlying panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(Color.WHITE);
		// Creates panel to hold cards

		gridPanel = new JPanel(
				new GridLayout(this.yLength(difficulty.getSessionSetting()), xLength(difficulty.getSessionSetting())));

		gridPanel.setBackground(Color.WHITE);
		// Creates panel to hold title
		topPanel = new JPanel();
		topPanel.setBackground(Color.WHITE);
		// Creates panel to hold player info
		bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.WHITE);

		// creates menus and items for file, quit, about, and version
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		aboutMenu = new JMenu("About");
		newGameItem = new JMenuItem("New Game");
		quitItem = new JMenuItem("Quit");
		aboutItem = new JMenuItem("About Game");
		versionItem = new JMenuItem("Version");

		// Adds menus and items to menu bar
		this.setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(aboutMenu);
		fileMenu.add(newGameItem);
		fileMenu.add(quitItem);
		aboutMenu.add(aboutItem);
		aboutMenu.add(versionItem);

		newGameItem.setName("new_game");
		quitItem.setName("quit_game");
		aboutItem.setName("about");
		versionItem.setName("version");

		ObservableActionListener listener = new ObservableActionListener(session);
		// Adds action listeners to each menu item
		newGameItem.addActionListener(listener);
		quitItem.addActionListener(listener);
		aboutItem.addActionListener(listener);
		versionItem.addActionListener(listener);

		// Fills the list will two of each card type using the given
		// difficulty level and randomizes their order.
		Map<JLabel, Card> map = new HashMap<>();
		List<Card> list = Util.fetchRandomizedList(difficulty.getSessionSetting());

		// Adds the cards onto the frame
		list.forEach((card) -> {
			JLabel label = new JLabel();
			label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
			label.setIcon(Card.BACK);
			label.addMouseListener(new ObservableMouseListener(session));
			gridPanel.add(label);
			map.put(label, card);
		});

		session.setCardMap(map);
		// Sets up a font
		Font f = new Font("Courier", Font.BOLD, 20);

		// Creates player, message, and game title labels with new font
		playerNameLabel = new JLabel("Player Name: " + name, SwingConstants.LEFT);
		playerNameLabel.setFont(f);
		playerScoreLabel = new JLabel("    Player Score: 0", SwingConstants.LEFT);
		playerScoreLabel.setFont(f);
		messageLabel = new JLabel("", SwingConstants.LEFT);
		messageLabel.setFont(f);
		gameTitleLabel = new JLabel("The Game of Concentration", SwingConstants.CENTER);
		gameTitleLabel.setFont(f);

		// Adds label to bottom panel
		topPanel.add(gameTitleLabel);
		bottomPanel.add(playerNameLabel);
		bottomPanel.add(playerScoreLabel);
		bottomPanel.add(messageLabel);

		// Adds top, grid, and bottom panel to main
		mainPanel.add(topPanel);
		mainPanel.add(gridPanel);
		mainPanel.add(bottomPanel);

		// Sets title, adds main panel, sets size, etc.
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setTitle("Concentration");
		this.getContentPane().add(mainPanel);
		this.setVisible(true);
		this.setSize(750, 600);
		this.setLocationRelativeTo(null);
		// this.setResizable(false);
	}

	private int xLength(GameSessionSetting gd) {
		return gd.getNumberOfCards() / 4;
	}

	private int yLength(GameSessionSetting gd) {
		return gd.getNumberOfCards() / xLength(gd);
	}

	@Override
	public void update(Observable ob, Object o) {
		if (o instanceof GameSession) {
			GameSession session = (GameSession) ob;
			String action = session.getUIAction();
			switch (action) {
			case "dispose":
				dispose();
				return;
			case "update_score":
				getScoreLabel().setText("Player Score: " + session.getSessionMatches());
				return;
			case "repaint":
				revalidate();
				repaint();
				return;
			default:
				return;
			}
		} else if (ob instanceof GameSessionDifficulty) {
			if (Objects.isNull(messageLabel))
				return;
			messageLabel.setText(((GameSessionDifficulty) ob).createUIString());
		}

	}

}
