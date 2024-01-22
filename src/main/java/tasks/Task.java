package tasks;

public abstract class Task {

    protected int tick = 0;
    //Max number of ticks to complete task

    //Flag for if task complete
    protected boolean complete = false;

    public abstract void runTask();


    public void updateTask() {
        if(!complete) {
            runTask();
            tick++;
        }
    }

    public void setComplete() {
        complete = true;
    }

    public boolean isComplete() {
        return complete;
    }

}
