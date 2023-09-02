package levels;

import main.GamePanel;
import tile.TileManager;

import java.awt.*;

import static main.GamePanel.TILE_SIZE;

public class LevelManager {

    private Level level;
    private TileManager tileManager;

    public void loadNewLevel(String tileSpritePath, String levelMapPath) {
        loadTileManager(tileSpritePath);
        loadLevel(levelMapPath);
    }

    private void loadTileManager(String tileSpritePath) {
        tileManager = new TileManager(tileSpritePath);
        tileManager.loadTiles();
    }

    private void loadLevel(String levelMapPath) {
        level = new Level(tileManager.getTiles(), levelMapPath);
        level.loadLevelData();
    }

    public void draw(Graphics g, float playerWorldX, float playerWorldY, float playerScreenX, float playerScreenY) {
        int maxWorldRow = level.getLevelHeight();
        int maxWorldCol = level.getLevelWidth();

        int worldRow = 0;
        int worldCol = 0;

        while (worldCol < maxWorldCol && worldRow < maxWorldRow) {
            int worldX = worldCol * TILE_SIZE;
            int worldY = worldRow * TILE_SIZE;
            int screenX = (int) (worldX - playerWorldX + playerScreenX);
            int screenY = (int) (worldY - playerWorldY + playerScreenY);

            //Not rendering all of map outside of view
            if (screenX + TILE_SIZE > 0 && screenX < GamePanel.SCREEN_WIDTH &&
                    screenY + TILE_SIZE > 0 && screenY < GamePanel.SCREEN_HEIGHT) {
                int tileIndex = level.getTileIndex(worldCol, worldRow);
                g.drawImage(level.getLevelTiles()[tileIndex].getTileImage(), screenX, screenY, TILE_SIZE, TILE_SIZE, null);
            }
            worldCol++;

            if(worldCol == maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

    public void update() {

    }

    public Level getLevel() {
        return level;
    }
}
