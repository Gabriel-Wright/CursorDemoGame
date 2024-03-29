package options.menu.execute.gameState;

import main.GamePanel;
import options.menu.execute.Executes;
import states.GameState;

import static levels.LevelConstants.EASY_LEVEL;

public class LevelOneExecute implements Executes {
    @Override
    public void executeChoice() {
        GameState.setLevelID(EASY_LEVEL);
        GameState.setWavePoints(50);
        GamePanel.startGame();
    }
}
