package gameObjects.events.generic;

import gameObjects.entities.player.GameCursor;
import gameObjects.entities.player.Player;
import levels.Level;
import states.GameState;
import tasks.TaskRunner;
import tasks.gameWaves.spawnConstants.PositionalEventSpawnInfo;
import tasks.visualTasks.backgroundColorTasks.TimedExplosion;
//import tasks.taskQueue.SpawnTimedBombTask;

import java.awt.*;

import static gameObjects.handler.GameObjectHandler.eventRemoveQueue;
import static inputs.KeyHandler.hitboxToggle;
import static main.GamePanel.UPS;

public class PlayerTimedBomb extends PositionalEvent{
    private Color[] transitionColors;
    private TimedExplosion continuousAlarm;
//    private SpawnTimedBombTask spawnTimedBombEvent;
    public PlayerTimedBomb(Color[] transitionColors, PositionalEventSpawnInfo positionalEventSpawnInfo) {
        super(positionalEventSpawnInfo.x(), positionalEventSpawnInfo.y(), positionalEventSpawnInfo.width(), positionalEventSpawnInfo.height());
        continuousAlarm = new TimedExplosion(UPS*20, UPS*2, GameState.getBackgroundColor(), transitionColors);
    }

    public void initialEffects() {
        complete = false;
        continuousAlarm.reset();
        TaskRunner.addTask(continuousAlarm);
    }

    @Override
    public void runEvent(Player player) {
        continuousAlarm.setComplete();
        setComplete();
        GameState.updateGameBackground(Color.BLACK);
        eventRemoveQueue.add(this);
    }

    @Override
    public void runEvent(GameCursor cursor) {
//        System.out.println("Cursor can't disable");
    }

    @Override
    public void reset() {
        continuousAlarm.setComplete();
    }

    @Override
    public void render(Graphics g, Level level) {

        int entityXPos = (int) (startX - level.getLevelCamera().getxOffset());
        int entityYPos = (int) (startY - level.getLevelCamera().getyOffset());

        g.setColor(Color.MAGENTA);
        g.fillRect(entityXPos, entityYPos, width, height);

        if (hitboxToggle) {
            g.setColor(Color.WHITE);
            g.drawRect(entityXPos, entityYPos, width, height);
        }


    }
}
