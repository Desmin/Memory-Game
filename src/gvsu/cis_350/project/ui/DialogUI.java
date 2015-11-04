package gvsu.cis_350.project.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gvsu.cis_350.project.core.GameSessionDifficulty;
import gvsu.cis_350.project.core.GameSessionSetting;
import gvsu.cis_350.project.core.GameSessionType;

/**
 * This class represents a dialog that starts the game. It asks for the user's
 * name and then creates a main user interface for the game.
 * 
 * @author Nick Spruit
 * @version 10/7/2015
 */

public class DialogUI extends JFrame {

	private JComboBox<GameSessionSetting> difficultyLevel;
	private JComboBox<GameSessionType> gameSessionType;
	private JButton startButton;
	private JTextField nameInput;

	public DialogUI() {
		this.setTitle("Setup Memory Game");
		this.setBackground(Color.WHITE);
		difficultyLevel = new JComboBox<GameSessionSetting>(GameSessionSetting.values());
		difficultyLevel.setBackground(Color.WHITE);
		gameSessionType = new JComboBox<GameSessionType>(GameSessionType.values());
		gameSessionType.setBackground(Color.WHITE);

		JLabel background = new JLabel(new ImageIcon("resources/thinkingImg.jpg"));
		JPanel panel = new JPanel(new GridLayout(3, 2));
		JPanel titlePanel = new JPanel(new FlowLayout());
		startButton = new JButton("Start Game");
		startButton.addActionListener(new ButtonListener());

		Font f = new Font("Courier", Font.BOLD, 20);

		JLabel name = new JLabel("Player Name:");
		JLabel title = new JLabel("Memory Game Setup");
		title.setFont(f);
		JLabel difficultyLabel = new JLabel("Choose Difficulty:");
		JLabel typeLabel = new JLabel("Choose Type:");
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
		panel.add(typeLabel);
		panel.add(gameSessionType);

		titlePanel.setBackground(Color.WHITE);
		titlePanel.setSize(300, 50);
		titlePanel.setLocation(235, 70);
		titlePanel.add(title);

		startButton.setSize(100, 30);
		startButton.setLocation(350, 220);
		startButton.setBackground(Color.YELLOW);

		this.getContentPane().add(background);
		this.setSize(900, 575);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);

	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			GameSessionDifficulty dif = new GameSessionDifficulty(
					(GameSessionSetting) difficultyLevel.getSelectedItem(),
					(GameSessionType) gameSessionType.getSelectedItem());
			System.out.println(dif.getSessionSetting().name());
			System.out.println(dif.getSessionType().name());
			new GameFrame(nameInput.getText(), dif);
			dispose();
		}

	}

	public static void main(String[] args) {
		new DialogUI();
	}
}