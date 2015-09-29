package gvsu.cis_350.project.testing;

import java.util.List;

import org.junit.Test;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.Card.CardType;
import gvsu.cis_350.project.utils.Utilities;
import static org.junit.Assert.*;


public class CardTest {

	@Test
	public void test() {
		Card card1 = new Card(CardType.ONE);
		Card card2 = new Card(CardType.TWO);
		Card card3 = new Card(CardType.THREE);
		Card card1_test = new Card(CardType.ONE);
		
		assertEquals(card1, card1_test);
		assertNotEquals(card2, card3);
		assertNotEquals(card1, card3);
		
	}

}
