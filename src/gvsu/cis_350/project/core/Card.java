package gvsu.cis_350.project.core;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import gvsu.cis_350.project.core.game.MemoryGame;

/**
 * Represents a single card in the game. This card will have an associated match.
 * 
 * @author Desmin Little
 *
 */
@SuppressWarnings("serial")
public class Card extends JLabel implements MouseListener {
	
	/**
	 * Default Card constructor. Builds a card with the given
	 * CardType.
	 * @param type The CardType of this card.
	 */
	public Card(CardType type) {
		super("", SwingConstants.CENTER);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		this.cardType = type;
		this.setIcon(BACK);
		this.addMouseListener(this);
	}
	
	/**
	 * The file path to the game's resources.
	 */
	private static final String IMG_PATH = "resources/";
	
	/**
	 * Static and final because all cards will share the same back and blank image.
	 */
	public static final ImageIcon BACK = new ImageIcon(IMG_PATH + "questionImg.png");
	public static final ImageIcon BLANK = new ImageIcon(IMG_PATH + "blankImg.png");
	
	/**
	 * The specific {@link CardType} of this card.
	 */
	private CardType cardType;
	
	/**
	 * Tells us whether or not this card has been clicked.
	 */
	private boolean clicked = false;
	
	/**
	 * Tells us whether or not this card has been clicked.
	 */
	public boolean hasBeenClicked() {
		return clicked;
	}
	
	/**
	 * Sets whether or not this card has been clicked.
	 */
	public void setHasBeenClicked(boolean flag) {
		this.clicked = flag;
	}
	
	/**
	 * Retrieves the {@link CardType} of this card.
	 * @return The {@link CardType} of this card.
	 */
	public CardType getCardType() {
		return cardType;
	}
	
	/**
	 * An enum containing all possible card types in the game. Each element
	 * contains the face image for it's specific type.
	 * 
	 * @author Desmin Little
	 *
	 */
	public enum CardType {
		BANANA(new ImageIcon(IMG_PATH + "bananaImg.jpg")),
		ORANGE(new ImageIcon(IMG_PATH + "orangeImg.jpg")),
		RED_APPLE(new ImageIcon(IMG_PATH + "redAppleImg.jpg")),
		GREEN_APPLE(new ImageIcon(IMG_PATH + "greenAppleImg.png")),
		PINEAPPLE(new ImageIcon(IMG_PATH + "pineappleImg.png")),
		STRAWBERRY(new ImageIcon(IMG_PATH + "strawberryImg.png")),
		GRAPES(new ImageIcon(IMG_PATH + "grapesImg.jpg")),
		CHERRY(new ImageIcon(IMG_PATH + "cherryImg.png"));
		
		/**
		 * The default constructor requiring an image, which will
		 * be used as this card type's face.
		 * @param face The face image of this card type.
		 */
		CardType(ImageIcon face) {
			this.face = face;
		}
		
		/**
		 * This cards face image.
		 */
		private ImageIcon face;
		
		/**
		 * Retrieves this card type's face.
		 * @return
		 */
		public ImageIcon getFace() {
			return this.face;
		}
	}
	
	/**
	 * 'Flips' this card over on the interface, changing
	 * the icon and setting the card to clicked.
	 */
	public void flip() {
		this.setBackground(Color.cyan);
		this.setIcon(getCardType().getFace());
		this.setHasBeenClicked(true);
	}
	
	/**
	 * If the card has already been clicked this will remove
	 * the card from the board, otherwise it will reset the
	 * card to its previous state.
	 */
	public void reset() {
		this.setBackground(Color.white);
		if (this.hasBeenClicked()) {
			this.setIcon(BLANK);
			return;
		}
		this.setIcon(BACK);
	}

	/**
	 * The click event handling what happens when a
	 * card is clicked on the interface.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		MemoryGame.getInstance().onCardClick((Card)e.getSource());
	}

	/*
	 * Unused MouseListener methods.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Card){
			Card card = (Card)obj;
			return this.cardType == card.cardType;
		}
		return false;
	}

}
