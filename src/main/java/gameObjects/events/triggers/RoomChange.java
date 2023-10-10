package gameObjects.events.triggers;

import gameObjects.events.PositionalEvent;
import states.GameState;

import java.awt.*;

public class RoomChange extends PositionalEvent {

    private Color backgroundColor;
    public RoomChange(Color color, int x, int y, int width, int height, boolean playerTriggered) {
        super(x,y,width,height,playerTriggered);
        triggerBox = new Rectangle(x, y , width, height);
        backgroundColor = color;
    }
    @Override
    public void runEvent() {
        GameState.updateGameBackground(backgroundColor);
    }
}
