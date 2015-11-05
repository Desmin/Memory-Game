package gvsu.cis_350.project.testing;

import static org.junit.Assert.*;

import java.util.List;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.utils.Util;
import gvsu.cis_350.project.core.*;

import org.junit.Test;

public class UtilTest {

	@Test
	public void test() {
		List<Card> cards1 = Util.fetchRandomizedList(GameSessionSetting.MEDIUM);
		List<Card> cards2 = Util.fetchRandomizedList(GameSessionSetting.MEDIUM);
		assertNotEquals(cards1, cards2);
		
		String s1 = "BASIC";
		String s2 = "Basic";
		String s3 = Util.toLowerFirstUC(s2);
		assertEquals(s2, s3);
		
		String s4 = "B*sic";
		String s5 = Util.toLowerFirstUC(s4);
		assertNotEquals(s4, s2);
	}

}
