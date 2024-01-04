package tasks.gameWaves.waveManagement;

import tasks.TaskRunner;
import tasks.gameWaves.spawnConstants.SpawnEntityConstants;
import tasks.gameWaves.spawnTasks.SpawnEntity;

import java.awt.*;
import java.util.Random;

public class WaveEntityManager extends WaveSpawnManager{

    private SpawnEntityConstants spawnEntityConstants;
    private int[] entityIndexes;
    private Point[] entitySpawnLocations;

    public WaveEntityManager(SpawnEntityConstants spawnEntityConstants, Random entityRandom) {
        super(entityRandom);
        this.spawnEntityConstants = spawnEntityConstants;
        this.entityIndexes = spawnEntityConstants.getSavedEntityIndexes();
        this.entitySpawnLocations = spawnEntityConstants.getLoadedEntitySpawnPositions();
    }

    public void spawnNew() {
        Point entitySpawnPosition = getRandomEntityLocation();
        int entityIndex = getRandomEntityIndex();
        SpawnEntity entitySpawnTask = spawnEntityConstants.findSpawnEntity(entityIndex);
        entitySpawnTask.initialiseEntitySpawn(entitySpawnPosition);
        setPointsBuffer(entitySpawnTask.getTaskValue());
        setEntityTickBuffer(entitySpawnTask.getEntitySpawnTickBuffer());
        setEventTickBuffer(entitySpawnTask.getEventSpawnTickBuffer());
        TaskRunner.addTask(entitySpawnTask);
    }

    //This should weigh up how much tasks are worth to decide which ones to go for
    private int getRandomEntityIndex() {
        return entityIndexes[random.nextInt(entityIndexes.length)];
    }

    private Point getRandomEntityLocation() {
        return entitySpawnLocations[random.nextInt(entitySpawnLocations.length)];
    }
}
