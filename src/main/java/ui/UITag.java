package ui;

import java.awt.*;

import static main.GamePanel.*;

public class UITag {
    private static final int ticksTotalDisplay = FPS * 5;
    private static final int ticksXEnter = FPS / 2;
    private static final int ticksYDrop = FPS / 2;
    private static final int ticksXLeave = ticksTotalDisplay - ticksXEnter;

    private String message;
    private int messageTicks = 0;
    private int yChangeTick;
    private String tagFlair;
    private int originalX = -TILE_SIZE;
    private int x;
    private int y = TARGET_SCREEN_HEIGHT / 4;
    private int destX = TILE_SIZE;
    private int destY = TARGET_SCREEN_HEIGHT / 4;

    public UITag(String message, String tagFlair) {
        this.message = message;
        this.tagFlair = tagFlair;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void updateDestY() {
        destY += TILE_SIZE;
        yChangeTick = messageTicks;
    }

    public boolean UITagUpdateTick() {
        messageTicks++;
        if (messageTicks > ticksTotalDisplay) {
            return true;
        }
        return false;
    }

    public void updatePos() {
        if (messageTicks > ticksXLeave) {
            x = destX + (messageTicks - ticksXLeave) * (originalX - destX) / ticksXEnter;
        }

        if (messageTicks < ticksXEnter) {
            x = originalX + (messageTicks) * (destX - originalX) / ticksXEnter;
        }

        if (y != destY) {
            y = y + (messageTicks - yChangeTick) * (destY - y) / ticksYDrop;
        }
    }

    public boolean drawTag(Graphics g, Font font) {
        Graphics2D g2d = (Graphics2D) g;
        //Here this is where we would set tag special looks and everything too - probably within
        // a separate method
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        updatePos();
        g2d.drawString(message, x, y);
        return UITagUpdateTick();
    }
}
