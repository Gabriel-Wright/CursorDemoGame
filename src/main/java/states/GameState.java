package states;

import entities.player.Player;
import entities.player.PlayerConstants;
import levels.LevelManager;
import utils.UI;

import java.awt.*;

import static main.GamePanel.TILE_SIZE;

public class GameState extends State{

    private int startX = 23*TILE_SIZE;
    private int startY = 40*TILE_SIZE;

    private Player player;
    private PlayerConstants playerConstants;
    private LevelManager levelManager;
    private UI gameUI;
    public void loadTestGame() {
        loadLevelManager();
        loadPlayerInfo();
        loadUI();
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
    public void update() {
        player.update();
        //CollisionChecker.checkCornerCollision(player,levelManager.getLevel());
        levelManager.update();
    }

    @Override
    public void render(Graphics g) {
        levelManager.draw(g);
        player.render(g);
        gameUI.render(g);
    }
}
