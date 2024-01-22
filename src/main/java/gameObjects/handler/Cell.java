package gameObjects.handler;

import gameObjects.entities.Entity;
import gameObjects.entities.player.Player;
import gameObjects.entities.player.GameCursor;
import gameObjects.events.generic.PositionalEvent;
import levels.Level;
import gameObjects.objects.SuperObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static inputs.KeyHandler.pathFindingDisplay;
import static main.GamePanel.TILE_SIZE;

public class Cell {
    private Point celLIndexes;
    private List<Entity> entities;
    private List<SuperObject> objects;
    private List<PositionalEvent> positionalEvents;
    private Player player;

    //These variables store the relevant pathfinding info to reach the player for this cell
    //The bools flag whether any entities in the cell are on this path
    private List<Point> agroPath;
    private Point nextAgroPathPoint;

    public Cell(int x, int y) {
        this.celLIndexes = new Point(x,y);
        entities = new ArrayList<>();
        objects = new ArrayList<>();
        positionalEvents = new ArrayList<>();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void addObject(SuperObject object) {
        objects.add(object);
    }

    public void addPositionalEvent(PositionalEvent positionalEvent) {
        positionalEvents.add(positionalEvent);
    }


    public void removePlayer() {
        this.player = null;
    }
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }

    public void removePositionalEvent(PositionalEvent positionalEvent){
        positionalEvents.remove(positionalEvent);
    }

    public void removeObject(SuperObject object) {
        objects.remove(object);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<SuperObject> getObjects() {
        return objects;
    }

    public List<PositionalEvent> getPositionalEvents() {
        return positionalEvents;
    }


    public void renderEntities(Graphics g, Level level) {
        for(Entity entity: entities) {
            if(!entity.renderedThisFrame) {
                entity.render(g, level);
            }
        }
        //Won't work if camera can move
        if(pathFindingDisplay && agroPath!=null) {
            for(Point pathPoint: agroPath) {
                g.setColor(new Color(255,0,0,70));
                g.fillRect((int) (pathPoint.x*TILE_SIZE-level.getLevelCamera().getxOffset()),(int)(pathPoint.y*TILE_SIZE-level.getLevelCamera().getyOffset()),TILE_SIZE,TILE_SIZE);
            }
        }
    }
    public void renderObjects(Graphics g, Level level) {
        for(SuperObject object: objects) {
            object.renderObject(g, level);
        }
    }
    public void renderEvents(Graphics g, Level level) {
        for(PositionalEvent event: positionalEvents) {
            event.render(g, level);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Entities in this cell:\n");
        for (Entity entity : entities) {
            sb.append(entity.toString()).append("\n");
        }

        sb.append("Objects in this cell:\n");
        for (SuperObject object : objects) {
            sb.append(object.toString()).append("\n");
        }

        return sb.toString();
    }

    public List<Point> getAgroPath() {
        return agroPath;
    }

    public void setAgroPath(List<Point> agroPath) {
        this.agroPath = agroPath;
    }

    public Point getNextAgroPathPoint() {
        return nextAgroPathPoint;
    }

    public void setNextAgroPathPoint(Point nextAgroPathPoint) {
        this.nextAgroPathPoint = nextAgroPathPoint;
    }

    public Point getCellIndexes() {
        return celLIndexes;
    }
}
