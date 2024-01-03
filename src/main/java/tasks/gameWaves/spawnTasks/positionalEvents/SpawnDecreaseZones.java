package tasks.gameWaves.spawnTasks.positionalEvents;

import gameObjects.events.generic.PositionalEvent;
import tasks.gameWaves.spawnTasks.SpawnPositionalEvent;

public class SpawnDecreaseZones extends SpawnPositionalEvent {
    public SpawnDecreaseZones(int taskValue, int completeCheck, PositionalEvent positionalEvent) {
        super(taskValue, completeCheck, positionalEvent);
    }

    @Override
    public void runTask() {

    }
}
