package gameObjects.entities.player;

import gameObjects.entities.constants.EntityConstants;
import gameObjects.events.entity.EntityEvent;

import static main.GamePanel.TILE_SIZE;
import static main.GamePanel.UPS;
public class PlayerConstants extends EntityConstants {


    @Override
    public float getSpeed() {
//        return 5.0f;
        return (float) TILE_SIZE/24;
    }

    @Override
    public String getAnimationPath() {
        return "/entities/playeuniformSprite.png";
    }

    @Override
    public int[] getAnimationFlags() {
        int[] animationFlags = {IDLE, WALKING,SHOOTING};
        return animationFlags;
    }

    @Override
    public EntityEvent getEntityEvent() {
        return null;
    }

    @Override
    public int getNumAnimationFrames(int playerAction) {
        switch(playerAction) {
            case IDLE, WALKING:
                return 1;
            case SHOOTING:
                return 6;
        }
        return 0;
    }

    @Override
    public int getAnimationStartXDimension(int playerAction) {
        return 0;
    }

    @Override
    public int getAnimationStartYDimension(int playerAction) {
        switch(playerAction){
            case IDLE,WALKING:
                return 0;
            case SHOOTING:
                return 33;
        }
        return 1;
    }

    @Override
    public int getAnimationWidth(int playerAction) {
        return 32;
    }

    @Override
    public int getAnimationHeight(int playerAction) {
        return 32;
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
        return TILE_SIZE;
    }

    @Override
    public int getHitboxXoffset() {
        return TILE_SIZE/8;
    }

    @Override
    public int getHitboxYoffset() {
        return TILE_SIZE/8;
    }

    @Override
    public int getHitboxWidth() {
        return 3*TILE_SIZE/4;
    }

    @Override
    public int getHitboxHeight() {
        return 3*TILE_SIZE/4;
    }

}
