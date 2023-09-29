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

import static main.GamePanel.TILE_SIZE;

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
        for(Entity entity: entities) {
            entity.render(g, level);
        }
    }
}
