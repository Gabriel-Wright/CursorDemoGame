package levels;

import static main.GamePanel.*;

public class LevelCamera {

    private float xOffset, yOffset;
    private int levelWidth, levelHeight;

    public LevelCamera(float xOffset, float yOffset, int levelWidth, int levelHeight) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.levelWidth = levelWidth;
        this.levelHeight = levelHeight;
    }

    private void checkBlankSpace() {
        if (xOffset < 0) {
            xOffset = 0;
        } else if (xOffset > levelWidth * TILE_SIZE - SCREEN_WIDTH) {
            xOffset = levelWidth * TILE_SIZE - SCREEN_WIDTH;
        }
        if (yOffset < 0) {
            yOffset = 0;
        } else if (yOffset > levelHeight * TILE_SIZE - SCREEN_HEIGHT) {
            yOffset = levelHeight * TILE_SIZE - SCREEN_HEIGHT;
        }
    }

    public void centerOnPos(float x, float y) {
        xOffset = x - SCREEN_WIDTH / 2;
        yOffset = y - SCREEN_HEIGHT / 2;
        checkBlankSpace();
    }

    public void move(float x, float y) {
        xOffset += x;
        yOffset += y;
    }

    public float getxOffset() {
        return xOffset;
    }

    public float getyOffset() {
        return yOffset;
    }
}
