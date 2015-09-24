package gvsu.cis_350.project.core;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Represents a single card in the game. This card will have an associated match.
 * 
 * @author Desmin Little
 *
 */
public class Card extends JLabel implements MouseListener {
	
	public Card(String text, int alignment, CardType type) {
		super(text, alignment);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		this.cardType = type;
		this.setIcon(back);
	}
	
	/**
	 * Default card constructor that instantiates the
	 * card with the given card type.
	 * @param cardType
	 */
	public Card(CardType cardType) {
		this.cardType = cardType;
	}
	
	private static final String imgPath = "src/resources/";
	
	/*
	 * Static and final because all cards will share the same back image.
	 */
	private static final ImageIcon back = new ImageIcon("bananaImg.jpg");
	
	/**
	 * The specific {@link CardType} of this card.
	 */
	private CardType cardType;
	
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
		ONE(new ImageIcon("bananaImg.jpg")),
		TWO,
		THREE,
		FOUR,
		FIVE,
		SIX,
		SEVEN,
		EIGHT;
		
		/**
		 * The default constructor requiring an image, which will
		 * be used as this card type's face.
		 * @param face The face image of this card type.
		 */
		CardType(ImageIcon face) {
			this.face = face;
		}
		
		/**
		 * Placeholder constructor until we have the images created.
		 */
		CardType() {};
		
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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
