package gameObjects.events.generic;

import gameObjects.entities.player.Cursor;
import gameObjects.entities.player.Player;
import levels.Level;
import tasks.gameWaves.spawnConstants.PositionalEventSpawnInfo;

import java.awt.*;

import static inputs.KeyHandler.hitboxToggle;

public class DecreaseChargeTrigger extends PositionalEvent {

    public DecreaseChargeTrigger(PositionalEventSpawnInfo positionalEventSpawnInfo) {
        super(positionalEventSpawnInfo.x(), positionalEventSpawnInfo.y(), positionalEventSpawnInfo.width(), positionalEventSpawnInfo.height());
    }

    @Override
    public void runEvent(Player player) {

    }

    @Override
    public void runEvent(Cursor cursor) {
        cursor.decreaseKillMetre();
    }

    @Override
    public void reset() {

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
