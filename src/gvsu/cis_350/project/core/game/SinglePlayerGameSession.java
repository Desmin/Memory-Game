package gvsu.cis_350.project.core.game;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Observable;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.GameSession;
import gvsu.cis_350.project.core.game.event.ObservableActionListener;
import gvsu.cis_350.project.core.game.event.ObservableMouseListener;
import gvsu.cis_350.project.core.io.FileIO;
import gvsu.cis_350.project.ui.GameJLabel;
import gvsu.cis_350.project.ui.MainUI;

public class SinglePlayerGameSession extends GameSession {

	@Override
	public boolean initialize(String sessionPlayerName, GameDifficulty sessionDifficulty) {
		this.setSessionDifficulty(sessionDifficulty);
		this.setSessionPlayer(FileIO.loadPlayerData(sessionPlayerName));
		return true;
	}

	@Override
	public boolean reset() {
		setSessionMatches(0);
		this.setUIAction("dispose");
		new MainUI(this.getSessionPlayer().getName(), this.getSessionDifficulty());
		return true;
	}

	@Override
	public void quit(boolean restart) {
		try {
			FileIO.savePlayerData(getSessionPlayer());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setUIAction("dispose");
		if (!restart) {
			System.exit(1);
		} else
			reset();
	}

	@Override
	public void update(Observable ob, Object o) {
		if (ob instanceof ObservableActionListener) {
			quit(((ObservableActionListener)ob).getFlag());
			return;
		} else if (ob instanceof ObservableMouseListener) {
			GameJLabel label = ((ObservableMouseListener)ob).getLabelClicked();
			onCardClick(label);
		}
		
	}
	
	public void onCardClick(GameJLabel label) {
		Card card = getCardMap().get(label);
		if (card.hasBeenClicked() || !isClickingEnabled()) //If the card has already been clicked we return.
			return;
		label.setIcon(card.getCardType().getFace());//Changes the state of the card.
		card.setHasBeenClicked(true);
		if (Objects.isNull(getLastCardClicked())) { //If this is the first card to be flipped over we set it as the
			setLastCardClicked(card);			   //last card to clicked.
			return;
		} else
			setClickingEnabled(false);; //Otherwise we disable clicking as we'll be checking for a match.
		
		boolean match = card.equals(getLastCardClicked()); //Do we have a match?
		new SwingWorker<Void, Void>() { //SwingWroker to delay the flipping/resetting of the clicked cards.

			@Override
			protected Void doInBackground() throws Exception {
				if (match) { //If it's a match we update how many matches we've got and display that.
					addMatch();
					setUIAction("update_score");
				}
				Thread.sleep(match ? 500 : 1000); //Wait 500-1000ms to do anything else so players can
				return null;					  //see which cards they clicked.
			}
			
			@Override
			protected void done() {
				if (match) {
					if (card.hasBeenClicked())
						label.setIcon(Card.BLANK);
					else
						label.setIcon(Card.BACK);
					resetLastCardClicked();
					setUIAction("repaint");
					if (getSessionMatches() == getSessionDifficulty().getMatchesNeededToWin()) {
						getSessionPlayer().addWin();
						int response = JOptionPane.showConfirmDialog(label.getParent(),
								"You won! Do you wish to start again?", "Winner!",
								JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
						boolean restart = false;
						switch(response) {
						case JOptionPane.YES_OPTION:
							restart = true;
							break;
						}
						quit(restart);
					}
				} else {
					card.setHasBeenClicked(false);
					getLastCardClicked().setHasBeenClicked(false);
					if (card.hasBeenClicked())
						label.setIcon(Card.BLANK);
					else
						label.setIcon(Card.BACK);
					resetLastCardClicked();
					setLastCardClicked(null);
				}
				setClickingEnabled(true);
			}
			
		}.execute();
	}
	
	private void resetLastCardClicked() {
		for (Entry<GameJLabel, Card> e : getCardMap().entrySet()) {
			if (e.getValue() == getLastCardClicked()) {
				GameJLabel label = e.getKey();
				if (e.getValue().hasBeenClicked())
					label.setIcon(Card.BLANK);
				else
					label.setIcon(Card.BACK);
				break;
			}
		}
		setLastCardClicked(null);
	}

}
