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
    private boolean skippable;
    public SpawnPositionEvents(int taskValue, int entitySpawnTickBuffer, int eventSpawnTickBuffer, ArrayList<PositionalEvent> positionalEvents, boolean skippable) {
        super(taskValue,entitySpawnTickBuffer, eventSpawnTickBuffer);
        this.positionalEvents = positionalEvents;
        this.skippable = skippable;
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
        activeEventFlags = loadEventFlags(positionalEvents.size());
        this.spawnIndexes = spawnIndexes;
        loadInitialEvents();
        System.out.println("Adding positionalEvents:" + positionalEvents.size());
        eventQueue.addAll((positionalEvents));
    }

    private void loadInitialEvents(){
        for(PositionalEvent positionalEvent: positionalEvents) {
            positionalEvent.initialEffects();
        }
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
//                eventRemoveQueue.add(positionalEvent);
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
        for(PositionalEvent positionalEvent: positionalEvents) {
            positionalEvent.reset();
        }
    }

    public Point getSpawnIndexes() {
        return spawnIndexes;
    }

    public boolean isSkippable() {
        return skippable;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SpawnPositionEvents{");
        sb.append(", positionalEvents=").append(positionalEvents);
        sb.append(", activeEventFlags=").append(activeEventFlags);
        sb.append(", spawnIndexes=").append(spawnIndexes);
        sb.append('}');
        return sb.toString();
    }

}
