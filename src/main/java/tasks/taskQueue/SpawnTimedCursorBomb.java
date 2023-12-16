package tasks.taskQueue;

import gameObjects.events.generic.CursorTimedBomb;
import tasks.Task;
import tasks.TaskQueueConstants;

import java.awt.*;

import static gameObjects.handler.GameObjectHandler.eventQueue;

public class SpawnTimedCursorBomb extends Task {

    private CursorTimedBomb cursorTimedBomb;
    private Color[] transitionColors;
    private TaskQueueConstants.PositionalEventSpawnInfo positionalEventSpawnInfo;
    private int explosionTick;
    public SpawnTimedCursorBomb(int explosionTick, Color[] transitionColors, TaskQueueConstants.PositionalEventSpawnInfo positionalEventSpawnInfo) {
        this.explosionTick = explosionTick;
        this.transitionColors = transitionColors;
        this.positionalEventSpawnInfo = positionalEventSpawnInfo;
    }

    public void loadCursorTimedBomb() {
        cursorTimedBomb = new CursorTimedBomb(transitionColors, positionalEventSpawnInfo.x(), positionalEventSpawnInfo.y(), positionalEventSpawnInfo.width(), positionalEventSpawnInfo.height(), this);
        eventQueue.add(cursorTimedBomb);
    }

    @Override
    public void runTask() {
        if(tick>explosionTick) {
            System.out.println("Game Over");
            complete = true;
        }
    }

    @Override
    public void checkComplete() {

    }
}
