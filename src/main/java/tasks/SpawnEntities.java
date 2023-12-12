package tasks;

import gameObjects.entities.Entity;
import gameObjects.entities.enemies.GreenDeath.GreenDeath;
import gameObjects.entities.enemies.GreenDeath.GreenDeathConstants;
import gameObjects.handler.GameObjectHandler;

import static main.GamePanel.TILE_SIZE;
import static main.GamePanel.UPS;

public class SpawnEntities extends Task{

    private GreenDeathConstants greenDeathConstants;
    private GreenDeath greenDeath;
    private int spawnIndex=0;
    private int spawnRate = UPS*5;
    public void initialiseConstants() {
        greenDeathConstants = new GreenDeathConstants();
    }

    private Entity getNewGreenDeathSpawn() {
//        switch(spawnIndex) {
//            case 0:
//                spawnIndex = 1;
//                return greenDeath = new GreenDeath(2*TILE_SIZE, 15*TILE_SIZE,greenDeathConstants);
//            case 1:
//                spawnIndex = 2;
//                return greenDeath = new GreenDeath(31*TILE_SIZE, 3*TILE_SIZE,greenDeathConstants);
//            case 2:
//                spawnIndex=0;
//                return greenDeath = new GreenDeath(25*TILE_SIZE, 29*TILE_SIZE, greenDeathConstants);
//        }
        return greenDeath = new GreenDeath(2*TILE_SIZE, 15*TILE_SIZE,greenDeathConstants);
    }

    @Override
    public void runTask() {
        if(tick%(spawnRate)==0) {
            GameObjectHandler.entityQueue.add(getNewGreenDeathSpawn());
            if(spawnRate>UPS) {
                spawnRate -= 5;
            }
        }
    }

    @Override
    public void checkComplete() {

    }
}
