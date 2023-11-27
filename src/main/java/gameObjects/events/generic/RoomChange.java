package gameObjects.events.generic;

import gameObjects.events.generic.PositionalEvent;
import gameObjects.handler.GameObjectHandler;
import states.GameState;
import tasks.TaskHandler;
import tasks.backgroundColorTasks.BackGroundColorChange;

import java.awt.*;

import static main.GamePanel.UPS;


public class RoomChange extends PositionalEvent {

    private Color backgroundColor;
    public RoomChange(Color color, int x, int y, int width, int height, boolean playerTriggered) {
        super(x,y,width,height,playerTriggered);
        triggerBox = new Rectangle(x, y , width, height);
        backgroundColor = color;
    }
    @Override
    public void runEvent() {
//        System.out.println("Test");
//        GameState.updateGameBackground(backgroundColor);

        TaskHandler.addTask(new BackGroundColorChange(UPS, GameState.getBackgroundColor(),backgroundColor));
        complete = true;
        GameObjectHandler.eventQueue.add(new TriggerAlarm(new Color[]{Color.RED,Color.BLUE,Color.YELLOW,Color.GREEN,Color.PINK},12,12,2,2,true));
    }
}
