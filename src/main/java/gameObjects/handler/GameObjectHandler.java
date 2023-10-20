package gameObjects.handler;

import gameObjects.entities.Entity;
import gameObjects.entities.enemies.GreenDeath.GreenDeath;
import gameObjects.entities.enemies.GreenDeath.GreenDeathConstants;
import gameObjects.entities.player.Player;
import gameObjects.entities.player.PlayerConstants;
import gameObjects.events.PositionalEvent;
import gameObjects.events.RoomChange;
import levels.Level;
import gameObjects.objects.SuperObject;
import ui.UI;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static main.GamePanel.*;

public class GameObjectHandler {

    //Used to store all data of cells maps from (x,y) -> Cell
    private GameObjectGrid gameObjectGrid;
    private List<Entity> entities;
    private List<SuperObject> objects;
    private List<PositionalEvent> positionalEvents;
    private Player player;
    private PlayerConstants playerConstants;
    private GreenDeathConstants greenDeathConstants;
    public static int ECPU; //Entity collision checks per update - how many checks per ingame update
    //Also add event triggers
    public GameObjectHandler(Player player, List<Entity> entities, List<SuperObject> objects) {
        gameObjectGrid = new GameObjectGrid();
        this.entities = entities;
        this.objects = objects;
    }

    public GameObjectGrid getGameObjectGrid() {
        return gameObjectGrid;
    }

    public void loadGameObjectHandler() {
        loadPlayerInfo();
        loadTestEntities();
        loadTestEvents();
        gameObjectGrid.initialiseGrid(player, entities, objects, positionalEvents);
    }

    private void loadPlayerInfo() {
        int startX = 5*TILE_SIZE;
        int startY = 5*TILE_SIZE;
        playerConstants = new PlayerConstants();
        player = new Player(startX, startY, playerConstants);
    }

    private void loadTestEvents() {
        positionalEvents = new ArrayList<>();
        RoomChange testEvent = new RoomChange(Color.GREEN, 7, 7, 1, 1, true);
        RoomChange testEvent2 = new RoomChange(Color.RED, 10, 10, 2, 2, true);
        positionalEvents.add(testEvent);
        positionalEvents.add(testEvent2);
    }

    private void loadTestEntities() {
        entities = new ArrayList<>();
        greenDeathConstants = new GreenDeathConstants();
        GreenDeath greenDeathTest = new GreenDeath(6*TILE_SIZE, 5*TILE_SIZE, greenDeathConstants);
        int index = 0;
        for(int i =0; i<100; i++) {
            GreenDeath greenDeath = new GreenDeath(2*TILE_SIZE+index*TILE_SIZE, 5*TILE_SIZE, greenDeathConstants);
            entities.add(greenDeath);
            if(index ==4) {
                index = 0;
            }
        }
    }

    //Pass level as argument for logic calculations with tile collisions
    //Need to restrict this to entities within a certain range, as with render method.
    public void update(Level level) {
        player.update(level, gameObjectGrid);
        for(Entity entity: entities) {
            if(entity !=player) {
                entity.update(level, gameObjectGrid);
            }
        }
        //For loop for entity updates - movement and tile collision
        //Within same for loop do collisions with object grid
        UI.ECPULOCAL = ECPU;
        ECPU = 0;
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
                    gameObjectGrid.getCell(x,y).renderObjects(g, level);
                    gameObjectGrid.getCell(x,y).renderEvents(g,level);
                }
            }
        }
        player.render(g, level);
    }
}
