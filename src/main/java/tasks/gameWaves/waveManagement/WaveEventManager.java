package tasks.gameWaves.waveManagement;

import tasks.TaskRunner;
import tasks.gameWaves.spawnConstants.SpawnPositionalEventConstants;
import tasks.gameWaves.spawnTasks.SpawnPositionEvents;

import java.awt.*;
import java.util.*;
import java.util.List;

import static main.GamePanel.UPS;

public class WaveEventManager extends WaveSpawnManager{

    private SpawnPositionalEventConstants spawnPositionalEventConstants;
    private int[] eventIndexes;
    private Map<Integer, Map<Integer, SpawnPositionEvents>> spawnPositionalEventsMap;
    private final int noKeysEventBuffer = UPS*5;
    private final int noKeysEntityBuffer = UPS*5;

    private List<Point> availableKeys = new ArrayList<>();
    private Set<Point> activeKeys = new HashSet<>();
    private Set<Point> activeUnskippableKeys = new HashSet<>();

    private Set<SpawnPositionEvents> activeEvents = new HashSet<>();
    public WaveEventManager(SpawnPositionalEventConstants spawnPositionalEventConstants, Random eventRandom) {
        super(eventRandom);
        this.spawnPositionalEventConstants =spawnPositionalEventConstants;
        eventIndexes = spawnPositionalEventConstants.getEventIndexes();
        spawnPositionalEventsMap = spawnPositionalEventConstants.getSpawnPositionsEventsMap();
        loadAvailablEvents();
//        System.out.println("Available events.size:" + availableKeys.size());
    }

    private void loadAvailablEvents() {
        List<Integer> outerKeys = new ArrayList<>(spawnPositionalEventsMap.keySet());
        for(int outerKey: outerKeys) {
            for(int innerKey: spawnPositionalEventsMap.get(outerKey).keySet()) {
                availableKeys.add(new Point(outerKey, innerKey));
            }
        }
    }

    public void spawnNew() {
        //If no available events to spawn then spawn 0
        if(availableKeys.isEmpty()) {
            noSpawn();
            return;
        }
        Point spawnIndexes = getRandomSpawnIndexes();
        int eventIndex = spawnIndexes.x;
        int innerIndex = spawnIndexes.y;
        addEventAdjustActiveKeys(spawnIndexes);
        addNewEventsToTaskRunner(spawnPositionalEventsMap.get(eventIndex).get(innerIndex), spawnIndexes);
        setPointsBuffer(spawnPositionalEventConstants.findEventWorth(eventIndex));
        setEntityTickBuffer(spawnPositionalEventConstants.findEntitySpawnTickBuffer(eventIndex));
        setEventTickBuffer(spawnPositionalEventConstants.findEventSpawnTickBuffer(eventIndex));

    }

    private void noSpawn() {
        setPointsBuffer(0);
        setEventTickBuffer(noKeysEventBuffer);
        setEventTickBuffer(noKeysEntityBuffer);
    }

    private void addEventAdjustActiveKeys(Point spawnIndexes) {
        activeKeys.add(spawnIndexes);
        if(!spawnPositionalEventsMap.get(spawnIndexes.x).get(spawnIndexes.y).isSkippable()) {
            activeUnskippableKeys.add(spawnIndexes);
        }
        availableKeys.remove(spawnIndexes);
    }

    private void removeEventAdjustActiveKeys(Point spawnIndexes) {
        availableKeys.add(spawnIndexes);
        activeKeys.remove(spawnIndexes);
        if(activeUnskippableKeys.contains(spawnIndexes)) {
            activeUnskippableKeys.remove(spawnIndexes);
        }
    }

    private void addNewEventsToTaskRunner(SpawnPositionEvents spawnPositionEvents, Point spawnIndexes) {
        activeEvents.add(spawnPositionEvents);
        spawnPositionEvents.initialiseEventSpawn(spawnIndexes);
//        System.out.println(spawnPositionEvents.toString());
//        System.out.println(activeKeys);
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

    private int getRandomEventIndex() {
        return eventIndexes[random.nextInt(eventIndexes.length)];
    }

    //Check whether spawn tasks are completed - if an event has already been completed, it will already have been removed from the event m
    public void checkSpawnTasksComplete() {
        Iterator<SpawnPositionEvents> iterator = activeEvents.iterator();

        while (iterator.hasNext()) {
            SpawnPositionEvents activeEventTask = iterator.next();
            //Here run a check on the activeTasks to see whether they are complete? Or does it just get done automatically with taskrunner?
            activeEventTask.checkCompleteEvents();
            if (activeEventTask.isComplete()) {
                Point spawnIndexes = activeEventTask.getSpawnIndexes();
                removeEventAdjustActiveKeys(spawnIndexes);
                iterator.remove();
            }
        }
    }

    public void resetManager() {
        for(SpawnPositionEvents positionalEvent: activeEvents) {
            positionalEvent.reset();
        }
        activeEvents = new HashSet<>();
        activeKeys = new HashSet<>();
    }

    public Set<Point> getActiveUnskippableKeys() {
        return activeUnskippableKeys;
    }
}
