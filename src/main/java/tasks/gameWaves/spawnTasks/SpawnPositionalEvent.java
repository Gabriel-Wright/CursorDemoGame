package tasks.gameWaves.spawnTasks;

import gameObjects.events.generic.PositionalEvent;
import tasks.gameWaves.spawnConstants.PositionalEventSpawnInfo;

import static gameObjects.handler.GameObjectHandler.eventQueue;
import static main.GamePanel.UPS;

public abstract class SpawnPositionalEvent extends SpawnTask{
    private PositionalEvent positionalEvent;

    public SpawnPositionalEvent(int taskValue, int completeCheck, PositionalEvent positionalEvent) {
        super(taskValue,completeCheck);
        this.positionalEvent = positionalEvent;
    }

    public void initialisePositionalEventSpawn(PositionalEventSpawnInfo positionalEventSpawnInfo) {
        positionalEvent.setPositionalEventSpawnInfo(positionalEventSpawnInfo);
        eventQueue.add(positionalEvent);
    }


    @Override
    protected boolean checkSpawnPurposeComplete() {
        return positionalEvent.isComplete();
    }
}
