package tasks.gameWaves.spawnConstants;

import gameObjects.events.generic.ChargingPort;
import gameObjects.events.generic.CursorTimedBomb;
import gameObjects.events.generic.DecreaseChargeTrigger;
import gameObjects.events.generic.PositionalEvent;
import tasks.gameWaves.spawnTasks.SpawnPositionEvent;
import tasks.gameWaves.spawnTasks.SpawnPositionEvents;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static levels.LevelConstants.TEST_DEMICHROME;
import static main.GamePanel.TILE_SIZE;
import static main.GamePanel.UPS;

;

public class SpawnPositionalEventConstants{

    //Event flags
    public final static int CURSOR_TIMER = 0;
    public final static int PLAYER_TIMER = 1;
    public final static int RED_ZONE = 2;
    public final static int CHARGE_ZONE = 3;

    private int[] eventIndexes;
//    private Map<Integer,Map<Integer, ArrayList<PositionalEventSpawnInfo>>> eventSpawnPositionsMap;
    //Outer index refers to the event indexes i.e. event flags
    //Inner index is just the different references for where PositionalEvents can spawn
//    private Map<Integer,Map<Integer, ArrayList<PositionalEvent>>> positionalEventMap;
    private Map<Integer, ArrayList<SpawnPositionEvents>> spawnEventsMap;
    private Map<Integer, Map<Integer, SpawnPositionEvents>> spawnPositionsEventsMap;
    /*
    * FINDERS - find all data stored for spawn events that are constants e.g. the corresponding events, what events are assigned to what level
    * the position of the events within each level
     */

    private int[] findPositionalEventIndexes(int id) {
        return switch(id) {
            case TEST_DEMICHROME -> new int[] {RED_ZONE, CHARGE_ZONE, CURSOR_TIMER};
            default -> new int[]{CHARGE_ZONE};
        };
    }

    //Finds the full positionalEventMap - used
    private Map<Integer, Map<Integer, ArrayList<PositionalEvent>>> findPositionalEventMap(int id) {
        Map<Integer, Map<Integer, ArrayList<PositionalEvent>>> positionalEventMap = new HashMap<>();
        for(int eventIndex: eventIndexes) {
            positionalEventMap.put(eventIndex, findPositionalEvents(eventIndex, id));
        }
        return positionalEventMap;
    }

    //Returns the inner map that contains all possible spawn combinations for a given event
    private Map<Integer,ArrayList<PositionalEventSpawnInfo>> findEventSpawnPostionMaps(int index, int id) {
        return switch(index) {
            case RED_ZONE -> findDecreaseZoneLocations(id);
            case CHARGE_ZONE -> findIncreaseZoneLocations(id);
            case CURSOR_TIMER -> findCursorTimerLocations(id);
            default -> throw new IllegalStateException("Unexpected value: " + index);
        };
    }

    public int findEventWorth(int entityIndex) {
        return switch(entityIndex) {
            case CURSOR_TIMER -> 25;
            case PLAYER_TIMER -> 30;
            case RED_ZONE -> 8;
            case CHARGE_ZONE -> 10;
            default -> 10;
        };
    }



