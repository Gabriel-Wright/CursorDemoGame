package gameObjects.events.generic;

import gameObjects.events.generic.Event;
import levels.Level;
import tasks.gameWaves.spawnConstants.PositionalEventSpawnInfo;
import tasks.gameWaves.spawnTasks.SpawnPositionalEvent;

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

    protected int startX;
    protected int startY;
    protected int width;
    protected int height;
//    public PositionalEvent(int startCol, int startRow, int numCols, int numRows) {
//        this.startCol = startCol;
//        this.startRow = startRow;
//        this.numCols = numCols;
//        this.numRows = numRows;
//        triggerBox = new Rectangle(startCol*TILE_SIZE, startRow*TILE_SIZE, numCols*TILE_SIZE, numRows*TILE_SIZE);
//    }
    public PositionalEvent() {

    }

    public PositionalEvent(int startX, int startY, int width, int height) {
        this.startX = startX;
        startCol = startX/TILE_SIZE;
        this.startY = startY;
        startRow = startY/TILE_SIZE;
        this.width = width;
        numCols = width/TILE_SIZE;
        this.height = height;
        numRows = height/TILE_SIZE;
        triggerBox = new Rectangle(startX, startY, width, height);
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

        int entityXPos = (int) (startX - level.getLevelCamera().getxOffset());
        int entityYPos = (int) (startY - level.getLevelCamera().getyOffset());

        if (hitboxToggle) {
            g.setColor(Color.WHITE);
            g.drawRect(entityXPos, entityYPos, width, height);
        }


    }

    public void setPositionalEventSpawnInfo(PositionalEventSpawnInfo positionalEventSpawnInfo) {
        this.startX = positionalEventSpawnInfo.x();
        startCol = startX/TILE_SIZE;
        this.startY = positionalEventSpawnInfo.y();
        startRow = startY/TILE_SIZE;
        this.width = positionalEventSpawnInfo.width();
        numCols = width/TILE_SIZE;
        this.height = positionalEventSpawnInfo.height();
        numRows = height/TILE_SIZE;
        triggerBox = new Rectangle(startX, startY, width, height);
    }
}
