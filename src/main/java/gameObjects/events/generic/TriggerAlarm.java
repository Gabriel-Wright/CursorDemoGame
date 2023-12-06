package gameObjects.events.generic;

import gameObjects.entities.player.Cursor;
import gameObjects.entities.player.Player;
import states.GameState;
import tasks.TaskHandler;
import tasks.backgroundColorTasks.ContinuousBackGroundColorChange;

import java.awt.*;

import static main.GamePanel.UPS;

public class TriggerAlarm extends PositionalEvent{

    private Color[] transitionColors;
    public TriggerAlarm(Color[] transitionColors, int startCol, int startRow, int numCols, int numRows) {
        super(startCol, startRow, numCols, numRows);
        this.transitionColors = transitionColors;
    }

    @Override
    public void runEvent(Player player) {
        TaskHandler.addTask(new ContinuousBackGroundColorChange(UPS*2, GameState.getBackgroundColor(), transitionColors));
    }

    @Override
    public void runEvent(Cursor cursor) {
        System.out.println("Trigger alarm cursor");
    }
}
