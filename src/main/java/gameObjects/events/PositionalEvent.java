package gameObjects.events;

import java.awt.*;

public abstract class PositionalEvent implements Event{

    //Position of the event
    protected Rectangle triggerBox;

    private boolean complete = false;
    private boolean playerTriggered;

    public PositionalEvent(int x, int y, int width, int height, boolean playerTriggered) {
        triggerBox = new Rectangle(x, y, width, height);
        this.playerTriggered = playerTriggered;
    }

    public Rectangle getTriggerBox() {
        return triggerBox;
    }
}
