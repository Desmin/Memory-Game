package gvsu.cis_350.project.ui;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainUI extends JFrame implements ActionListener{
	
	private String userName;
	private JPanel mainPanel, gridPanel, topPanel, bottomPanel;
	private JLabel[][] cardPosition;
	private JLabel playerNameLabel, playerScoreLabel, messageLabel, gameTitleLabel; 
	private JMenuBar menuBar;
	private JMenu fileMenu, 
				  aboutMenu;
	private JMenuItem newGameItem, 
					  quitItem, 
					  saveItem, 
					  loadItem,
					  aboutItem,
					  versionItem;

	private ImageIcon bananaImg;
	
	public MainUI(String userName){
		
		this.userName = userName;
		bananaImg = new ImageIcon("bananaImg.jpg");
	
		//Create three panels
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		//mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
		gridPanel = new JPanel(new GridLayout(4,4));
		//gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
		topPanel = new JPanel();
		//topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
		bottomPanel = new JPanel();
		//bottomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
		
		//Menus & items
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		aboutMenu = new JMenu("About");
		newGameItem = new JMenuItem("New Game");
		saveItem = new JMenuItem("Save Game");
		loadItem = new JMenuItem("Load Game");
		quitItem = new JMenuItem("Quit");
		aboutItem = new JMenuItem("About Game");
		versionItem = new JMenuItem("Version");
		
		//Adds menus & items
		this.setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(aboutMenu);
		fileMenu.add(newGameItem);
		fileMenu.add(saveItem);
		fileMenu.add(loadItem);
		fileMenu.add(quitItem);
		aboutMenu.add(aboutItem);
		aboutMenu.add(versionItem);
		
		//Adds action listeners
		newGameItem.addActionListener(this);
		saveItem.addActionListener(this);
		loadItem.addActionListener(this);
		quitItem.addActionListener(this);
		aboutItem.addActionListener(this);
		versionItem.addActionListener(this);
		
		//Adds the labels to the panel
		cardPosition = new JLabel[4][4];
		for(int i = 0; i < cardPosition.length; i++){
			for(int j = 0; j < cardPosition[i].length; j++){
				cardPosition[i][j] = new JLabel("", SwingConstants.CENTER);
				cardPosition[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
				gridPanel.add(cardPosition[i][j]);
				cardPosition[i][j].setIcon(bananaImg);
			}
		}
		Font f = new Font("Courier", Font.BOLD, 20);
		
		playerNameLabel = new JLabel("Player Name: " + userName, SwingConstants.LEFT);
		playerNameLabel.setFont(f);
		
		playerScoreLabel = new JLabel("    Player Score: 0", SwingConstants.LEFT);
		playerScoreLabel.setFont(f);
		
		messageLabel = new JLabel("", SwingConstants.LEFT);
		messageLabel.setFont(f);
		
		gameTitleLabel = new JLabel("The Game of Concentration", SwingConstants.CENTER);
		gameTitleLabel.setFont(f);
		
		topPanel.add(gameTitleLabel);
		bottomPanel.add(playerNameLabel);
		bottomPanel.add(playerScoreLabel);
		bottomPanel.add(messageLabel);
		

		mainPanel.add(topPanel);
		mainPanel.add(gridPanel);
		mainPanel.add(bottomPanel);
		
		this.setTitle("Concentration");
		this.getContentPane().add(mainPanel);
		this.setVisible(true);
		this.setSize(600,600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == newGameItem){
			
		}
		if(e.getSource() == saveItem){
			
		}
		if(e.getSource() == loadItem){
			
		}
		if(e.getSource() == quitItem){
			
		}
		if(e.getSource() == aboutItem){
			
		}
		if(e.getSource() == versionItem){
			
		}
		
	}
	
}
