package tasks.gameWaves.waveManagement;

import tasks.TaskRunner;
import tasks.gameWaves.spawnConstants.SpawnEntityConstants;
import tasks.gameWaves.spawnTasks.SpawnEntity;

import java.awt.*;
import java.util.Random;

import static tasks.gameWaves.waveManagement.WaveManager.updatePoints;

public class WaveEntityManager {

    private SpawnEntityConstants spawnEntityConstants;
    private Random entityRandom;
    private int[] entityIndexes;
    private Point[] entitySpawnLocations;

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
        updatePoints(entitySpawnTask);
//        updateSpawnTick(entitySpawnTask);
        TaskRunner.addTask(entitySpawnTask);
    }

    //This should weigh up how much tasks are worth to decide which ones to go for
    private int getRandomEntityIndex() {
        return entityIndexes[entityRandom.nextInt(entityIndexes.length)];
    }

    private Point getRandomEntityLocation() {
        return entitySpawnLocations[entityRandom.nextInt(entitySpawnLocations.length)];
    }

}
