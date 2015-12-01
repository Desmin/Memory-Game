/**
 *
 */
package gvsu.cis_350.project.testing;

import gvsu.cis_350.project.core.game.difficulty.GameSessionDifficulty;
import gvsu.cis_350.project.core.game.difficulty.GameSessionSetting;
import gvsu.cis_350.project.core.game.difficulty.GameSessionType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @author EmilyT
 *         Tests Game Session Type
 */
public class GameSessionTypeTest {

    @Test
    public void typeTest() {
        GameSessionDifficulty game1 = new GameSessionDifficulty(GameSessionSetting.SMALL, GameSessionType.NORMAL);
        GameSessionDifficulty game2 = new GameSessionDifficulty(GameSessionSetting.MEDIUM, GameSessionType.NORMAL);
        GameSessionDifficulty game3 = new GameSessionDifficulty(GameSessionSetting.SMALL, GameSessionType.TIMED);
        GameSessionDifficulty game4 = new GameSessionDifficulty(GameSessionSetting.LARGE, GameSessionType.LIMITED_MATCH_ATTEMPTS);
        GameSessionDifficulty game5 = new GameSessionDifficulty(GameSessionSetting.LARGE, GameSessionType.TIMED);
        GameSessionDifficulty game6 = new GameSessionDifficulty(GameSessionSetting.MEDIUM, GameSessionType.LIMITED_MATCH_ATTEMPTS);
        GameSessionDifficulty game7 = new GameSessionDifficulty(GameSessionSetting.SMALL, GameSessionType.NORMAL);

        assertNotEquals(game2, game1);
        assertNotEquals(game3, game5);
        assertNotEquals(game4, game6);
        assertNotEquals(game1, game3);
        assertNotEquals(game2, game6);
        assertNotEquals(game4, game5);
        assertEquals(game1, game7);
    }

}
