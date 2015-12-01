package gvsu.cis_350.project.core.game.difficulty;

/**
 * Created by Desmin Little on 11/29/2015.
 */
public class SessionDifficultyValue {

    private Number sessionDifficultyValue;

    public SessionDifficultyValue(GameSessionDifficulty difficulty) {
        sessionDifficultyValue = getAssociatedValue(difficulty);
    }

    public void reset(GameSessionDifficulty difficulty) {
        setValue(getAssociatedValue(difficulty));
    }

    public void setValue(long value) {
        setValue(Long.valueOf(value));
    }

    public void setValue(int value) {
        setValue(Integer.valueOf(value));
    }

    public Number getValue() {
        return sessionDifficultyValue;
    }

    private void setValue(Number num) {
        this.sessionDifficultyValue = num;
    }

    private Number getAssociatedValue(GameSessionDifficulty difficulty) {
        Number num = null;
        int cards = difficulty.getSessionSetting().getNumberOfCards();
        if (difficulty.getSessionType().isTimed()) {
            num = Long.valueOf((cards * 10000));
        } else if (difficulty.getSessionType().hasLimitedMatchAttempts()) {
            num = Integer.valueOf((int) (cards * .75));
        }
        return num;
    }

}
