package gameObjects.events.generic;

import gameObjects.entities.player.Player;
import gameObjects.entities.player.Cursor;

public interface Event {

    public void runEvent(Player player);

    public void runEvent(Cursor cursor);
}
