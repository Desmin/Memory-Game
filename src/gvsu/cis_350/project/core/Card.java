package gvsu.cis_350.project.core;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Represents a single card in the game. This card will have an associated match.
 * 
 * @author Desmin Little
 *
 */
public class Card extends JLabel implements MouseListener {
	
	/**
	 * Default Card constructor. Builds a card with the given
	 * CardType.
	 * @param type
	 */
	public Card(CardType type) {
		super("", SwingConstants.CENTER);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		this.cardType = type;
		this.setIcon(back);
	}
	
	private static final String IMG_PATH = "resource/";
	
	/**
	 * Static and final because all cards will share the same back image.
	 */
	private static final ImageIcon back = new ImageIcon("questionImg.png");
	
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
		TWO(new ImageIcon("orangeImg.jpg")),
		THREE(new ImageIcon("redAppleImg.jpg")),
		FOUR(new ImageIcon("greenAppleImg.jpg")),
		FIVE(new ImageIcon("pineappleImg.jpg")),
		SIX(new ImageIcon("strawberryImg.jpg")),
		SEVEN(new ImageIcon("grapesImg.jpg")),
		EIGHT(new ImageIcon("cherryImg.jpg"));
		
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
