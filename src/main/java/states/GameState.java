package states;

import levels.LevelConstants;
import levels.LevelManager;
import main.GamePanel;
import tasks.TaskRunner;
import ui.UI;

import java.awt.*;

import static main.GamePanel.lockCursor;

public class GameState extends State{

    private TaskRunner taskHandler;
    private LevelManager levelManager;
    private UI gameUI;

    public void loadTestGame() {
        taskHandler = new TaskRunner();
        loadLevelManager();
        loadUI();
    }


    //Need to switch these to be linked to FPS rather than updates.
    public static void updateGameBackground(Color color) {
        GamePanel.backGroundColor = color;
    }

    public static Color getBackgroundColor() {
        return GamePanel.backGroundColor;
    }

    private void loadUI() {
        gameUI = new UI(levelManager);
    }

    private void loadLevelManager() {
        levelManager = new LevelManager();
        levelManager.loadNewLevel(LevelConstants.TEST_DEMICHROME);
    }

    @Override
    public void initialiseState() {
        loadTestGame();
    }

    @Override
    public void reloadState() {
        lockCursor();
    }

    @Override
    public void update() {
        levelManager.update();
        taskHandler.updateTasks();
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    @Override
    public void render(Graphics g) {
        levelManager.draw(g);
        gameUI.render(g);
    }

}
