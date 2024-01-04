package tasks.gameWaves.spawnTasks;

import gameObjects.events.generic.PositionalEvent;
import tasks.gameWaves.spawnConstants.PositionalEventSpawnInfo;

import static gameObjects.handler.GameObjectHandler.eventQueue;

public class SpawnPositionEvent extends SpawnTask{
    private PositionalEvent positionalEvent;

    public SpawnPositionEvent(int taskValue, int completeCheck, PositionalEvent positionalEvent) {
        super(taskValue,completeCheck);
        this.positionalEvent = positionalEvent;
    }

    public void initialiseEventSpawn() {
        eventQueue.add(positionalEvent);
    }
    @Override
    protected boolean checkSpawnPurposeComplete() {
        return positionalEvent.isComplete();
    }

    @Override
    public void runTask() {
        System.out.println("Event task triggered");
    }
}
