package states;

import levels.LevelConstants;
import levels.LevelManager;
import main.GamePanel;
import tasks.TaskRunner;
import tasks.gameWaves.waveManagement.WaveManager;
import ui.UI;

import java.awt.*;
import java.util.Random;

import static main.GamePanel.lockCursor;

public class GameState extends State{

    private TaskRunner taskHandler;
    private LevelManager levelManager;
    private WaveManager waveManager;
    private UI gameUI;
    private int level;
    private int gameSeed = 1;
    public void loadTestGame() {
        taskHandler = new TaskRunner();
        loadLevelManager();
        //0 as this is the first round of the new wave
        loadWaveManager(0);
        loadUI();
        Random topSeedRandom = new Random();
        gameSeed = topSeedRandom.nextInt(2,10);
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
        level = LevelConstants.TEST_DEMICHROME;
        levelManager.loadNewLevel(level);
    }

    private void loadWaveManager(int waveRound) {
        //Will be 0 - as the game has just been
        waveManager = new WaveManager(waveRound, 50, gameSeed);
        waveManager.loadRandomGenerators();
        waveManager.loadSpawnConstants(level);
        TaskRunner.addTask(waveManager);
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
