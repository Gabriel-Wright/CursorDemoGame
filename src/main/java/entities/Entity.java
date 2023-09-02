package entities;

import animations.EntityAnimations;
import entities.constants.EntityConstants;
import levels.Level;

import java.awt.*;

import static main.GamePanel.TILE_SIZE;

public abstract class Entity {

    protected Level level;

    protected EntityAnimations animations;
    protected int aniTick = 0, aniIndex = 0, aniSpeed;
    protected int[] animationFlags;
    protected int entityWidth, entityHeight;
    protected EntityConstants entityConstants;

    //Entity Position related variables
    protected int worldX, worldY;
    protected int newWorldX, newWorldY;

    protected int speed;
    protected int xMove;
    protected int yMove;
    protected boolean moving = false;
    protected int action;
    protected Rectangle bounds; // May need to create own class at some point for hitboxes

    public Entity(int x, int y, EntityConstants entityConstants, Level level) {
        this.level = level;
        this.worldX = x;
        this.worldY = y;
        this.entityConstants = entityConstants;
        bounds = new Rectangle(0, 0, entityWidth, entityHeight);
        loadClassValuesFromConstants();
        loadAnimations();
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

    protected void move() {
        moveX();
        moveY();
    }

    private void moveX() {
        if (xMove > 0) {//Moving right
            int tx = (worldX + xMove + bounds.x + bounds.width) / TILE_SIZE;
            if (!level.isSolidTile(tx, (worldY + bounds.y) / TILE_SIZE) && !level.isSolidTile(tx, (worldY + bounds.y + bounds.height) / TILE_SIZE)) {
                worldX += xMove;
            } else {
                worldX = tx *TILE_SIZE - bounds.x - bounds.width - 1;
            }
        } else if (xMove < 0) {
            int tx = (worldX + xMove + bounds.x) / TILE_SIZE;
            if (!level.isSolidTile(tx, (worldY + bounds.y) / TILE_SIZE) && !level.isSolidTile(tx, (worldY + bounds.y + bounds.height) / TILE_SIZE)) {
                worldX += xMove;
            } else {
                worldX = tx * TILE_SIZE +TILE_SIZE - bounds.x;
            }
        }
    }

    private void moveY() {
        if (yMove < 0) { //up
            int ty = (worldY + yMove + bounds.y) / TILE_SIZE;
            if (!level.isSolidTile((worldX + bounds.x) / TILE_SIZE, ty) && !level.isSolidTile((worldX + bounds.x + bounds.width) / TILE_SIZE, ty)) {
                worldY += yMove;
            } else {
                worldY = ty * TILE_SIZE+ TILE_SIZE - bounds.y;
            }
        } else if (yMove > 0) { // down
            int ty = (worldY + yMove + bounds.y + bounds.height) / TILE_SIZE;
            if (!level.isSolidTile((worldX + bounds.x) / TILE_SIZE, ty) && !level.isSolidTile((worldX + bounds.x + bounds.width) / TILE_SIZE, ty)) {
                worldY += yMove;
            } else {
                worldY = ty * TILE_SIZE -bounds.y -bounds.height - 1;
            }

        }
    }

    public void update() {
        updatePos();
        updateAction();
        updateAnimationTick();
    }

    protected abstract void updatePos();

    protected abstract void updateAction();

    //    protected void moveX() {
//        if (xMove > 0) { // Moving right
//            int tx = (xMove + hitbox.x + hitbox.width) / TILE_SIZE; //Tile X index
//            if(!level.isSolidTile(tx, hitbox.y / TILE_SIZE) && !level.isSolidTile(tx, (hitbox.y + hitbox.height)/ TILE_SIZE)) {
//                worldX += xMove;
//            }
//        } else if (xMove <0) { // Moving left
//            worldX += xMove;
//        }
//        updateHitboxX();
//    }
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

    public void render(Graphics g) {
        animations.drawImage(g, action, aniIndex, (int) worldX, (int) worldY, entityWidth, entityHeight);
    }

    public float getWorldX() {
        return worldX;
    }

    public float getWorldY() {
        return worldY;
    }

    public int getNewWorldX() {
        return newWorldX;
    }

    public int getNewWorldY() {
        return newWorldY;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
}
