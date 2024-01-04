package tasks.gameWaves.spawnConstants;

import gameObjects.events.generic.DecreaseChargeTrigger;
import gameObjects.events.generic.PositionalEvent;
import tasks.gameWaves.spawnTasks.SpawnPositionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static levels.LevelConstants.TEST_DEMICHROME;
import static main.GamePanel.TILE_SIZE;

;

public class SpawnPositionalEventConstants {

    //Event flags
    public final static int CURSOR_TIMER = 0;
    public final static int PLAYER_TIMER = 1;
    public final static int RED_ZONE = 2;
    public final static int CHARGE_ZONE = 3;

    private int[] eventIndexes;
    private Map<Integer,Map<Integer, ArrayList<PositionalEventSpawnInfo>>> eventSpawnPositions;

    public void loadEventIndexes(int id) {
        eventIndexes = getPositionalEventIndexes(id);
    }

    public int[] getEventIndexes() {
        return eventIndexes;
    }

    private int[] getPositionalEventIndexes(int id) {
        return switch(id) {
            case TEST_DEMICHROME -> new int[] {RED_ZONE};
            default -> new int[]{CHARGE_ZONE};
        };
    }

    public Map<Integer, Map<Integer, ArrayList<PositionalEventSpawnInfo>>> getEventSpawnPositions() {
        return eventSpawnPositions;
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

    //Map<Integer,Map<Integer, PositionalEventSpawnInfo>

    //Map which is indexed by the event Index - then refers to all possible spawn positions
    public void loadEventSpawnPositions(int id) {
        eventSpawnPositions = new HashMap<>();
        for (int eventIndex : eventIndexes) {
            eventSpawnPositions.put(eventIndex,getEventSpawnPostionMaps(eventIndex, id));
        }
    }

    private Map<Integer,ArrayList<PositionalEventSpawnInfo>> getEventSpawnPostionMaps(int index, int id) {
        return switch(index) {
            case RED_ZONE -> getDecreaseZoneLocations(id);
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }

    public PositionalEventSpawnInfo[] getBoxSpawnLocations(int id) {
        return switch (id) {
            case TEST_DEMICHROME -> new PositionalEventSpawnInfo[]{new PositionalEventSpawnInfo(TILE_SIZE * 14, TILE_SIZE * 17, TILE_SIZE * 2, TILE_SIZE * 2),
                    new PositionalEventSpawnInfo(TILE_SIZE * 28, TILE_SIZE * 11, TILE_SIZE * 2, TILE_SIZE * 2),
                    new PositionalEventSpawnInfo(TILE_SIZE * 26, TILE_SIZE * 9, TILE_SIZE * 2, TILE_SIZE)};
            default ->
                    new PositionalEventSpawnInfo[]{new PositionalEventSpawnInfo(25 * TILE_SIZE, 14 * TILE_SIZE, TILE_SIZE, TILE_SIZE)};
        };
    }

    public Map<Integer, ArrayList<PositionalEventSpawnInfo>> getDecreaseZoneLocations(int id) {
        return switch(id) {
            case TEST_DEMICHROME -> {
                Map<Integer, ArrayList<PositionalEventSpawnInfo>> map = new HashMap<>();
                ArrayList<PositionalEventSpawnInfo> tempList1 = new ArrayList<>();
                //First combo of positions
                tempList1.add(new PositionalEventSpawnInfo(25*TILE_SIZE, 14*TILE_SIZE, (TILE_SIZE*3), TILE_SIZE));
                tempList1.add(new PositionalEventSpawnInfo(27*TILE_SIZE, 25*TILE_SIZE,TILE_SIZE*3, TILE_SIZE));
                map.put(1, tempList1);
                yield map;
            }
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }

    public int getPositionalEventCompleteCheck(int positionalEventIndex) {
        return switch(positionalEventIndex){
            case RED_ZONE -> 10;
            default -> 0;
        };
    }

    public SpawnPositionEvent getPositionalEventSpawnTask(int positionalEventIndex, PositionalEventSpawnInfo positionalEventSpawnInfo) {
        int eventWorth = getEventWorth(positionalEventIndex);
        int eventCompleteCheck = getPositionalEventCompleteCheck(positionalEventIndex);
        return switch(positionalEventIndex) {
            case RED_ZONE -> new SpawnPositionEvent(eventWorth, eventCompleteCheck, getPositionalEvent(positionalEventIndex, positionalEventSpawnInfo)) {
            };
            default -> null;
        };
    }

    private PositionalEvent getPositionalEvent(int positionalEventIndex, PositionalEventSpawnInfo positionalEventSpawnInfo) {
        return switch(positionalEventIndex){
            case RED_ZONE -> new DecreaseChargeTrigger(positionalEventSpawnInfo);
            default -> throw new IllegalStateException("Unexpected value: " + positionalEventIndex);
        };
    }
}
