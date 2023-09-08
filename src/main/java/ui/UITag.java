package ui;

import java.awt.*;

import static main.GamePanel.*;
import static ui.UI.removeUITag;

public class UITag {
    private static final int ticksTotalDisplay = FPS*5;
    private static final int ticksXEnter = FPS/2;
    private static final int ticksQueueMove = FPS/2;
    private static final int ticksXLeave = ticksTotalDisplay - ticksXEnter;
    private final Font arial_tileSize = new Font("Arial", Font.PLAIN, TILE_SIZE/2);


    private String message;
    private int messageTicks = 0;
    private String tagFlair;
    private int originalX = -TILE_SIZE;
    private int x;
    private int y = SCREEN_HEIGHT/4;
    private int destX = TILE_SIZE;
    private int destY = SCREEN_HEIGHT/4;

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

    public boolean UITagUpdateTick() {
        messageTicks++;
        if(messageTicks>ticksTotalDisplay) {
            return true;
        }
        return false;
    }

    public void updatePos() {
        if(messageTicks > ticksXLeave) {
            x =  destX + (messageTicks - ticksXLeave)*(originalX - destX)/ticksXEnter;
        }

        if(messageTicks < ticksXEnter) {
            x = originalX + (messageTicks)*(destX-originalX)/ticksXEnter;
        }
    }

    public boolean drawTag(Graphics g, Font font) {
        Graphics2D g2d = (Graphics2D) g;
        //Here this is where we would set tag special looks and everything too - probably within
        // a separate method
        g2d.setFont(font);
        g2d.setColor(Color.WHITE);
        updatePos();
        g2d.drawString(message,x,y);
        return UITagUpdateTick();
    }
}
