package gvsu.cis_350.project.testing;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.Card.CardType;
import gvsu.cis_350.project.utils.Utilities;

public class CardRandomizerTest {

	@Test
	public void test() {
		LinkedList<Card> cards = new LinkedList<>();
		for (CardType type : CardType.values()) {
			Card card = new Card(type);
			cards.add(card);
			cards.add(card);
		}
		//cards.forEach(card -> System.out.println(card.getCardType().name()));
		Queue<Card> randomizedCards;
		randomizedCards = Utilities.randomize(cards);
		System.out.println("");
		randomizedCards.forEach(card -> System.out.println(card.getCardType().name()));

	}

}
