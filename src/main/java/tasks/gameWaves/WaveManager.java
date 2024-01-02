package tasks.gameWaves;

import gameObjects.entities.enemies.GreenDeath.GreenDeath;
import gameObjects.entities.enemies.GreenDeath.GreenDeathConstants;
import tasks.Task;
import tasks.TaskQueueConstants;
import tasks.TaskRunner;
import tasks.gameWaves.spawnConstants.SpawnConstants;
import tasks.gameWaves.spawnTasks.SpawnEntity;
import tasks.gameWaves.spawnTasks.SpawnTask;

import java.awt.*;
import java.util.Map;
import java.util.Random;

import static gameObjects.handler.GameObjectHandler.entityQueue;
import static main.GamePanel.UPS;

public class WaveManager extends Task {

    //Wave round value and random seed value
    private int waveRound;
    private int seed;

    //SpawnConstants - calculates all necessary spawnTasks for current level
    SpawnConstants spawnConstants;


    private int wavePoints;
    private final int entityValue = 8;
    private final int objectValue = 2;
    private final int bufferValue = 5;
    private final int eventValue = 25;

    //Entity spawn Randomiser, location and points
//    private SpawnEntity[] entitySpawnTasks;
    private int[] entityIndexes;
    private Point[] entitySpawnLocation;

    private Random entityRandom;
    private Random objectRandom;
    private Random eventRandom;

    //2 seconds before the first event
    private int nextSpawnTick = UPS*2;


    public WaveManager(int waveRound, int seed) {
        this.waveRound = waveRound;
        this.seed = seed;
    }

    public void loadSpawnConstants(int id) {
        spawnConstants = new SpawnConstants();
        spawnConstants.loadSpawnConstants(id);
        loadEntitySpawnInfo();
    }

    private void loadEntitySpawnInfo() {
//        entitySpawnTasks = spawnConstants.getSpawnEntityConstants().getLoadedEntitySpawns();
        entityIndexes = spawnConstants.getSpawnEntityConstants().getSavedEntityIndexes();
        entitySpawnLocation = spawnConstants.getSpawnEntityConstants().getLoadedEntitySpawnPositions();
    }

    private void loadEventSpawnInfo() {

    }

    private void loadObjectSpawnInfo() {

    }

    public void loadRandomGenerators() {
        entityRandom = new Random(seed);
        eventRandom = new Random(seed);
        objectRandom = new Random(seed);
    }

    @Override
    public void runTask() {
        if(tick==nextSpawnTick) {
            spawnNewTask();
        }
    }

    private void spawnNewTask() {
        spawnNewEntity();
    }

    private void spawnNewEntity() {
        Point entitySpawnPosition = getRandomEntityLocation();
        int entityIndex = getRandomEntityIndex();
        SpawnEntity entitySpawnTask = spawnConstants.getSpawnEntityConstants().getSpawnEntity(entityIndex);
        entitySpawnTask.initialiseEntitySpawn(entitySpawnPosition);
        updatePoints(entitySpawnTask);
        TaskRunner.addTask(entitySpawnTask);
    }

    private int getRandomEntityIndex() {
        return entityIndexes[entityRandom.nextInt(entityIndexes.length)];
    }

    private Point getRandomEntityLocation() {
        return entitySpawnLocation[entityRandom.nextInt(entitySpawnLocation.length)];
    }

//    private SpawnEntity getRandomEntitySpawn() {
//        return entitySpawnTasks[entityRandom.nextInt(entitySpawnTasks.length)];
//    }

    private void updatePoints(SpawnEntity spawnEntity) {
            wavePoints -= spawnEntity.getTaskValue();
            tick=0;
            updateSpawnTick(spawnEntity.getTaskValue());
    }

    private void updateSpawnTick(int taskValue) {
        nextSpawnTick -= 10;
    }
    @Override
    public void checkComplete() {

    }
}
