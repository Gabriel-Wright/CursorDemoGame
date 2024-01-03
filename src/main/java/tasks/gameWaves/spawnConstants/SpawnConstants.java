package tasks.gameWaves.spawnConstants;

public class SpawnConstants {

    private SpawnEntityConstants spawnEntityConstants;
    private SpawnPositionalEventConstants spawnPositionalEventConstants;
    private SpawnObjectConstants spawnObjectConstants;


    public void loadSpawnConstants(int id) {
        loadSpawnEntityConstants(id);
        loadSpawnPositionalEventConstants(id);
        loadSpawnobjectConstants(id);
    }

    private void loadSpawnEntityConstants(int id) {
        spawnEntityConstants = new SpawnEntityConstants();
        spawnEntityConstants.loadEntitySpawnsPositions(id);
        spawnEntityConstants.loadEntityIndexes(id);
//
    }

    private void loadSpawnPositionalEventConstants(int id) {
        spawnPositionalEventConstants = new SpawnPositionalEventConstants();
        spawnPositionalEventConstants.loadEventIndexes(id);
        spawnPositionalEventConstants.loadEventSpawnPositions(id);
    }

    private void loadSpawnobjectConstants(int id) {

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
