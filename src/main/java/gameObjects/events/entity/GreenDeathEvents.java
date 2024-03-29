package gameObjects.events.entity;

import gameObjects.entities.Entity;
import gameObjects.entities.enemies.GreenDeath.GreenDeathConstants;
import gameObjects.entities.player.GameCursor;
import gameObjects.entities.player.Player;
import main.GamePanel;

public class GreenDeathEvents implements EntityEvent{
    @Override
    public void RunEvent(Entity entity) {

    }

    @Override
    public void RunEntityEntityCollideEvent(Entity entity1, Entity entity2) {
        int action = entity1.getAction();
        switch (action){
            case GreenDeathConstants.IDLE:
                entity1.setAction(GreenDeathConstants.DEAD);
                break;
            case GreenDeathConstants.DEAD:
                entity1.setAction(GreenDeathConstants.IDLE);

        }

    }

    @Override
    public void RunEntityPlayerCollideEvent(Entity entity, Player player) {
        player.die();
        GamePanel.gameOver();
    }

    @Override
    public void RunEntityCursorCollideEvent(Entity entity, GameCursor cursor) {
        entity.die();
    }
}
