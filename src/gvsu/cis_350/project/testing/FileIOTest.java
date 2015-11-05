package gvsu.cis_350.project.testing;

import gvsu.cis_350.project.core.Player;
import gvsu.cis_350.project.io.FileIO;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * A unit test to ensure user data saves and loads correctly.
 *
 * @author Emily
 */
public class FileIOTest {

    @Test
    public void test() {
        //Test user's name
        String test = "test1234";

        //Creating the first user
        Player playerOne = new Player(test, 1);
        try {
            //Saving the first user
            FileIO.savePlayerData(playerOne);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Player playerTwo;

        //Loading the first user's data into a new player object
        playerTwo = FileIO.loadPlayerData(test);

        //Asserting that they're equal to eachother
        assertEquals(playerOne, playerTwo);
    }

}
