package gvsu.cis_350.project.ui;
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
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.game.Game;
import gvsu.cis_350.project.core.game.GameDifficulty;
import gvsu.cis_350.project.core.game.MemoryGame;
import gvsu.cis_350.project.utils.Utilities;

@SuppressWarnings("serial")
public class MainUI extends JFrame implements ActionListener {
	
	private JPanel mainPanel, gridPanel, topPanel, bottomPanel;
	private List<Card> cards;
	private JLabel playerNameLabel, playerScoreLabel, messageLabel, gameTitleLabel; 
	private JMenuBar menuBar;
	private JMenu fileMenu, 
				  aboutMenu;
	private JMenuItem newGameItem, 
					  quitItem, 
					  aboutItem,
					  versionItem;
	
	public JLabel getScoreLabel() {
		return playerScoreLabel;
	}
	
	public MainUI(String name, GameDifficulty difficulty){
		
		/*
		 * A WindowAdapter that ensures the game completely shuts down when
		 * the UI is closed.
		 */
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				MemoryGame.getInstance().shutdown(false);
				super.windowClosing(e);
			}
		});
		
		//Create three panels
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		//mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
		gridPanel = new JPanel(new GridLayout(4,4));
		//gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
		topPanel = new JPanel();
		//topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
		bottomPanel = new JPanel();
		//bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
		
		//Menus & items
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		aboutMenu = new JMenu("About");
		newGameItem = new JMenuItem("New Game");
		quitItem = new JMenuItem("Quit");
		aboutItem = new JMenuItem("About Game");
		versionItem = new JMenuItem("Version");
		
		//Adds menus & items
		this.setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(aboutMenu);
		fileMenu.add(newGameItem);
		fileMenu.add(quitItem);
		aboutMenu.add(aboutItem);
		aboutMenu.add(versionItem);
		
		//Adds action listeners
		newGameItem.addActionListener(this);
		quitItem.addActionListener(this);
		aboutItem.addActionListener(this);
		versionItem.addActionListener(this);
		
		/*
		 * Fills the list will two of each card type using the given
		 * difficulty level and randomizes their order.
		 */
		cards = Utilities.randomize(Utilities.fillList(difficulty));
		
		//Adds the cards onto the frame.
		cards.forEach((card) -> {
			gridPanel.add(card);
		});
		
		Font f = new Font("Courier", Font.BOLD, 20);
		
		playerNameLabel = new JLabel("Player Name: " + name, SwingConstants.LEFT);
		playerNameLabel.setFont(f);
		
		playerScoreLabel = new JLabel("    Player Score: 0", SwingConstants.LEFT);
		playerScoreLabel.setFont(f);
		
		messageLabel = new JLabel("", SwingConstants.LEFT);
		messageLabel.setFont(f);
		
		gameTitleLabel = new JLabel("The Game of Concentration", SwingConstants.CENTER);
		gameTitleLabel.setFont(f);
		
		topPanel.add(gameTitleLabel);
		bottomPanel.add(playerNameLabel);
		bottomPanel.add(playerScoreLabel);
		bottomPanel.add(messageLabel);
		

		mainPanel.add(topPanel);
		mainPanel.add(gridPanel);
		mainPanel.add(bottomPanel);
		
		this.setTitle("Concentration");
		this.getContentPane().add(mainPanel);
		this.setVisible(true);
		this.setSize(600,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Game game = MemoryGame.getInstance();
		if(e.getSource() == newGameItem){
			game.shutdown(true);
		}
		if(e.getSource() == quitItem){
			game.shutdown(false);
		}
		if(e.getSource() == aboutItem){
			
		}
		if(e.getSource() == versionItem){
			
		}
		
	}
	
}
