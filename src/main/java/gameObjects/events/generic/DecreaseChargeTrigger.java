package gameObjects.events.generic;

import gameObjects.entities.player.Cursor;
import gameObjects.entities.player.Player;
import levels.Level;

import java.awt.*;

import static inputs.KeyHandler.hitboxToggle;
import static main.GamePanel.TILE_SIZE;

public class DecreaseChargeTrigger extends PositionalEvent{
    public DecreaseChargeTrigger(int startX, int startY, int width, int height) {
        super(startX, startY, width, height);
    }

    @Override
    public void runEvent(Player player) {

    }

    @Override
    public void runEvent(Cursor cursor) {
        cursor.decreaseKillMetre();
    }

    public void render(Graphics g, Level level) {
        int entityXPos = (int) (startX - level.getLevelCamera().getxOffset());
        int entityYPos = (int) (startY - level.getLevelCamera().getyOffset());

        g.setColor(Color.RED);
        g.fillRect(entityXPos, entityYPos, width, height);

        if (hitboxToggle) {
            g.setColor(Color.WHITE);
            g.drawRect(entityXPos, entityYPos, width, height);
        }
    }

}
