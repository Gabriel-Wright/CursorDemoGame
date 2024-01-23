package gameObjects.events;

import gameObjects.entities.player.GameCursor;
import gameObjects.entities.player.Player;
import gameObjects.events.generic.Event;

public class CollectItem implements Event {


    @Override
    public void runEvent(Player player) {
//        System.out.println("Got item :))");
    }

    @Override
    public void runEvent(GameCursor cursor) {
//        System.out.println("Cursor over item :OO");
    }
}
