package gameObjects.events;

import java.awt.*;

public abstract class PositionalEvent implements Event{

    //Position of the event
    protected Rectangle triggerBox;

    private boolean playerTriggered;


}
