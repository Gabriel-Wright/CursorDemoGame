package entities;

import animations.EntityAnimations;
import entities.constants.EntityConstants;

import java.awt.*;

public abstract class Entity {

    protected EntityAnimations animations;
    protected int aniTick=0, aniIndex=0, aniSpeed;
    protected int[] animationFlags;
    protected int entityWidth, entityHeight;
    protected EntityConstants entityConstants;

    //Entity Position related variables
    protected float worldX, worldY;
    protected float speed;
    protected boolean moving=false;
    protected int action;
    public Entity(float x, float y, EntityConstants entityConstants) {
        this.worldX = x;
        this.worldY = y;
        this.entityConstants = entityConstants;
        loadClassValuesFromConstants();
        loadAnimations();
    }

    private void loadClassValuesFromConstants() {
        this.aniSpeed = entityConstants.getAnimationSpeed();
        this.animationFlags = entityConstants.getAnimationFlags();
        this.animations = new EntityAnimations(entityConstants);
        this.entityWidth = entityConstants.getEntityWidth();
        this.entityHeight = entityConstants.getEntityHeight();
        this.speed = entityConstants.getSpeed();
    }

    private void loadAnimations(){
        animations.loadAnimations();
    }

    public void update() {
        updatePos();
        updateAction();
        updateAnimationTick();
    }

    protected abstract void updatePos();

    protected abstract void updateAction();

    protected void checkActionChangeAniIndexAniTick(int action) {
        if(this.action!=action) {
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


}
