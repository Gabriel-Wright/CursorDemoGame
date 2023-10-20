package gameObjects.entities.player;

import gameObjects.entities.Entity;
import gameObjects.entities.constants.EntityConstants;
import gameObjects.handler.Cell;
import gameObjects.handler.GameObjectGrid;
import gameObjects.objects.SuperObject;
import levels.Level;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static inputs.KeyHandler.*;

public class Player extends Entity {

//    private Inventory playerInventory = new Inventory();

    public Player(int x, int y, PlayerConstants playerConstants) {
        super(x, y, playerConstants);
    }

//    public Inventory getPlayerInventory() {
//        return playerInventory;
//    }

    @Override
    public void update(Level level, GameObjectGrid gameObjectGrid) {
        //Update position calculations
        updatePos();
        //Verify states/positions based on collisions within level post movement
        move(level);
        //Handle local event triggers
        handleLocalEventTriggers(gameObjectGrid);
        //Check entity collisions
        handleLocalEntityCollisions(gameObjectGrid);
        //Check object collisions
        handleLocalObjectCollisions(level, gameObjectGrid);
        //Centre camera on player
        centerCamera(level);
        //Update action state of the entity
        updateAction();
        //Adjust the entity animation based on its action state
        updateAnimationTick();

        //If entity has moved in this update - reassign its grid position
        if (Math.abs(xMove) > 0.001 || Math.abs(yMove) > 0.001) {
            // Do something when xMove or yMove is not close to 0
            gameObjectGrid.reassignPlayerCells(this, -xMove, -yMove);
        }
    }

    @Override
    protected void handleEntityCollision(Entity entity) {
        if(getCollisionBounds().intersects(entity.getCollisionBounds())) {
//            System.out.println("Collision");
            entity.checkActionChangeAniIndexAniTick(EntityConstants.DEAD);
        }
    }
    private void handleLocalObjectCollisions(Level level, GameObjectGrid gameObjectGrid) {
        Point[] cellIndexes = gameObjectGrid.getAssignedCells(this).toArray(new Point[0]);
        Set<SuperObject> cellObjects = new HashSet<>();
        //Retrieve all entities
        for(Point cellIndex: cellIndexes) {
            Cell cell = gameObjectGrid.getCell(cellIndex.x, cellIndex.y);
            cellObjects.addAll(cell.getObjects());
        }

        //Complete collisions for those entities
        for(SuperObject cellObject: cellObjects) {
            //Check entity is not itself
            handleLocalCollision(level, cellObject);
        }

    }

    private void handleLocalCollision(Level level, SuperObject cellObject) {
        cellObject.getEvent().runEvent();
    }
    //This is intended for only single trigger in a small space
    private void handleLocalEventTriggers(GameObjectGrid gameObjectGrid) {
        Point[] cellIndexes = gameObjectGrid.getAssignedCells(this).toArray(new Point[0]);
        for(Point cellIndex: cellIndexes) {
            if(!gameObjectGrid.getCell(cellIndex.x, cellIndex.y).getPositionalEvents().isEmpty()){
                gameObjectGrid.getCell(cellIndex.x, cellIndex.y).runEvents();
            }
        }
    }


    @Override
    protected void updatePos() {
        xMove = 0;
        yMove = 0;
        moving = false;
        if (leftPressed) {
            moving = true;
            xMove -= speed;
        }
        if (rightPressed) {
            moving = true;
            xMove += speed;
        }
        if (downPressed) {
            moving = true;
            yMove += speed;
        }
        if (upPressed) {
            moving = true;
            yMove -= speed;
        }

        //move();
        //checkObjectCollide();
    }


    @Override
    //May need to make some changes to this e.g. if changed action make sure that animationIndex is reset to 0, or something like that.
    //Through this method I could end up entering an animation for a different action too far in e.g. start at animation
    protected void updateAction() {
        if (moving) {
            checkActionChangeAniIndexAniTick(PlayerConstants.WALKING);
            action = PlayerConstants.WALKING;
        } else {
            checkActionChangeAniIndexAniTick(PlayerConstants.IDLE);
            action = PlayerConstants.IDLE;
        }
    }


}
