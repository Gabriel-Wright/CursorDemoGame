package gameObjects.events.entity;

import gameObjects.entities.Entity;
import gameObjects.entities.player.GameCursor;
import gameObjects.entities.player.Player;

public interface EntityEvent {

    public void RunEvent(Entity entity);

    public void RunEntityEntityCollideEvent(Entity entity1, Entity entity2);
    //
    public void RunEntityPlayerCollideEvent(Entity entity, Player player);

    public void RunEntityCursorCollideEvent(Entity entity, GameCursor cursor);

}
