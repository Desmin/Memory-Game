package gvsu.cis_350.project.testing;

import gvsu.cis_350.project.core.game.GameSessionDifficulty;
import gvsu.cis_350.project.core.game.GameSessionSetting;
import gvsu.cis_350.project.core.game.GameSessionType;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Simple tests to ensure Game difficulty levels are
 * properly implemented.
 *
 * @author Emily
 */

public class GameSessionDifficultyTest {

    @Test
    public void test() {
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

        game4.reset();
        assertEquals(game3.getSessionType(), game5.getSessionType());
        assertEquals(game2.getSessionSetting(), game6.getSessionSetting());

        game1.setSessionTypeValue(16);
        game7.setSessionTypeValue(16);
        assertEquals(game1.getSessionTypeValue(), game7.getSessionTypeValue());
        game1.setSessionTypeValue((long) 64);
        game7.setSessionTypeValue((long) 64);
        assertEquals(game1.getSessionTypeValue(), game7.getSessionTypeValue());

        assertEquals(game1.createUIString(), game7.createUIString());
        assertNotEquals(game3.createUIString(), game5.createUIString());

        GameSessionDifficulty game8 = new GameSessionDifficulty(GameSessionSetting.SMALL, GameSessionType.NORMAL);
        assertEquals(GameSessionSetting.SMALL.getMatchesNeededToWin(), GameSessionSetting.SMALL.getMatchesNeededToWin());
        assertEquals(GameSessionSetting.SMALL.getMatchesNeededToWin(), GameSessionSetting.SMALL.getMatchesNeededToWin());
        assertTrue(GameSessionSetting.SMALL.isSmall());
        assertTrue(GameSessionSetting.LARGE.isLarge());
        assertTrue(GameSessionSetting.MEDIUM.isMedium());


    }

}
