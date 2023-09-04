package entities.player;

import entities.constants.EntityConstants;

import static main.GamePanel.TILE_SIZE;
import static main.GamePanel.UPS;
public class PlayerConstants extends EntityConstants {

    public static final int IDLE = 0;
    public static final int WALKING = 1;

    @Override
    public float getSpeed() {
        return 5.0f;
    }

    @Override
    public String getAnimationPath() {
        return "/entities/playerIdleMove.png";
    }

    @Override
    public int[] getAnimationFlags() {
        int[] animationFlags = {IDLE, WALKING};
        return animationFlags;
    }

    @Override
    public int getNumAnimationFrames(int playerAction) {
        switch(playerAction) {
            case IDLE:
                return 2;
            case WALKING:
                return 3;
        }
        return 0;
    }

    @Override
    public int getMaxNumAnimationFrames() {
        return 3;
    }

    @Override
    public int getAnimationStartXDimension(int playerAction) {
        return 1;
    }

    @Override
    public int getAnimationStartYDimension(int playerAction) {
        switch(playerAction){
            case IDLE:
                return 1;
            case WALKING:
                return 66;
        }
        return 1;
    }

    @Override
    public int getAnimationWidth(int playerAction) {
        return 32;
    }

    @Override
    public int getAnimationHeight(int playerAction) {
        return 64;
    }

    @Override
    public int getAnimationSpeed() {
        return UPS/3;
    }

    @Override
    public int getEntityWidth() {
        return TILE_SIZE;
    }

    @Override
    public int getEntityHeight() {
        return TILE_SIZE*2;
    }

    @Override
    public int getHitboxXoffset() {
        return 10;
    }

    @Override
    public int getHitboxYoffset() {
        return 20;
    }

    @Override
    public int getHitboxWidth() {
        return TILE_SIZE/2;
    }

    @Override
    public int getHitboxHeight() {
        return TILE_SIZE;
    }

}
