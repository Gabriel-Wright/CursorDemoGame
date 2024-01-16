package ui;

import tasks.Task;

import java.awt.*;

import static main.GamePanel.*;
import static main.GamePanel.TARGET_SCREEN_HEIGHT;

public class UITagTask extends Task {
    private static final int ticksTotalDisplay = UPS * 5;
    private static final int ticksXEnter = UPS / 2;
    private static final int ticksYDrop = UPS / 2;
    private static final int ticksXLeave = ticksTotalDisplay - ticksXEnter;

    private int yChangeTick;


    private int originalX = -TILE_SIZE*8;
    private int x;
    private int y = TARGET_SCREEN_HEIGHT / 4;
    private int destX = TILE_SIZE;
    private int destY = TARGET_SCREEN_HEIGHT / 4;

    private String message;

    public UITagTask (String message) {
        this.message = message;
    }

    @Override
    public void runTask() {
        updatePos();
        checkComplete();
    }

    public void updateDestY() {
        destY += TILE_SIZE;
        yChangeTick = tick;
    }

    private void updatePos() {
        if (tick > ticksXLeave) {
            x = destX + (tick - ticksXLeave) * (originalX - destX) / ticksXEnter;
        }

        if (tick < ticksXEnter) {
            x = originalX + (tick) * (destX - originalX) / ticksXEnter;
        }

        if (y != destY) {
            y = y + (tick - yChangeTick) * (destY - y) / ticksYDrop;
        }
    }

    private void checkComplete() {
        if (tick > ticksTotalDisplay) {
            setComplete();
            UITagManager.removeUITag(this);
        }
    }

    public void drawTag(Graphics g, Font font) {
        Graphics2D g2d = (Graphics2D) g;
        //Here this is where we would set tag special looks and everything too - probably within
        // a separate method
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        g2d.drawString(message, x, y);
    }

}
