package gameObjects.events.generic;

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
    public TimedBomb(Color[] transitionColors, int x, int y, int width, int height, boolean playerTriggered) {
        super(x,y,width,height,playerTriggered);
        triggerBox = new Rectangle(x, y, width, height);
        continuousAlarm = new ContinuousBackGroundColorChange(UPS*2, GameState.getBackgroundColor(), transitionColors);
    }

    public void initialEffects() {
        TaskHandler.addTask(continuousAlarm);
    }
    @Override
    public void runEvent() {
        continuousAlarm.setComplete();

    }
}
