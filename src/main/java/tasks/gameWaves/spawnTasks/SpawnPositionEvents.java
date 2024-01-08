package tasks.gameWaves.spawnTasks;

import gameObjects.events.generic.PositionalEvent;

import java.util.ArrayList;

import static gameObjects.handler.GameObjectHandler.eventQueue;
import static gameObjects.handler.GameObjectHandler.eventRemoveQueue;

public class SpawnPositionEvents extends SpawnTask{
    private ArrayList<PositionalEvent> positionalEvents;

    public SpawnPositionEvents(int taskValue, int entitySpawnTickBuffer, int eventSpawnTickBuffer, int completeCheck, ArrayList<PositionalEvent> positionalEvents) {
        super(taskValue,entitySpawnTickBuffer, eventSpawnTickBuffer, completeCheck);
        this.positionalEvents = positionalEvents;
    }

    public void initialiseEventSpawn() {
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

    public void reset() {
        setComplete();
        for(PositionalEvent positionalEvent: positionalEvents) {
            eventRemoveQueue.add(positionalEvent);
        }
    }

}
