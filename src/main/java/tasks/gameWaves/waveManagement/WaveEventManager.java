package tasks.gameWaves.waveManagement;

import gameObjects.events.generic.PositionalEvent;
import tasks.TaskRunner;
import tasks.gameWaves.spawnConstants.PositionalEventSpawnInfo;
import tasks.gameWaves.spawnConstants.SpawnPositionalEventConstants;
import tasks.gameWaves.spawnTasks.SpawnPositionEvent;
import tasks.gameWaves.spawnTasks.SpawnPositionEvents;

import java.awt.*;
import java.util.*;
import java.util.List;

import static main.GamePanel.UPS;

public class WaveEventManager extends WaveSpawnManager{

    private SpawnPositionalEventConstants spawnPositionalEventConstants;
    private int[] eventIndexes;
    private Map<Integer, Map<Integer, SpawnPositionEvents>> spawnPositionalEventsMap;
    private int availableEvents;
    private final int noKeysEventBuffer = UPS*5;
    private final int noKeysEntityBuffer = UPS*5;

    private Set<Point> activeKeys = new HashSet<>();
    private Set<SpawnPositionEvents> activeEvents = new HashSet<>();
    public WaveEventManager(SpawnPositionalEventConstants spawnPositionalEventConstants, Random eventRandom) {
        super(eventRandom);
        this.spawnPositionalEventConstants =spawnPositionalEventConstants;
        eventIndexes = spawnPositionalEventConstants.getEventIndexes();
        spawnPositionalEventsMap = spawnPositionalEventConstants.getSpawnPositionsEventsMap();
        availableEvents = calculateTotalNumberEvents();
    }

    private int calculateTotalNumberEvents() {
        int totalsize = 0;
        List<Integer> outerKeys = new ArrayList<>(spawnPositionalEventsMap.keySet());
        for(int key: outerKeys) {
            int innerKeySize = spawnPositionalEventsMap.get(key).keySet().size();
            totalsize += innerKeySize;
        }
        return totalsize;
    }

    public void spawnNew() {
        //If no available events to spawn then spawn 0
        if(availableEvents == 0) {
            noSpawn();
            return;
        }
        Point spawnIndexes = getRandomSpawnIndexes();
        int eventIndex = spawnIndexes.x;
        int innerIndex = spawnIndexes.y;
        adjustActiveKeys(spawnIndexes);
        addNewEventsToTaskRunner(spawnPositionalEventsMap.get(eventIndex).get(innerIndex));
        setPointsBuffer(spawnPositionalEventConstants.findEventWorth(eventIndex));
        setEntityTickBuffer(spawnPositionalEventConstants.findEntitySpawnTickBuffer(eventIndex));
        setEventTickBuffer(spawnPositionalEventConstants.findEventSpawnTickBuffer(eventIndex));

    }

    private void noSpawn() {
        setPointsBuffer(0);
        setEventTickBuffer(noKeysEventBuffer);
        setEventTickBuffer(noKeysEntityBuffer);
    }
    private void adjustActiveKeys(Point spawnIndexes) {
        activeKeys.add(spawnIndexes);
        availableEvents--;
    }

    private void addNewEventsToTaskRunner(SpawnPositionEvents spawnPositionEvents) {
        activeEvents.add(spawnPositionEvents);
        spawnPositionEvents.initialiseEventSpawn();
        TaskRunner.addTask(spawnPositionEvents);
    }
    private Point getRandomSpawnIndexes() {
        int eventIndex = getRandomEventIndex();
        int innerIndex = getRandomSpawnEventIndex(eventIndex);
        Point mapKeys = new Point(eventIndex, innerIndex);
        if(activeKeys.contains(mapKeys)) {
            return getRandomSpawnIndexes();
        }
        return mapKeys;

    }
    private int getRandomSpawnEventIndex(int eventIndex) {
        Set<Integer> keySet = spawnPositionalEventsMap.get(eventIndex).keySet();
        int numKeys = keySet.size();
        List<Integer> spawnKeyList = new ArrayList<>(keySet);
        int nextIndex = spawnKeyList.get(random.nextInt(numKeys));
        return nextIndex;
    }

    public void resetManager() {
        for(SpawnPositionEvents positionalEvent: activeEvents) {
            positionalEvent.reset();
        }
        activeEvents = new HashSet<>();
        activeKeys = new HashSet<>();
    }

    private int getRandomEventIndex() {
        return eventIndexes[random.nextInt(eventIndexes.length)];
    }
}
