package gvsu.cis_350.project.core.io;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;

import gvsu.cis_350.project.core.Node;
import gvsu.cis_350.project.core.Player;
import gvsu.cis_350.project.core.game.MemoryGame;

public class FileIO {
	
	private static final String SAVE_PATH = "./data/saves/";
	
	public static final boolean save(Node node) throws IOException {
		FileOutputStream file = new FileOutputStream(SAVE_PATH + (node.isPlayer() ? "player/" + ((Player)node).getName() : "gamesave/game") + ".mgd");
		DataOutputStream out = new DataOutputStream(file);
		if (node.isPlayer()) {
			Player player = (Player)node;
			out.writeInt(player.getWins());
			out.writeInt(player.getLosses());
		} else {
			MemoryGame game = (MemoryGame)node;
			//save current points
			//save cards and their states
		}
		out.close();
		file.close();
		return true;
	}
	
	public static final Node load() throws IOException {
		//FileInputStream file = new FileInputStream()
		return null;
	}

}
