package gameObjects.events;

import gameObjects.events.generic.Event;
import levels.LevelManager;

public class DeleteEntityEvent implements Event {

    private LevelManager levelManager;
    public DeleteEntityEvent(LevelManager levelManager) {
        this.levelManager = levelManager;
    }
    @Override
    public void runEvent() {

    }
}
