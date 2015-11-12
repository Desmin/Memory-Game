package gvsu.cis_350.project.testing;

import gvsu.cis_350.project.core.game.GameSessionDifficulty;
import gvsu.cis_350.project.core.game.GameSessionSetting;
import gvsu.cis_350.project.core.game.GameSessionType;
import gvsu.cis_350.project.core.game.impl.SinglePlayerGameSession;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Desmin Little on 11/11/2015.
 */
public class SinglePlayerGameSessionTest {

    /**
     * Create overrides so that the UI isn't shown during testing.
     */
    private SinglePlayerGameSession session = new SinglePlayerGameSession();

    @Test
    public void testInitialize() throws Exception {
        //Initialize the session
        GameSessionDifficulty sessionDifficulty = new GameSessionDifficulty(GameSessionSetting.LARGE,
                GameSessionType.LIMITED_MATCH_ATTEMPTS);
        session.initialize("Des", sessionDifficulty);
        //Test that the values are assigned correctly
        assertEquals(session.getSessionPlayer().getName(), "Des");
        assertEquals(session.getSessionDifficulty(), sessionDifficulty);
    }

    @Test
    public void testReset() throws Exception {
        //Initialize the session
        GameSessionDifficulty sessionDifficulty = new GameSessionDifficulty(GameSessionSetting.LARGE,
                GameSessionType.LIMITED_MATCH_ATTEMPTS);
        session.initialize("Des", sessionDifficulty);

        //Add a match
        session.addMatch();
        //Test that a match was added
        assertEquals(session.getSessionMatches(), 1);
        //Reset the session
        session.reset();
        //Test that associated values are reset correctly.
        assertEquals(session.getSessionMatches(), 0);
        assertEquals(session.getSessionDifficulty(), new GameSessionDifficulty(GameSessionSetting.LARGE,
                GameSessionType.LIMITED_MATCH_ATTEMPTS));
    }

}