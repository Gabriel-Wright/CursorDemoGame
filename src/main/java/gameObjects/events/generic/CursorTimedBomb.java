package gameObjects.events.generic;

import gameObjects.entities.player.Cursor;
import gameObjects.entities.player.Player;
import levels.Level;
import states.GameState;
import tasks.taskQueue.SpawnTimedBombTask;
import tasks.TaskRunner;
import tasks.backgroundColorTasks.ContinuousBackGroundColorChange;

import java.awt.*;

import static gameObjects.handler.GameObjectHandler.eventRemoveQueue;
import static inputs.KeyHandler.hitboxToggle;
import static main.GamePanel.UPS;

public class CursorTimedBomb extends PositionalEvent{

    private Color[] transitionColors;
    private ContinuousBackGroundColorChange continuousAlarm;
    private SpawnTimedBombTask spawnTimedBombEvent;
    public CursorTimedBomb(Color[] transitionColors, int x, int y, int width, int height, SpawnTimedBombTask spawnTimedBombEvent) {
        super(x,y,width,height);
        triggerBox = new Rectangle(x, y, width, height);
        continuousAlarm = new ContinuousBackGroundColorChange(UPS*6, GameState.getBackgroundColor(), transitionColors);
        this.spawnTimedBombEvent = spawnTimedBombEvent;
    }

    public void initialEffects() {
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
        spawnTimedBombEvent.setComplete();
        GameState.updateGameBackground(Color.BLACK);
        eventRemoveQueue.add(this);
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
