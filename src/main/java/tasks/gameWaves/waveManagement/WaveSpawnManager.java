package tasks.gameWaves.waveManagement;

import java.util.Random;

public abstract class WaveSpawnManager {


    protected Random random;

    private int pointsBuffer;
    private int eventTickBuffer;
    private int entityTickBuffer;

    public WaveSpawnManager(Random random) {
        this.random = random;
    }

    public abstract void spawnNew();

    public int getPointsBuffer() {
        return pointsBuffer;
    }

    public void setPointsBuffer(int pointsBuffer) {
        this.pointsBuffer = pointsBuffer;
    }

    public int getEventTickBuffer() {
        return eventTickBuffer;
    }

    public void setEventTickBuffer(int eventTickBuffer) {
        this.eventTickBuffer = eventTickBuffer;
    }

    public int getEntityTickBuffer() {
        return entityTickBuffer;
    }

    public void setEntityTickBuffer(int entityTickBuffer) {
        this.entityTickBuffer = entityTickBuffer;
    }
}
