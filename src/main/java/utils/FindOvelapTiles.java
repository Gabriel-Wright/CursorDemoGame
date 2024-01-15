package utils;

import gameObjects.entities.Entity;
import gameObjects.entities.player.Cursor;
import java.awt.*;

import static main.GamePanel.TILE_SIZE;

public class FindOvelapTiles {

    public static Point[] FindOverlapTiles(Rectangle collisionBox) {
        int startX = collisionBox.x / TILE_SIZE;
        int startY = collisionBox.y / TILE_SIZE;
        int endX = (collisionBox.x + collisionBox.width) / TILE_SIZE;
        int endY = (collisionBox.y + collisionBox.height) / TILE_SIZE;

        int numTiles = (endX - startX + 1) * (endY - startY + 1);
        Point[] tileIndexes = new Point[numTiles];
        int i = 0;
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                tileIndexes[i] = new Point(x, y);
                i++;
            }
        }
        return tileIndexes;
    }


//    Probably better to add a small hitbox for the cursor
    public static Point[] FindOverlapTiles(Cursor cursor, float xOffset, float yOffset) {
        int startX = (int) (cursor.getMouseX() + cursor.getCursorHitbox().x + xOffset)/TILE_SIZE;
        int startY = (int) (cursor.getMouseY() + cursor.getCursorHitbox().y +yOffset)/TILE_SIZE;
        int endX = (int) (cursor.getMouseX() + cursor.getCursorHitbox().width + cursor.getCursorHitbox().x + xOffset)/TILE_SIZE;
        int endY = (int) (cursor.getMouseY() + cursor.getCursorHitbox().height + cursor.getCursorHitbox().y + yOffset)/TILE_SIZE;

        int numTiles = (endX - startX +1) * (endY - startY +1);
        Point[] tileIndexes = new Point[numTiles];
        int i=0;
        for(int x = startX; x<=endX; x++) {
            for(int y= startY; y <=endY; y++) {
                tileIndexes[i] = new Point(x,y);
                i++;
            }
        }
        return tileIndexes;
    }

    public static Point[] FindOverlapTiles(Cursor cursor) {
        int startX = (int) (cursor.getMouseX() + cursor.getCursorHitbox().x)/TILE_SIZE;
        int startY = (int) (cursor.getMouseY() + cursor.getCursorHitbox().y)/TILE_SIZE;
        int endX =  (int) (cursor.getMouseX() + cursor.getCursorHitbox().width + cursor.getCursorHitbox().x)/TILE_SIZE;
        int endY = (int) (cursor.getMouseY() + cursor.getCursorHitbox().height + cursor.getCursorHitbox().y)/TILE_SIZE;

        int numTiles = (endX - startX +1) * (endY - startY +1);
        Point[] tileIndexes = new Point[numTiles];
        int i=0;
        for(int x = startX; x<=endX; x++) {
            for(int y= startY; y <=endY; y++) {
                tileIndexes[i] = new Point(x,y);
                i++;
            }
        }
        return tileIndexes;
    }

    public static Point[] FindOverlapTiles(Entity entity, float xOffset, float yOffset) {
        int startX = (int) Math.floor((entity.getX() + entity.getBounds().getX() + xOffset) / TILE_SIZE);
        int startY = (int) Math.floor((entity.getY() + entity.getBounds().getY() + yOffset) / TILE_SIZE);
        int endX = (int) Math.floor((entity.getX() + entity.getBounds().getX() + entity.getBounds().getWidth() + xOffset) / TILE_SIZE);
        int endY = (int) Math.floor((entity.getY() + entity.getBounds().getY() + entity.getBounds().getHeight() + yOffset) / TILE_SIZE);

        int numTiles = (endX - startX +1) * (endY - startY +1);
        Point[] tileIndexes = new Point[numTiles];
        int i=0;
        for(int x = startX; x<=endX; x++) {
            for(int y= startY; y <=endY; y++) {
                tileIndexes[i] = new Point(x,y);
                i++;
            }
        }
        return tileIndexes;
    }

    public static Point[] FindOverlapTiles(Entity entity) {
        int startX = (int) Math.floor((entity.getX()+entity.getBounds().getX()) / TILE_SIZE);
        int startY = (int) Math.floor((entity.getY()+entity.getBounds().getY()) / TILE_SIZE);
        int endX = (int) Math.floor((entity.getX() + entity.getBounds().getX()+ entity.getBounds().getWidth()) / TILE_SIZE);
        int endY = (int) Math.floor((entity.getY() + entity.getBounds().getY() + entity.getBounds().getHeight()) / TILE_SIZE);

        int numTiles = (endX - startX +1) * (endY - startY +1);
        Point[] tileIndexes = new Point[numTiles];
        int i=0;
        for(int x = startX; x<=endX; x++) {
            for(int y= startY; y <=endY; y++) {
                tileIndexes[i] = new Point(x,y);
                i++;
            }
        }
        return tileIndexes;
    }

}
