package gameObjects.events.generic;

import gameObjects.entities.player.Cursor;
import gameObjects.entities.player.Player;
import levels.Level;
import states.GameState;

import tasks.TaskRunner;
import tasks.gameWaves.spawnConstants.PositionalEventSpawnInfo;
import tasks.visualTasks.backgroundColorTasks.TimedExplosion;


import java.awt.*;

import static inputs.KeyHandler.hitboxToggle;
import static main.GamePanel.UPS;

public class CursorTimedBomb extends PositionalEvent{

    private TimedExplosion continuousAlarm;

//    public CursorTimedBomb(Color[] transitionColors, int x, int y, int width, int height) {
//        super(x,y,width,height);
//        triggerBox = new Rectangle(x, y, width, height);
//        continuousAlarm = new TimedExplosion(UPS*10, UPS*2, GameState.getBackgroundColor(), transitionColors);
//    }

    public CursorTimedBomb(Color[] transitionColors, PositionalEventSpawnInfo positionalEventSpawnInfo) {
        super(positionalEventSpawnInfo.x(), positionalEventSpawnInfo.y(), positionalEventSpawnInfo.width(), positionalEventSpawnInfo.height());
        //Need to make these non floating variables
        continuousAlarm = new TimedExplosion(UPS*10, UPS*2, GameState.getBackgroundColor(), transitionColors);
    }

    public void initialEffects() {
        complete = false;
        continuousAlarm.reset();
        TaskRunner.addTask(continuousAlarm);
    }

    @Override
    public void runEvent(Player player) {
        System.out.println("Player cant disable it");
    }

    @Override
    public void runEvent(Cursor cursor) {
        System.out.println("Cursor event run");
        continuousAlarm.setComplete();
        setComplete();
        GameState.updateGameBackground(Color.BLACK);
    }

    @Override
    public void reset() {
        continuousAlarm.setComplete();
    }

    @Override
    public void render(Graphics g, Level level) {

        int entityXPos = (int) (startX - level.getLevelCamera().getxOffset());
        int entityYPos = (int) (startY - level.getLevelCamera().getyOffset());

        g.setColor(Color.PINK);
        g.fillRect(entityXPos, entityYPos, width, height);

        if (hitboxToggle) {
            g.setColor(Color.WHITE);
            g.drawRect(entityXPos, entityYPos, width, height);
        }
    }

}
