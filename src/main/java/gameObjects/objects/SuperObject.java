package gameObjects.objects;

import levels.Level;
import gameObjects.events.generic.Event;
import java.awt.*;
import java.awt.image.BufferedImage;

import static inputs.KeyHandler.hitboxToggle;
import static main.GamePanel.TILE_SIZE;

public class SuperObject {

    protected BufferedImage objectImage;
    //Whether Object has been interacted with yet
    protected boolean collided = false;
    //Only one object per tile
    protected int x, y;
    protected String objectName;
    protected Rectangle objectCollisionBox;
    protected Event collisionEvent;
    public SuperObject(int x, int y, int objectID, ObjectConstants objectConstants) {
        this.x = x;
        this.y =y;
        objectCollisionBox = new Rectangle(x*TILE_SIZE, y*TILE_SIZE, objectConstants.getObjectWidth(objectID), objectConstants.getObjectHeight(objectID));
        this.objectImage = objectConstants.getObjectImage(objectID);
        this.objectName = objectConstants.getObjectName(objectID);
        this.collisionEvent = objectConstants.getObjectEvent(objectID);
    }

    public Rectangle getObjectCollisionBox() {
        return objectCollisionBox;
    }


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

    public Event getEvent() {
        return collisionEvent;
    }
    public String getObjectName() {
        return objectName;
    }

    public void renderObject(Graphics g, Level level) {
        int xPos = (int) (x*TILE_SIZE - level.getLevelCamera().getxOffset());
        int yPos = (int) (y*TILE_SIZE - level.getLevelCamera().getyOffset());

        g.drawImage(objectImage,xPos,yPos,TILE_SIZE,TILE_SIZE,null);
        if(hitboxToggle) {
            g.setColor(Color.WHITE);
            g.drawRect(xPos, yPos, TILE_SIZE, TILE_SIZE);
        }
    }
}
