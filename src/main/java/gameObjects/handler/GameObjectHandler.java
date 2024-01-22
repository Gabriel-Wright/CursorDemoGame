package gameObjects.handler;

import gameObjects.entities.Entity;
import gameObjects.entities.enemies.GreenDeath.GreenDeathConstants;
import gameObjects.entities.player.Player;
import gameObjects.entities.player.PlayerConstants;
import gameObjects.events.generic.PositionalEvent;
import levels.Level;
import gameObjects.objects.SuperObject;
import ui.UI;
import utils.PathFinder;

import java.awt.*;
import java.util.*;
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
    private PathFinder pathFinder;

    //Queues of entities, objects and positionalEvents that are added
    public static Queue<Entity> entityQueue = new LinkedList<>();
    public static Queue<SuperObject> objectQueue = new LinkedList<>();
    public static Queue<PositionalEvent> eventQueue = new LinkedList<>();
    public static Queue<SuperObject> objectRemoveQueue = new LinkedList<>();
    public static Queue<PositionalEvent> eventRemoveQueue = new LinkedList<>();

    public static int ECPU; //Entity collision checks per update - how many checks per ingame update

    //Also add event triggers
    public GameObjectHandler(Player player, List<Entity> entities, List<SuperObject> objects, Level level) {
        gameObjectGrid = new GameObjectGrid();
        this.entities = entities;
        this.objects = objects;
        this.pathFinder = new PathFinder(level);
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
        int startX = 46 * TILE_SIZE;
        int startY = 14 * TILE_SIZE;
        playerConstants = new PlayerConstants();
        player = new Player(startX, startY, playerConstants);
    }

    private void loadTestEvents() {
        positionalEvents = new ArrayList<>();
    }

    private void loadTestEntities() {
        entities = new ArrayList<>();
    }

    //Pass level as argument for logic calculations with tile collisions
    //Need to restrict this to entities within a certain range, as with render method.
    public void update(Level level) {
        updateEntityPaths();
        gameObjectLogicUpdate(level);
        gameObjectQueueUpdate();
        //For loop for entity updates - movement and tile collision
        //Within same for loop do collisions with object grid
        pollEventQueueRemoval();
        UI.ECPULOCAL = ECPU;
        ECPU = 0;
    }

    private void pollEventQueueRemoval() {
        while (!eventRemoveQueue.isEmpty()) {
            PositionalEvent event = eventRemoveQueue.poll();
            positionalEvents.remove(event);
            gameObjectGrid.removeEventFromCells(event);
        }
    }

    private void gameObjectLogicUpdate(Level level) {
        if (!player.isDeleted()) {
            player.update(level, gameObjectGrid);
        }
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            entity.update(level, gameObjectGrid);
            // Check whether to remove the entity
            if (entity.isDeleted()) {
                iterator.remove();
                gameObjectGrid.removeEntityFromCells(entity);
            }
        }
    }

    private void updateEntityPaths() {
        gameObjectGrid.updateEntityAgroPaths(pathFinder, gameObjectGrid.getAssignedCells(player).get(0));
    }

    private void gameObjectQueueUpdate() {
        // Check and add entities from the entity queue
        while (!entityQueue.isEmpty()) {
            Entity entity = entityQueue.poll();
            entities.add(entity);
            gameObjectGrid.addEntityToCell(entity);
        }

        // Check and add events from the event queue
        while (!eventQueue.isEmpty()) {
            PositionalEvent event = eventQueue.poll();
            positionalEvents.add(event);
            gameObjectGrid.addTriggerEventToCell(event);
        }

        // Check and add objects from the object queue
        while (!objectQueue.isEmpty()) {
            SuperObject object = objectQueue.poll();
            objects.add(object);
            gameObjectGrid.addObjectToCell(object);
        }
    }


    public Player getPlayer() {
        return player;
    }

    //Iterate through all cells within the camera's range - and then render them all
    public void render(Graphics g, Level level) {
        int xStart = (int) Math.max(0, level.getLevelCamera().getxOffset() / TILE_SIZE);
        int yStart = (int) Math.max(0, level.getLevelCamera().getyOffset() / TILE_SIZE);
        int xEnd = (int) Math.min(level.getLevelWidth(), (level.getLevelCamera().getxOffset() + level.getLevelCamera().getxOffset() + TARGET_SCREEN_WIDTH) / TILE_SIZE + 1);
        int yEnd = (int) Math.min(level.getLevelHeight(), (level.getLevelCamera().getyOffset() + TARGET_SCREEN_HEIGHT) / TILE_SIZE + 1);
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                if (gameObjectGrid.getCell(x, y) != null) {
                    gameObjectGrid.renderEvents(x, y, g, level);
                }
            }
        }
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                if (gameObjectGrid.getCell(x, y) != null) {
                    gameObjectGrid.renderObjects(x, y, g, level);
                    gameObjectGrid.renderEntities(x, y, g, level);
                }
            }
        }
        player.render(g, level);
    }
}
