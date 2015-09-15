package gvsu.cis_350.project.core;

import javafx.scene.image.Image;

/**
 * Represents a single card in the game. This card will have an associated match.
 * 
 * @author Desmin Little
 *
 */
public class Card {
	
	public Card(Type cardType) {
		this.cardType = cardType;
		this.face = cardType.getFace();
	}
	
	/*
	 * Static and final because all cards will share the same back image.
	 */
	public static final Image back = null;
	
	private Image face;
	
	private Type cardType;
	
	public Type getCardType() {
		return cardType;
	}
	
	public Image getBackImage() {
		return back;
	}
	
	public Image getFaceImage() {
		return face;
	}

	public enum Type {
		ONE,
		TWO,
		THREE,
		FOUR,
		FIVE,
		SIX,
		SEVEN,
		EIGHT;
		
		private Image face;
		
		public Image getFace() {
			return this.face;
		}
	}
}
