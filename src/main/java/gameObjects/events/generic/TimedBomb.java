package gameObjects.events.generic;

import gameObjects.entities.player.Cursor;
import gameObjects.entities.player.Player;
import states.GameState;
import tasks.Task;
import tasks.TaskHandler;
import tasks.backgroundColorTasks.BackGroundColorChange;
import tasks.backgroundColorTasks.ContinuousBackGroundColorChange;

import java.awt.*;

import static main.GamePanel.UPS;

public class TimedBomb extends PositionalEvent{

    private Color[] transitionColors;
    private ContinuousBackGroundColorChange continuousAlarm;
    public TimedBomb(Color[] transitionColors, int x, int y, int width, int height) {
        super(x,y,width,height);
        triggerBox = new Rectangle(x, y, width, height);
        continuousAlarm = new ContinuousBackGroundColorChange(UPS*2, GameState.getBackgroundColor(), transitionColors);
    }

    public void initialEffects() {
        TaskHandler.addTask(continuousAlarm);
    }
    @Override
    public void runEvent(Player player) {
        continuousAlarm.setComplete();
    }

    @Override
    public void runEvent(Cursor cursor) {
        System.out.println("Background color alarm");
    }
}
