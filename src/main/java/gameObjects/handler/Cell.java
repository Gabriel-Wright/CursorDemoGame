package gameObjects.handler;

import gameObjects.entities.Entity;
import levels.Level;
import net.bytebuddy.implementation.bind.annotation.Super;
import object.SuperObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cell {
    private List<Entity> entities;
    private List<SuperObject> objects;

    public Cell() {
        entities = new ArrayList<>();
        objects = new ArrayList<>();
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void addObject(SuperObject object) {
        objects.add(object);
    }

    public void removeEntity(Entity entity) {
        entities.remove(entity);
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

    public void renderEntities(Graphics g, Level level) {
        for(Entity entity: entities) {
            entity.render(g, level);
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
