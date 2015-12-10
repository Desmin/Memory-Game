package gvsu.cis_350.project.testing;

import gvsu.cis_350.project.core.game.difficulty.GameSessionSetting;
import gvsu.cis_350.project.core.game.difficulty.GameSessionType;
import gvsu.cis_350.project.core.game.difficulty.impl.TwoPlayerDifficulty;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TwoPlayerDifficultyTest {

    @Test
    public void test() {
        TwoPlayerDifficulty t1 = new TwoPlayerDifficulty(GameSessionSetting.MEDIUM,
                GameSessionType.TIMED);
        TwoPlayerDifficulty t2 = new TwoPlayerDifficulty(GameSessionSetting.SMALL,
                GameSessionType.LIMITED_MATCH_ATTEMPTS);
        t1.setPlayerOneValue(16);
        t1.setPlayerTwoValue(12);

        t2.setPlayerOneValue(1500);

        assertNotEquals(t1.getPlayerOneValue().getValue(), t1.getPlayerTwoValue().getValue());
        assertNotEquals(t2.getPlayerOneValue().getValue(), t1.getPlayerOneValue().getValue());

        assertEquals(1500, t2.getPlayerOneValue().getValue());

        assertEquals(12, t1.getPlayerTwoValue().getValue());

        assertEquals(16, t1.getPlayerOneValue().getValue());
    }

}
