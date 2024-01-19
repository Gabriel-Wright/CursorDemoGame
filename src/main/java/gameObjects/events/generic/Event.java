package gameObjects.events.generic;

import gameObjects.entities.player.Player;
import gameObjects.entities.player.GameCursor;

public interface Event {

    public void runEvent(Player player);

    public void runEvent(GameCursor cursor);
}
