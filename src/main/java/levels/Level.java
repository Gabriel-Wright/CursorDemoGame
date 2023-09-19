package levels;

import object.CollectableObject;
import object.SuperObject;
import tile.Tile;

import java.awt.image.BufferedImage;
import java.util.Map;

public class Level {

    //private Tile[] levelTiles;
    private Map<Integer, Tile> templateLevelTiles;
    private CollectableObject[] levelObjects;
    private BufferedImage levelMap;
//    private SuperObject[][] levelObjects;
    private int[][] levelTileData;
    private int levelWidth;
    private int levelHeight;
    private LevelCamera levelCamera;



    public Level(int[][] levelTileData, Map<Integer, Tile> templateLevelTiles, CollectableObject[] templateLevelObjects) {
        this.levelTileData = levelTileData;
        this.templateLevelTiles = templateLevelTiles;
        this.levelObjects = templateLevelObjects;
        levelWidth = levelTileData[0].length;
        levelHeight = levelTileData.length;
        levelCamera = new LevelCamera(0 ,0, levelWidth, levelHeight);
    }

    /**
     *
     */
//    public void loadLevelData() {
//        levelTileData = new int[levelWidth][levelHeight];
//        levelObjects = new CollectableObject[levelWidth][levelHeight];
//        for (int i = 0; i < levelWidth; i++) {
//            for (int j = 0; j < levelHeight; j++) {
//                Color color = new Color(levelMap.getRGB(i, j));
//                int tileIndex = color.getRed();
//                int objectIndex = color.getGreen();
//                //If invalid tileIndex - then set the tile to failed tile
//                if (tileIndex > levelTiles.length) {
//                    tileIndex = levelTiles.length - 1;
//                }
//                levelTileData[i][j] = tileIndex;
//                if(objectIndex < templateLevelObjects.length) {
//                    CollectableObject tempObj = new CollectableObject(i,j,templateLevelObjects[objectIndex].getObjectImage(),templateLevelObjects[objectIndex].hasCollided(),templateLevelObjects[objectIndex].getName());
//                    levelObjects[i][j] = tempObj;
//                } else {
//                    levelObjects[i][j] = null;
//                }
//            }
//        }
//    }



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

}
