package gameObjects.events.generic;

import gameObjects.entities.player.Player;
import gameObjects.entities.player.Cursor;
import gameObjects.handler.GameObjectHandler;
import states.GameState;
import tasks.TaskRunner;
import tasks.visualTasks.backgroundColorTasks.BackGroundColorChange;

import java.awt.*;

import static main.GamePanel.UPS;


public class RoomChange extends PositionalEvent {

    private Color backgroundColor;
    public RoomChange(Color color, int x, int y, int width, int height) {
        super(x,y,width,height);
        triggerBox = new Rectangle(x, y , width, height);
        backgroundColor = color;
    }

    @Override
    public void runEvent(Player player) {
        TaskRunner.addTask(new BackGroundColorChange(UPS, GameState.getBackgroundColor(),backgroundColor));
        complete = true;
        GameObjectHandler.eventQueue.add(new TriggerAlarm(new Color[]{Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN,Color.PINK},12,12,2,2));
    }

    @Override
    public void runEvent(Cursor cursor) {
        System.out.println("Event collides with cursor");
    }

    @Override
    public void initialEffects() {

    }

    @Override
    public void reset() {

    }
}
