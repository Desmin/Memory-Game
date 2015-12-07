package gvsu.cis_350.project.core.game.difficulty;

import gvsu.cis_350.project.core.game.difficulty.impl.SinglePlayerDifficulty;
import gvsu.cis_350.project.core.game.difficulty.impl.TwoPlayerDifficulty;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;

public class GameSessionDifficulty extends Observable {

    private final GameSessionType sessionType;
    private final GameSessionSetting sessionSetting;

    public GameSessionDifficulty(GameSessionSetting setting, GameSessionType type) {
        this.sessionSetting = setting;
        this.sessionType = type;
    }

    public GameSessionType getSessionType() {
        return sessionType;
    }

    public GameSessionSetting getSessionSetting() {
        return sessionSetting;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GameSessionDifficulty) {
            GameSessionDifficulty game = (GameSessionDifficulty) obj;
            return this.sessionSetting == game.sessionSetting && this.sessionType == game.sessionType;
        }
        return false;
    }

    public String createUIString(SessionDifficultyValue givenPlayerValue) {
        String toReturn = "";
        if (this.getSessionType().isTimed()) {
            String form = givenPlayerValue.getValue().longValue() > 600000 ? "mm 'minutes'"
                    : givenPlayerValue.getValue().longValue() > 60000 ? "m 'minutes'" : "";
            form += " ss 'seconds remaining!'";
            SimpleDateFormat format = new SimpleDateFormat(form);
            toReturn = "    " + format.format(new Date(givenPlayerValue.getValue().longValue()));
        } else if (this.getSessionType().hasLimitedMatchAttempts()) {
            toReturn = "    " + givenPlayerValue.getValue().intValue() + " match attempts remaining!";
        }
        return toReturn;
    }

    public static GameSessionDifficulty createRandomDifficulty(String expectedClass) {
        SecureRandom ran = new SecureRandom();
        GameSessionSetting setting = GameSessionSetting.values()[ran.nextInt(GameSessionSetting.values().length)];
        GameSessionType type = GameSessionType.values()[ran.nextInt(GameSessionType.values().length)];
        if (expectedClass.equals("two_player"))
            return new TwoPlayerDifficulty(setting, type);
        else if (expectedClass.equals("one_player"))
            return new SinglePlayerDifficulty(setting, type);
        else
            return new GameSessionDifficulty(setting, type);
    }

}