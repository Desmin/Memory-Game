package gvsu.cis_350.project.core.game.difficulty.impl;

import gvsu.cis_350.project.core.game.difficulty.GameSessionDifficulty;
import gvsu.cis_350.project.core.game.difficulty.GameSessionSetting;
import gvsu.cis_350.project.core.game.difficulty.GameSessionType;
import gvsu.cis_350.project.core.game.difficulty.SessionDifficultyValue;

/**
 * Created by Desmin Little on 11/29/2015.
 */
public class SinglePlayerDifficulty extends GameSessionDifficulty {

    private SessionDifficultyValue playerValue;

    public SinglePlayerDifficulty(GameSessionSetting setting, GameSessionType type) {
        super(setting, type);
        if (!type.isNormal())
            playerValue = new SessionDifficultyValue(this);
    }

    public SessionDifficultyValue getPlayerValue() {
        return playerValue;
    }

    public void setPlayerValue(long playerValue) {
        this.playerValue.setValue(playerValue);
        this.setChanged();
        this.notifyObservers(this.playerValue);
    }

    public void setPlayerValue(int playerValue) {
        this.playerValue.setValue(playerValue);
        this.setChanged();
        this.notifyObservers(playerValue);
    }

    public void reset() {
        playerValue.reset(this);
    }
}
