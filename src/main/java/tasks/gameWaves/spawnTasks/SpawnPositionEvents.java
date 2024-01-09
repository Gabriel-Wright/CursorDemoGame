package tasks.gameWaves.spawnTasks;

import gameObjects.events.generic.PositionalEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import static gameObjects.handler.GameObjectHandler.eventQueue;
import static gameObjects.handler.GameObjectHandler.eventRemoveQueue;

public class SpawnPositionEvents extends SpawnTask{
    private ArrayList<PositionalEvent> positionalEvents;
    private ArrayList<Integer> activeEventFlags;
    private Point spawnIndexes;
    public SpawnPositionEvents(int taskValue, int entitySpawnTickBuffer, int eventSpawnTickBuffer, int completeCheck, ArrayList<PositionalEvent> positionalEvents) {
        super(taskValue,entitySpawnTickBuffer, eventSpawnTickBuffer, completeCheck);
        this.positionalEvents = positionalEvents;
        activeEventFlags = loadEventFlags(positionalEvents.size());
    }

    private ArrayList<Integer> loadEventFlags(int num) {
        ArrayList<Integer> intList = new ArrayList<>();
        for(int i=0; i<num; i++) {
            intList.add(i);
        }
        return intList;
    }

    public void initialiseEventSpawn(Point spawnIndexes) {
        complete = false;
        this.spawnIndexes = spawnIndexes;
        eventQueue.addAll((positionalEvents));
    }
    @Override
    protected boolean checkSpawnPurposeComplete() {
        return false;
    }

    @Override
    public void runTask() {
//        System.out.println("Event task triggered");
    }

    public void checkCompleteEvents() {
        Iterator<Integer> iterator = activeEventFlags.iterator();

        while (iterator.hasNext()) {
            PositionalEvent positionalEvent = positionalEvents.get(iterator.next());

            if (positionalEvent.isComplete()) {
                eventRemoveQueue.add(positionalEvent);
                iterator.remove();
            }
        }
        if(activeEventFlags.isEmpty()) {
            reset();
        }
    }
    public void reset() {
        setComplete();
        for(int eventIndex: activeEventFlags) {
            if(!positionalEvents.get(eventIndex).isComplete()) {
                eventRemoveQueue.add(positionalEvents.get(eventIndex));
            }
        }
    }

    public Point getSpawnIndexes() {
        return spawnIndexes;
    }
}
