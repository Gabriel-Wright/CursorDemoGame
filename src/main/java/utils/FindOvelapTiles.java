package utils;

import gameObjects.entities.Entity;

import java.awt.*;

import static main.GamePanel.TILE_SIZE;

public class FindOvelapTiles {

    private static int testVariable;
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

    public static Point[] FindOverlapTiles(Entity entity, float xOffset, float yOffset) {
        int startX = (int) (entity.getX()+entity.getBounds().getX() + xOffset) / TILE_SIZE;
        int startY = (int) (entity.getY()+entity.getBounds().getY() + yOffset) / TILE_SIZE;
        int endX = (int) (entity.getX() + entity.getBounds().getX()+ entity.getBounds().getWidth() + xOffset) / TILE_SIZE;
        int endY = (int) (entity.getY() + entity.getBounds().getY() + entity.getBounds().getHeight() + yOffset) / TILE_SIZE;

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
        int startX = (int) (entity.getX()+entity.getBounds().getX()) / TILE_SIZE;
        int startY = (int) (entity.getY()+entity.getBounds().getY()) / TILE_SIZE;
        int endX = (int) (entity.getX() + entity.getBounds().getX()+ entity.getBounds().getWidth()) / TILE_SIZE;
        int endY = (int) (entity.getY() + entity.getBounds().getY() + entity.getBounds().getHeight()) / TILE_SIZE;

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
