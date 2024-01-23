package tasks.gameWaves.spawnConstants;

import gameObjects.entities.Entity;
import gameObjects.entities.constants.EntityConstants;
import gameObjects.entities.enemies.GreenDeath.GreenDeath;
import gameObjects.entities.enemies.GreenDeath.GreenDeathConstants;
import tasks.gameWaves.spawnTasks.SpawnEntity;
import ui.PointPair;

import java.awt.*;

import static levels.LevelConstants.EASY_LEVEL;
import static levels.LevelConstants.TEST_DEMICHROME;
import static main.GamePanel.TILE_SIZE;
import static main.GamePanel.UPS;

public class SpawnEntityConstants{

//    private final int id;

    //EntityFlags
    public final static int GREEN_DEATH = 0;

    private SpawnEntity[] loadedEntitySpawns;
    private int[] entityIndexes;
    private Point[] loadedEntitySpawnPositions;
    private PointPair[] loadedPointPairs;
    /*
    * FINDERS - find all data stored for entities that are constants e.g. spawn positions, assigned to levels etc.
     */

    //Provides you with the entities assigned to that level
    public int[] findLevelEntityIndexes(int id) {
        return switch(id) {
            case TEST_DEMICHROME, EASY_LEVEL -> new int[]{GREEN_DEATH};
            default -> new int[]{GREEN_DEATH};
        };
    }


    //For now we will have entities spawn in the same positions I think
    public Point[] findEntitySpawnLocations(int id) {
        return switch(id) {
            case TEST_DEMICHROME -> new Point[]{new Point(2*TILE_SIZE, 15*TILE_SIZE), new Point(31*TILE_SIZE, 3*TILE_SIZE), new Point(25*TILE_SIZE,29*TILE_SIZE)};
            case EASY_LEVEL -> new Point[]{new Point(2*TILE_SIZE, 15*TILE_SIZE), new Point(31*TILE_SIZE, 3*TILE_SIZE), new Point(25*TILE_SIZE, 31*TILE_SIZE)};
            default -> new Point[]{new Point(0,0)};
        };
    }

    public PointPair[] findEntitySpawnLocationsP(int id) {
        return switch(id) {
            case TEST_DEMICHROME -> new PointPair[]{new PointPair(new Point(2*TILE_SIZE,15*TILE_SIZE),new Point(3*TILE_SIZE,15*TILE_SIZE)),
                    new PointPair(new Point(31*TILE_SIZE, 3*TILE_SIZE), new Point(27*TILE_SIZE,5*TILE_SIZE)),
                    new PointPair(new Point(25*TILE_SIZE, 29*TILE_SIZE), new Point(22*TILE_SIZE, 25*TILE_SIZE))};
            case EASY_LEVEL -> new PointPair[]{new PointPair(new Point(2*TILE_SIZE, 15*TILE_SIZE), new Point(4*TILE_SIZE, 17*TILE_SIZE)),
                    new PointPair(new Point(31*TILE_SIZE, 3*TILE_SIZE), new Point(31*TILE_SIZE, 5*TILE_SIZE)),
                    new PointPair(new Point(25*TILE_SIZE,31*TILE_SIZE), new Point(25*TILE_SIZE, 28*TILE_SIZE))};
            default -> new PointPair[] {new PointPair(new Point(0,0), new Point(1,1))};
        };
    }

    private EntityConstants findEntityConstants(int entityIndex) {
        return switch(entityIndex) {
            case GREEN_DEATH -> new GreenDeathConstants();
            default -> new GreenDeathConstants();
        };
    }

    private Entity findEntity(int entityIndex) {
        return switch(entityIndex) {
            case GREEN_DEATH -> new GreenDeath(findEntityConstants(entityIndex));
            default ->new GreenDeath(findEntityConstants(GREEN_DEATH));
        };
    }

    private int findEntityWorth(int entityIndex) {
        return switch(entityIndex) {
            case GREEN_DEATH -> 10;
            default ->10;
        };
    }

    private int findEntityCheckTick(int entityIndex) {
        return switch(entityIndex) {
            case GREEN_DEATH -> UPS*5;
            default -> UPS;
        };
    }

    private int findEntitySpawnTickBuffer(int entityIndex) {
        return switch(entityIndex) {
            case GREEN_DEATH -> UPS*2;
            default ->UPS;
        };
    }

    private int findEventSpawnTickBuffer(int entityIndex) {
        return switch(entityIndex) {
            case GREEN_DEATH -> UPS;
            default -> UPS;
        };
    }

    public SpawnEntity findSpawnEntity(int entityIndex) {
        return new SpawnEntity(findEntityWorth(entityIndex), findEntitySpawnTickBuffer(entityIndex), findEventSpawnTickBuffer(entityIndex),
                findEntity(entityIndex));
    }

    /*
    * LOADERS - load entity spawn position and indexes based on the id of the level
     */

    public void loadEntitySpawnsPositions(int id) {
        loadedEntitySpawnPositions = findEntitySpawnLocations(id);
    }

    public void loadEntitySpawnPositionsP(int id) {
        loadedPointPairs = findEntitySpawnLocationsP(id);
    }

    public void loadEntityIndexes(int id){
        entityIndexes = findLevelEntityIndexes(id);
    }

    /*
    * GETTERS - Return data stored about the entities that is saved to the constants class
     */

    public SpawnEntity[] getLoadedEntitySpawns() {
        return loadedEntitySpawns;
    }

    public PointPair[] getLoadedPointPairs() {
        return loadedPointPairs;
    }

    public Point[] getLoadedEntitySpawnPositions() {
        return loadedEntitySpawnPositions;
    }

    public int[] getSavedEntityIndexes(){
        return entityIndexes;
    }


}
