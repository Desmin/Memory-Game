package gvsu.cis_350.project.core.game;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import gvsu.cis_350.project.core.Player;
import gvsu.cis_350.project.core.io.FileIO;
import gvsu.cis_350.project.ui.MainUI;

public class MemoryGame implements Game {
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	private int points = 0;
	
	private MainUI gameFrame;
	private Player player;
	
	private static MemoryGame instance;
	
	public static MemoryGame getInstance() {
		return instance;
	}
	
	@Override
	public void initialize(String username) {
		instance = this;
		this.player = FileIO.loadPlayerData(username);
		log.log(Level.INFO, "Building gameframe...");
		gameFrame = new MainUI(username);
		log.log(Level.INFO, "Gameframe successfully built.");
	}

	@Override
	public void reset() {
		log.log(Level.INFO, "Resetting game...");
		points = 0;
		initialize(player.getName());
	}

	@Override
	public boolean update() {
		//check for matches
		//add points if a match occurs
		//update UI
		return true;
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
