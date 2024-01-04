package tasks.gameWaves.waveManagement;

import tasks.TaskRunner;
import tasks.gameWaves.spawnConstants.PositionalEventSpawnInfo;
import tasks.gameWaves.spawnConstants.SpawnPositionalEventConstants;
import tasks.gameWaves.spawnTasks.SpawnPositionEvent;

import java.util.*;

public class WaveEventManager extends WaveSpawnManager{

    private SpawnPositionalEventConstants spawnPositionalEventConstants;
    private int[] eventIndexes;
    private Map<Integer, Map<Integer, ArrayList<PositionalEventSpawnInfo>>> eventSpawnPositions;

    public WaveEventManager(SpawnPositionalEventConstants spawnPositionalEventConstants, Random eventRandom) {
        super(eventRandom);
        this.spawnPositionalEventConstants =spawnPositionalEventConstants;
        eventIndexes = spawnPositionalEventConstants.getEventIndexes();
        eventSpawnPositions = spawnPositionalEventConstants.getEventSpawnPositions();
    }

    public void spawnNew() {
        int eventIndex = getRandomEventIndex();
        ArrayList<PositionalEventSpawnInfo> eventSpawns = getRandomEventSpawnPositions(eventIndex);
        ArrayList<SpawnPositionEvent> newSpawnEvents = loadNewSpawnEvents(eventIndex, eventSpawns);
        addNewEventsToTaskRunner(newSpawnEvents);
        setPointsBuffer(spawnPositionalEventConstants.findEventWorth(eventIndex));
        setEntityTickBuffer(spawnPositionalEventConstants.findEntitySpawnTickBuffer(eventIndex));
        setEventTickBuffer(spawnPositionalEventConstants.findEventSpawnTickBuffer(eventIndex));
    }

    private void addNewEventsToTaskRunner(ArrayList<SpawnPositionEvent> newSpawnEvents) {
        for(SpawnPositionEvent newSpawnEvent: newSpawnEvents) {
            TaskRunner.addTask(newSpawnEvent);
            newSpawnEvent.initialiseEventSpawn();
        }
    }

    private ArrayList<SpawnPositionEvent> loadNewSpawnEvents(int eventIndex, ArrayList<PositionalEventSpawnInfo> eventSpawnPositions) {
        ArrayList<SpawnPositionEvent> newSpawnPositionEvents = new ArrayList<>();
        for(PositionalEventSpawnInfo positionalEventSpawnInfo: eventSpawnPositions) {
            newSpawnPositionEvents.add(spawnPositionalEventConstants.findPositionalEventSpawnTask(eventIndex,positionalEventSpawnInfo));
        }
        return newSpawnPositionEvents;
    }

    private ArrayList<PositionalEventSpawnInfo> getRandomEventSpawnPositions(int eventIndex) {
        ArrayList<PositionalEventSpawnInfo> eventSpawns = new ArrayList<>();
        Map<Integer, ArrayList<PositionalEventSpawnInfo>> positionalEventSpawnInfoMap = eventSpawnPositions.get(eventIndex);
        return getRandomMapSpawnList(positionalEventSpawnInfoMap);
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
