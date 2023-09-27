package gameObjects.handler;

import gameObjects.entities.Entity;
import object.SuperObject;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static main.GamePanel.TILE_SIZE;
import static utils.FindOvelapTiles.FindOverlapTiles;

public class GameObjectGrid {
    private Map<Integer, Map<Integer,Cell>> cells;
    private int numRows;
    private int numCols;
    public GameObjectGrid(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        cells = new HashMap<>();
    }

    public void initialiseGrid(List<Entity> entities, List<SuperObject> objects) {
        addEntitiesToCells(entities);
    }

    private void addObjectsToCells(List<Entity> entities) {

    }
    private void addEntitiesToCells(List<Entity> entities) {
        for(Entity entity: entities) {
            //Find corner positions of entity and add it to all of the overlapping tiles
            Point[] cellIndexes = FindOverlapTiles(entity.getBounds());
            for(Point cellIndex: cellIndexes) {
                addEntityToCell(cellIndex.x, cellIndex.y, entity);
            }
        }
    }


    public void addEntityToCell(int x, int y, Entity entity) {
        // Initialize inside map if it does not exist (i.e. map between y and Cell)
        if (!cells.containsKey(x)) {
            cells.put(x, new HashMap<>());
            addCell(x,y);
        }

        if(!cells.get(x).containsKey((y))) {
            cells.get(x).put(y, new Cell());
        }
        getCell(x, y).addEntity(entity);
    }

    private void addCell(int x, int y) {
        cells.get(x).put(y, new Cell());
    }
    public Cell getCell(int x, int y) {
        //Check if row (x exists within grid
        if(cells.containsKey(x)) {
            if(cells.get(x).containsKey(y)) {
                return cells.get(x).get(y);
            }
        }
        //If the cell doesn't exist, return null - can use this to ignore collisions in neighboring cells if doesn't exist also
        return null;
    }

}
