package tasks.gameWaves.spawnConstants;

import gameObjects.events.generic.DecreaseChargeTrigger;
import tasks.gameWaves.spawnTasks.SpawnPositionalEvent;
import tasks.gameWaves.spawnTasks.positionalEvents.SpawnDecreaseZones;

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
            case TEST_DEMICHROME -> new int[] {CURSOR_TIMER, PLAYER_TIMER, RED_ZONE, CHARGE_ZONE};
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
                tempList1.add(new PositionalEventSpawnInfo(25*TILE_SIZE, 14*TILE_SIZE, (TILE_SIZE*3), (4*TILE_SIZE-3*TILE_SIZE)/4));
                tempList1.add(new PositionalEventSpawnInfo(25*TILE_SIZE, (56*TILE_SIZE+3*TILE_SIZE)/4,TILE_SIZE*3, 4*TILE_SIZE-3*(TILE_SIZE)/4));
                map.put(1, tempList1);
                yield map;
            }
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }

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

    public int getPositionalEventCompleteCheck(int positionalEventIndex) {
        return switch(positionalEventIndex){
            default -> 0;
        };
    }

    public SpawnPositionalEvent getPositionalEvent(int positionalEventIndex) {
        int eventWorth = getEventWorth(positionalEventIndex);
        int eventCompleteCheck = getPositionalEventCompleteCheck(positionalEventIndex);
        return switch(positionalEventIndex) {
            case RED_ZONE -> new SpawnDecreaseZones(eventWorth, eventCompleteCheck, new DecreaseChargeTrigger());
            default -> null;
        };
    }

    public SpawnPositionalEvent getPositionalEvent(int positionalEventIndex, ArrayList<PositionalEventSpawnInfo> positionalEventSpawnInfoList) {
//        return switch(positionalEventIndex){
//            case RED_ZONE -> return new
//        }
        return null;
    }
}
