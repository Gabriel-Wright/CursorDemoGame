package tasks.gameWaves.waveManagement;

import tasks.TaskRunner;
import tasks.gameWaves.spawnConstants.SpawnEntityConstants;
import tasks.gameWaves.spawnTasks.SpawnEntity;

import java.awt.*;
import java.util.Random;

public class WaveEntityManager {

    private SpawnEntityConstants spawnEntityConstants;
    private Random entityRandom;
    private int[] entityIndexes;
    private Point[] entitySpawnLocations;

    private int pointsBuffer;
    private int eventTickBuffer;
    private int entityTickBuffer;

    public WaveEntityManager(SpawnEntityConstants spawnEntityConstants, Random entityRandom) {
        this.spawnEntityConstants = spawnEntityConstants;
        this.entityRandom = entityRandom;
        this.entityIndexes = spawnEntityConstants.getSavedEntityIndexes();
        this.entitySpawnLocations = spawnEntityConstants.getLoadedEntitySpawnPositions();
    }

    public void spawnNewEntity() {
        Point entitySpawnPosition = getRandomEntityLocation();
        int entityIndex = getRandomEntityIndex();
        SpawnEntity entitySpawnTask = spawnEntityConstants.findSpawnEntity(entityIndex);
        entitySpawnTask.initialiseEntitySpawn(entitySpawnPosition);
        pointsBuffer = entitySpawnTask.getTaskValue();
        entityTickBuffer = entitySpawnTask.getEntitySpawnTickBuffer();
        eventTickBuffer = entitySpawnTask.getEventSpawnTickBuffer();
        TaskRunner.addTask(entitySpawnTask);
    }

    //This should weigh up how much tasks are worth to decide which ones to go for
    private int getRandomEntityIndex() {
        return entityIndexes[entityRandom.nextInt(entityIndexes.length)];
    }

    private Point getRandomEntityLocation() {
        return entitySpawnLocations[entityRandom.nextInt(entitySpawnLocations.length)];
    }

    public int getPointsBuffer() {
        return pointsBuffer;
    }

    public int getEntityTickBuffer() {
        return entityTickBuffer;
    }

    public int getEventTickBuffer() {
        return eventTickBuffer;
    }
}
