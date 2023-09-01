package levels;

import tile.TileManager;

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
        level = new Level(tileManager.getTiles(),levelMapPath);
        level.loadLevelData();
    }


}
