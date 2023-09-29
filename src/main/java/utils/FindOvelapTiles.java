package utils;

import gameObjects.entities.Entity;

import java.awt.*;

import static main.GamePanel.TILE_SIZE;

//
//import entities.Entity;
//
//import static inputs.KeyHandler.leftPressed;
//import static inputs.KeyHandler.upPressed;
//import static main.GamePanel.TILE_SIZE;
//
//import java.awt.*;
//
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
        int startX = (int) (entity.getX()+entity.getBounds().getX() - xOffset) / TILE_SIZE;
        int startY = (int) (entity.getY()+entity.getBounds().getY() - yOffset) / TILE_SIZE;
        int endX = (int) (entity.getX() + entity.getBounds().getX()+ entity.getBounds().getWidth()- xOffset) / TILE_SIZE;
        int endY = (int) (entity.getY() + entity.getBounds().getY() + entity.getBounds().getHeight() - yOffset) / TILE_SIZE;

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


        // Debug prints
        System.out.println("startX: " + startX);
        System.out.println("startY: " + startY);
        System.out.println("endX: " + endX);
        System.out.println("endY: " + endY);

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
//    public static Point[] FindOverlapTilesHorizontal(Entity entity) {
//        if(leftPressed) {
//            return FindOverlapTilesLeftIndexes(entity);
//        } else {
//            return FindOverlapTilesRightIndexes(entity);
//        }
//    }
//
//    public static Point[] FindOverlapTilesVertical(Entity entity) {
//        if(upPressed) {
//            return FindOverlapTilesTopIndexes(entity);
//        } else {
//            return FindOverlapTilesBottomIndexes(entity);
//        }
//    }
//    public static Point[] FindOverlapTilesTopIndexes(Entity entity) {
//        Rectangle hitbox = entity.getHitbox();
//        int startTileX = (int) hitbox.getX() / TILE_SIZE;
//        int startTileY = (int) hitbox.getY() / TILE_SIZE;
//        int endTileX   = (int) (hitbox.getX()+hitbox.getWidth()-1) / TILE_SIZE;
//
//        int numOverLappingTilesX = endTileX - startTileX +1;
//        Point[] overLapIndexes    = new Point[numOverLappingTilesX];
//        int index = 0;
//
//        for (int tileX = startTileX; tileX <= endTileX; tileX++) {
//            overLapIndexes[index] = new Point(tileX, startTileY);
//            index++;
//        }
//        return overLapIndexes;
//
//    }
//
//    public static Point[] FindOverlapTilesLeftIndexes(Entity entity)  {
//        Rectangle hitbox = entity.getHitbox();
//        int startTileX = (int) hitbox.getX() / TILE_SIZE;
//        int startTileY = (int) hitbox.getY() / TILE_SIZE;
//        int endTileY   = (int) (hitbox.getY()+ hitbox.getHeight()-1)/TILE_SIZE;
//
//        int numOverlappingTilesY = endTileY - startTileY +1;
//        Point[] overLapIndexes = new Point[numOverlappingTilesY];
//        int index = 0;
//
//        for(int tileY = startTileY; tileY <= endTileY; tileY++) {
//            overLapIndexes[index] = new Point(startTileX, tileY);
//            index++;
//        }
//        return overLapIndexes;
//    }
//
//    public static Point[] FindOverlapTilesRightIndexes(Entity entity) {
//        Rectangle hitbox = entity.getHitbox();
//        int startX = (int) (hitbox.getX() + hitbox.getWidth()) / TILE_SIZE;
//        int startY = (int) (hitbox.getY()) / TILE_SIZE;
//        int endY   = (int) (hitbox.getY() + hitbox.getHeight()-1) / TILE_SIZE;
//
//        int numOverlappingTilesY = endY - startY +1;
//        Point[] overlapIndexes = new Point[numOverlappingTilesY];
//        int index = 0;
//
//        for(int tileY = startY;tileY<= endY; tileY++) {
//            overlapIndexes[index] = new Point(startX, tileY);
//            index++;
//        }
//        return overlapIndexes;
//    }
//
//    public static Point[] FindOverlapTilesBottomIndexes(Entity entity) {
//        Rectangle hitbox = entity.getHitbox();
//        int startX = (int) (hitbox.getX()) / TILE_SIZE;
//        int startY = (int) (hitbox.getY()+ hitbox.getHeight())/ TILE_SIZE;
//        int endTileX   = (int) (hitbox.getX()+hitbox.getWidth()-1) / TILE_SIZE;
//
//        int numOverLappingTilesX = endTileX - startX +1;
//        Point[] overlapIndexes = new Point[numOverLappingTilesX];
//        int index = 0;
//
//        for(int tileX = startX; tileX <= endTileX; tileX++) {
//            overlapIndexes[index] = new Point(tileX, startY);
//            index++;
//        }
//
//        return overlapIndexes;
}
//
//
