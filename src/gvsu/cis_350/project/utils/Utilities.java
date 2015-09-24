package gvsu.cis_350.project.utils;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.Queue;

import gvsu.cis_350.project.core.Card;

/**
 * Utilities will house utility methods for various use throughout
 * the project.
 * 
 * @author Desmin Little
 *
 */
public final class Utilities {
	
	/**
	 * Randomizes the order of the given cards, then returns a {@link Queue} containing the new order.
	 * 
	 * @param cards A {@link LinkedList} of cards to be randomized.
	 * @return A {@link Queue} with a randomized order of cards.
	 */
	public static Queue<Card> randomize(LinkedList<Card> cards) {
		Queue<Card> randomizedCards = new LinkedList<>();
		SecureRandom random = new SecureRandom();
		while (!cards.isEmpty()) {
			randomizedCards.offer(cards.remove(random.nextInt(cards.size())));
		}
		return randomizedCards;
	}

}
