package gvsu.cis_350.project.core.game.event;

import gvsu.cis_350.project.core.game.GameSession;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

public final class ObservableMouseListener extends Observable implements MouseListener {

    private JLabel labelClicked;

    public ObservableMouseListener(GameSession observer) {
        this.addObserver(observer);
    }

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
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
