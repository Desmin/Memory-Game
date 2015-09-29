package gvsu.cis_350.project.testing;

import static org.junit.Assert.*;
import gvsu.cis_350.project.core.Player;
import gvsu.cis_350.project.core.io.FileIO;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class FileIOTest {
	
	@Test
	public void test() {
		String test = "test1234";
		
		Player playerOne = new Player(test, 1 ,3);
		try {
			FileIO.savePlayerData(playerOne);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Player playerTwo;
		playerTwo = FileIO.loadPlayerData(test);
		
		assertEquals(playerOne, playerTwo);
	}

}
