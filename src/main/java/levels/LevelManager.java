package levels;

import main.GamePanel;
import tile.TileManager;

import java.awt.*;

import static main.GamePanel.*;

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

    public void draw(Graphics g) {
        int levelHeight = level.getLevelHeight();
        int levelWidth = level.getLevelWidth();

        int xStart = (int) Math.max(0, level.getLevelCamera().getxOffset() / TILE_SIZE);
        int yStart = (int) Math.max(0, level.getLevelCamera().getyOffset() / TILE_SIZE);
        int xEnd = (int) Math.min(levelWidth, (level.getLevelCamera().getxOffset() + level.getLevelCamera().getxOffset() + SCREEN_WIDTH)/TILE_SIZE +1);
        int yEnd = (int) Math.min(levelHeight, (level.getLevelCamera().getyOffset() + SCREEN_HEIGHT)/TILE_SIZE +1);
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                int tileIndex = level.getTileIndex(x, y);
                g.drawImage(level.getLevelTiles()[tileIndex].getTileImage(),
                        (int) (x * TILE_SIZE - level.getLevelCamera().getxOffset()),
                        (int) (y * TILE_SIZE - level.getLevelCamera().getyOffset()),
                        TILE_SIZE, TILE_SIZE, null);
            }
        }
    }

    public void update() {

    }

    public Level getLevel() {
        return level;
    }
}
