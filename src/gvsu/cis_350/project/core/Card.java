package gvsu.cis_350.project.core;

import javax.swing.ImageIcon;

/**
 * Represents a single card in the game. This card will have an associated match.
 * 
 * @author Desmin Little
 *
 */

public final class Card {
	
	/**
	 * Default Card constructor. Builds a card with the given
	 * CardType.
	 * @param type The CardType of this card.
	 */
	public Card(CardType type) {
		this.cardType = type;
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
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Card){
			Card card = (Card)obj;
			return this.cardType == card.cardType;
		}
		return false;
	}

}
