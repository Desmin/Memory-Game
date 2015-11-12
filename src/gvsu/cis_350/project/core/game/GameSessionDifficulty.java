package gvsu.cis_350.project.core.game;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;

public final class GameSessionDifficulty extends Observable {

    private final GameSessionType sessionType;
    private final GameSessionSetting sessionSetting;
    private Number sessionTypeValue;
    public GameSessionDifficulty(GameSessionSetting setting, GameSessionType type) {
        this.sessionSetting = setting;
        this.sessionType = type;
        if (!sessionType.isNormal())
            setSessionTypeValue(getAssociatedValue());
    }

    public void reset() {
        setSessionTypeValue(getAssociatedValue());
    }

    public GameSessionType getSessionType() {
        return sessionType;
    }

    public GameSessionSetting getSessionSetting() {
        return sessionSetting;
    }

    public void setSessionTypeValue(long value) {
        setSessionTypeValue(Long.valueOf(value));
    }

    public void setSessionTypeValue(int value) {
        setSessionTypeValue(Integer.valueOf(value));
    }

    public Number getSessionTypeValue() {
        return sessionTypeValue;
    }

    private void setSessionTypeValue(Number num) {
        this.sessionTypeValue = num;
        this.setChanged();
        this.notifyObservers();
    }

    private Number getAssociatedValue() {
        Number num = null;
        if (sessionType.isTimed()) {
            int cards = sessionSetting.getNumberOfCards();
            num = Long.valueOf((cards * 10000));
        } else if (sessionType.hasLimitedMatchAttempts()) {
            num = Integer.valueOf((int) (sessionSetting.getNumberOfCards() * .75));
        }
        return num;
    }

    public String createUIString() {
        String toReturn = "";
        if (this.getSessionType().isTimed()) {
            String form = sessionTypeValue.longValue() > 600000 ? "mm 'minutes'"
                    : sessionTypeValue.longValue() > 60000 ? "m 'minutes'" : "";
            form += " ss 'seconds remaining!'";
            SimpleDateFormat format = new SimpleDateFormat(form);
            toReturn = "    " + format.format(new Date(sessionTypeValue.longValue()));
        } else if (this.getSessionType().hasLimitedMatchAttempts()) {
            toReturn = "    " + sessionTypeValue.intValue() + " match attempts remaining!";
        }
        return toReturn;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GameSessionDifficulty) {
            GameSessionDifficulty game = (GameSessionDifficulty) obj;
            return this.sessionSetting == game.sessionSetting && this.sessionType == game.sessionType;
        }
        return false;
    }


}
