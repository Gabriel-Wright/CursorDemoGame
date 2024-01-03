package tasks.gameWaves.spawnConstants;

import gameObjects.entities.Entity;
import gameObjects.entities.constants.EntityConstants;
import gameObjects.entities.enemies.GreenDeath.GreenDeath;
import gameObjects.entities.enemies.GreenDeath.GreenDeathConstants;
import tasks.gameWaves.spawnTasks.SpawnEntity;
import tasks.gameWaves.spawnTasks.SpawnPositionalEvent;
import tasks.gameWaves.spawnTasks.SpawnTask;

import java.awt.*;

import static levels.LevelConstants.TEST_DEMICHROME;
import static main.GamePanel.TILE_SIZE;
import static main.GamePanel.UPS;

public class SpawnEntityConstants {

//    private final int id;

    //EntityFlags
    public final static int GREEN_DEATH = 0;

    private SpawnEntity[] loadedEntitySpawns;
    private int[] entityIndexes;
    private Point[] loadedEntitySpawnPositions;

    public int[] getSavedEntityIndexes(){
        return entityIndexes;
    }

    //Provides you with the entities assigned to that level
    public int[] getLevelEntityIndexes(int id) {
        return switch(id) {
            case TEST_DEMICHROME -> new int[]{GREEN_DEATH};
            default -> new int[]{GREEN_DEATH};
        };
    }

    //For now we will have entities spawn in the same positions I think
    public Point[] getEntitySpawnLocations(int id) {
        return switch(id) {
            case TEST_DEMICHROME -> new Point[]{new Point(2*TILE_SIZE, 15*TILE_SIZE), new Point(31*TILE_SIZE, 3*TILE_SIZE), new Point(25*TILE_SIZE,29*TILE_SIZE)};
            default -> new Point[]{new Point(0,0)};
        };
    }

    public void loadEntitySpawnsPositions(int id) {
        loadedEntitySpawnPositions = getEntitySpawnLocations(id);
    }

    public void loadEntityIndexes(int id){
        entityIndexes = getLevelEntityIndexes(id);
    }

    private EntityConstants getEntityConstants(int entityIndex) {
        return switch(entityIndex) {
            case GREEN_DEATH -> new GreenDeathConstants();
            default -> new GreenDeathConstants();
        };
    }

    private Entity getEntity(int entityIndex) {
        return switch(entityIndex) {
            case GREEN_DEATH -> new GreenDeath(getEntityConstants(entityIndex));
            default ->new GreenDeath(getEntityConstants(GREEN_DEATH));
        };
    }

    private int getEntityWorth(int entityIndex) {
        return switch(entityIndex) {
            case GREEN_DEATH -> 10;
            default ->10;
        };
    }

    private int getEntityCheckTick(int entityIndex) {
        return switch(entityIndex) {
            case GREEN_DEATH -> UPS*5;
            default -> UPS;
        };
    }

    //For each level get the possible array of entity spawn tasks (minus their spawn positions, or with spawn positions) ?
    public void loadEntitySpawnTasks(int id) {
            int[] levelEntityIndexes = getLevelEntityIndexes(id);
            int numEntities = levelEntityIndexes.length;
            SpawnEntity[] entitySpawnTasks = new SpawnEntity[numEntities];
            int i, entityIndex;
            for(i=0; i<numEntities; i++) {
                entityIndex = levelEntityIndexes[i];
                entitySpawnTasks[i] = new SpawnEntity(getEntityWorth(entityIndex), getEntityCheckTick(entityIndex), getEntity(entityIndex));
            }
            loadedEntitySpawns = entitySpawnTasks;
    }

    public SpawnEntity getSpawnEntity(int entityIndex) {
        return new SpawnEntity(getEntityWorth(entityIndex), getEntityCheckTick(entityIndex), getEntity(entityIndex));
    }

    public SpawnEntity[] getLoadedEntitySpawns() {
        return loadedEntitySpawns;
    }

    public Point[] getLoadedEntitySpawnPositions() {
        return loadedEntitySpawnPositions;
    }
}