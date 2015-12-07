package gvsu.cis_350.project.testing;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.Player;
import gvsu.cis_350.project.core.game.difficulty.GameSessionDifficulty;
import gvsu.cis_350.project.core.game.difficulty.impl.SinglePlayerDifficulty;
import gvsu.cis_350.project.core.game.difficulty.impl.TwoPlayerDifficulty;
import gvsu.cis_350.project.core.game.impl.SinglePlayerGameSession;
import gvsu.cis_350.project.io.FileIO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Desmin Little on 11/11/2015.
 */
public class SinglePlayerGameSessionTest {

    @Test
    public void testPlayerGetterAndSetter() {
        SinglePlayerGameSession session = new SinglePlayerGameSession();
        Player player = FileIO.loadPlayerData("Des");
        session.addPlayerToGame("Des");
        Assert.assertEquals(player, session.getSessionPlayer());
    }

    @Test
    public void testPlayerGetterAndSetterFailure() {
        SinglePlayerGameSession session = new SinglePlayerGameSession();
        Player player = FileIO.loadPlayerData("Des");
        session.addPlayerToGame("desmin");
        Assert.assertNotEquals(player, session.getSessionPlayer());
    }

    @Test
    public void testAddMatchAndMatchGetter() {
        SinglePlayerGameSession session = new SinglePlayerGameSession();
        Assert.assertEquals(0, session.getPlayerMatches());
        session.addMatch();
        Assert.assertNotEquals(0, session.getPlayerMatches());
        session.addMatch();
        Assert.assertEquals(2, session.getPlayerMatches());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitializeFailure() {
        SinglePlayerGameSession session = new SinglePlayerGameSession();
        GameSessionDifficulty difficulty = GameSessionDifficulty.createRandomDifficulty("two_player");
        session.initialize(difficulty);

    }

    @Test
    public void testInitialize() {
        SinglePlayerGameSession session = new SinglePlayerGameSession();
        SinglePlayerDifficulty difficulty
                = (SinglePlayerDifficulty)GameSessionDifficulty.createRandomDifficulty("one_player");
        session.initialize(difficulty);
        Assert.assertEquals(difficulty, session.getSessionDifficulty());

    }

    @Test
    public void testCardClick() {
        SinglePlayerGameSession session = new SinglePlayerGameSession(); //Create class
        session.initialize(GameSessionDifficulty.createRandomDifficulty("one_player")); //Initialize class
        session.addPlayerToGame("Des"); //Add a player
        Map<JLabel, Card> map = new HashMap<>(); //Create card map for game session
        JLabel labelOne = new JLabel(), labelTwo = new JLabel(); //Create labels for clicking
        Card cardOne = new Card(Card.CardType.BANANA), cardTwo = new Card(Card.CardType.BROWN_HAT); //Create label cards
        //Populate the map
        map.put(labelOne, cardOne);
        map.put(labelTwo, cardTwo);
        session.setCardMap(map); //Set the card map in the game session
        //'Click' both labels
        session.onCardClick(labelOne);
        session.onCardClick(labelTwo);
        //Ensure the method works and doesn't counts these as a match.
        Assert.assertEquals(0, session.getPlayerMatches());
    }
}