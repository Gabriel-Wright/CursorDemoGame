package levels;

import object.Object;
import object.SuperObject;
import tile.Tile;
import utils.LoadFiles;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class Level {

    private Tile[] levelTiles;
    private SuperObject[] templateLevelObjects;
    private BufferedImage levelMap;
    private SuperObject[][] levelObjects;
    private int[][] levelTileData;
    private int levelWidth;
    private int levelHeight;
    private LevelCamera levelCamera;


    public Level(Tile[] levelTiles, SuperObject[] templateLevelObjects, String levelMapPath) {
        this.levelTiles = levelTiles;
        this.templateLevelObjects = templateLevelObjects;
        this.levelMap = LoadFiles.importImg(levelMapPath);
        levelWidth = levelMap.getWidth();
        levelHeight = levelMap.getHeight();
        levelCamera = new LevelCamera(0, 0, levelWidth, levelHeight);
    }

    /**
     *
     */
    public void loadLevelData() {
        levelTileData = new int[levelWidth][levelHeight];
        levelObjects = new SuperObject[levelWidth][levelHeight];
        for (int i = 0; i < levelWidth; i++) {
            for (int j = 0; j < levelHeight; j++) {
                Color color = new Color(levelMap.getRGB(i, j));
                int tileIndex = color.getRed();
                int objectIndex = color.getGreen();
                //If invalid tileIndex - then set the tile to failed tile
                if (tileIndex > levelTiles.length) {
                    tileIndex = levelTiles.length - 1;
                }
                levelTileData[i][j] = tileIndex;
                if(objectIndex < templateLevelObjects.length) {
                    Object tempObj = new Object(i,j,templateLevelObjects[objectIndex].getObjectImage(),templateLevelObjects[objectIndex].hasCollided());
                    levelObjects[i][j] = tempObj;
                } else {
                    levelObjects[i][j] = null;
                }
            }
        }
    }

    public int[][] getLevelTileData() {
        return levelTileData;
    }

    public int getTileIndex(int x, int y) {
        return levelTileData[x][y];
    }

    //Have to update to handle out of map tiles
    public Tile getTile(int x, int y) {
        return levelTiles[levelTileData[x][y]];
    }

    public Tile[] getLevelTiles() {
        return levelTiles;
    }

    public SuperObject[][] getLevelObjects() {
        return levelObjects;
    }

    public int getLevelWidth() {
        return levelWidth;
    }

    public int getLevelHeight() {
        return levelHeight;
    }


    public LevelCamera getLevelCamera() {
        return levelCamera;
    }

    public boolean isSolidTile(int x, int y) {
        return getTile(x, y).isSolid();
    }
}
