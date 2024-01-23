package options.menu.execute.gameState;

import main.GamePanel;
import options.menu.execute.Executes;
import states.GameState;

import static levels.LevelConstants.TEST_DEMICHROME;

public class TestLevelExecute implements Executes {

    @Override
    public void executeChoice() {
        GameState.setLevelID(TEST_DEMICHROME);
        GameState.setWavePoints(100);
        GamePanel.startGame();
    }

}
