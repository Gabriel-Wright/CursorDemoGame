package tasks.gameWaves.waveManagement;

import gameObjects.entities.player.Player;
import tasks.Task;
import tasks.TaskRunner;
import tasks.gameWaves.spawnConstants.PositionalEventSpawnInfo;
import tasks.gameWaves.spawnConstants.SpawnConstants;
import ui.UITagManager;
import ui.UITagTask;

import java.util.*;

import static main.GamePanel.UPS;
import static main.GamePanel.setCurrentState;

public class WaveManager extends Task {

    //Wave round value and random seed value
    private int waveRound;
    private int seed;

    //SpawnConstants - calculates all necessary spawnTasks for current level
    SpawnConstants spawnConstants;

    //Sub waveManagers
    private WaveEntityManager waveEntityManager;
    private WaveEventManager waveEventManager;

    private int wavePointsStart;
    private static int wavePoints;
    private boolean roundEnded=false;

    //Entity spawn Randomiser, location and points

    private Random entityRandom;
    private Random objectRandom;
    private Random eventRandom;

    //2 seconds before the first event
    private int nextEntitySpawnTick = UPS*2;
    private int nextEventSpawnTick = UPS*5;
    private int completeEventsCheck = UPS/2;

    //In  between round start buffers
    private int nextWaveStart;
    private int nextWaveStartBuffer = 5*UPS;

    //Wave UI Tags
    private UITagTask roundStart;
    private UITagTask roundEnd;

    public WaveManager(int waveRound, int wavePoints, int seed) {
        this.waveRound = waveRound;
        this.wavePointsStart = wavePoints;
        this.wavePoints = wavePoints;
        this.seed = seed;
    }

    public void loadSpawnConstants(int id) {
        spawnConstants = new SpawnConstants();
        spawnConstants.loadSpawnConstants(id);
        loadEntitySpawnInfo();
        loadEventSpawnInfo();
        loadTags();
    }

    private void loadEntitySpawnInfo() {
        waveEntityManager = new WaveEntityManager(spawnConstants.getSpawnEntityConstants(), entityRandom);
    }

    private void loadEventSpawnInfo() {
        waveEventManager = new WaveEventManager(spawnConstants.getSpawnPositionalEventConstants(), eventRandom);
    }

    private void loadTags() {
        roundStart = new UITagTask("Next wave start:" + waveRound);
        roundEnd = new UITagTask("Wave ended! Current Score:" + Player.getSCORE());
    }

    private void loadObjectSpawnInfo() {

    }

    public void loadRandomGenerators() {
        entityRandom = new Random(seed);
        eventRandom = new Random(seed);
        objectRandom = new Random(seed);
    }

    @Override
    public void runTask() {
        if(wavePoints>=0) {
            if (tick == nextEntitySpawnTick) {
                addNewSpawn(waveEntityManager);
            }
            if (tick == nextEventSpawnTick) {
                addNewSpawn(waveEventManager);
            }
        }
        if(!roundEnded) {
            checkCompletedEvents();
            checkRoundEnd();
        } else {
            startNextRound();
        }
    }

    private void checkCompletedEvents() {
        if(tick % completeEventsCheck == 0) {
            waveEventManager.checkSpawnTasksComplete();
        }
    }

    private void checkRoundEnd() {
        if(wavePoints<=0&& waveEventManager.getActiveUnskippableKeys().isEmpty()) {
//            System.out.println("ROUND ENDED");
            endRound();
            roundEnded = true;
        }
    }

    private void addNewSpawn(WaveSpawnManager waveSpawnManager) {
        waveSpawnManager.spawnNew();
        updatePoints(waveSpawnManager.getPointsBuffer());
        adjustNextEntitySpawnTick(waveSpawnManager.getEntityTickBuffer());
        adjustNextEventSpawnTick(waveSpawnManager.getEventTickBuffer());
    }

    private void endRound() {
        waveEventManager.resetManager();
        startNextRound();
        nextWaveStart = tick + nextWaveStartBuffer;
        roundEnd.reset();
        roundEnd.setMessage("Wave ended! Current Score:" + Player.getSCORE());
        UITagManager.addUITag(roundEnd);
    }

    private void startNextRound(){
        //This current round is complete. We will reset the points available WaveManager after a buffer.
        if(tick == nextWaveStart) {
            nextEventSpawnTick = tick + UPS;
            nextEntitySpawnTick = tick + UPS;
            wavePoints = wavePointsStart + 150;
            wavePointsStart = wavePoints;
            roundEnded = false;
            waveRound ++;
            roundStart.reset();
            roundStart.setMessage("Next wave start:" + waveRound);
            UITagManager.addUITag(roundStart);
        }
    }

    private void updatePoints(int pointBuffer) {
        wavePoints -= pointBuffer;
//        System.out.println("Points remaining:"+wavePoints);
    }

    private void adjustNextEntitySpawnTick(int tickBuffer) {
        nextEntitySpawnTick += tickBuffer;
    }

    private void adjustNextEventSpawnTick(int tickBuffer) {
        nextEventSpawnTick += tickBuffer;
    }
}
