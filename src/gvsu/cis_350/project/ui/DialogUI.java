package gvsu.cis_350.project.ui;
import javax.swing.JOptionPane;
import gvsu.cis_350.project.core.game.GameDifficulty;
import gvsu.cis_350.project.core.game.MemoryGame;
/**
 * This class represents a dialog that starts the 
 * game. It asks for the user's name and then creates a 
 * main user interface for the game.
 * 
 * @author Nick Spruit
 * @version 10/7/2015
 */
public class DialogUI {

	/** Keeps track of user name */
	private String userName;
	
	/**
	 * Constructor displays dialog box and initializes a game
	 * set with an easy difficulty
	 */
	public DialogUI(){	
		//Empty string
		String empty = new String();
		
		//Player name and message for player
		userName = "";
		String message = "Welcome to the game of Concentration! \n Please enter your name";
		
		//Continues asking for a name until user name is not empty
		while(userName.equals(empty))
			userName = JOptionPane.showInputDialog(message);
		
		//Starts a new game
		new MemoryGame().initialize(userName.toUpperCase(), GameDifficulty.EASY);
	}	
	/**
	 * Main method starts up the dialog
	 * @param args - An array of strings
	 * @return void
	 */
	public static void main(String[] args){
		new DialogUI();
	}
}