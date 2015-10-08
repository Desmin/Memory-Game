package gvsu.cis_350.project.core.game;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.Player;
import gvsu.cis_350.project.core.io.FileIO;
import gvsu.cis_350.project.ui.MainUI;

/**
 * A single player memory game.
 * 
 * @author Desmin Little
 *
 */
public class MemoryGame implements Game {
	
	/**
	 * The logger for this class.
	 */
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	/**
	 * The number of matches the player has found.
	 */
	private int matches = 0;
	
	/**
	 * The main user interface.
	 */
	private MainUI gameFrame;
	
	/**
	 * The player associated with this game.
	 */
	private Player player;
	
	/**
	 * The difficulty of this game.
	 */
	private GameDifficulty difficulty;
	
	/**
	 * A singleton instance of this game, so that we
	 * can access its methods in the UI class.
	 */
	private static MemoryGame instance;
	
	/**
	 * Remembers which card was last clicked in the game.
	 */
	private Card lastClicked;
	
	/**
	 * Tells us whether or not clicking is enabled on the interface.
	 */
	private boolean clickingEnabled = true;
	
	public static MemoryGame getInstance() {
		return instance;
	}
	
	@Override
	public void initialize(String username, GameDifficulty difficulty) {
		instance = this;
		this.difficulty = difficulty;
		this.player = FileIO.loadPlayerData(username);
		log.log(Level.INFO, "Building gameframe...");
		gameFrame = new MainUI(username, difficulty);
		log.log(Level.INFO, "Gameframe successfully built.");
	}

	@Override
	public void reset() {
		log.log(Level.INFO, "Resetting game...");
		matches = 0;
		initialize(player.getName(), difficulty);
	}
	
	@Override
	public void onCardClick(Card card) {
		if (card.hasBeenClicked() || !clickingEnabled) //If the card has already been clicked we return.
			return;
		card.flip(); //Changes the state of the card.
		if (Objects.isNull(lastClicked)) { //If this is the first card to be flipped over we set it as the
			lastClicked = card;			   //last card to clicked.
			return;
		} else
			clickingEnabled = false; //Otherwise we disable clicking as we'll be checking for a match.
		
		boolean match = card.equals(lastClicked); //Do we have a match?
		new SwingWorker<Void, Void>() { //SwingWroker to delay the flipping/resetting of the clicked cards.

			@Override
			protected Void doInBackground() throws Exception {
				if (match) { //If it's a match we update how many matches we've got and display that.
					matches++;
					gameFrame.getScoreLabel().setText("    Player Score: " + matches);
				}
				Thread.sleep(match ? 500 : 1000); //Wait 500-1000ms to do anything else so players can
				return null;					  //see which cards they clicked.
			}
			
			@Override
			protected void done() {
				if (match) {
					card.reset();
					lastClicked.reset();
					gameFrame.revalidate();
					gameFrame.repaint();
					lastClicked = null;
					if (matches == difficulty.getMatchesNeededToWin()) {
						getPlayer().addWin();
						int response = JOptionPane.showConfirmDialog(gameFrame,
								"You won! Do you wish to start again?", "Winner!",
								JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						boolean restart = false;
						switch(response) {
						case JOptionPane.YES_OPTION:
							restart = true;
							break;
						}
						shutdown(restart);
					}
				} else {
					card.setHasBeenClicked(false);
					lastClicked.setHasBeenClicked(false);
					card.reset();
					lastClicked.reset();
					lastClicked = null;
				}
				clickingEnabled = true;
			}
			
		}.execute();
	}

	@Override
	public void shutdown(boolean restarting) {
		try {
			FileIO.savePlayerData(player);
		} catch (IOException e) {
			log.log(Level.SEVERE, "Error saving user data!", e);
		}
		log.log(Level.INFO, "Destroying gameframe...");
		gameFrame.dispose();
		if (!restarting) {
			log.log(Level.INFO, "Shutting program down...");
			System.exit(1);
		} else
			reset();
	}
	
	public int getPoints() {
		return matches;
	}

	@Override
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MemoryGame){
			MemoryGame game = (MemoryGame)obj;
			return this.difficulty == game.difficulty;
		}
		return false;
	}


}
