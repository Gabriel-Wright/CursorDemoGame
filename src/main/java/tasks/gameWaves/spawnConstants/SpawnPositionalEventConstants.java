package tasks.gameWaves.spawnConstants;

import gameObjects.events.generic.DecreaseChargeTrigger;
import gameObjects.events.generic.PositionalEvent;
import tasks.gameWaves.spawnTasks.SpawnPositionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static levels.LevelConstants.TEST_DEMICHROME;
import static main.GamePanel.TILE_SIZE;
import static main.GamePanel.UPS;

;

public class SpawnPositionalEventConstants {

    //Event flags
    public final static int CURSOR_TIMER = 0;
    public final static int PLAYER_TIMER = 1;
    public final static int RED_ZONE = 2;
    public final static int CHARGE_ZONE = 3;

    private int[] eventIndexes;
    private Map<Integer,Map<Integer, ArrayList<PositionalEventSpawnInfo>>> eventSpawnPositions;

    /*
    * FINDERS - find all data stored for spawn events that are constants e.g. the corresponding events, what events are assigned to what level
    * the position of the events within each level
     */

    public int findEventWorth(int entityIndex) {
        return switch(entityIndex) {
            case CURSOR_TIMER -> 25;
            case PLAYER_TIMER -> 30;
            case RED_ZONE -> 8;
            case CHARGE_ZONE -> 10;
            default -> 10;
        };
    }


    //Returns the inner map that contains all possible spawn combinations for a given event
    private Map<Integer,ArrayList<PositionalEventSpawnInfo>> findEventSpawnPostionMaps(int index, int id) {
        return switch(index) {
            case RED_ZONE -> findDecreaseZoneLocations(id);
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }

    //
    private PositionalEventSpawnInfo[] findBoxSpawnLocations(int id) {
        return switch (id) {
            case TEST_DEMICHROME -> new PositionalEventSpawnInfo[]{new PositionalEventSpawnInfo(TILE_SIZE * 14, TILE_SIZE * 17, TILE_SIZE * 2, TILE_SIZE * 2),
                    new PositionalEventSpawnInfo(TILE_SIZE * 28, TILE_SIZE * 11, TILE_SIZE * 2, TILE_SIZE * 2),
                    new PositionalEventSpawnInfo(TILE_SIZE * 26, TILE_SIZE * 9, TILE_SIZE * 2, TILE_SIZE)};
            default ->
                    new PositionalEventSpawnInfo[]{new PositionalEventSpawnInfo(25 * TILE_SIZE, 14 * TILE_SIZE, TILE_SIZE, TILE_SIZE)};
        };
    }

    private Map<Integer, ArrayList<PositionalEventSpawnInfo>> findDecreaseZoneLocations(int id) {
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

    private int findPositionalEventCompleteCheck(int positionalEventIndex) {
        return switch(positionalEventIndex){
            case RED_ZONE -> 10;
            default -> 0;
        };
    }

    public SpawnPositionEvent findPositionalEventSpawnTask(int positionalEventIndex, PositionalEventSpawnInfo positionalEventSpawnInfo) {
        int eventWorth = findEventWorth(positionalEventIndex);
        int eventCompleteCheck = findPositionalEventCompleteCheck(positionalEventIndex);
        return switch(positionalEventIndex) {
            case RED_ZONE -> new SpawnPositionEvent(eventWorth, eventCompleteCheck, findEntitySpawnTickBuffer(positionalEventIndex), findEventSpawnTickBuffer(positionalEventIndex), findPositionalEvent(positionalEventIndex, positionalEventSpawnInfo)) {
            };
            default -> null;
        };
    }

    private PositionalEvent findPositionalEvent(int positionalEventIndex, PositionalEventSpawnInfo positionalEventSpawnInfo) {
        return switch(positionalEventIndex){
            case RED_ZONE -> new DecreaseChargeTrigger(positionalEventSpawnInfo);
            default -> throw new IllegalStateException("Unexpected value: " + positionalEventIndex);
        };
    }

    private int[] findPositionalEventIndexes(int id) {
        return switch(id) {
            case TEST_DEMICHROME -> new int[] {RED_ZONE};
            default -> new int[]{CHARGE_ZONE};
        };
    }

    public int findEntitySpawnTickBuffer(int eventIndex) {
        return switch(eventIndex){
            case RED_ZONE -> UPS/2;
            default -> 0;
        };
    }

    public int findEventSpawnTickBuffer(int eventIndex) {
        return switch(eventIndex) {
            case RED_ZONE -> UPS;
            default ->UPS;
        };
    }

    /*
    *  LOADERS - load possible event indexes that can spawn in the level, and the map of all positions for those levels
     */

    //Map<Integer,Map<Integer, PositionalEventSpawnInfo>

    //Map which is indexed by the event Index - then refers to all possible spawn positions
    public void loadEventSpawnPositions(int id) {
        eventSpawnPositions = new HashMap<>();
        for (int eventIndex : eventIndexes) {
            eventSpawnPositions.put(eventIndex, findEventSpawnPostionMaps(eventIndex, id));
        }
    }

    public void loadEventIndexes(int id) {
        eventIndexes = findPositionalEventIndexes(id);
    }

    /*
    * GETTERS - Return data stored about events that is saved within this constant class
     */
    public int[] getEventIndexes() {
        return eventIndexes;
    }


    public Map<Integer, Map<Integer, ArrayList<PositionalEventSpawnInfo>>> getEventSpawnPositions() {
        return eventSpawnPositions;
    }

}
