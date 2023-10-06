package gameObjects.entities.enemies.GreenDeath;

import gameObjects.entities.constants.EntityConstants;

import static main.GamePanel.TILE_SIZE;
import static main.GamePanel.UPS;


public class GreenDeathConstants extends EntityConstants {

    public static final int IDLE = 1;
    public static final int DEAD = 0;
    @Override
    public String getAnimationPath() {
        return "/entities/greendeath.png";
    }

    @Override
    public int[] getAnimationFlags() {
        int[] animationFlags = {IDLE, DEAD};
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
    public int getMaxNumAnimationFrames() {
        return 3;
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
    public int getAnimationWidth(int entityAction) {
        return 32;
    }

    @Override
    public int getAnimationHeight(int entityAction) {
        return 32;
    }

    @Override
    public int getAnimationSpeed() {
        return UPS/2;
    }

    @Override
    public float getSpeed() {
        return 3;
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
