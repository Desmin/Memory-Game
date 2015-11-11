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

        assertEquals(game1.getSessionType(), game2.getSessionType());
        assertNotEquals(game5.getSessionType(), game6.getSessionType());
        assertEquals(game4.getSessionSetting(), game5.getSessionSetting());
        assertNotEquals(game3.getSessionSetting(), game6.getSessionSetting());
    }
    
    @Test 
    public void resetTest() {
        GameSessionDifficulty game2 = new GameSessionDifficulty(GameSessionSetting.MEDIUM, GameSessionType.NORMAL);
        GameSessionDifficulty game3 = new GameSessionDifficulty(GameSessionSetting.SMALL, GameSessionType.TIMED);
        GameSessionDifficulty game4 = new GameSessionDifficulty(GameSessionSetting.LARGE, GameSessionType.LIMITED_MATCH_ATTEMPTS);
        GameSessionDifficulty game5 = new GameSessionDifficulty(GameSessionSetting.LARGE, GameSessionType.TIMED);
        GameSessionDifficulty game6 = new GameSessionDifficulty(GameSessionSetting.MEDIUM, GameSessionType.LIMITED_MATCH_ATTEMPTS);
        
        game4.reset();
        assertEquals(game3.getSessionType(), game5.getSessionType());
        assertEquals(game2.getSessionSetting(), game6.getSessionSetting());
        assertEquals(game4.getSessionSetting(), game5.getSessionSetting());
    }
    
    @Test
    public void valueTest() {

    	GameSessionDifficulty game1 = new GameSessionDifficulty(GameSessionSetting.SMALL, GameSessionType.NORMAL);
    	GameSessionDifficulty game7 = new GameSessionDifficulty(GameSessionSetting.SMALL, GameSessionType.NORMAL);
    	
        game1.setSessionTypeValue(16);
        game7.setSessionTypeValue(16);
        assertEquals(game1.getSessionTypeValue(), game7.getSessionTypeValue());
        game1.setSessionTypeValue((long) 64);
        game7.setSessionTypeValue((long) 64);
        assertEquals(game1.getSessionTypeValue(), game7.getSessionTypeValue());
    }
    
    @Test
    public void UIStringTest() {
    	GameSessionDifficulty game1 = new GameSessionDifficulty(GameSessionSetting.SMALL, GameSessionType.NORMAL);
        GameSessionDifficulty game3 = new GameSessionDifficulty(GameSessionSetting.SMALL, GameSessionType.TIMED);
        GameSessionDifficulty game5 = new GameSessionDifficulty(GameSessionSetting.LARGE, GameSessionType.TIMED);
        GameSessionDifficulty game7 = new GameSessionDifficulty(GameSessionSetting.SMALL, GameSessionType.NORMAL);

        assertEquals(game1.createUIString(), game7.createUIString());
        assertNotEquals(game3.createUIString(), game5.createUIString());
    }
    
    @Test
    public void matchTest(){
        assertEquals(GameSessionSetting.SMALL.getMatchesNeededToWin(), GameSessionSetting.SMALL.getMatchesNeededToWin());
        assertEquals(GameSessionSetting.SMALL.getMatchesNeededToWin(), GameSessionSetting.SMALL.getMatchesNeededToWin());
        
    }
    
    @Test
    public void SettingTest(){
        assertTrue(GameSessionSetting.SMALL.isSmall());
        assertTrue(GameSessionSetting.LARGE.isLarge());
        assertTrue(GameSessionSetting.MEDIUM.isMedium());


    }

}
