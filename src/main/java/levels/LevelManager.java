package levels;

import gameObjects.handler.GameObjectHandler;
import gameObjects.objects.ObjectManager;
import tile.TileManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static main.GamePanel.TILE_SIZE;

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
        gameObjectHandler = new GameObjectHandler( null, null, Arrays.asList(objectManager.getCollectableObjects()));
        gameObjectHandler.loadGameObjectHandler();
    }
    private void loadTileManager() {
        tileManager = new TileManager(levelConstants.getTileConstants());
        tileManager.loadTiles(levelConstants.getTileReferences(levelRef));
    }

    private void loadObjectManager() {
        objectManager = new ObjectManager(levelConstants.getObjectConstants());
        objectManager.loadObjects();
    }

    private void loadLevel() {
        level = new Level(levelConstants.getLevelJsonData(levelRef), tileManager.getTiles(), objectManager.getCollectableObjects());
    }

    public void draw(Graphics g) {
        //Draw background tiles centred around levelCamera
        level.render(g);
        //Draw entities and objects - should do check to see whether they are within camera range also
        gameObjectHandler.render(g,level);
        g.setColor(Color.GRAY);
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
