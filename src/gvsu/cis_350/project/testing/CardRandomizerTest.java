package gvsu.cis_350.project.testing;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.game.GameDifficulty;
import gvsu.cis_350.project.utils.Util;

/**
 * A class to test card randomization.
 * 
 * @author Desmin Little
 *
 */
public class CardRandomizerTest {

	@Test
	public void test() {
		List<Card> cards = Util.fillList(GameDifficulty.EASY);
		/*
		 * A test to ensure the cards are being randomized.
		 */
		assertFalse(cards.equals(Util.randomize(cards)));

	}
	

}
