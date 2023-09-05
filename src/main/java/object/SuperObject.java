package object;

import entities.Entity;
import entities.player.Player;
import levels.Level;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.GamePanel.TILE_SIZE;

public abstract class SuperObject {

    protected BufferedImage objectImage;
    //Whether Object has been interacted with yet
    protected boolean collided = false;
    //Only one object per tile
    protected int x, y;
    protected Rectangle objectCollisionBox;
    public SuperObject(int x, int y, BufferedImage objectImage, boolean collided) {
        this.x = x;
        this.y = y;
        objectCollisionBox = new Rectangle(x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
        this.objectImage = objectImage;
        this.collided = collided;
    }

    public abstract void CollideWithEntity(Entity entity, Level level);
    public abstract void CollideWithEntity(Player player, Level level);
    public BufferedImage getObjectImage() {
        return objectImage;
    }

    public Rectangle getObjectCollisionBox() {
        return objectCollisionBox;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void setCollided(boolean collided) {
        this.collided = collided;
    }

    public boolean hasCollided() {
        return collided;
    }
}
