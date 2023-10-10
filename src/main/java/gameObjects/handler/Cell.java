package gameObjects.handler;

import gameObjects.entities.Entity;
import gameObjects.entities.player.Player;
import gameObjects.events.Event;
import gameObjects.events.PositionalEvent;
import levels.Level;
import net.bytebuddy.implementation.bind.annotation.Super;
import object.SuperObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cell {
    private List<Entity> entities;
    private List<SuperObject> objects;
    private List<PositionalEvent> positionalEvents;
    private Player player;
    public Cell() {
        entities = new ArrayList<>();
        objects = new ArrayList<>();
        positionalEvents = new ArrayList<>();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
    public void addPlayer(Player player) {
        this.player = player;
    }
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void addPositionalEvent(PositionalEvent positionalEvent) {
        positionalEvents.add(positionalEvent);
    }

    public void addObject(SuperObject object) {
        objects.add(object);
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

    public void runEvents() {
        for(PositionalEvent event: positionalEvents) {
            if(!event.isComplete()) {
                event.runEvent();
            }
        }
    }
    public void renderEntities(Graphics g, Level level) {
        for(Entity entity: entities) {
            if(!entity.renderedThisFrame) {
                entity.render(g, level);
            }
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

}
