package tasks;

public abstract class Task {

    private int tick = 0;
    //Max number of ticks to complete task
    private int maxTick;

    public Task(int maxTick){
        this.maxTick = maxTick;
    }

    //Runs tasks in background - incrementing ticks. If tick = maxTick then task complete
    public abstract void runTask();

    public void updateTask() {
        runTask();
        tick++;
    }
    public int getTick() {
        return tick;
    }

    public int getMaxTick() {
        return maxTick;
    }
}
