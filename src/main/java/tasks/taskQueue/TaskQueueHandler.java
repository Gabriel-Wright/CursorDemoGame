package tasks.taskQueue;

import tasks.Task;
import tasks.TaskHandler;

import java.awt.*;
import java.util.Random;

import static main.GamePanel.TILE_SIZE;
import static main.GamePanel.UPS;

//Ongoing task that passes other tasks to the TaskHandler
public class TaskQueueHandler extends Task {

    private SpawnTimedBombTask spawnTimedBombTask;
    private int[] randomEventSpawnFrequency;
    private int randomArrayIndex = 0;
    private Random taskRandom;

    public TaskQueueHandler(Random random) {
        this.taskRandom = random;
    }

    public void initialiseTasks() {
        randomiseEventFrequency();
        TaskHandler.addTask(this);
    }

    private void randomiseEventFrequency() {
        randomEventSpawnFrequency = new int[10];
        // Fill the array with random values between 0 and 3
        for (int i = 0; i < 10; i++) {
            // Use nextInt(4) to generate random integers in the range [0, 4)
            randomEventSpawnFrequency[i] = taskRandom.nextInt(5,10);
        }
    }


    @Override
    public void runTask() {
        if(tick%(UPS*randomEventSpawnFrequency[randomArrayIndex])==0 && tick!=0){
            spawnNewTimedBomb();
            randomArrayIndex++;
            if(randomArrayIndex==10) {
                randomArrayIndex=0;
            }
        }
    }

    private void spawnNewTimedBomb() {
        spawnTimedBombTask = new SpawnTimedBombTask(new Color[]{Color.RED, Color.YELLOW}, taskRandom);
        spawnTimedBombTask.initialiseTask();
        TaskHandler.addTask(spawnTimedBombTask);
    }

    @Override
    public void checkComplete() {

    }
}
