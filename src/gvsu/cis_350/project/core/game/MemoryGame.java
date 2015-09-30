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

public class MemoryGame implements Game {
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	private int points = 0;
	
	private MainUI gameFrame;
	private Player player;
	private GameDifficulty difficulty;
	
	private static MemoryGame instance;
	
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
		points = 0;
		initialize(player.getName(), difficulty);
	}
	
	private Card lastClicked;
	private boolean clickingEnabled = true;
	
	@Override
	public void onCardClick(Card card) {
		if (card.hasBeenClicked() || !clickingEnabled)
			return;
		System.out.println("Clicked: " + card.getCardType().name());
		System.out.println("Last clicked " + ((Objects.isNull(lastClicked)) ? "nothing" : lastClicked.getCardType().name()));
		card.flip();
		if (Objects.isNull(lastClicked)) {
			lastClicked = card;
			return;
		} else {
			if (card.equals(lastClicked)) {
				points++;
				card.reset();
				lastClicked.reset();
				lastClicked = null;
				gameFrame.getScoreLabel().setText("    Player Score: " + points);
				gameFrame.revalidate();
				gameFrame.repaint();
				if (points == difficulty.getPointsToWin()) {
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
					this.shutdown(restart);
				}
			} else {
				clickingEnabled = false;
				new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						Thread.sleep(1000);
						return null;
					}
					
					@Override
					protected void done() {
						card.setHasBeenClicked(false);
						lastClicked.setHasBeenClicked(false);
						card.reset();
						lastClicked.reset();
						lastClicked = null;
						clickingEnabled = true;
					}
					
				}.execute();
			}
		}
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
		return points;
	}

	@Override
	public Player getPlayer() {
		return player;
	}


}
