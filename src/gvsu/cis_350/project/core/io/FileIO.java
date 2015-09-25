package gvsu.cis_350.project.core.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import gvsu.cis_350.project.core.Player;
//import gvsu.cis_350.project.core.game.MemoryGame;

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
	private static final String SAVE_PATH = "./data/";
	
	/**
	 * Serializes important information about the given node into .mgd file.
	 * 
	 * @param node The node being saved.
	 * @throws IOException
	 */
	public static final void savePlayerData(Player player) throws IOException {
		FileOutputStream file = new FileOutputStream(SAVE_PATH + player.getName() + ".mgd");
		DataOutputStream out = new DataOutputStream(file);
		out.writeInt(player.getWins());
		out.writeInt(player.getLosses());
		out.close();
		file.close();
	}
	
	/**
	 * Deserializes a file and loads that information into a node.
	 * 
	 * @param name The user we're trying to load previous data of.
	 * @throws IOException
	 */
	public static final Player loadPlayerData(String name) {
		Player newPlayer = new Player(name, 0, 0);
		FileInputStream file;
		try {
			file = new FileInputStream(SAVE_PATH + name + ".mgd");
			if (Objects.nonNull(file)) {
				DataInputStream in = new DataInputStream(file);
				newPlayer.update(in.readInt(), in.readInt());
				in.close();
			}
		} catch (IOException e) {
			return newPlayer;
		}
		try {
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newPlayer;
	}

}
