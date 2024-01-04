//package tasks.taskQueue;
//
//import gameObjects.entities.Entity;
//import gameObjects.entities.enemies.GreenDeath.GreenDeath;
//import gameObjects.entities.enemies.GreenDeath.GreenDeathConstants;
//import gameObjects.handler.GameObjectHandler;
//import tasks.Task;
//
//import java.util.Random;
//
//import static main.GamePanel.TILE_SIZE;
//import static main.GamePanel.UPS;
//import static utils.RandomArray.randomiseIntegerArray;
//
//public class SpawnEntities extends Task {
//
//    private GreenDeathConstants greenDeathConstants;
//    private GreenDeath greenDeath;
//    private int spawnIndex=0;
//    private int spawnRate = UPS*5;
//    private int[] randomSpawnOrder;
//    private int sizeOfSpawnOrderArray = 10;
//    private Random entitySpawnRandom;
//
//    public SpawnEntities(Random entitySpawnRandom) {
//        this.entitySpawnRandom = entitySpawnRandom;
//    }
//
//    public void initialiseConstants() {
//        greenDeathConstants = new GreenDeathConstants();
//        randomSpawnOrder = new int[sizeOfSpawnOrderArray];
//        randomiseIntegerArray(entitySpawnRandom,randomSpawnOrder, sizeOfSpawnOrderArray, 0, 3);
//    }
//
//    private Entity getNewGreenDeathSpawn() {
//        if(spawnIndex==sizeOfSpawnOrderArray) {
//            spawnIndex=0;
//        }
//        switch(randomSpawnOrder[spawnIndex]) {
//            case 0:
//                spawnIndex++;
//                return greenDeath = new GreenDeath(2*TILE_SIZE, 15*TILE_SIZE,greenDeathConstants);
//            case 1:
//                spawnIndex++;
//                return greenDeath = new GreenDeath(31*TILE_SIZE, 3*TILE_SIZE,greenDeathConstants);
//            case 2:
//                spawnIndex++;
//                return greenDeath = new GreenDeath(25*TILE_SIZE, 29*TILE_SIZE, greenDeathConstants);
//        }
//        return greenDeath = new GreenDeath(2*TILE_SIZE, 15*TILE_SIZE,greenDeathConstants);
//    }
//
//    @Override
//    public void runTask() {
//        if(tick%(spawnRate)==0) {
//            GameObjectHandler.entityQueue.add(getNewGreenDeathSpawn());
//            if(spawnRate>UPS) {
//                spawnRate -= 5;
//            }
//        }
//    }
//
//    @Override
//    public void checkComplete() {
//
//    }
//}
