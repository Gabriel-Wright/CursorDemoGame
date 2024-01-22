package states;

import levels.LevelConstants;
import levels.LevelManager;
import main.GamePanel;
import tasks.TaskRunner;
import tasks.gameWaves.waveManagement.WaveManager;
import ui.UI;

import java.awt.*;
import java.util.Random;

import static inputs.KeyHandler.isPaused;
import static main.GamePanel.lockCursor;
import static main.GamePanel.togglePause;

public class GameState extends State{

    private TaskRunner taskHandler;
    private LevelManager levelManager;
    private WaveManager waveManager;
    private UI gameUI;
    private int level;
    private int gameSeed = 1;
    private Random seedRandomiser;


    private static int levelID;
    private static int wavePoints;

    @Override
    public void initialiseState() {
        loadTestGame();
        isPaused = false;
    }

    @Override
    public void reloadState() {
        lockCursor();
    }

    private void checkPauseState() {
        if(isPaused) {
            togglePause();
        }
    }

    private void loadTestGame() {
        taskHandler = new TaskRunner();
        loadLevelManager(levelID);
        loadRandomiser();
        //0 as this is the first round of the new wave
        loadWaveManager(0, wavePoints);
        loadUI();
    }

    private void loadLevelManager(int levelID) {
        levelManager = new LevelManager();
        level = levelID;
        levelManager.loadNewLevel(level);
    }

    private void loadRandomiser() {
        seedRandomiser = new Random();
        gameSeed = seedRandomiser.nextInt();
    }

    private void loadWaveManager(int waveRound, int wavePoints) {
        waveManager = new WaveManager(waveRound, wavePoints, gameSeed);
        waveManager.loadRandomGenerators();
        waveManager.loadSpawnConstants(level);
        TaskRunner.addTask(waveManager);
    }

    private void loadUI() {
        gameUI = new UI(levelManager);
    }

    public static void setLevelID(int id) {
        levelID = id;
    }

    public static void setWavePoints(int points) {
        wavePoints = points;
    }

    public static void updateGameBackground(Color color) {
        GamePanel.backGroundColor = color;
    }

    public static Color getBackgroundColor() {
        return GamePanel.backGroundColor;
    }


    @Override
    public void update() {
        checkPauseState();
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
