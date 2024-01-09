package tasks.gameWaves.spawnTasks;

import tasks.Task;

public abstract class SpawnTask extends Task {

    private int taskValue;
    private int completionCheckTick;
    private int entitySpawnTickBuffer;
    private int eventSpawnTickBuffer;
    public SpawnTask(int taskValue, int entitySpawnTickBuffer, int eventSpawnTickBuffer, int completeCheck) {
        this.taskValue = taskValue;
        this.entitySpawnTickBuffer = entitySpawnTickBuffer;
        this.eventSpawnTickBuffer = eventSpawnTickBuffer;
        this.completionCheckTick = completeCheck;
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

    @Override
    public void checkComplete() {
        if(tick%completionCheckTick==0 && checkSpawnPurposeComplete()) {
            complete = true;
        }
    }



    protected abstract boolean checkSpawnPurposeComplete();
}
