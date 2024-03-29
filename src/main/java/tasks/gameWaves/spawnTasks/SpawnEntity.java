package tasks.gameWaves.spawnTasks;

import gameObjects.entities.Entity;
import tasks.TaskRunner;
import tasks.soundTasks.PlaySoundEffect;
import ui.*;

import java.awt.*;

import static gameObjects.entities.player.Player.addSCORE;
import static gameObjects.handler.GameObjectHandler.entityQueue;
import static options.sound.SoundConstants.ENTITY_SPAWN;

public class SpawnEntity extends SpawnTask{

    private Entity entity;
    private UITagTask uiTag;
    private UIWarningTag uiWarningTag;
    private PlaySoundEffect spawnSound;
    public SpawnEntity(int taskValue, int entitySpawnTickBuffer, int eventSpawnTickBuffer, Entity entity) {
        super(taskValue, entitySpawnTickBuffer, eventSpawnTickBuffer);
        uiTag = new UITagTask("Entity spawned!");
        uiWarningTag = new UIWarningTag("!!!!");
        this.entity = entity;
        spawnSound = new PlaySoundEffect(ENTITY_SPAWN);
    }

    public void initialiseEntitySpawn(PointPair spawnPoint) {
        addSCORE(getTaskValue());
        Point entitySpawn = spawnPoint.getPoint1();
        Point warningSpawn = spawnPoint.getPoint2();
        entity.setX(entitySpawn.x);
        entity.setY(entitySpawn.y);
        uiWarningTag.setX(warningSpawn.x);
        uiWarningTag.setY(warningSpawn.y);
        spawnSound.playSound();
        UITagManager.addUITag(uiTag);
        UIEntityInfo.addWarning(uiWarningTag);
        entityQueue.add(entity);
        TaskRunner.addTask(spawnSound);
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
