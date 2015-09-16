package gvsu.cis_350.project.core.io;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import gvsu.cis_350.project.core.Node;
import gvsu.cis_350.project.core.Player;
import gvsu.cis_350.project.core.game.MemoryGame;
import javafx.scene.control.Button;

/**
 * A class used for the saving and loading of files to and from the game.
 * 
 * @author Desmin Little
 *
 */
public class FileIO {
	
	/**
	 * The program's save path for data files.
	 */
	private static final String SAVE_PATH = "./data/saves/";
	
	/**
	 * Serializes important information about the given node into .mgd file.
	 * 
	 * @param node The node being saved.
	 * @return True if the save is successful, false otherwise.
	 * @throws IOException
	 */
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
	
	/**
	 * Deserializes a file and loads that information into a node.
	 * @return A node with the loaded information.
	 * @throws IOException
	 */
	public static final Node load() throws IOException {
		//FileInputStream file = new FileInputStream()
		return null;
	}
	

}
