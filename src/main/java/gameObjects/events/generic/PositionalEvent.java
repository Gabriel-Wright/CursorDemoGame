package gameObjects.events.generic;

import gameObjects.events.generic.Event;
import levels.Level;

import java.awt.*;

import static inputs.KeyHandler.hitboxToggle;
import static main.GamePanel.TILE_SIZE;

public abstract class PositionalEvent implements Event {


    //World size triggerBox rectangle - scaled to pixel size.
    protected Rectangle triggerBox;

    //Position of the event in terms of tiles (i.e. events overlap with a group of tiles)
    protected int startCol;
    protected int startRow;
    protected int numRows;
    protected int numCols;
    protected boolean complete = false;
    private boolean playerTriggered;


    public PositionalEvent(int startCol, int startRow, int numCols, int numRows, boolean playerTriggered) {
        this.startCol = startCol;
        this.startRow = startRow;
        this.numCols = numCols;
        this.numRows = numRows;
        triggerBox = new Rectangle(startCol*TILE_SIZE, startRow*TILE_SIZE, numCols*TILE_SIZE, numRows*TILE_SIZE);
        this.playerTriggered = playerTriggered;
    }

    public Rectangle getTriggerBox() {
        return triggerBox;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public boolean isComplete() {
        return complete;
    }

    public void render(Graphics g, Level level) {
        int entityXPos = (int) (startCol*TILE_SIZE - level.getLevelCamera().getxOffset());
        int entityYPos = (int) (startRow*TILE_SIZE - level.getLevelCamera().getyOffset());

        if (hitboxToggle) {
            g.setColor(Color.WHITE);
            g.drawRect(entityXPos, entityYPos, numCols*TILE_SIZE, numRows*TILE_SIZE);
        }


    }
}
