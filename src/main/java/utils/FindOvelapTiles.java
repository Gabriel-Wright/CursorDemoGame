//package utils;
//
//import entities.Entity;
//
//import static inputs.KeyHandler.leftPressed;
//import static inputs.KeyHandler.upPressed;
//import static main.GamePanel.TILE_SIZE;
//
//import java.awt.*;
//
//public class FindOvelapTiles {
//
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
//
//    }
//}
//
//
