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
    protected String objectName;
    protected Rectangle objectCollisionBox;
//    public SuperObject(int x, int y, BufferedImage objectImage, boolean collided, int objectName) {
//        this.x = x;
//        this.y = y;
//        objectCollisionBox = new Rectangle(x*TILE_SIZE, y*TILE_SIZE, TILE_SIZE, TILE_SIZE);
//        this.objectImage = objectImage;
//        this.collided = collided;
//        this.objectName = objectName;
//    }
//
//    public SuperObject(int x, int y, int objectName, boolean collided) {
//        this.x = x;
//        this.y = y;
//        objectCollisionBox = new Rectangle(x*TILE_SIZE, y*TILE_SIZE, ObjectConstants.getObjectWidth(objectName), ObjectConstants.getObjectHeight(objectName));
//        this.objectImage =
//    }

    public SuperObject(int x, int y, int objectID, ObjectConstants objectConstants) {
        this.x = x;
        this.y =y;
        objectCollisionBox = new Rectangle(x*TILE_SIZE, y*TILE_SIZE, objectConstants.getObjectWidth(objectID), objectConstants.getObjectHeight(objectID));
        this.objectImage = objectConstants.getObjectImage(objectID);
        this.objectName = objectConstants.getObjectName(objectID);
    }

    public abstract void CollideWithEntity(Entity entity, Level level);
    public abstract void CollideWithEntity(Player player, Level level);
    public BufferedImage getObjectImage() {
        return objectImage;
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

    public boolean hasCollided() {
        return collided;
    }
}
