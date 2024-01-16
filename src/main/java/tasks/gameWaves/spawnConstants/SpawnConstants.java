package tasks.gameWaves.spawnConstants;

public class SpawnConstants {

    private SpawnEntityConstants spawnEntityConstants;
    private SpawnPositionalEventConstants spawnPositionalEventConstants;
    private SpawnObjectConstants spawnObjectConstants;


    public void loadSpawnConstants(int id) {
        loadSpawnEntityConstants(id);
        loadSpawnPositionalEventConstants(id);
        loadSpawnObjectConstants(id);
    }

    private void loadSpawnEntityConstants(int id) {
        spawnEntityConstants = new SpawnEntityConstants();
        spawnEntityConstants.loadEntitySpawnsPositions(id);
        spawnEntityConstants.loadEntitySpawnPositionsP(id);
        spawnEntityConstants.loadEntityIndexes(id);
    }

    private void loadSpawnPositionalEventConstants(int id) {
        spawnPositionalEventConstants = new SpawnPositionalEventConstants();
        spawnPositionalEventConstants.loadEventIndexes(id);
        spawnPositionalEventConstants.loadSpawnEventsMap(id);
//        spawnPositionalEventConstants.loadEventSpawnPositions(id);
//        spawnPositionalEventConstants.findPositionalEventMap(id);
    }

    private void loadSpawnObjectConstants(int id) {

    }

    public SpawnEntityConstants getSpawnEntityConstants() {
        return spawnEntityConstants;
    }

    public SpawnPositionalEventConstants getSpawnPositionalEventConstants() {
        return spawnPositionalEventConstants;
    }

    public SpawnObjectConstants getSpawnObjectConstants() {
        return spawnObjectConstants;
    }
}
