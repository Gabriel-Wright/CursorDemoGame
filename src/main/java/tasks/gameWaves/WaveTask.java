package tasks.gameWaves;

import gameObjects.entities.enemies.GreenDeath.GreenDeath;
import gameObjects.entities.enemies.GreenDeath.GreenDeathConstants;
import tasks.Task;
import tasks.TaskQueueConstants;
import tasks.gameWaves.spawnTasks.SpawnEntity;

import java.awt.*;
import java.util.Map;
import java.util.Random;

import static gameObjects.handler.GameObjectHandler.entityQueue;

public class WaveTask  extends Task {

    private int wavePoints;
    private final int entityValue = 8;
    private final int objectValue = 2;
    private final int bufferValue = 5;
    private final int eventValue = 25;

    //Entity spawn Randomiser, location and points
    private Random entityRandom;
    private SpawnEntity[] entitySpawnTasks;
    private Point[] entitySpawnLocation;

    private Random objectRandom;
    private Random eventRandom;

    //

    //Entity and event position options
    private Map<Integer, TaskQueueConstants.PositionalEventSpawnInfo> decreaseSpawnLocations;
    private TaskQueueConstants.PositionalEventSpawnInfo[] cursorBombLocations;

    public WaveTask(int wavePoints, Random entityRandom, Random objectRandom, Random eventRandom, Point[] entitySpawnLocations,
    Map<Integer, TaskQueueConstants.PositionalEventSpawnInfo> decreaseSpawnLocations,
    TaskQueueConstants.PositionalEventSpawnInfo[] cursorBombLocations) {
        this.wavePoints = wavePoints;
        this.entityRandom = entityRandom;
        this.objectRandom = objectRandom;
        this.eventRandom = eventRandom;
        this.entitySpawnLocation = entitySpawnLocations;
        this.decreaseSpawnLocations = decreaseSpawnLocations;
        this.cursorBombLocations = cursorBombLocations;
    }

    //Unsure whether to separate this into a separate task
    public void loadWaveEntityInfo(Random entityRandom, SpawnEntity[] entitySpawnTasks, Point[] entitySpawnLocations) {
        this.entityRandom = entityRandom;
        this.entitySpawnTasks = entitySpawnTasks;
        this.entitySpawnLocation = entitySpawnLocations;
    }

    private void spawnEntity() {
        int randomSpawnIndex = entityRandom.nextInt(entitySpawnLocation.length);
        Point randomSpawn = entitySpawnLocation[randomSpawnIndex];
        entityQueue.add(new GreenDeath(randomSpawn.x, randomSpawn.y, new GreenDeathConstants()));
        wavePoints-=entityValue;
    }

    private void spawnNewEvent() {
    }



    @Override
    public void runTask() {

    }

    @Override
    public void checkComplete() {

    }
}
