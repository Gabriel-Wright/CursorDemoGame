package tasks;

import java.util.Random;

public class TaskManager {

    private TaskQueueConstants taskQueueConstants;
    private int levelRef;
    public int entitySpawnSeed = 0;
    public int objectSpawnSeed = 0;
    public int eventSpawnSeed = 0;
    private Random entityRandom;
    private Random objectRandom;
    private Random eventRandom;
    public TaskManager(int id, int entitySpawnSeed, int objectSpawnSeed, int eventSpawnSeed) {
        this.levelRef = id;
        this.entitySpawnSeed = entitySpawnSeed;
        this. objectSpawnSeed = objectSpawnSeed;
        this. eventSpawnSeed = eventSpawnSeed;
        taskQueueConstants = new TaskQueueConstants(levelRef);
    }

    private void loadRandomGenerators() {
        entityRandom = new Random(entitySpawnSeed);
        objectRandom = new Random(objectSpawnSeed);
        eventRandom = new Random(eventSpawnSeed);
    }

    private void loadTaskQueueHandler() {

    }
}
