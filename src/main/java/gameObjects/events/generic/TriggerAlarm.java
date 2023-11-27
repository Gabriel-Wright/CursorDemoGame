package gameObjects.events.generic;

import states.GameState;
import tasks.TaskHandler;
import tasks.backgroundColorTasks.ContinuousBackGroundColorChange;

import java.awt.*;

import static main.GamePanel.UPS;

public class TriggerAlarm extends PositionalEvent{

    private Color[] transitionColors;
    public TriggerAlarm(Color[] transitionColors, int startCol, int startRow, int numCols, int numRows, boolean playerTriggered) {
        super(startCol, startRow, numCols, numRows, playerTriggered);
        this.transitionColors = transitionColors;
    }

    @Override
    public void runEvent() {
        TaskHandler.addTask(new ContinuousBackGroundColorChange(UPS*2, GameState.getBackgroundColor(), transitionColors));
    }
}
