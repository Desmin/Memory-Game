package gvsu.cis_350.project.utils;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.Card.CardType;
import gvsu.cis_350.project.core.game.GameDifficulty;

/**
 * Util will house utility methods for various use throughout
 * the project.
 * 
 * @author Desmin Little
 *
 */
public final class Util {
	
	/**
	 * Randomizes the order of the given cards, then returns a {@link List} containing the new order.
	 * 
	 * @param cards A {@link LinkedList} of cards to be randomized.
	 * @return A {@link List} with a randomized order of cards.
	 */
	public static List<Card> randomize(List<Card> cards) {
		Collections.shuffle(cards, new SecureRandom());
		return cards;
	}
	
	/**
	 * Fills a list with the needed amount of Card objects based on the given
	 * game difficulty. This list is then used to build the UI.
	 * @param difficulty The difficulty of this game session.
	 * @return An ArrayList<Card> holding the required amount of cards for the
	 * given difficulty.
	 */
	public static List<Card> fillList(GameDifficulty difficulty) {
		int limit = difficulty.getNumberOfCards();
		List<Card> map = new LinkedList<>();
		for (CardType type : CardType.values()) {
			if ((type.ordinal()+1) <= limit) {
				map.add(new Card(type));
				map.add(new Card(type));
			}
		}		
		return map;
	}

}
