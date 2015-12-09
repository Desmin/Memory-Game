package gvsu.cis_350.project.ui;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.game.GameSession;
import gvsu.cis_350.project.core.game.difficulty.GameSessionDifficulty;
import gvsu.cis_350.project.core.game.difficulty.GameSessionSetting;
import gvsu.cis_350.project.core.game.difficulty.SessionDifficultyValue;
import gvsu.cis_350.project.core.game.event.ObservableActionListener;
import gvsu.cis_350.project.core.game.event.ObservableMouseListener;
import gvsu.cis_350.project.core.game.impl.SinglePlayerGameSession;
import gvsu.cis_350.project.core.game.impl.TwoPlayerGameSession;
import gvsu.cis_350.project.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

@SuppressWarnings("serial")

/**
 * This class represents the main user interface for the game of concentration.
 * It creates a GUI that has a grid layout and menu bars extra options.
 *
 * @author Nick Spruit
 * @author Desmin Little
 * @version 10/7/2015
 */
public class GameFrame extends JFrame implements Observer {

    /**
     * Middle panel holds the cards in a grid
     */
    private JPanel gridPanel;

    /**
     * Label for player score
     */
    private JLabel playerScoreLabel;

    /**
     * Label for message to user
     */
    private JLabel difficultyInfoLabel;

    /**
     * Main panel
     */
    private JPanel mainPanel;
    /**
     * Player name label
     */
    private JLabel playerNameLabel;

    /**
     * Constructor creates cards for game, creates panels to add to frame, sets
     * up labels, and uses listeners for menu items
     */
    public GameFrame(GameSessionDifficulty difficulty, String... players) {
        GameSession session;
     // Creates panel to hold cards
        gridPanel = new JPanel(
               new GridLayout(this.yLength(difficulty.getSessionSetting()),
                        xLength(difficulty.getSessionSetting())));
        gridPanel.setBackground(Color.WHITE);
        if (players.length > 1) {
            session = new TwoPlayerGameSession();
        } else {
            session = new SinglePlayerGameSession();
        }
        //Creates and starts a new game session
        for (String name : players)
            session.addPlayerToGame(name);
        session.initialize(difficulty);
        session.addObserver(this);
        difficulty.addObserver(this);

        // Create main underlying panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        
        //Top panel holds the title and timing/limit info
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);

        //Bottom panel holds player info
        JPanel playerInfoPanel = new JPanel();
        playerInfoPanel.setBackground(Color.WHITE);
        playerInfoPanel.setLayout(new GridLayout(1, 2));
        JPanel difficultyInfoPanel = new JPanel();
        difficultyInfoPanel.setBackground(Color.WHITE);

        //Menu bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(Color.WHITE);
        
        //File menu
        JMenu fileMenu = new JMenu("File");

        //About menu
        JMenu aboutMenu = new JMenu("About");

        //New game menu item
        JMenuItem newGameItem = new JMenuItem("New Game");

        //Quit menu item
        JMenuItem quitItem = new JMenuItem("Quit");

        //About menu item
        JMenuItem aboutItem = new JMenuItem("About Game");

        //Version menu item
        JMenuItem versionItem = new JMenuItem("Version");

        // Adds menus and items to menu bar
        this.setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);
        fileMenu.add(newGameItem);
        fileMenu.add(quitItem);
        aboutMenu.add(aboutItem);
        aboutMenu.add(versionItem);

        //Sets names for each item
        newGameItem.setName("new_game");
        quitItem.setName("quit_game");
        aboutItem.setName("about");
        versionItem.setName("version");

        // Adds action listeners to each menu item
        ObservableActionListener listener = new ObservableActionListener(session);
        newGameItem.addActionListener(listener);
        quitItem.addActionListener(listener);
        aboutItem.addActionListener(listener);
        versionItem.addActionListener(listener);

        // Fills the list with two of each card type using the given
        // difficulty level and randomizes their order.
        Map<JLabel, Card> map = new HashMap<>();
        List<Card> list = Util.fetchRandomizedList(difficulty.getSessionSetting());

        // Adds the cards onto the frame
        list.forEach((card) -> {
            JLabel label = new JLabel(Card.BACK, JLabel.CENTER);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            //label.setIcon(Card.BACK);
            label.addMouseListener(new ObservableMouseListener(session));
            gridPanel.add(label);
            map.put(label, card);
        });
        session.setCardMap(map);

        //Sets up a font
        Font f = new Font("Courier", Font.BOLD, 20);

        //Creates player, message, and game labels
        playerNameLabel = new JLabel("Player Name: " + players[0], SwingConstants.LEFT);
        playerNameLabel.setFont(f);
        playerScoreLabel = new JLabel("    Player Score: 0", SwingConstants.LEFT);
        playerScoreLabel.setFont(f);
        difficultyInfoLabel = new JLabel("     ", SwingConstants.LEFT);
        difficultyInfoLabel.setFont(f);

        //Label for title
        JLabel gameTitleLabel = new JLabel("The Game of Concentration", SwingConstants.CENTER);
        gameTitleLabel.setFont(f);

        // Adds label to bottom panel
        topPanel.add(gameTitleLabel);
        topPanel.add(difficultyInfoLabel);
        playerInfoPanel.add(playerNameLabel);
        playerInfoPanel.add(playerScoreLabel);
        //difficultyInfoPanel.add(difficultyInfoLabel);

        // Adds top, grid, and bottom panel to main
        mainPanel.add(topPanel);
        mainPanel.add(gridPanel);
        mainPanel.add(playerInfoPanel);
        //mainPanel.add(difficultyInfoPanel);

        // Sets title, adds main panel, sets size, etc.
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.setTitle("Concentration");
        this.getContentPane().add(mainPanel);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Returns the player score label
     *
     * @return playerScoreLabel - the label that holds the player score
     */
    public JLabel getScoreLabel() {
        return playerScoreLabel;
    }

    /**
     * Method calculates the width of the grid
     *
     * @param gd - the game session setting
     * @return the size of the width
     */
    private int xLength(GameSessionSetting gd) {
        if (gd.getNumberOfCards() == 16 || gd.getNumberOfCards() == 32)
            return gd.getNumberOfCards() / 4;
        else
            return 8;
    }

    /**
     * Method calculates the height of the grid
     *
     * @param gd - the game session setting
     * @return the size of the height
     */
    private int yLength(GameSessionSetting gd) {
        return gd.getNumberOfCards() / xLength(gd);
    }

    /**
     * Method updates the frame
     *
     * @param ob - the observable object
     * @param o  - an object
     */
    @Override
    public void update(Observable ob, Object o) {
        if (ob instanceof GameSession) {
            GameSession session = (GameSession) ob;
            String action = session.getUIAction();
            //Disposes, updates, or repaints
            switch (action) {
                case "dispose":
                    dispose();
                    return;
                case "update_score":
                    getScoreLabel().setText("Player Score: " + session.getPlayerMatches());
                    return;
                case "repaint":
                    revalidate();
                    repaint();
                
                    return;
                case "switch_player":
                    playerNameLabel.setText(((TwoPlayerGameSession) session).getCurrentPlayer().getName());
                    getScoreLabel().setText("Player Score: " + session.getPlayerMatches());
                    return;
            }
        } else if (ob instanceof GameSessionDifficulty) {
            if (Objects.isNull(difficultyInfoLabel))
                return;
            difficultyInfoLabel.setText(((GameSessionDifficulty) ob).createUIString((SessionDifficultyValue) o));
        }
    }
}
