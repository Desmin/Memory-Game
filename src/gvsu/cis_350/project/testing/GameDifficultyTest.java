package gvsu.cis_350.project.testing;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import gvsu.cis_350.project.core.game.GameDifficulty;
import gvsu.cis_350.project.core.game.MemoryGame;

/**
 * A class to test game difficulty settings.
 * 
 * @author Emily
 *
 */
public class GameDifficultyTest {

	@Test
	public void test() {
		String s1 = new String("Player 1");
		String s2 = new String("Player 2");
		String s3 = new String("Player 3");
		MemoryGame game1 = new MemoryGame();
		game1.initialize(s1, GameDifficulty.EASY);
		MemoryGame game2 = new MemoryGame();
		game2.initialize(s2, GameDifficulty.EASY);
		MemoryGame game3 = new MemoryGame();
		game3.initialize(s3, GameDifficulty.HARD);
		assertEquals(game1, game2);
		assertNotEquals(game1, game3);
	}

}
