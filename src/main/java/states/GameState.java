package states;

import entities.player.Player;
import entities.player.PlayerConstants;
import levels.LevelManager;
import ui.UI;
import static inputs.KeyHandler.isPaused;
import java.awt.*;

import static main.GamePanel.TILE_SIZE;

public class GameState extends State{

    private int startX = 23*TILE_SIZE;
    private int startY = 40*TILE_SIZE;
    private PauseState pauseState;
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
        isPaused = true;
    }

    public void resumeGame() {
        isPaused = false;
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
        levelManager.loadNewLevel("/tiles/testTiles.png","/objects/testObjects.png","/levelMaps/testMap2.png");
    }
    @Override
    public void initialiseState() {
        loadTestGame();
    }
    @Override
    public void update() {
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
}
