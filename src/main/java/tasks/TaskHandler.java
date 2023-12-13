package tasks;

import gameObjects.entities.Entity;

import java.util.*;

//Stores continuous background tasks that have to be updated e.g. updateBackground
public class TaskHandler {
    private static List<Task> activeTasks;
    public static Queue<Task> queuedTasks;

    public TaskHandler() {
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