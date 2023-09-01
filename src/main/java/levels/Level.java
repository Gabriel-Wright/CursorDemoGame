package levels;

import tile.Tile;
import utils.LoadFiles;

import java.awt.image.BufferedImage;
import java.awt.Color;
public class Level {

    private Tile[] levelTiles;
    private BufferedImage levelMap;
    private int[][] levelData;

    public Level(Tile[] levelTiles, String levelMapPath) {
        this.levelTiles = levelTiles;
        this.levelMap = LoadFiles.importImg(levelMapPath);
    }

    /**
     *
     * @param x - dimension x of level map
     * @param y - dimension y of level map
     */
    public void loadLevelData() {
        int levelWidth = levelMap.getWidth();
        int levelHeight = levelMap.getHeight();
        levelData = new int[levelWidth][levelHeight];
        for(int i=0; i<levelWidth; i++) {
            for(int j=0; j<levelHeight; j++) {
                Color color = new Color(levelMap.getRGB(i,j));
                int tileIndex = color.getRed();
                //If invalid tileIndex - then set the tile to failed tile
                if(tileIndex > levelTiles.length) {
                    tileIndex = levelTiles.length-1;
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

    public Tile getTile(int x, int y) {
        return levelTiles[levelData[x][y]];
    }

    public Tile[] getLevelTiles() {
        return levelTiles;
    }

}
