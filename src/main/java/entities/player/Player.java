package entities.player;

import entities.Entity;
import levels.Level;

import java.awt.*;

import static inputs.KeyHandler.*;
import static main.GamePanel.SCREEN_WIDTH;
import static main.GamePanel.SCREEN_HEIGHT;
import static main.GamePanel.TILE_SIZE;

public class Player extends Entity {

    //Position that entity is placed on the screen.
    private int screenX = SCREEN_WIDTH / 2 - (TILE_SIZE / 2);
    private int screenY = SCREEN_HEIGHT / 2 - (TILE_SIZE / 2);

    public Player(int x, int y, PlayerConstants playerConstants, Level level) {
        super(x, y, playerConstants, level);
    }

    @Override
    protected void updatePos() {
        xMove = 0;
        yMove = 0;
        moving = false;
        if (leftPressed) {
            moving = true;
            xMove -= speed;
        }
        if (rightPressed) {
            moving = true;
            xMove += speed;
        }
        if (downPressed) {
            moving = true;
            yMove += speed;
        }
        if (upPressed) {
            moving = true;
            yMove -= speed;
        }

        move();
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
        g.setColor(Color.WHITE);
        g.fillRect(screenX + bounds.x, screenY + bounds.y, bounds.width, bounds.height);
    }

}
