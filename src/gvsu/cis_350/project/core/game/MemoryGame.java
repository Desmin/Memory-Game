package gvsu.cis_350.project.core.game;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import gvsu.cis_350.project.core.Player;
import gvsu.cis_350.project.core.io.FileIO;
import gvsu.cis_350.project.ui.MainUI;

public class MemoryGame implements Game {
	
	private Logger log = Logger.getLogger(this.toString());
	
	private int points = 0;
	
	private MainUI gameFrame;
	private Player player;
	
	private static MemoryGame instance;
	
	public static MemoryGame getInstance() {
		return instance;
	}
	
	@Override
	public boolean initialize(String username) {
		instance = this;
		this.player = FileIO.loadPlayerData(username);
		gameFrame = new MainUI(username);
		log.log(Level.INFO, "UI successfully built.");
		return true;
	}

	@Override
	public void reset() {
		log.log(Level.INFO, "Resetting game...");
		points = 0;
	}

	@Override
	public boolean update() {
		//check for matches
		//add points if a match occurs
		//update UI
		return true;
	}

	@Override
	public boolean shutdown(boolean restarting) {
		try {
			FileIO.savePlayerData(player);
		} catch (IOException e) {
			e.printStackTrace();
		}
		gameFrame.dispose();
		if (!restarting)
			System.exit(1);
		return true;
	}
	
	public int getPoints() {
		return points;
	}

	@Override
	public Player getPlayer() {
		return player;
	}


}
