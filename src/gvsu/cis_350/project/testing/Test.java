package gvsu.cis_350.project.testing;

import gvsu.cis_350.project.core.game.difficulty.GameSessionSetting;
import gvsu.cis_350.project.core.game.difficulty.GameSessionType;
import gvsu.cis_350.project.core.game.difficulty.impl.TwoPlayerDifficulty;
import gvsu.cis_350.project.ui.GameFrame;

/**
 * Created by Admin on 12/1/2015.
 */
public class Test {
    public static void main(String[] args) {
        new GameFrame(new TwoPlayerDifficulty(GameSessionSetting.MEDIUM,
                GameSessionType.TIMED), "Des", "Jon");
    }
}
