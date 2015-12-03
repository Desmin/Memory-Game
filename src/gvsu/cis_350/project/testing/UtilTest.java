package gvsu.cis_350.project.testing;

import gvsu.cis_350.project.core.Card;
import gvsu.cis_350.project.core.game.difficulty.GameSessionSetting;
import gvsu.cis_350.project.utils.Util;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class UtilTest {

    @Test
    public void randomTest() {
        List<Card> cards1 = Util.fetchRandomizedList(GameSessionSetting.MEDIUM);
        List<Card> cards2 = Util.fetchRandomizedList(GameSessionSetting.MEDIUM);
        assertNotEquals(cards1, cards2);
    }

}
