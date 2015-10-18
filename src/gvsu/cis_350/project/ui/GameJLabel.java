package gvsu.cis_350.project.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.GameSession;
import gvsu.cis_350.project.core.game.event.ObservableMouseListener;

@SuppressWarnings("serial")
public class GameJLabel extends JLabel {

	public GameJLabel(GameSession observer) {
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		this.setIcon(Card.BACK);
		this.addMouseListener(new ObservableMouseListener(observer));
	}

}
