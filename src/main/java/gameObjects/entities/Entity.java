package gameObjects.entities;

import animations.EntityAnimations;
import gameObjects.entities.constants.EntityConstants;
import gameObjects.handler.GameObjectGrid;
import levels.Level;

import java.awt.*;

import static main.GamePanel.TILE_SIZE;

public abstract class Entity {


    protected EntityAnimations animations;
    protected int aniTick = 0, aniIndex = 0, aniSpeed;
    protected int[] animationFlags;
    protected int entityWidth, entityHeight;
    protected EntityConstants entityConstants;

    //Entity Position related variables
    protected float x, y;
    protected int newWorldX, newWorldY;

    protected float speed;
    protected float xMove;
    protected float yMove;
    protected boolean moving = false;
    protected int action;
    protected Rectangle bounds; // May need to create own class at some point for hitboxes

    public Entity(int x, int y, EntityConstants entityConstants) {
        this.x = x;
        this.y = y;
        this.entityConstants = entityConstants;
        bounds = new Rectangle(0, 0, entityWidth, entityHeight);
        if(entityConstants!=null) {
            loadClassValuesFromConstants();
            loadAnimations();
        }
        //Default bounds
    }

    private void loadClassValuesFromConstants() {
        bounds.x = entityConstants.getHitboxXoffset();
        bounds.y = entityConstants.getHitboxYoffset();
        bounds.width = entityConstants.getHitboxWidth();
        bounds.height = entityConstants.getHitboxHeight();

        this.aniSpeed = entityConstants.getAnimationSpeed();
        this.animationFlags = entityConstants.getAnimationFlags();
        this.animations = new EntityAnimations(entityConstants);
        this.entityWidth = entityConstants.getEntityWidth();
        this.entityHeight = entityConstants.getEntityHeight();
        this.speed = entityConstants.getSpeed();
    }

    private void loadAnimations() {
        animations.loadAnimations();
    }



    //Centres the camera on the entities position
    private void centerCamera(Level level) {
        level.getLevelCamera().centerOnPos(x, y);
    }

    protected void move(Level level) {
        checkLevelCollisions(level);
        x +=xMove;
        y +=yMove;
        centerCamera(level);
    }

    private void checkLevelCollisions(Level level) {
        checkLevelCollisionXMove(level);
        checkLevelCollisionYMove(level);
    }


    //Adjusting these to change position immediately - we will see how this affects
    private void checkLevelCollisionXMove(Level level) {
        if (xMove > 0) {//Moving right
            //Check right bound of entity
            int tx = (int) ((x + xMove + bounds.x + bounds.width) / TILE_SIZE);
            //If tile overlapping the right bound of the entity is solid then change the xMove value so that the player lines up
            //Maybe adjust this so that the xValue is set here?
            if (level.isSolidTile(tx, (int) (y + bounds.y) / TILE_SIZE) || level.isSolidTile(tx, (int) (y + bounds.y + bounds.height) / TILE_SIZE)) {
                xMove = tx * TILE_SIZE - bounds.x - bounds.width - 1 - x;
            }
        } else if (xMove < 0) { //Moving left
            //Check left bound of entity
            int tx = (int) (x + xMove + bounds.x) / TILE_SIZE;
            //If tile overlapping the left bound of the entity is solid then change the xMove value so that the player lines up
            if (level.isSolidTile(tx, (int) (y + bounds.y) / TILE_SIZE) || level.isSolidTile(tx, (int) (y + bounds.y + bounds.height) / TILE_SIZE)) {
                xMove = tx * TILE_SIZE + TILE_SIZE - bounds.x - x;
            }
        }

    }

    private void checkLevelCollisionYMove(Level level) {
        if (yMove < 0) { //Moving up
            //Check upper bound of entity
            int ty = (int) (y + yMove + bounds.y) / TILE_SIZE;
            //If tile overlapping the upper bound of the entity is solid then change the yMove value so that the player lines up with that tile
            if (level.isSolidTile((int) (x + bounds.x) / TILE_SIZE, ty) || level.isSolidTile((int) (x + bounds.x + bounds.width) / TILE_SIZE, ty)) {
                yMove = ty * TILE_SIZE + TILE_SIZE - bounds.y - y;
            }
        } else if (yMove > 0) { // Moving down
            //Check lower bound of entity
            int ty = (int) (y + yMove + bounds.y + bounds.height) / TILE_SIZE;
            //If tile overlapping the lower bound of the entity is solid then change the yMove value so that hte player lines up with that tile
            if (level.isSolidTile((int) (x + bounds.x) / TILE_SIZE, ty) || level.isSolidTile((int) (x + bounds.x + bounds.width) / TILE_SIZE, ty)) {
                yMove = ty * TILE_SIZE - bounds.y - bounds.height - 1 - y;
            }
        }
    }
    //This should be improved

    public void update(Level level, GameObjectGrid gameObjectGrid) {
        //Update position calculations
        updatePos();
        //Verify states/positions based on collisions within level post movement
        move(level);
        //Update action state of the entity
        updateAction();
        //Adjust the entity animation based on its action state
        updateAnimationTick();

        //If entity has moved in this update - reassign its grid position
        if(xMove> 0 || yMove >0) {
            gameObjectGrid.reassignEntityCells(this, xMove, yMove);
        }
    }

    protected abstract void updatePos();

    protected abstract void updateAction();

    protected void checkActionChangeAniIndexAniTick(int action) {
        if (this.action != action) {
            aniIndex = 0;
            aniTick = 0;
        }
    }

    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= entityConstants.getNumAnimationFrames(action)) {
                aniIndex = 0;
            }
        }
    }

    public void render(Graphics g, Level level) {
        animations.drawImage(g, action, aniIndex, (int) x, (int) y, entityWidth, entityHeight);
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) +(x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public Rectangle getBounds() {
        return bounds;
    }

    public void setX(float x) {
        this. x = x;
    }
    public void setY(float y) {
        this.y = y;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

}
