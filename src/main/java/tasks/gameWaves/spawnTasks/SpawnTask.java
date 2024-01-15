package tasks.gameWaves.spawnTasks;

import tasks.Task;

public abstract class SpawnTask extends Task {

    private int taskValue;
    private int entitySpawnTickBuffer;
    private int eventSpawnTickBuffer;
    public SpawnTask(int taskValue, int entitySpawnTickBuffer, int eventSpawnTickBuffer) {
        this.taskValue = taskValue;
        this.entitySpawnTickBuffer = entitySpawnTickBuffer;
        this.eventSpawnTickBuffer = eventSpawnTickBuffer;
    }

    public int getTaskValue() {
        return taskValue;
    }

    public int getEntitySpawnTickBuffer() {
        return entitySpawnTickBuffer;
    }

    public int getEventSpawnTickBuffer() {
        return eventSpawnTickBuffer;
    }

    public void setTaskValue(int taskValue) {
        this.taskValue = taskValue;
    }



    protected abstract boolean checkSpawnPurposeComplete();
}
