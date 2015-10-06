package gvsu.cis_350.project.testing;
import junit.framework.Assert;
import gvsu.cis_350.project.core.game.*;
import static org.junit.Assert.*;
import gvsu.cis_350.project.core.game.GameDifficulty;
import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.Player;

import org.junit.Test;

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
