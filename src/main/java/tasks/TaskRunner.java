package tasks;

import java.util.*;

//Stores continuous background tasks that have to be updated e.g. updateBackground
public class TaskRunner {
    private static List<Task> activeTasks;
    public static Queue<Task> queuedTasks;

    public TaskRunner() {
        activeTasks = new ArrayList<>();
        queuedTasks = new LinkedList<>();
    }

    public static void addTask(Task task) {
        queuedTasks.add(task);
    }

    private void pollTaskQueue() {
        while (!queuedTasks.isEmpty()) {
            Task task = queuedTasks.poll();
            activeTasks.add(task);
        }

    }

    private void runActiveTasks() {
        Iterator<Task> taskIterator = activeTasks.iterator();
        while (taskIterator.hasNext()) {
            Task task = taskIterator.next();
            task.updateTask();
            if (task.complete) {
                // If task has been completed - remove it
                taskIterator.remove();
            }
        }

    }

    public void updateTasks() {
        pollTaskQueue();
        runActiveTasks();
    }
}