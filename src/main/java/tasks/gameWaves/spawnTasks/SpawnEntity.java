package tasks.gameWaves.spawnTasks;

import gameObjects.entities.Entity;
import ui.*;

import java.awt.*;

import static gameObjects.handler.GameObjectHandler.entityQueue;
import static main.GamePanel.TILE_SIZE;
import static main.GamePanel.UPS;

public class SpawnEntity extends SpawnTask{

    private Entity entity;
    private UITagTask uiTag;
    private UIWarningTag uiWarningTag;
    public SpawnEntity(int taskValue, int entitySpawnTickBuffer, int eventSpawnTickBuffer, Entity entity) {
        super(taskValue, entitySpawnTickBuffer, eventSpawnTickBuffer);
        uiTag = new UITagTask("Entity spawned!");
        uiWarningTag = new UIWarningTag("!!!!");
        this.entity = entity;
    }

    public void initialiseEntitySpawn(PointPair spawnPoint) {
        Point entitySpawn = spawnPoint.getPoint1();
        Point warningSpawn = spawnPoint.getPoint2();
        entity.setX(entitySpawn.x);
        entity.setY(entitySpawn.y);
        uiWarningTag.setX(warningSpawn.x);
        uiWarningTag.setY(warningSpawn.y);
        UITagManager.addUITag(uiTag);
        UIEntityInfo.addWarning(uiWarningTag);
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
