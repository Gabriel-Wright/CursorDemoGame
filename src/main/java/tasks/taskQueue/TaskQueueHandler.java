//package tasks.taskQueue;
//
//import gameObjects.events.generic.DecreaseChargeTrigger;
//import tasks.Task;
//import tasks.TaskRunner;
//
//import java.awt.*;
//import java.util.Map;
//import java.util.Random;
//
//import static gameObjects.handler.GameObjectHandler.eventQueue;
//import static main.GamePanel.TILE_SIZE;
//import static main.GamePanel.UPS;
//
////Ongoing task that passes other tasks to the TaskHandler
//public class TaskQueueHandler extends Task {
//
//    private SpawnTimedBombTask spawnTimedBombTask;
//    private DecreaseChargeTrigger decreaseChargeTrigger;
//    private int[] randomEventSpawnFrequency;
//    private int randomArrayIndex = 0;
//    private Random taskRandom;
//    private Random entityRandom;
//    private Random objectRandom;
//    private Random eventRandom;
//    private Point[] entitySpawnLocation;
//    private Map<Integer, TaskQueueConstants.PositionalEventSpawnInfo> decreaseSpawnLocations;
//    private TaskQueueConstants.PositionalEventSpawnInfo[] cursorBombLocations;
//    public TaskQueueHandler(Random random) {
//        this.taskRandom = random;
//    }
//
//    public TaskQueueHandler(Random entityRandom, Random objectRandom, Random eventRandom, Point[] entitySpawnLocations,
//                            Map<Integer, TaskQueueConstants.PositionalEventSpawnInfo> decreaseSpawnLocations,
//                            TaskQueueConstants.PositionalEventSpawnInfo[] cursorBombLocations) {
//        this.entityRandom = entityRandom;
//        this.objectRandom = objectRandom;
//        this.eventRandom = eventRandom;
//        this.entitySpawnLocation = entitySpawnLocations;
//        this.decreaseSpawnLocations = decreaseSpawnLocations;
//        this.cursorBombLocations = cursorBombLocations;
//    }
//
//    public void initialiseTasks() {
//        randomiseEventFrequency();
//        TaskRunner.addTask(this);
//    }
//
//    private void randomiseEventFrequency() {
//        randomEventSpawnFrequency = new int[10];
//        // Fill the array with random values between 0 and 3
//        for (int i = 0; i < 10; i++) {
//            // Use nextInt(4) to generate random integers in the range [0, 4)
//            randomEventSpawnFrequency[i] = taskRandom.nextInt(15,20);
//        }
//    }
//
//
//    @Override
//    public void runTask() {
//        if(tick%(UPS*randomEventSpawnFrequency[randomArrayIndex])==0 && tick!=0){
//            spawnNewTimedBomb();
//            spawnNewDecreaseCharger();
//            randomArrayIndex++;
//            if(randomArrayIndex==10) {
//                randomArrayIndex=0;
//            }
//            tick=0;
//        }
//    }
//
//    private void spawnNewTimedBomb() {
//        spawnTimedBombTask = new SpawnTimedBombTask(new Color[]{Color.ORANGE, Color.YELLOW}, taskRandom);
//        spawnTimedBombTask.initialiseTask();
//        TaskRunner.addTask(spawnTimedBombTask);
//    }
//
//    private void spawnNewDecreaseCharger() {
//        decreaseChargeTrigger = new DecreaseChargeTrigger(28*TILE_SIZE, 17*TILE_SIZE-TILE_SIZE, 5*TILE_SIZE, (4*TILE_SIZE-3*TILE_SIZE)/4);
//        eventQueue.add(decreaseChargeTrigger);
//        decreaseChargeTrigger = new DecreaseChargeTrigger(27*TILE_SIZE, 16*TILE_SIZE,3*TILE_SIZE, (4*TILE_SIZE-3*TILE_SIZE)/4);
//        eventQueue.add(decreaseChargeTrigger);
//
//    }
//
//    @Override
//    public void checkComplete() {
//
//    }
//}
