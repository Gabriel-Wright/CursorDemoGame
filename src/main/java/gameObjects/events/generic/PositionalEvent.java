package gameObjects.events.generic;

import levels.Level;
import java.awt.*;

import static inputs.KeyHandler.hitboxToggle;

public abstract class PositionalEvent implements Event {


    //World size triggerBox rectangle - scaled to pixel size.
    protected Rectangle triggerBox;

    protected boolean complete = false;

    protected int startX;
    protected int startY;
    protected int width;
    protected int height;

    public PositionalEvent(int startX, int startY, int width, int height) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        triggerBox = new Rectangle(startX, startY, width, height);
    }

    public Rectangle getTriggerBox() {
        return triggerBox;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete() {
        complete = true;
    }

    public abstract void reset();
    public void render(Graphics g, Level level) {

        int entityXPos = (int) (startX - level.getLevelCamera().getxOffset());
        int entityYPos = (int) (startY - level.getLevelCamera().getyOffset());

        if (hitboxToggle) {
            g.setColor(Color.WHITE);
            g.drawRect(entityXPos, entityYPos, width, height);
        }
    }
}
