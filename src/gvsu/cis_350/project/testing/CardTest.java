package gvsu.cis_350.project.testing;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.Card.CardType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * A simple test to ensure Cards are easily understood
 * to be different by the game.
 *
 * @author Emily
 */
public class CardTest {

    @Test
    public void test() {
        Card card1 = new Card(CardType.BANANA);
        Card card2 = new Card(CardType.ORANGE);
        Card card3 = new Card(CardType.RED_APPLE);
        Card card1_test = new Card(CardType.BANANA);

        assertEquals(card1, card1_test);
        assertNotEquals(card2, card3);
        assertNotEquals(card1, card3);

        assertEquals(card1.getCardType(), card1_test.getCardType());
        card1.setHasBeenClicked(true);
        card1_test.setHasBeenClicked(true);
        assertEquals(card1.hasBeenClicked(), card1_test.hasBeenClicked());
        assertEquals(card1.getCardType(), card1_test.getCardType());

    }

}
