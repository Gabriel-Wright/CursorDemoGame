package entities.player;

import entities.constants.EntityConstants;

public class PlayerConstants extends EntityConstants {
    @Override
    public String getAnimationPath() {
        return null;
    }

    @Override
    public int[] getAnimationFlags() {
        return new int[0];
    }

    @Override
    public int getNumAnimationFrames(int playerAction) {
        return 0;
    }

    @Override
    public int getMaxNumAnimationFrames() {
        return 0;
    }

    @Override
    public int getAnimationStartXDimension(int playerAction) {
        return 0;
    }

    @Override
    public int getAnimationStartYDimension(int playerAction) {
        return 0;
    }

    @Override
    public int getAnimationWidth(int playerAction) {
        return 0;
    }

    @Override
    public int getAnimationHeight(int playerAction) {
        return 0;
    }

    @Override
    public int getEntityWidth() {
        return 0;
    }

    @Override
    public int getEntityHeight() {
        return 0;
    }
}
