package tasks.gameWaves;

import gameObjects.entities.enemies.GreenDeath.GreenDeath;
import gameObjects.entities.enemies.GreenDeath.GreenDeathConstants;
import tasks.Task;
import tasks.TaskRunner;
import tasks.gameWaves.spawnConstants.PositionalEventSpawnInfo;
import tasks.gameWaves.spawnConstants.SpawnConstants;
import tasks.gameWaves.spawnTasks.SpawnEntity;
import tasks.gameWaves.spawnTasks.SpawnPositionalEvent;
import tasks.gameWaves.spawnTasks.SpawnTask;

import java.awt.*;
import java.util.*;

import static gameObjects.handler.GameObjectHandler.entityQueue;
import static main.GamePanel.UPS;

public class WaveManager extends Task {

    //Wave round value and random seed value
    private int waveRound;
    private int seed;

    //SpawnConstants - calculates all necessary spawnTasks for current level
    SpawnConstants spawnConstants;


    private int wavePoints=100;
    private boolean roundEnded=false;
    private final int entityValue = 8;
    private final int objectValue = 2;
    private final int bufferValue = 5;
    private final int eventValue = 25;

    //Entity spawn Randomiser, location and points
//    private SpawnEntity[] entitySpawnTasks;
    private int[] entityIndexes;
    private Point[] entitySpawnLocation;

    private int[] eventIndexes;
    private Map<Integer, Map<Integer, ArrayList<PositionalEventSpawnInfo>>> eventSpawnPositions;

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
        eventIndexes = spawnConstants.getSpawnPositionalEventConstants().getEventIndexes();
        eventSpawnPositions = spawnConstants.getSpawnPositionalEventConstants().getEventSpawnPositions();
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
        if(tick==nextSpawnTick&&!roundEnded) {
            spawnNewTask();
        }
        checkRoundEnd();
    }

    private void checkRoundEnd() {
        if(wavePoints==0&!roundEnded) {
            System.out.println("ROUND ENDED");
            roundEnded = true;
        }
    }

    private void spawnNewTask() {
        Random random = new Random();
        if(random.nextInt(2)==0) {
            spawnNewEntity();
            return;
        }
        spawnNewEvent();

    }

    private void spawnNewEvent() {
        int eventIndex = getRandomEventIndex();
        ArrayList<PositionalEventSpawnInfo> eventSpawns = getRandomEventSpawnPositions(eventIndex);

    }

    private ArrayList<PositionalEventSpawnInfo> getRandomEventSpawnPositions(int eventIndex) {
        ArrayList<PositionalEventSpawnInfo> eventSpawns = new ArrayList<>();
        Map<Integer, ArrayList<PositionalEventSpawnInfo>> positionalEventSpawnInfoMap = eventSpawnPositions.get(eventIndex);
        return getRandomMapSpawnList(positionalEventSpawnInfoMap);
    }

    private ArrayList<PositionalEventSpawnInfo> getRandomMapSpawnList(Map<Integer, ArrayList<PositionalEventSpawnInfo>> positionalEventSpawnInfoMap) {
        //Should probably leave as set or unsure?
        ArrayList<Integer> spawnPositionKeys = (ArrayList<Integer>) positionalEventSpawnInfoMap.keySet();
        int numSpawnCombinations = spawnPositionKeys.size();
        //randomly choose an index of the map
        int spawnPositionsIndex = spawnPositionKeys.get(eventRandom.nextInt(numSpawnCombinations));
        return positionalEventSpawnInfoMap.get(spawnPositionsIndex);
    }

    private int getRandomEventIndex() {
        return eventIndexes[eventRandom.nextInt(eventIndexes.length)];
    }

    private void spawnNewEntity() {
        Point entitySpawnPosition = getRandomEntityLocation();
        int entityIndex = getRandomEntityIndex();
        SpawnEntity entitySpawnTask = spawnConstants.getSpawnEntityConstants().getSpawnEntity(entityIndex);
        entitySpawnTask.initialiseEntitySpawn(entitySpawnPosition);
        updatePoints(entitySpawnTask);
        updateSpawnTick(entitySpawnTask);
        TaskRunner.addTask(entitySpawnTask);
    }

    //This should weigh up how much tasks are worth to decide which ones to go for
    private int getRandomEntityIndex() {
        return entityIndexes[entityRandom.nextInt(entityIndexes.length)];
    }

    private Point getRandomEntityLocation() {
        return entitySpawnLocation[entityRandom.nextInt(entitySpawnLocation.length)];
    }

//    private SpawnEntity getRandomEntitySpawn() {
//        return entitySpawnTasks[entityRandom.nextInt(entitySpawnTasks.length)];
//    }


    private void updateSpawnTick(SpawnEntity spawnTask) {
        nextSpawnTick = UPS*3;
        tick=0;
    }

    private void updatePoints(SpawnTask spawnTask) {
        wavePoints -= spawnTask.getTaskValue();
        System.out.println("Points remaining:"+wavePoints);
    }
    @Override
    public void checkComplete() {

    }
}
