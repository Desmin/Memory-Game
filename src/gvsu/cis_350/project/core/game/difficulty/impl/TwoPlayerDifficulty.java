package gvsu.cis_350.project.core.game.difficulty.impl;

import gvsu.cis_350.project.core.game.difficulty.GameSessionDifficulty;
import gvsu.cis_350.project.core.game.difficulty.GameSessionSetting;
import gvsu.cis_350.project.core.game.difficulty.GameSessionType;
import gvsu.cis_350.project.core.game.difficulty.SessionDifficultyValue;

/**
 * Created by Desmin Little on 11/29/2015.
 */
public class TwoPlayerDifficulty extends GameSessionDifficulty {

    private SessionDifficultyValue playerOneValue, playerTwoValue;

    public TwoPlayerDifficulty(GameSessionSetting setting, GameSessionType type) {
        super(setting, type);
        if (!type.isNormal()) {
            playerOneValue = new SessionDifficultyValue(this);
            playerTwoValue = new SessionDifficultyValue(this);
        }
    }

    public SessionDifficultyValue getPlayerOneValue() {
        return playerOneValue;
    }

    public void setPlayerOneValue(int playerOneValue) {
        this.playerOneValue.setValue(playerOneValue);
        this.setChanged();
        this.notifyObservers(this.playerOneValue);
    }

    public void setPlayerOneValue(long playerOneValue) {
        this.playerOneValue.setValue(playerOneValue);
        this.setChanged();
        this.notifyObservers(this.playerOneValue);
    }

    public SessionDifficultyValue getPlayerTwoValue() {
        return playerTwoValue;
    }

    public void setPlayerTwoValue(int playerTwoValue) {
        this.playerTwoValue.setValue(playerTwoValue);
        this.setChanged();
        this.notifyObservers(this.playerTwoValue);
    }

    public void reset() {
        playerOneValue.reset(this);
        playerTwoValue.reset(this);
    }
}
