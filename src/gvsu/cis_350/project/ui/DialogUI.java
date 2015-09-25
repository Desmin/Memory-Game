package gvsu.cis_350.project.ui;

import javax.swing.JOptionPane;

import gvsu.cis_350.project.core.game.MemoryGame;


public class DialogUI {

	//Name of player
	private String userName;
	
	//Message to player
	private String message;
	
	//Constructor shows dialog to start game
	public DialogUI(){
		
		//Empty string
		String empty = new String();
		
		//Player name and message for player
		userName = "";
		message = "Welcome to the game of Concentration! \n Please enter your name";
		
		//Continues asking for a name
		while(userName.equals(empty))
			userName = JOptionPane.showInputDialog(message);
		
		new MemoryGame().initialize(userName);
	}
	
	//Main method starts the game
	public static void main(String[] args){
		new DialogUI();
	}
}
