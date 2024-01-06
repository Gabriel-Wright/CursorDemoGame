package gameObjects.entities.enemies.GreenDeath;

import gameObjects.entities.constants.AnimationConstants;
import gameObjects.entities.constants.EntityConstants;
import gameObjects.events.entity.EntityEvent;
import gameObjects.events.entity.GreenDeathEvents;

import static main.GamePanel.*;


public class GreenDeathConstants extends EntityConstants {
    @Override
    public String getAnimationPath() {
        return "/entities/greendeath.png";
    }

    @Override
    public int[] getAnimationFlags() {
        int[] animationFlags = {DEAD, IDLE};
        return animationFlags;
    }

    @Override
    public int getNumAnimationFrames(int entityAction) {
        switch(entityAction) {
            case IDLE, DEAD:
                return 3;

        }
        return 0;
    }

    @Override
    public int getAnimationStartXDimension(int entityAction) {
        return 0;
    }

    @Override
    public int getAnimationStartYDimension(int entityAction) {
        switch (entityAction) {
            case IDLE:
                return 32;
            case DEAD:
                return 0;
        }
        return 0;
    }

    @Override
    public EntityEvent getEntityEvent() {
        return new GreenDeathEvents();
    }

    @Override
    public int getAnimationWidth(int entityAction) {
        return 32;
    }

    @Override
    public int getAnimationHeight(int entityAction) {
        return 32;
    }

    @Override
    public int getAnimationSpeed() {
        return FPS/2;
    }

    @Override
    public float getSpeed() {
        float originalSpeed = (float) TILE_SIZE/16;
        return Math.round(originalSpeed * 10.0) / 10.0f;
    }

    @Override
    public int getEntityWidth() {
        return TILE_SIZE;
    }

    @Override
    public int getEntityHeight() {
        return TILE_SIZE;
    }

    @Override
    public int getHitboxXoffset() {
        return 3+TILE_SIZE/4;
    }

    @Override
    public int getHitboxYoffset() {
        return TILE_SIZE/4;
    }

    @Override
    public int getHitboxWidth() {
        return TILE_SIZE/2;
    }

    @Override
    public int getHitboxHeight() {
        return TILE_SIZE/2;
    }

}
