package tasks.gameWaves.spawnTasks;

import tasks.Task;

public abstract class SpawnTask extends Task {

    private int taskValue;
    private int completionCheckTick;
    private int spawnTickBuffer;
    public SpawnTask(int taskValue, int completeCheck) {
        this.taskValue = taskValue;
        this.completionCheckTick = completeCheck;
    }

    public int getTaskValue() {
        return taskValue;
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
