package gvsu.cis_350.project.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.*;
import gvsu.cis_350.project.core.game.difficulty.GameSessionDifficulty;
import gvsu.cis_350.project.core.game.difficulty.GameSessionSetting;
import gvsu.cis_350.project.core.game.difficulty.GameSessionType;
import gvsu.cis_350.project.core.game.difficulty.impl.SinglePlayerDifficulty;
import gvsu.cis_350.project.core.game.difficulty.impl.TwoPlayerDifficulty;
import gvsu.cis_350.project.utils.Util;

public class GameNameDialog extends JFrame implements ActionListener{	
	private JPanel namePanel;
	JFrame f;
	private JButton startButton, cancelButton;
	private JComboBox nameBox1, nameBox2;
	private GameSessionDifficulty dif;
	private boolean singlePlayer;
	public GameNameDialog(GameSessionDifficulty d, JFrame f){
		this.f = f;
		dif = d;
		namePanel = new JPanel();
		namePanel.setBackground(Color.WHITE);
		startButton = new JButton("START GAME");
		startButton.addActionListener(this);
		startButton.setBackground(Color.GREEN);
		cancelButton = new JButton("CANCEL");
		cancelButton.addActionListener(this);
		cancelButton.setBackground(Color.RED);
		
		if(dif instanceof SinglePlayerDifficulty){
			singlePlayer = true;
			namePanel.setLayout(new GridLayout(2,2));
			JLabel playerLabel = new JLabel("Name of Player: ");
			nameBox1 = new JComboBox<>(Util.formatUserSelection(new File("./data/").list()));
			nameBox1.setEditable(true);
			nameBox1.setBackground(Color.WHITE);
			namePanel.add(playerLabel);
			namePanel.add(nameBox1);
		}
		else{
			singlePlayer = false;
			namePanel.setLayout(new GridLayout(3,2));
			String player1, player2;
			JLabel playerLabel1, playerLabel2;
			playerLabel1 = new JLabel("Name of Player 1: ");
			playerLabel2 = new JLabel("Name of Player 2: ");
			
			nameBox1 = new JComboBox<>(Util.formatUserSelection(new File("./data/").list()));
			nameBox2 = new JComboBox<>(Util.formatUserSelection(new File("./data/").list()));
			nameBox1.setEditable(true);
			nameBox2.setEditable(true);
			nameBox1.setBackground(Color.WHITE);
			nameBox2.setBackground(Color.WHITE);
			namePanel.add(playerLabel1);
			namePanel.add(nameBox1);
			namePanel.add(playerLabel2);
			namePanel.add(nameBox2);
		}
		namePanel.add(cancelButton);
		namePanel.add(startButton);
		
		this.getContentPane().add(namePanel);
		if(singlePlayer)
			this.setSize(400, 100);
		else
			this.setSize(400, 150);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        
        
        
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startButton){
			f.setVisible(false);
			if(singlePlayer){
				String player1 = (String)nameBox1.getSelectedItem(); 
				if(player1.trim().equals(""))
					JOptionPane.showMessageDialog(null, "Enter player's name...");
				else{
					new GameFrame(dif,player1);
					this.dispose();
					f.dispose();
				}				
			}
			else{
				String player1 = (String)nameBox1.getSelectedItem();
				String player2 = (String)nameBox2.getSelectedItem();
				if(player1.trim().equals(""))
					JOptionPane.showMessageDialog(null, "Enter player 1's name...");
				else if(player2.trim().equals(""))
					JOptionPane.showMessageDialog(null, "Enter player 2's name...");
				else if(player1.equals(player2))
					JOptionPane.showMessageDialog(null, "Players cannot share the same name...");
				else{
					new GameFrame(dif,player1,player2);
					this.dispose();
					f.dispose();
				}
			}
		}
		else{
			this.dispose();
			f.setVisible(true);
		}
			
	}
}