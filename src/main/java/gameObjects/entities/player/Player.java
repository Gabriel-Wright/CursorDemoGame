package gameObjects.entities.player;

import gameObjects.entities.Entity;
import gameObjects.entities.Inventory;
import gameObjects.entities.constants.EntityConstants;
import gameObjects.handler.GameObjectGrid;
import levels.Level;

import java.awt.*;

import static inputs.KeyHandler.*;

public class Player extends Entity {

    private Inventory playerInventory = new Inventory();

    public Player(int x, int y, PlayerConstants playerConstants) {
        super(x, y, playerConstants);
    }

    public Inventory getPlayerInventory() {
        return playerInventory;
    }

    @Override
    public void update(Level level, GameObjectGrid gameObjectGrid) {
        //Update position calculations
        updatePos();
        //Verify states/positions based on collisions within level post movement
        move(level);
        //Check entity collisions
        handleLocalEntityCollisions(level, gameObjectGrid);
        //Centre camera on player
        centerCamera(level);
        //Update action state of the entity
        updateAction();
        //Adjust the entity animation based on its action state
        updateAnimationTick();

        //If entity has moved in this update - reassign its grid position
        if(xMove> 0 || yMove >0) {
            gameObjectGrid.reassignEntityCells(this, -xMove, -yMove);
        }
    }

    @Override
    protected void handleEntityCollision(Entity entity) {
        if(getCollisionBounds().intersects(entity.getCollisionBounds())) {
            System.out.println("Collision");
            entity.checkActionChangeAniIndexAniTick(EntityConstants.DEAD);
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
