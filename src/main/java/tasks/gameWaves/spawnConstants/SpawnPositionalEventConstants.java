package tasks.gameWaves.spawnConstants;

import tasks.gameWaves.spawnTasks.SpawnPositionalEvent;

public class SpawnPositionalEventConstants {

    private final int id;

    //Event flags
    public final static int CURSOR_TIMER = 0;
    public final static int PLAYER_TIMER = 1;
    public final static int RED_ZONE = 2;
    public final static int CHARGE_ZONE = 3;

    SpawnPositionalEventConstants(int id) {
        this.id = id;
    }

}
