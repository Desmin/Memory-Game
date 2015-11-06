package gvsu.cis_350.project.ui;

import gvsu.cis_350.project.core.game.GameSessionDifficulty;
import gvsu.cis_350.project.core.game.GameSessionSetting;
import gvsu.cis_350.project.core.game.GameSessionType;
import gvsu.cis_350.project.utils.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * This class represents a dialog that starts the game. It asks for the user's
 * name and then creates a main user interface for the game.
 *
 * @author Nick Spruit
 * @author Desmin Little
 * 10/7/2015
 */

public class DialogUI extends JFrame {

    private JComboBox<GameSessionSetting> difficultyLevel;
    private JComboBox<GameSessionType> gameSessionType;
    private JComboBox<String> userSelection;
    private JTextField nameInput;

    public DialogUI() {
        this.setTitle("Setup Memory Game");
        this.setBackground(Color.WHITE);
        difficultyLevel = new JComboBox<>(GameSessionSetting.values());
        difficultyLevel.setBackground(Color.WHITE);
        gameSessionType = new JComboBox<>(GameSessionType.values());
        gameSessionType.setBackground(Color.WHITE);

        JLabel background = new JLabel(new ImageIcon("resources/thinkingImg.jpg"));
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JPanel titlePanel = new JPanel(new FlowLayout());
        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(new ButtonListener());

        Font f = new Font("Courier", Font.BOLD, 20);

        JLabel name = new JLabel("Player Name:");
        JLabel title = new JLabel("Memory Game Setup");
        title.setFont(f);
        JLabel difficultyLabel = new JLabel("Choose Difficulty:");
        JLabel typeLabel = new JLabel("Choose Type:");
        nameInput = new JTextField();

        userSelection = new JComboBox<>(Util.formatUserSelection(new File("./data/").list()));
        userSelection.setEditable(true);

        background.setLayout(null);
        background.add(titlePanel);
        background.add(panel);
        background.add(startButton);

        panel.setSize(390, 70);
        panel.setLocation(200, 130);
        panel.setBackground(Color.WHITE);

        panel.add(name);
        panel.add(userSelection);
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

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
    }

    public static void main(String[] args) {
        new DialogUI();
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GameSessionDifficulty dif = new GameSessionDifficulty(
                    (GameSessionSetting) difficultyLevel.getSelectedItem(),
                    (GameSessionType) gameSessionType.getSelectedItem());
            String user = (String)userSelection.getSelectedItem();
            if (!user.trim().equals(""))
                new GameFrame(user, dif);
            else {
                userSelection.grabFocus();
                return;
            }
            dispose();
        }

    }
}