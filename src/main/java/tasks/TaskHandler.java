package tasks;

import levels.LevelManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Stores continuous background tasks that have to be updated e.g. updateBackground
public class TaskHandler {
    public static List<Task> activeTasks;
    private LevelManager levelManager;

    public TaskHandler() {
        activeTasks = new ArrayList<>();
    }

    public static void addTask(Task task) {
        activeTasks.add(task);
    }

    public void updateTasks() {
        Iterator<Task> taskIterator = activeTasks.iterator();
        while (taskIterator.hasNext()) {
            Task task = taskIterator.next();
            task.updateTask();
            if (task.getTick() == task.getMaxTick()) {
                // Task has reached maxTicks, remove it
                taskIterator.remove();
            }
        }
    }
}
