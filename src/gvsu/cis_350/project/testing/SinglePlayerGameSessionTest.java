package gvsu.cis_350.project.testing;

/**
 * Created by Desmin Little on 11/11/2015.
 */
public class SinglePlayerGameSessionTest {
    /**
     * Create overrides so that the UI isn't shown during testing.
     */
    /*private SinglePlayerGameSession session = new SinglePlayerGameSession();

    @Test
    public void testInitialize() throws Exception {
        //Initialize the session
        GameSessionDifficulty sessionDifficulty = new GameSessionDifficulty(GameSessionSetting.LARGE,
                GameSessionType.LIMITED_MATCH_ATTEMPTS);
        session.addPlayerToGame("Des");
        session.initialize(sessionDifficulty);
        //Test that the values are assigned correctly
        assertEquals(session.getSessionPlayer().getName(), "Des");
        assertEquals(session.getSessionDifficulty(), sessionDifficulty);
    }

    @Test
    public void testReset() throws Exception {
        //Initialize the session
        GameSessionDifficulty sessionDifficulty = new GameSessionDifficulty(GameSessionSetting.LARGE,
                GameSessionType.LIMITED_MATCH_ATTEMPTS);
        session.addPlayerToGame("Des");
        session.initialize(sessionDifficulty);

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
    }*/

}