package entities.player;

import entities.Entity;
import levels.Level;

import java.awt.*;

import static inputs.KeyHandler.*;
import static utils.FindOvelapTiles.FindOverlapTiles;

public class Player extends Entity {

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
        checkObjectCollide();
    }

    @Override
    protected void checkObjectCollide() {
        Point[] overlapTiles = FindOverlapTiles(getCollisionBounds(0, 0));
        for (Point objectIndexes : overlapTiles) {
            if (level.getLevelObjects()[objectIndexes.x][objectIndexes.y] != null) {
                level.getLevelObjects()[objectIndexes.x][objectIndexes.y].CollideWithEntity(this, level);
            }
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

    @Override
    public void render(Graphics g) {
        int entityXPos = (int) (x - level.getLevelCamera().getxOffset());
        int entityYPos = (int) (y - level.getLevelCamera().getyOffset());
        animations.drawImage(g, action, aniIndex, entityXPos, entityYPos, entityWidth, entityHeight);
        g.setColor(Color.WHITE);
        g.fillRect(entityXPos + bounds.x, entityYPos + bounds.y, bounds.width, bounds.height);
    }

}
