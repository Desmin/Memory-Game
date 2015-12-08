package gvsu.cis_350.project.testing;

import static org.junit.Assert.*;
import gvsu.cis_350.project.core.game.difficulty.*;
import gvsu.cis_350.project.core.game.difficulty.impl.TwoPlayerDifficulty;

import org.junit.Test;

public class TwoPlayerDifficultyTest {

	@Test
	public void test() {
		TwoPlayerDifficulty t1 = new TwoPlayerDifficulty(GameSessionSetting.MEDIUM,
                GameSessionType.TIMED);
		TwoPlayerDifficulty t2 = new TwoPlayerDifficulty(GameSessionSetting.SMALL, 
				GameSessionType.LIMITED_MATCH_ATTEMPTS);
		assertEquals(t1.getPlayerOneValue(), t1.getPlayerTwoValue());
		assertEquals(t2.getPlayerOneValue(), t1.getPlayerOneValue());
	}

}
