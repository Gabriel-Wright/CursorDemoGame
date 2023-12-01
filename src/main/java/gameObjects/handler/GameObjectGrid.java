package gameObjects.handler;

import gameObjects.entities.Entity;
import gameObjects.entities.player.Player;
import gameObjects.entities.player.Cursor;
import gameObjects.events.generic.PositionalEvent;
import gameObjects.objects.SuperObject;
import levels.Level;
import utils.PathFinder;

import java.awt.*;
import java.util.*;
import java.util.List;

import static utils.FindOvelapTiles.FindOverlapTiles;

public class GameObjectGrid {
    private Map<Integer, Map<Integer,Cell>> cells;
    public GameObjectGrid() {
        cells = new HashMap<>();
    }

    public void initialiseGrid(Player player, List<Entity> entities, List<SuperObject> objects, List<PositionalEvent> triggerEvents) {
        if(player !=null) {
            addPlayerToCells(player);
        }
        if(entities != null) {
            addEntitiesToCells(entities);
        }
        if(objects != null) {
            addObjectsToCells(objects);
        }
        if(triggerEvents != null) {
            addTriggerEventsToCells(triggerEvents);
        }
    }

    private void addPlayerToCells(Player player) {
        if(player ==null) {
            return;
        }
        Point[] cellIndexes = FindOverlapTiles(player);
        for(Point cellIndex: cellIndexes) {
            addPlayerToCell(cellIndex.x, cellIndex.y, player);
        }
    }

    private void addObjectsToCells(List<SuperObject> objects) {
        for(SuperObject object: objects) {
            Point[] cellIndexes = FindOverlapTiles(object.getObjectCollisionBox());
            for(Point cellIndex: cellIndexes) {
                addObjectToCell(cellIndex.x, cellIndex.y, object);
            }
        }
    }
    private void addEntitiesToCells(List<Entity> entities) {
        for(Entity entity: entities) {
            //Find corner positions of entity and add it to all of the overlapping tiles
            Point[] cellIndexes = FindOverlapTiles(entity);
            for(Point cellIndex: cellIndexes) {
                addEntityToCell(cellIndex.x, cellIndex.y, entity);
            }
        }
    }

    private void addTriggerEventsToCells(List<PositionalEvent> triggerEvents) {
        for(PositionalEvent triggerEvent: triggerEvents) {
            int startCol = triggerEvent.getStartCol();
            int startRow = triggerEvent.getStartRow();
            int numCols = triggerEvent.getNumCols();
            int numRows = triggerEvent.getNumRows();
            for(int i =0; i<numCols; i++) {
                for(int j=0; j<numRows; j++) {
                    addTriggerEventToCell(startCol+i, startRow+i, triggerEvent);
                }
            }
        }
    }

    public void addObjectToCell(SuperObject object) {
        Point[] cellIndexes = FindOverlapTiles(object.getObjectCollisionBox());
        for(Point cellIndex: cellIndexes) {
            addObjectToCell(cellIndex.x, cellIndex.y, object);
        }
    }

    public void addEntityToCell(Entity entity) {
        Point[] cellIndexes = FindOverlapTiles(entity);
        for(Point cellIndex: cellIndexes) {
            addEntityToCell(cellIndex.x, cellIndex.y, entity);
        }
    }

    public void addTriggerEventToCell(PositionalEvent triggerEvent) {
        int startCol = triggerEvent.getStartCol();
        int startRow = triggerEvent.getStartRow();
        int numCols = triggerEvent.getNumCols();
        int numRows = triggerEvent.getNumRows();
        for(int i =0; i<numCols; i++) {
            for(int j=0; j<numRows; j++) {
                addTriggerEventToCell(startCol+i, startRow+i, triggerEvent);
            }
        }

    }

    private void addObjectToCell(int x, int y, SuperObject object) {
        // Initialize inside map if it does not exist (i.e. map between y and Cell)
        if (!cells.containsKey(x)) {
            cells.put(x, new HashMap<>());
            addCell(x,y);
        }

        if(!cells.get(x).containsKey((y))) {
            cells.get(x).put(y, new Cell(x,y));
        }
        getCell(x,y).addObject(object);

    }

    private void addPlayerToCell(int x, int y, Player player) {
        // Initialize inside map if it does not exist (i.e. map between y and Cell)
        if (!cells.containsKey(x)) {
            cells.put(x, new HashMap<>());
            addCell(x,y);
        }

        if(!cells.get(x).containsKey((y))) {
            cells.get(x).put(y, new Cell(x,y));
        }
        getCell(x,y).setPlayer(player);
    }

    private void addTriggerEventToCell(int x, int y, PositionalEvent positionalEvent) {
        if(!cells.containsKey(x)) {
            cells.put(x, new HashMap<>());
            addCell(x,y);
        }

        if(!cells.get(x).containsKey((y))) {
            cells.get(x).put(y, new Cell(x,y));
        }

        getCell(x,y).addPositionalEvent(positionalEvent);
    }

    public void addEntityToCell(int x, int y, Entity entity) {
        // Initialize inside map if it does not exist (i.e. map between y and Cell)
        if (!cells.containsKey(x)) {
            cells.put(x, new HashMap<>());
            addCell(x,y);
        }

        if(!cells.get(x).containsKey((y))) {
            cells.get(x).put(y, new Cell(x,y));
        }
        getCell(x, y).addEntity(entity);
    }

