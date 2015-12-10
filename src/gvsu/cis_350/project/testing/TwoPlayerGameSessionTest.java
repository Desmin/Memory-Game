package gvsu.cis_350.project.testing;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.Player;
import gvsu.cis_350.project.core.game.difficulty.GameSessionDifficulty;
import gvsu.cis_350.project.core.game.difficulty.impl.TwoPlayerDifficulty;
import gvsu.cis_350.project.core.game.impl.TwoPlayerGameSession;
import gvsu.cis_350.project.io.FileIO;
import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;


public class TwoPlayerGameSessionTest {

    @Test
    public void testPlayerGetterAndSetterFailure() {
        TwoPlayerGameSession session = new TwoPlayerGameSession();
        Player player = FileIO.loadPlayerData("Des");
        session.addPlayerToGame("desmin");
        Assert.assertNotEquals(player, session.getCurrentPlayer());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitializeFailure() {
        TwoPlayerGameSession session = new TwoPlayerGameSession();
        GameSessionDifficulty difficulty = GameSessionDifficulty.createRandomDifficulty("three_player");
        session.initialize(difficulty);

    }

    @Test
    public void testInitialize() {
        TwoPlayerGameSession session = new TwoPlayerGameSession();
        TwoPlayerDifficulty difficulty
                = (TwoPlayerDifficulty) GameSessionDifficulty.createRandomDifficulty("two_player");
        session.initialize(difficulty);
        //Assert.assertEquals(difficulty, session.getCurrentPlayerValue());
        Assert.assertEquals(difficulty, session.getSessionDifficulty());

    }

    @Test
    public void testCardClick() {
        TwoPlayerGameSession session = new TwoPlayerGameSession(); //Create class
        session.initialize(GameSessionDifficulty.createRandomDifficulty("two_player")); //Initialize class
        session.addPlayerToGame("Des"); //Add a player
        session.addPlayerToGame("Emily"); //Add 2nd player
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
        Assert.assertEquals(0, session.getTotalMatches());
        //Assert.assertEquals(0, session.getPlayerMatches());
    }

}
