package options.menu;

import java.awt.*;

import static main.GamePanel.*;

public class BackNode {

    private Rectangle triggerBox;
    private boolean focused = false; //whether the mouse is hovering over
    private MenuListRenderer menuRenderer;

    public BackNode(MenuListRenderer menuRenderer) {
        this.menuRenderer = menuRenderer;
        loadTriggerBox();
    }

    private void loadTriggerBox() {
        triggerBox = new Rectangle(TARGET_SCREEN_WIDTH - (TILE_SIZE * 6), TARGET_SCREEN_HEIGHT - (TILE_SIZE *6), TILE_SIZE*5, TILE_SIZE*5);
    }

    public void renderBackButton(Graphics g) {
        Font font = new Font("Arial", Font.PLAIN, TILE_SIZE*2);

        if(isFocused()) {
            g.setColor(Color.GREEN);
            g.fillRect(triggerBox.x, triggerBox.y, triggerBox.width, triggerBox.height);
        }

        g.setFont(font);
        g.setColor(Color.WHITE);

        g.drawRect(triggerBox.x, triggerBox.y, triggerBox.width, triggerBox.height);
        g.drawString("Back",triggerBox.x+triggerBox.width/12, triggerBox.y+ 3*triggerBox.height/5);
    }

    public Rectangle getTriggerBox() {
        return triggerBox;
    }

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }
}
