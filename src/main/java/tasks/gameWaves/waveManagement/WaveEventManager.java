package tasks.gameWaves.waveManagement;

import gameObjects.events.generic.PositionalEvent;
import tasks.TaskRunner;
import tasks.gameWaves.spawnConstants.PositionalEventSpawnInfo;
import tasks.gameWaves.spawnConstants.SpawnPositionalEventConstants;
import tasks.gameWaves.spawnTasks.SpawnPositionEvent;
import tasks.gameWaves.spawnTasks.SpawnPositionEvents;

import java.util.*;

public class WaveEventManager extends WaveSpawnManager{

    private SpawnPositionalEventConstants spawnPositionalEventConstants;
    private int[] eventIndexes;
//    private Map<Integer, Map<Integer, ArrayList<PositionalEventSpawnInfo>>> eventSpawnPositions;
//    private Map<Integer, Map<Integer, ArrayList<PositionalEvent>>> positionalSpawnEvents;
//    private Map<Integer, ArrayList<SpawnPositionEvents>> spawnEventsMap;
    private Map<Integer, Map<Integer, SpawnPositionEvents>> spawnPositionalEventsMap;

    public WaveEventManager(SpawnPositionalEventConstants spawnPositionalEventConstants, Random eventRandom) {
        super(eventRandom);
        this.spawnPositionalEventConstants =spawnPositionalEventConstants;
        eventIndexes = spawnPositionalEventConstants.getEventIndexes();
//        spawnEventsMap = spawnPositionalEventConstants.getSpawnEventsMap();
        spawnPositionalEventsMap = spawnPositionalEventConstants.getSpawnPositionsEventsMap();
//        eventSpawnPositions = spawnPositionalEventConstants.getEventSpawnPositionsMap();
    }

    public void spawnNew() {
        int eventIndex = getRandomEventIndex();
        SpawnPositionEvents nextEvents = getRandomSpawnEvents(eventIndex);
        addNewEventsToTaskRunner(nextEvents);
        setPointsBuffer(spawnPositionalEventConstants.findEventWorth(eventIndex));
        setEntityTickBuffer(spawnPositionalEventConstants.findEntitySpawnTickBuffer(eventIndex));
        setEventTickBuffer(spawnPositionalEventConstants.findEventSpawnTickBuffer(eventIndex));
    }

    private void addNewEventsToTaskRunner(SpawnPositionEvents spawnPositionEvents) {
        spawnPositionEvents.initialiseEventSpawn();
        TaskRunner.addTask(spawnPositionEvents);
    }

//    private ArrayList<SpawnPositionEvent> loadNewSpawnEvents(int eventIndex, ArrayList<PositionalEventSpawnInfo> eventSpawnPositions) {
//        ArrayList<SpawnPositionEvent> newSpawnPositionEvents = new ArrayList<>();
//        for(PositionalEventSpawnInfo positionalEventSpawnInfo: eventSpawnPositions) {
//            newSpawnPositionEvents.add(spawnPositionalEventConstants.findPositionalEventSpawnTask(eventIndex,positionalEventSpawnInfo));
//        }
//        return newSpawnPositionEvents;
//    }

    private SpawnPositionEvents getRandomSpawnEvents(int eventIndex) {
        Set<Integer> keySet = spawnPositionalEventsMap.get(eventIndex).keySet();
        int numKeys = keySet.size();
        List<Integer> spawnKeyList = new ArrayList<>(keySet);
        int nextIndex = spawnKeyList.get(random.nextInt(numKeys));
        return spawnPositionalEventsMap.get(eventIndex).get(nextIndex);
    }

    private ArrayList<PositionalEventSpawnInfo> getRandomMapSpawnList(Map<Integer, ArrayList<PositionalEventSpawnInfo>> positionalEventSpawnInfoMap) {
        //Should probably leave as set or unsure?
        Set<Integer> spawnPositionKeys = positionalEventSpawnInfoMap.keySet();
        int numSpawnCombinations = spawnPositionKeys.size();
        //randomly choose an index of the map
        // Convert the set to a list for random access
        List<Integer> spawnPositionList = new ArrayList<>(spawnPositionKeys);
        int spawnPositionsIndex = spawnPositionList.get(random.nextInt(numSpawnCombinations));
        return positionalEventSpawnInfoMap.get(spawnPositionsIndex);
    }

    private int getRandomEventIndex() {
        return eventIndexes[random.nextInt(eventIndexes.length)];
    }
}
