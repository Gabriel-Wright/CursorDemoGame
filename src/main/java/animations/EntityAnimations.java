package animations;

import gameObjects.entities.constants.AnimationConstants;
import utils.LoadFiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EntityAnimations {

    private BufferedImage spriteSheet;
    private BufferedImage[][] animationFrames;
    /**
     * These value refer to the first index of animationFrames - refer to the ACTION of a given entity.
     *  e.g 0 = IDLE. , 1 = WALK
     */
    private int[] animationFlags;
    /**
     * Constants relevant for given
     */
    private AnimationConstants animationConstants;

    public EntityAnimations(AnimationConstants animationConstants) {
        this.animationConstants = animationConstants;
        spriteSheet = LoadFiles.importImg(animationConstants.getAnimationPath());
        this.animationFlags = animationConstants.getAnimationFlags();
    }

    public void loadAnimations() {
        int numAnimations = animationFlags.length;
        int maxNumAnimationFrames = animationConstants.getMaxNumAnimationFrames();

        int numAnimationFrames;
        int action;

        int startXDim, startYDim, width, height;

        animationFrames = new BufferedImage[numAnimations][maxNumAnimationFrames];
        for(int i=0; i<numAnimations ;i++) {
            action = animationFlags[i];
            numAnimationFrames = animationConstants.getNumAnimationFrames(action);
            startYDim = animationConstants.getAnimationStartYDimension(action);
            startXDim = animationConstants.getAnimationStartXDimension(action);
            width = animationConstants.getAnimationWidth(action);
            height= animationConstants.getAnimationHeight(action);

            for(int j=0; j<numAnimationFrames;j++) {
                animationFrames[i][j] = spriteSheet.getSubimage(j+startXDim+(width)*j,startYDim,width,height);
            }
        }

    }

    /**
     *  Draws sprite at desired location using private class variable animationFrames.
     * @param g - Graphics object used to draw onto screen
     * @param action - Integer action index used to choose relevant animation
     * @param aniIndex - Frame index of this animation i.e. frame aniIndex of action animation
     * @param x - Position x on GamePanel where animation image will be drawn
     * @param y - Position y on GamePanel where animation image will be drawn
     * @param width - width of the animation image on the GamePanel
     * @param height - height of the animation image on the GamePanel
     */
    public void drawImage(Graphics g, int action, int aniIndex, int x, int y, int width, int height) {
        g.drawImage(animationFrames[action][aniIndex], x, y, width, height, null);
    }
}
