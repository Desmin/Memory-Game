package gvsu.cis_350.project.ui;

import javax.swing.*;


public class DialogUI {

	//Name of player
	private String userName;
	
	//Message to player
	private String message;
	
	//The user interface
	private MainUI mainUserInterface;
	
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
		
		//Starts the game interface
		mainUserInterface = new MainUI(userName);
		
	}
	
	//Main method starts the game
	public static void main(String[] args){
		DialogUI dialog= new DialogUI();
	}
}
