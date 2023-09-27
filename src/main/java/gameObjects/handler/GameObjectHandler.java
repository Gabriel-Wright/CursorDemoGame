package gameObjects.handler;

import gameObjects.entities.Entity;
import levels.Level;
import object.SuperObject;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class GameObjectHandler {

    //Used to store all data of cells maps from (x,y) -> Cell
    private GameObjectGrid gameObjectGrid;
    private List<Entity> entities;
    private List<SuperObject> objects;
    //Also add event triggers
    public GameObjectHandler(int numRows, int numCols, List<Entity> entities, List<SuperObject> objects) {
        gameObjectGrid = new GameObjectGrid(numRows, numCols);
        this.entities = entities;
        this.objects = objects;
    }

    public void loadGameObjectHandler() {
        gameObjectGrid.initialiseGrid(entities, objects);
    }

    //Pass level as argument for logic calculations with tile collisions
    public void update(Level level) {
        for(Entity entity: entities) {
            entity.update();
        }
        //For loop for entity updates - movement and tile collision
        //Within same for loop do collisions with object grid
    }

    public void render(Graphics g) {

    }
}
