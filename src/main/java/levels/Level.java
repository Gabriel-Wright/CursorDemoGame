package levels;

import tile.Tile;
import utils.LoadFiles;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class Level {

    private Tile[] levelTiles;
    private BufferedImage levelMap;
    private int[][] levelData;
    private int levelWidth;
    private int levelHeight;

    public Level(Tile[] levelTiles, String levelMapPath) {
        this.levelTiles = levelTiles;
        this.levelMap = LoadFiles.importImg(levelMapPath);
        levelWidth = levelMap.getWidth();
        levelHeight = levelMap.getHeight();
    }

    /**
     *
     */
    public void loadLevelData() {
        levelData = new int[levelWidth][levelHeight];
        for (int i = 0; i < levelWidth; i++) {
            for (int j = 0; j < levelHeight; j++) {
                Color color = new Color(levelMap.getRGB(i, j));
                int tileIndex = color.getRed();
                //If invalid tileIndex - then set the tile to failed tile
                if (tileIndex > levelTiles.length) {
                    tileIndex = levelTiles.length - 1;
                }
                levelData[i][j] = tileIndex;
            }
        }
    }

    public int[][] getLevelData() {
        return levelData;
    }

    public int getTileIndex(int x, int y) {
        return levelData[x][y];
    }

    //Have to update to handle out of map tiles
    public Tile getTile(int x, int y) {
        return levelTiles[levelData[x][y]];
    }

    public Tile[] getLevelTiles() {
        return levelTiles;
    }

    public int getLevelWidth() {
        return levelWidth;
    }

    public int getLevelHeight() {
        return levelHeight;
    }

    public boolean isSolidTile(int x, int y) {
        return getTile(x, y).isSolid();
    }
}
