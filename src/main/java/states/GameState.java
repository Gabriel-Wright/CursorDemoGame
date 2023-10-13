package states;

import gameObjects.entities.player.Player;
import gameObjects.entities.player.PlayerConstants;
import levels.LevelConstants;
import levels.LevelManager;
import main.GamePanel;
import tasks.TaskHandler;
import ui.UI;
import static inputs.KeyHandler.isPaused;
import java.awt.*;

import static main.GamePanel.TILE_SIZE;

public class GameState extends State{

    private TaskHandler taskHandler;
    private PauseState pauseState;
    private boolean inPauseState = false;
    private LevelManager levelManager;
    private UI gameUI;

    public void loadTestGame() {
        loadLevelManager();
        loadUI();
        pauseState = new PauseState(this);
        taskHandler = new TaskHandler();
    }

    //Need to switch these to be linked to FPS rather than updates.
    public static void updateGameBackground(Color color) {
        GamePanel.backGroundColor = color;
    }

    public static Color getBackgroundColor() {
        return GamePanel.backGroundColor;
    }

    public void pauseGame() {
        pauseState.initialiseState();
    }

    public void resumeGame() {
        pauseState.endState();
    }
    private void loadUI() {
        gameUI = new UI(levelManager);
    }

    private void loadLevelManager() {
        levelManager = new LevelManager();
        levelManager.loadNewLevel(LevelConstants.TEST_LEVEL);
    }
    @Override
    public void initialiseState() {
        loadTestGame();
    }

    //This update method checks whether a pause state has changed -> this is important because
    //in this case resumeGame() or pauseGame() is ran when a change occurs.
    @Override
    public void update() {
        if(hasSwitchedPauseState()) {
            if(inPauseState) {
                pauseGame();
            } else {
                resumeGame();
            }
        }
        if(!isPaused) {
            levelManager.update();
        }
        if(isPaused) {
            pauseState.update();
        }
        taskHandler.updateTasks();
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    @Override
    public void render(Graphics g) {
        if(!isPaused) {
            levelManager.draw(g);
            gameUI.render(g);
        }
        if(isPaused) {
            pauseState.render(g);
        }
    }
    private boolean hasSwitchedPauseState() {
        boolean hasSwitched = isPaused != inPauseState;
        inPauseState = isPaused;
        return hasSwitched;
    }

}