    //
    private Map<Integer, ArrayList<PositionalEventSpawnInfo>> findCursorTimerLocations(int id) {
        return switch(id) {
            case TEST_DEMICHROME -> {
                Map<Integer, ArrayList<PositionalEventSpawnInfo>> map = new HashMap<>();
                ArrayList<PositionalEventSpawnInfo> tempList1 = new ArrayList<>();
                tempList1.add(new PositionalEventSpawnInfo(45*TILE_SIZE, 9*TILE_SIZE, 2*TILE_SIZE, 2*TILE_SIZE));
                map.put(1, tempList1);
                ArrayList<PositionalEventSpawnInfo> tempList2 = new ArrayList<>();
                tempList2.add(new PositionalEventSpawnInfo(26*TILE_SIZE, 9*TILE_SIZE, 2*TILE_SIZE, TILE_SIZE));
                map.put(2,tempList2);
                ArrayList<PositionalEventSpawnInfo> tempList3 = new ArrayList<>();
                tempList3.add(new PositionalEventSpawnInfo(7*TILE_SIZE, 23*TILE_SIZE, 2*TILE_SIZE, 3*TILE_SIZE));
                map.put(3,tempList3);
                yield map;
            }
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }

    private Map<Integer, ArrayList<PositionalEventSpawnInfo>> findDecreaseZoneLocations(int id) {
        return switch(id) {
            case TEST_DEMICHROME -> {
                Map<Integer, ArrayList<PositionalEventSpawnInfo>> map = new HashMap<>();
                ArrayList<PositionalEventSpawnInfo> tempList1 = new ArrayList<>();
                //First combo of positions
                tempList1.add(new PositionalEventSpawnInfo(6*TILE_SIZE+TILE_SIZE/2, 8*TILE_SIZE+TILE_SIZE/2,3*TILE_SIZE/4, TILE_SIZE*3));
                tempList1.add(new PositionalEventSpawnInfo(8*TILE_SIZE+3*TILE_SIZE/4, 8*TILE_SIZE+TILE_SIZE/2, 3*TILE_SIZE/4, TILE_SIZE*3));
                map.put(1, tempList1);
                ArrayList<PositionalEventSpawnInfo> tempList2 = new ArrayList<>();
                tempList2.add(new PositionalEventSpawnInfo(31*TILE_SIZE,11*TILE_SIZE + 4*TILE_SIZE/5, 10*TILE_SIZE,2*TILE_SIZE/5));
                tempList2.add(new PositionalEventSpawnInfo(32*TILE_SIZE, 10*TILE_SIZE + 4*TILE_SIZE/5, 4*TILE_SIZE, 2*TILE_SIZE/5));
                tempList2.add(new PositionalEventSpawnInfo(37*TILE_SIZE, 10*TILE_SIZE + 4*TILE_SIZE/5, TILE_SIZE*5, 2*TILE_SIZE/5));
                map.put(2,tempList2);
                yield map;
            }
            default -> throw new IllegalStateException("Unexpected value: " + id);
        };
    }

    private Map<Integer, ArrayList<PositionalEventSpawnInfo>> findIncreaseZoneLocations(int id) {
        return switch(id) {
            case TEST_DEMICHROME -> {
                Map<Integer, ArrayList<PositionalEventSpawnInfo>> map = new HashMap<>();
                ArrayList<PositionalEventSpawnInfo> tempList1 = new ArrayList<>();
                tempList1.add(new PositionalEventSpawnInfo(37*TILE_SIZE, 18*TILE_SIZE,2*TILE_SIZE,2*TILE_SIZE));
                map.put(1, tempList1);
                ArrayList<PositionalEventSpawnInfo> tempList2 = new ArrayList<>();
                tempList2.add(new PositionalEventSpawnInfo(14*TILE_SIZE,23*TILE_SIZE,2*TILE_SIZE,2*TILE_SIZE));
                map.put(2, tempList2);
                yield map;
            }
            default -> throw new IllegalStateException("Unexpected value:" + id);
        };
    }

        private int findPositionalEventCompleteCheck(int positionalEventIndex) {
        return switch(positionalEventIndex){
            case RED_ZONE, CHARGE_ZONE -> 10;
            case CURSOR_TIMER -> 1;
            default -> 0;
        };
    }

    //Finds positional events per eventIndex for the inner of the positionalEventMap
    private Map<Integer,ArrayList<PositionalEvent>> findPositionalEvents(int eventIndex, int id) {
        Map<Integer, ArrayList<PositionalEventSpawnInfo>> positionalSpawnInfoMap = findEventSpawnPostionMaps(eventIndex, id);
        Set<Integer> outerKeySet = positionalSpawnInfoMap.keySet();
        Map<Integer, ArrayList<PositionalEvent>> positionalEventsInnerMap = new HashMap<>();
        for (int key: outerKeySet) {
            ArrayList<PositionalEvent> innerMapPositionalEvents = new ArrayList<>();
            ArrayList<PositionalEventSpawnInfo> spawnInfoList = positionalSpawnInfoMap.get(key);
            for (PositionalEventSpawnInfo spawnInfo : spawnInfoList) {
                PositionalEvent tempPositionalEvent = findPositionalEvent(eventIndex, spawnInfo);
                innerMapPositionalEvents.add(tempPositionalEvent);
            }
            positionalEventsInnerMap.put(key, innerMapPositionalEvents);
        }
        return positionalEventsInnerMap;
    }


    public SpawnPositionEvent findPositionalEventSpawnTask(int positionalEventIndex, PositionalEventSpawnInfo positionalEventSpawnInfo) {
        int eventWorth = findEventWorth(positionalEventIndex);
        int eventCompleteCheck = findPositionalEventCompleteCheck(positionalEventIndex);
        return new SpawnPositionEvent(eventWorth, eventCompleteCheck, findEntitySpawnTickBuffer(positionalEventIndex), findEventSpawnTickBuffer(positionalEventIndex), findPositionalEvent(positionalEventIndex, positionalEventSpawnInfo));
    }

    public SpawnPositionEvents returnPositionalSpawnEvents(int positionalEventIndex, ArrayList<PositionalEvent> positionalEvents) {
        int eventWorth = findEventWorth(positionalEventIndex);
        int eventCompleteCheck = findPositionalEventCompleteCheck(positionalEventIndex);
        int entitySpawnBuffer = findEntitySpawnTickBuffer(positionalEventIndex);
        int eventSpawnBuffer = findEventSpawnTickBuffer(positionalEventIndex);
        boolean skippable = findSkippable(positionalEventIndex);
        return new SpawnPositionEvents(eventWorth, entitySpawnBuffer, eventSpawnBuffer, eventCompleteCheck, positionalEvents, skippable);
    }

    private PositionalEvent findPositionalEvent(int positionalEventIndex, PositionalEventSpawnInfo positionalEventSpawnInfo) {
        return switch(positionalEventIndex){
            case RED_ZONE -> new DecreaseChargeTrigger(positionalEventSpawnInfo);
            case CHARGE_ZONE -> new ChargingPort(positionalEventSpawnInfo);
            case CURSOR_TIMER -> new CursorTimedBomb(new Color[]{Color.YELLOW, Color.ORANGE},positionalEventSpawnInfo);
            default -> throw new IllegalStateException("Unexpected value: " + positionalEventIndex);
        };
    }



    public int findEntitySpawnTickBuffer(int eventIndex) {
        return switch(eventIndex){
            case RED_ZONE, CHARGE_ZONE -> UPS/2;
            default -> 0;
        };
    }

    public int findEventSpawnTickBuffer(int eventIndex) {
        return switch(eventIndex) {
            case RED_ZONE, CHARGE_ZONE -> UPS;
            case CURSOR_TIMER -> UPS*7;
            default ->UPS;
        };
    }

    private boolean findSkippable(int eventIndex) {
        return switch(eventIndex) {
            case RED_ZONE, CHARGE_ZONE -> true;
            case CURSOR_TIMER -> false;
            default -> true;
        };
    }

    /*
    *  LOADERS - load possible event indexes that can spawn in the level, and the map of all positions for those levels
     */

    public void loadSpawnEventsMap(int id) {
        spawnPositionsEventsMap = new HashMap<>();
        spawnEventsMap = new HashMap<>();
        Map<Integer, Map<Integer, ArrayList<PositionalEvent>>> positionalEventsMap = findPositionalEventMap(id);
        for(int i : positionalEventsMap.keySet()) {
            Map<Integer, SpawnPositionEvents> innerMap = new HashMap<>();
            ArrayList<SpawnPositionEvents> tempList = new ArrayList<>();
            for(int j: positionalEventsMap.get(i).keySet()) {
                ArrayList<PositionalEvent> positionalEventsInMap = positionalEventsMap.get(i).get(j);
                innerMap.put(j, returnPositionalSpawnEvents(i, positionalEventsInMap));
                tempList.add(returnPositionalSpawnEvents(i, positionalEventsInMap));
            }
            spawnEventsMap.put(i, tempList);
            spawnPositionsEventsMap.put(i, innerMap);
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


//    public Map<Integer, Map<Integer, ArrayList<PositionalEventSpawnInfo>>> getEventSpawnPositionsMap() {
//        return eventSpawnPositionsMap;
//    }

    public Map<Integer, ArrayList<SpawnPositionEvents>> getSpawnEventsMap() {
        return spawnEventsMap;
    }

    public Map<Integer, Map<Integer, SpawnPositionEvents>> getSpawnPositionsEventsMap() {
        return spawnPositionsEventsMap;
    }
}
