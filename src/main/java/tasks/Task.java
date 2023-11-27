package tasks;

public abstract class Task {

    protected int tick = 0;
    //Max number of ticks to complete task
//    private int maxTick;

    //Flag for if task complete
    protected boolean complete = false;

    //Runs tasks in background - incrementing ticks. If tick = maxTick then task complete

    public abstract void runTask();

    public abstract void checkComplete();

    public void updateTask() {
        runTask();
        tick++;
        checkComplete();
    }

    public void setComplete() {
        complete = true;
    }
}
