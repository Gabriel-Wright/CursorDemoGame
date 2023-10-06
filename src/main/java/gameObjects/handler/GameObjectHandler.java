package gameObjects.handler;

import gameObjects.entities.Entity;
import gameObjects.entities.player.Player;
import gameObjects.entities.player.PlayerConstants;
import levels.Level;
import object.SuperObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static main.GamePanel.*;

public class GameObjectHandler {

    //Used to store all data of cells maps from (x,y) -> Cell
    private GameObjectGrid gameObjectGrid;
    private List<Entity> entities;
    private List<SuperObject> objects;
    private Player player;
    private PlayerConstants playerConstants;
    //Also add event triggers
    public GameObjectHandler(int numRows, int numCols, List<Entity> entities, List<SuperObject> objects) {
        gameObjectGrid = new GameObjectGrid(numRows, numCols);
        this.entities = entities;
        this.objects = objects;
    }

    public GameObjectGrid getGameObjectGrid() {
        return gameObjectGrid;
    }

    public void loadGameObjectHandler() {
        loadPlayerInfo();
        loadTestEntities();
        gameObjectGrid.initialiseGrid(entities, objects);
    }

    private void loadPlayerInfo() {
        int startX = 5*TILE_SIZE;
        int startY = 5*TILE_SIZE;
        playerConstants = new PlayerConstants();
        player = new Player(startX, startY, playerConstants);
    }

    private void loadTestEntities() {
        entities = new ArrayList<>();
        entities.add(player);
        objects = new ArrayList<>();

    }

    //Pass level as argument for logic calculations with tile collisions
    //Need to restrict this to entities within a certain range, as with render method.
    public void update(Level level) {
        for(Entity entity: entities) {
            entity.update(level, gameObjectGrid);
        }
        //For loop for entity updates - movement and tile collision
        //Within same for loop do collisions with object grid
    }

    public Player getPlayer() {
        return player;
    }

    public void render(Graphics g, Level level) {
        int xStart = (int) Math.max(0, level.getLevelCamera().getxOffset() / TILE_SIZE);
        int yStart = (int) Math.max(0, level.getLevelCamera().getyOffset() / TILE_SIZE);
        int xEnd = (int) Math.min(level.getLevelWidth(), (level.getLevelCamera().getxOffset() + level.getLevelCamera().getxOffset() + SCREEN_WIDTH)/TILE_SIZE +1);
        int yEnd = (int) Math.min(level.getLevelHeight(), (level.getLevelCamera().getyOffset() + SCREEN_HEIGHT)/TILE_SIZE +1);
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                if(gameObjectGrid.getCell(x,y)!=null) {
                    gameObjectGrid.getCell(x,y).renderEntities(g, level);
                }
            }
        }
    }
}
