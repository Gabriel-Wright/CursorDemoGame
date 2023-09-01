package entities.player;

import entities.Entity;

import java.awt.*;

import static inputs.KeyHandler.*;
import static main.GamePanel.SCREEN_WIDTH;
import static main.GamePanel.SCREEN_HEIGHT;
import static main.GamePanel.TILE_SIZE;
public class Player extends Entity {

    //Position that entity is placed on the screen.
    private int screenX = SCREEN_WIDTH / 2 - (TILE_SIZE /2);
    private int screenY = SCREEN_HEIGHT / 2 - (TILE_SIZE /2);

    public Player(float x, float y, PlayerConstants playerConstants) {
        super(x, y, playerConstants);
    }

    @Override
    protected void updatePos() {
        moving = false;
        if (leftPressed) {
            moving = true;
            worldX -= speed;
        }
        if (rightPressed) {
            moving = true;
            worldX += speed;
        }
        if (downPressed) {
            moving = true;
            worldY += speed;
        }
        if (upPressed) {
            moving = true;
            worldY -= speed;
        }
    }

    @Override
    //May need to make some changes to this e.g. if changed action make sure that animationIndex is reset to 0, or something like that.
    //Through this method I could end up entering an animation for a different action too far in e.g. start at animation
    protected void updateAction() {
        if (moving) {
            checkActionChangeAniIndexAniTick(PlayerConstants.WALKING);
            action = PlayerConstants.WALKING;
        } else {
            checkActionChangeAniIndexAniTick(PlayerConstants.IDLE);
            action = PlayerConstants.IDLE;
        }
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    @Override
    public void render(Graphics g) {
        animations.drawImage(g, action, aniIndex, screenX, screenY, entityWidth, entityHeight);
    }

}
