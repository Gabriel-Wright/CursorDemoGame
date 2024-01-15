package tasks.gameWaves.spawnTasks;

import gameObjects.entities.Entity;

import java.awt.*;

import static gameObjects.handler.GameObjectHandler.entityQueue;
import static main.GamePanel.UPS;

public class SpawnEntity extends SpawnTask{

    private Entity entity;
    private int entityDeletionCheck = UPS;
    public SpawnEntity(int taskValue, int entitySpawnTickBuffer, int eventSpawnTickBuffer, Entity entity) {
        super(taskValue, entitySpawnTickBuffer, eventSpawnTickBuffer);

        this.entity = entity;
    }

    public void initialiseEntitySpawn(Point spawnPoint) {
        entity.setX(spawnPoint.x);
        entity.setY(spawnPoint.y);
        entityQueue.add(entity);
    }

    @Override
    public void runTask() {
        complete = true;
    }

    @Override
    protected boolean checkSpawnPurposeComplete() {
        //Check if entity deleted -- unsure how to do this yet
        return false;
    }
}
