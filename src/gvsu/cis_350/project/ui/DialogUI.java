package gvsu.cis_350.project.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import gvsu.cis_350.project.core.game.GameDifficulty;
import gvsu.cis_350.project.utils.Util;
/**
 * This class represents a dialog that starts the 
 * game. It asks for the user's name and then creates a 
 * main user interface for the game.
 * 
 * @author Nick Spruit
 * @version 10/7/2015
 */

public class DialogUI extends JFrame {
	
	private JComboBox difficultyLevel;
	private JButton startButton;
	private JTextField nameInput;
	
	public DialogUI(){
		this.setTitle("Setup Memory Game");
		this.setBackground(Color.WHITE);
		difficultyLevel = new JComboBox(GameDifficulty.values());
		difficultyLevel.setBackground(Color.WHITE);
		
		JLabel background = new JLabel(new ImageIcon("resources/thinkingImg.jpg"));
		JPanel panel = new JPanel(new GridLayout(2,2));
		JPanel titlePanel = new JPanel(new FlowLayout());
		startButton = new JButton("Start Game");
		startButton.addActionListener(new ButtonListener());
		
		Font f = new Font("Courier", Font.BOLD, 20);

		JLabel name = new JLabel("Player Name:");
		JLabel title = new JLabel("Memory Game Setup");
		title.setFont(f);
		JLabel difficultyLabel = new JLabel("Choose Difficulty:");
		
		nameInput = new JTextField();
		
		
		
		background.setLayout(null);
		background.add(titlePanel);
		background.add(panel);
		background.add(startButton);
		
		
		panel.setSize(390, 70);
		panel.setLocation(200, 130);
		panel.setBackground(Color.WHITE);
		
		panel.add(name);
		panel.add(nameInput);
		panel.add(difficultyLabel);
		panel.add(difficultyLevel);
		
		titlePanel.setBackground(Color.WHITE);
		titlePanel.setSize(300,50);
		titlePanel.setLocation(235,70);
		titlePanel.add(title);
		
		startButton.setSize(100, 30);
		startButton.setLocation(350, 220);
		startButton.setBackground(Color.YELLOW);
		
		this.getContentPane().add(background);
		this.setSize(900,575);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
	private class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == startButton){
				if((GameDifficulty)difficultyLevel.getSelectedItem() != GameDifficulty.UNSET){
					new GameFrame(Util.firstToUpper(nameInput.getText()), 
							(GameDifficulty)difficultyLevel.getSelectedItem());
				}
			}
			/** TODO: Create JDialog to tell user to pick a difficulty */
			else{}
		}
		
	}
	public static void main(String[] args){
		new DialogUI();
	}
}