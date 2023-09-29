package levels;

import gameObjects.handler.GameObjectHandler;
import main.GamePanel;
import object.ObjectConstants;
import object.ObjectManager;
import object.SuperObject;
import tile.TileConstants;
import tile.TileManager;

import java.awt.*;

import static main.GamePanel.*;

public class LevelManager {

    private Level level;
    private int levelRef;
    private LevelConstants levelConstants;
    private TileManager tileManager;
    private ObjectManager objectManager;
    private GameObjectHandler gameObjectHandler;

    //Load new level from LevelConstants class
    public void loadNewLevel(int i) {
        //Create new instance here - unsure whether is better to avoid this. But we want to reset the value of levelConstants.
        levelRef = i;
        levelConstants = new LevelConstants();
        levelConstants.loadLevelConstants(i);
        loadTileManager();
        loadObjectManager();
        loadLevel();
        loadGameObjectHandler();
    }

    private void loadGameObjectHandler() {
        gameObjectHandler = new GameObjectHandler(level.getLevelWidth(), level.getLevelHeight(), null, null);
        gameObjectHandler.loadGameObjectHandler();
    }
    private void loadTileManager() {
        tileManager = new TileManager(levelConstants.getTileConstants());
        tileManager.loadTiles(levelConstants.getTileReferences(levelRef));
    }

    private void loadObjectManager() {
        objectManager = new ObjectManager(levelConstants.getObjectConstants());
        objectManager.loadObjects(levelConstants.getObjectReferences(levelRef));
    }

    private void loadLevel() {
        level = new Level(levelConstants.getLevelJsonData(levelRef), tileManager.getTiles(), objectManager.getCollectableObjects());
    }

    public void draw(Graphics g) {
        //Draw tiles
        int levelHeight = level.getLevelHeight();
        int levelWidth = level.getLevelWidth();

        int xStart = (int) Math.max(0, level.getLevelCamera().getxOffset() / TILE_SIZE);
        int yStart = (int) Math.max(0, level.getLevelCamera().getyOffset() / TILE_SIZE);
        int xEnd = (int) Math.min(levelWidth, (level.getLevelCamera().getxOffset() + level.getLevelCamera().getxOffset() + SCREEN_WIDTH)/TILE_SIZE +1);
        int yEnd = (int) Math.min(levelHeight, (level.getLevelCamera().getyOffset() + SCREEN_HEIGHT)/TILE_SIZE +1);
        //Level tiles
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                g.drawImage(level.getTile(x,y).getTileImage(),
                        (int) (x * TILE_SIZE - level.getLevelCamera().getxOffset()),
                        (int) (y * TILE_SIZE - level.getLevelCamera().getyOffset()),
                        TILE_SIZE, TILE_SIZE, null);
//                if(level.get()[x][y] !=null) {
//                    g.drawImage(level.getLevelObjects()[x][y].getObjectImage(),
//                            (int)(level.getLevelObjects()[x][y].getX()*TILE_SIZE-level.getLevelCamera().getxOffset()),
//                            (int)(level.getLevelObjects()[x][y].getY()*TILE_SIZE-level.getLevelCamera().getyOffset()),
//                            TILE_SIZE,TILE_SIZE,null);
//                }
            }
        }

        //Draw entities and objects - should do check to see whether they are within camera range also
        gameObjectHandler.render(g,level);
    }

    public void update() {
        gameObjectHandler.update(level);
    }

    public Level getLevel() {
        return level;
    }

    public GameObjectHandler getGameObjectHandler() {
        return gameObjectHandler;
    }

}
