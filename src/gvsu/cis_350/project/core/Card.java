package gvsu.cis_350.project.core;

import javafx.scene.image.Image;

/**
 * Represents a single card in the game. This card will have an associated match.
 * 
 * @author Desmin Little
 *
 */
public class Card {
	
	/**
	 * Default card constructor that instantiates the
	 * card with the given card type.
	 * @param cardType
	 */
	public Card(CardType cardType) {
		this.cardType = cardType;
	}
	
	/*
	 * Static and final because all cards will share the same back image.
	 */
	public static final Image back = null;
	
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
		ONE,
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
		CardType(Image face) {
			this.face = face;
		}
		
		/**
		 * Placeholder constructor until we have the images created.
		 */
		CardType() {};
		
		/**
		 * This cards face image.
		 */
		private Image face;
		
		/**
		 * Retrieves this card type's face.
		 * @return
		 */
		public Image getFace() {
			return this.face;
		}
	}
}