    private void addCell(int x, int y) {
        cells.get(x).put(y, new Cell(x,y));
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

    public void reassignEntityCells(Entity entity, float xMove, float yMove) {
        //Get current cell indexes
        Point[] cellIndexes = FindOverlapTiles(entity);
        //Set of previous cells that entity was attached -> minus since entity moved by xmove and ymove, so previous
        // cell set was xmove back ymove back
        Set<Point> prevCellSet = new HashSet<>(Arrays.asList(FindOverlapTiles(entity, xMove, yMove)));
        //Iterate through previous cell indexes to check removals
        for(Point cellIndex: cellIndexes) {
            //Check if cell exists within grid
            if(getCell(cellIndex.x, cellIndex.y) !=null) {
                //If previous cell doesn't contain current cellIndex - add entity to that cell
                if(!prevCellSet.contains(cellIndex)) {
                    addEntityToCell(cellIndex.x, cellIndex.y, entity);
                } else {
                    prevCellSet.remove(cellIndex);
                }
            } else {
                addEntityToCell(cellIndex.x, cellIndex.y, entity);
            }
        }

        // Iterate through current cell indexes to check additions
        for (Point cellIndex : prevCellSet) {
            if (getCell(cellIndex.x, cellIndex.y) != null) {
                getCell(cellIndex.x, cellIndex.y).removeEntity(entity);
            }
        }
    }

    public void reassignPlayerCells(Player player, float xMove, float yMove) {
        //Get current cell indexes
        Point[] cellIndexes = FindOverlapTiles(player);
        //Set of previous cells that entity was attached -> minus since entity moved by xmove and ymove, so previous
        // cell set was xmove back ymove back
        Set<Point> prevCellSet = new HashSet<>(Arrays.asList(FindOverlapTiles(player, xMove, yMove)));
        //Iterate through previous cell indexes to check removals
        for(Point cellIndex: cellIndexes) {
            //Check if cell exists within grid
            if(getCell(cellIndex.x, cellIndex.y) !=null) {
                //If previous cell doesn't contain current cellIndex - add entity to that cell
                if(!prevCellSet.contains(cellIndex)) {
                    addPlayerToCell(cellIndex.x, cellIndex.y, player);
                } else {
                    prevCellSet.remove(cellIndex);
                }
            } else {
                addPlayerToCell(cellIndex.x, cellIndex.y, player);
            }
        }

        // Iterate through current cell indexes to check additions
        for (Point cellIndex : prevCellSet) {
            if (getCell(cellIndex.x, cellIndex.y) != null) {
                getCell(cellIndex.x, cellIndex.y).removePlayer();
            }
        }
    }

    //Could be optimised to use array not arraylist
    public List<Point> getAssignedCells(Entity entity) {
        List<Point> assignedCells = new ArrayList<>();

        Point[] cellIndexes = FindOverlapTiles(entity);

        for (Point cellIndex : cellIndexes) {
            if (getCell(cellIndex.x, cellIndex.y) != null) {
                assignedCells.add(cellIndex);
            }
        }

        return assignedCells;
    }

//    xOffset and yOffset represent the camera offset, as cursor position is screen based not level based
    public List<Point> getAssignedCells(Cursor cursor, float xOffset, float yOffset) {
        List<Point> assignedCells = new ArrayList<>();

        Point[] cellIndexes = FindOverlapTiles(cursor, xOffset, yOffset);
        for (Point cellIndex : cellIndexes) {
            if (getCell(cellIndex.x, cellIndex.y) != null) {
                assignedCells.add(cellIndex);
            }
        }

        return assignedCells;


    }

    public void removeEntityFromCells(Entity entity) {
        List<Point> assignedCells = getAssignedCells(entity);
        for(Point assignedCell: assignedCells) {
            getCell(assignedCell.x,assignedCell.y).removeEntity(entity);
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GameObjectGrid Contents:\n");

        for (Map.Entry<Integer, Map<Integer, Cell>> entry : cells.entrySet()) {
            int x = entry.getKey();
            Map<Integer, Cell> row = entry.getValue();

            for (Map.Entry<Integer, Cell> cellEntry : row.entrySet()) {
                int y = cellEntry.getKey();
                Cell cell = cellEntry.getValue();

                sb.append("Cell (x=").append(x).append(", y=").append(y).append("):\n");
                sb.append(cell.toString()).append("\n");
            }
        }

        return sb.toString();
    }

    public void updateEntityPaths(PathFinder pathfinder, Level level) {
        for (Map.Entry<Integer, Map<Integer, Cell>> outerEntry : cells.entrySet()) {
            int outerKey = outerEntry.getKey();  // Extract the outer key
            Map<Integer, Cell> innerMap = outerEntry.getValue();  // Extract the inner map

            // Iterate over the inner map
            for (Map.Entry<Integer, Cell> innerEntry : innerMap.entrySet()) {

                Cell cell = innerEntry.getValue();   // Extract the cell
                if(!cell.getEntities().isEmpty()) {
                    //Find path to desired point --> add that path to the cell
                    pathfinder.findPath(cell.getCelLIndexes(), new Point(36, 8));
                    cell.setAgroPath(pathfinder.getPath());
                }
            }
        }
    }
}
