package tasks.gameWaves.spawnConstants;

import tasks.TaskQueueConstants;
import tasks.gameWaves.spawnTasks.SpawnPositionalEvent;

import java.util.HashMap;
import java.util.Map;

import static levels.LevelConstants.TEST_DEMICHROME;
import static main.GamePanel.TILE_SIZE;

public class SpawnPositionalEventConstants {

    private final int id;

    //Event flags
    public final static int CURSOR_TIMER = 0;
    public final static int PLAYER_TIMER = 1;
    public final static int RED_ZONE = 2;
    public final static int CHARGE_ZONE = 3;

    SpawnPositionalEventConstants(int id) {
        this.id = id;
    }

    public int[] getPositionalEventIndexes() {
        return switch(id) {
            case TEST_DEMICHROME -> new int[] {CURSOR_TIMER, PLAYER_TIMER, RED_ZONE, CHARGE_ZONE};
            default -> new int[]{CHARGE_ZONE};
        };
    }

    public int getEventWorth(int entityIndex) {
        return switch(entityIndex) {
            case CURSOR_TIMER -> 25;
            case PLAYER_TIMER -> 30;
            case RED_ZONE -> 8;
            case CHARGE_ZONE -> 10;
            default -> 10;
        };
    }

    public TaskQueueConstants.PositionalEventSpawnInfo[] getBoxSpawnLocations() {
        return switch (id) {
            case TEST_DEMICHROME -> new TaskQueueConstants.PositionalEventSpawnInfo[]{new TaskQueueConstants.PositionalEventSpawnInfo(TILE_SIZE * 14, TILE_SIZE * 17, TILE_SIZE * 2, TILE_SIZE * 2),
                    new TaskQueueConstants.PositionalEventSpawnInfo(TILE_SIZE * 28, TILE_SIZE * 11, TILE_SIZE * 2, TILE_SIZE * 2),
                    new TaskQueueConstants.PositionalEventSpawnInfo(TILE_SIZE * 26, TILE_SIZE * 9, TILE_SIZE * 2, TILE_SIZE)};
            default ->
                    new TaskQueueConstants.PositionalEventSpawnInfo[]{new TaskQueueConstants.PositionalEventSpawnInfo(25 * TILE_SIZE, 14 * TILE_SIZE, TILE_SIZE, TILE_SIZE)};
        };
    }

    public Map<Integer, TaskQueueConstants.PositionalEventSpawnInfo> getDecreaseZoneLocations() {
        return switch(id) {
            case TEST_DEMICHROME -> {
                Map<Integer, TaskQueueConstants.PositionalEventSpawnInfo> map = new HashMap<>();
                map.put(1, new TaskQueueConstants.PositionalEventSpawnInfo(25*TILE_SIZE, 14*TILE_SIZE, (TILE_SIZE*3), (4*TILE_SIZE-3*TILE_SIZE)/4));
                map.put(1, new TaskQueueConstants.PositionalEventSpawnInfo(25*TILE_SIZE, (56*TILE_SIZE+3*TILE_SIZE)/4,TILE_SIZE*3, 4*TILE_SIZE-3*(TILE_SIZE)/4));
                yield map;
            }
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }

//    //Returns positionalEvents for all index
//    public SpawnPositionalEvent[] getPositionalEvents(int positionalEventIndex) {
//
//    }
    public SpawnPositionalEvent[] getPositionalEventSpawnTasks(int[] positionalEventIndexes) {
        int numEvents = positionalEventIndexes.length;
        SpawnPositionalEvent[] positionalEventSpawnTasks = new SpawnPositionalEvent[numEvents];
        int i, eventIndex;
        for (i = 0; i < numEvents; i++) {
            eventIndex = positionalEventIndexes[i];
            //positionalEventSpawnTasks[i] =
        }
        return null;
    }
}
