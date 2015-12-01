package gvsu.cis_350.project.testing;

import gvsu.cis_350.project.core.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * A simple test to ensure Players and the players
 * statistics are correct.
 *
 * @author Emily
 */

public class PlayerTest {

    @Test
    public void test() {
        Player player1 = new Player("test", 8);
        Player player2 = new Player("test2", 10);
        Player player3 = new Player("test", 8);
        Player player4 = new Player("abcdefghijklmnop", 52);

        assertEquals(player1, player3);
        assertNotEquals(player1, player2);
        assertNotEquals(player3, player4);
    }

    @Test
    public void winTest() {
        Player player1 = new Player("test", 8);
        Player player2 = new Player("test2", 10);
        Player player3 = new Player("test", 8);

        player1.addWin();

        player2.addWin();
        player2.addWin();

        player3.addWin();

        assertNotEquals(player2, player1);
        assertEquals(player1, player3);
    }

}
