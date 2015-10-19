package gvsu.cis_350.project.core.game.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.JLabel;

import gvsu.cis_350.project.core.GameSession;

public final class ObservableMouseListener extends Observable implements MouseListener {

	public ObservableMouseListener(GameSession observer) {
		this.addObserver(observer);
	}
	
	private JLabel labelClicked;
	
	/**
	 * @return the labelClicked
	 */
	public JLabel getLabelClicked() {
		return labelClicked;
	}
	/**
	 * @param labelClicked the labelClicked to set
	 */
	public void setLabelClicked(JLabel labelClicked) {
		this.labelClicked = labelClicked;
		this.setChanged();
		this.notifyObservers();
	}
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		setLabelClicked((JLabel) e.getSource());

	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
}
