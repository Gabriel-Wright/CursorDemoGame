package states;

import entities.player.Player;
import entities.player.PlayerConstants;
import levels.LevelConstants;
import levels.LevelManager;
import ui.UI;
import static inputs.KeyHandler.isPaused;
import java.awt.*;

import static main.GamePanel.TILE_SIZE;

public class GameState extends State{

    private int startX = 5*TILE_SIZE;
    private int startY = 5*TILE_SIZE;
    private PauseState pauseState;
    private boolean inPauseState = false;
    private Player player;
    private PlayerConstants playerConstants;
    private LevelManager levelManager;
    private UI gameUI;
    public void loadTestGame() {
        loadLevelManager();
        loadPlayerInfo();
        loadUI();
        pauseState = new PauseState(this);
    }

    public void pauseGame() {
        pauseState.initialiseState();
    }

    public void resumeGame() {
        pauseState.endState();
    }
    private void loadUI() {
        gameUI = new UI(player, levelManager);
    }

    private void loadPlayerInfo() {
        playerConstants = new PlayerConstants();
        player = new Player(startX, startY, playerConstants,levelManager.getLevel());
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
            player.update();
            //CollisionChecker.checkCornerCollision(player,levelManager.getLevel());
            levelManager.update();
        }
        if(isPaused) {
            pauseState.update();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public LevelManager getLevelManager() {
        return levelManager;
    }

    @Override
    public void render(Graphics g) {
        if(!isPaused) {
            levelManager.draw(g);
            player.render(g);
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
