package levels;

import gameObjects.objects.SuperObject;
import levels.tile.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

import static main.GamePanel.*;
import static main.GamePanel.TILE_SIZE;

public class Level {

    //private Tile[] levelTiles;
    private Map<Integer, Tile> templateLevelTiles;
    private SuperObject[] levelObjects;
    private BufferedImage levelMap;
//    private SuperObject[][] levelObjects;
    private int[][] levelTileData;
    private int levelWidth;
    private int levelHeight;
    private LevelCamera levelCamera;



    public Level(int[][] levelTileData, Map<Integer, Tile> templateLevelTiles, SuperObject[] templateLevelObjects) {
        this.levelTileData = levelTileData;
        this.templateLevelTiles = templateLevelTiles;
        this.levelObjects = templateLevelObjects;
        levelHeight = levelTileData[0].length;
        levelWidth = levelTileData.length;
        levelCamera = new LevelCamera(0 ,0, levelWidth, levelHeight);
        levelCamera.move(TILE_SIZE*3,TILE_SIZE*3);
    }

    public int[][] getLevelTileData() {
        return levelTileData;
    }

    public int getTileIndex(int x, int y) {
        return levelTileData[x][y];
    }

    public boolean isSolidTile(int x, int y) {
        return getTile(x, y).isSolid();
    }

    //Have to update to handle out of map tiles
    public Tile getTile(int x, int y) {
        return templateLevelTiles.get(levelTileData[x][y]);
    }

    public Map<Integer, Tile> getTemplateLevelTiles() {
        return templateLevelTiles;
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

    public void render(Graphics g) {

        int xStart = (int) Math.max(0, levelCamera.getxOffset() / TILE_SIZE);
        int yStart = (int) Math.max(0, levelCamera.getyOffset() / TILE_SIZE);
        int xEnd = (int) Math.min(levelWidth, (levelCamera.getxOffset() + levelCamera.getxOffset() + TARGET_SCREEN_WIDTH)/TILE_SIZE +1);
        int yEnd = (int) Math.min(levelHeight, (levelCamera.getyOffset() + TARGET_SCREEN_HEIGHT)/TILE_SIZE +1);
        //Level tiles
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                g.drawImage(getTile(x,y).getTileImage(),
                        (int) (x * TILE_SIZE - levelCamera.getxOffset()),
                        (int) (y * TILE_SIZE - levelCamera.getyOffset()),
                        TILE_SIZE, TILE_SIZE, null);
            }
        }

    }
}
