//package main;
//
//import entities.Entity;
//import levels.Level;
//import utils.FindOvelapTiles;
//
//import java.awt.*;
//
//import static inputs.KeyHandler.*;
//import static utils.FindOvelapTiles.*;
//import static main.GamePanel.TILE_SIZE;
//
//public class CollisionChecker {
//
//    public static boolean collisionWithTile(Level level, int x, int y) {
//        return level.getTile(x, y).isSolid();
//    }
//
//    public static void checkCornerCollision(Entity entity, Level level) {
//        int entityLeftXTile = (entity.getNewWorldX()) / TILE_SIZE;
//        int entityRightXTile = (entity.getNewWorldX() + entity.getHitbox().width) / TILE_SIZE;
//        int entityTopY = entity.getNewWorldY() / TILE_SIZE;
//        int entityBottomY = (entity.getNewWorldY() + entity.getHitbox().height) / TILE_SIZE;
//
//        if (rightPressed) {
//            if (!collisionWithTile(level, entityRightXTile, entityTopY) && !collisionWithTile(level, entityRightXTile, entityBottomY)) {
//                entity.setWorldX(entity.getNewWorldX());
//                entity.updateHitboxX();
//            } else {
//                entity.setWorldX(entityRightXTile*TILE_SIZE - TILE_SIZE - 1);
//                entity.updateHitboxX();
//            }
//        }
//        if (leftPressed) {
//            if (!collisionWithTile(level, entityLeftXTile, entityTopY) && !collisionWithTile(level, entityLeftXTile, entityBottomY)) {
//                entity.setWorldX(entity.getNewWorldX());
//                entity.updateHitboxX();
//            } else {
//                entity.setWorldX(entityLeftXTile*TILE_SIZE+TILE_SIZE + 1);
//                entity.updateHitboxX();
//            }
//        }
//        if (upPressed) {
//            if(!collisionWithTile(level, entityLeftXTile, entityTopY) && !collisionWithTile(level, entityRightXTile, entityTopY)) {
//                entity.setWorldY(entity.getNewWorldY());
//                entity.updateHitboxY();
//            } else {
//                entity.setWorldY(entityTopY*TILE_SIZE+TILE_SIZE+1);
//                entity.updateHitboxY();
//            }
//        }
//        if(downPressed) {
//            if(!collisionWithTile(level, entityLeftXTile, entityBottomY) && !collisionWithTile(level, entityRightXTile, entityBottomY)) {
//                entity.setWorldY(entity.getNewWorldY());
//                entity.updateHitboxY();
//            } else {
//                entity.setWorldY(entityBottomY*TILE_SIZE-TILE_SIZE-1);
//                entity.updateHitboxY();
//            }
//        }
//    }
//
//    //Checks vertical collisions and then horizontal (this can cause issues)
//    //If i reduce to checking just corners of my
//    public static void checkLinearCollisions(Entity entity, Level level) {
//        if (leftPressed && !rightPressed) {
//            Point[] leftOverlap = FindOverlapTilesLeftIndexes(entity);
//            for (Point index : leftOverlap) {
//                if (level.getTile(index.x, index.y).isSolid()) {
//                    entity.setWorldX(((index.x + 1) * TILE_SIZE) + 1);
//                    entity.updateHitboxX();
//                }
//            }
//        }
//
//        if (rightPressed && !leftPressed) {
//            Point[] rightOverlap = FindOverlapTilesRightIndexes(entity);
//            for (Point index : rightOverlap) {
//                if (level.getTile(index.x, index.y).isSolid()) {
//                    entity.setWorldX((index.x - 1) * TILE_SIZE - 1);
//                    entity.updateHitboxX();
//                }
//            }
//        }
//
//        if (upPressed && !downPressed) {
//            Point[] upOverlap = FindOverlapTilesTopIndexes(entity);
//            for (Point index : upOverlap) {
//                if (level.getTile(index.x, index.y).isSolid()) {
//                    entity.setWorldY((index.y + 1) * TILE_SIZE + 1);
//                    entity.updateHitboxY();
//                }
//            }
//        }
//
//        if (downPressed && !upPressed) {
//            Point[] downOverlap = FindOverlapTilesBottomIndexes(entity);
//            for (Point index : downOverlap) {
//                if (level.getTile(index.x, index.y).isSolid()) {
//                    entity.setWorldY((index.y - 1) * TILE_SIZE - 1);
//                    entity.updateHitboxY();
//                }
//            }
//        }
//
//    }
//
//    public static void checkCollisions(Entity entity, Level level) {
//        int newX = entity.getNewWorldX();
//        int newY = entity.getNewWorldY();
//        boolean horizontalCollision = false;
//        boolean verticalCollision = false;
//        if ((rightPressed && !leftPressed) || (leftPressed && !rightPressed)) {
//            Point[] horizontalOverlap = FindOverlapTilesHorizontal(entity);
//            horizontalCollision = resolveHorizontalCollisions(entity, level, horizontalOverlap);
//        }
//        if ((upPressed && !downPressed) || (downPressed && !upPressed)) {
//            Point[] verticalOverlap = FindOverlapTilesVertical(entity);
//            verticalCollision = resolveVerticalCollisions(entity, level, verticalOverlap);
//        }
//
//        if (!horizontalCollision) {
//            entity.setWorldX(newX);
//            entity.updateHitboxX();
//        }
//
//        if (!verticalCollision) {
//            entity.setWorldY(newY);
//            entity.updateHitboxY();
//        }
//
//        // Optionally, handle special cases or events related to collisions.
//        if (horizontalCollision || verticalCollision) {
//            // Handle collisions, e.g., play a sound, reduce health, etc.
//        }
//
//    }
//
//    public static boolean resolveHorizontalCollisions(Entity entity, Level level, Point[] overlap) {
//        boolean collision = false;
//        for (Point index : overlap) {
//            if (level.getTile(index.x, index.y).isSolid()) {
//                if (leftPressed) {
//                    //Moving left
//                    int entityLeft = entity.getNewWorldX();
//                    //Rightmost point of tile;
//                    int tileRight = index.x * TILE_SIZE + TILE_SIZE; //(index.x + 1) * TILE_SIZE) + 1
//                    if (entityLeft <= tileRight) {
//                        entity.setWorldX(tileRight + 2);
//                        entity.updateHitboxX();
//                        collision = true;
//                    }
//                } else if (rightPressed) {
//                    //Moving right
//                    int entityRight = entity.getNewWorldX() + entity.getHitbox().width;
//                    //Leftmost point of tile
//                    int tileLeft = index.x * TILE_SIZE;
//                    if (entityRight >= tileLeft) {
//                        entity.setWorldX(tileLeft - entity.getHitbox().width - 1);
//                        entity.updateHitboxX();
//                        collision = true;
//                    }
//                }
//            }
//        }
//        return collision;
//    }
//
//    public static boolean resolveVerticalCollisions(Entity entity, Level level, Point[] overlap) {
//        boolean collision = false;
//        for (Point index : overlap) {
//            if (level.getTile(index.x, index.y).isSolid()) {
//                if (downPressed) {
//                    //Moving up
//                    int entityBottom = entity.getNewWorldY() + entity.getHitbox().height;
//                    //Topmost part of tile
//                    int tileTop = index.y * TILE_SIZE;
//                    if (entityBottom >= tileTop) {
//                        entity.setWorldY(tileTop - entity.getHitbox().height - 1);
//                        entity.updateHitboxY();
//                        collision = true;
//                    }
//                } else if (upPressed) {
//                    //Moving down
//                    int entityTop = entity.getNewWorldY();
//                    //Bottom of tile
//                    int tileBottom = index.y * TILE_SIZE + TILE_SIZE;
//                    if (entityTop <= tileBottom) {
//                        entity.setWorldY(tileBottom + 1);
//                        entity.updateHitboxY();
//                        collision = true;
//                    }
//                }
//            }
//        }
//        return collision;
//    }
//}
