package gameObjects.events.generic;

import gameObjects.entities.player.GameCursor;
import gameObjects.entities.player.Player;
import levels.Level;
import tasks.gameWaves.spawnConstants.PositionalEventSpawnInfo;

import java.awt.*;

import static gameObjects.handler.GameObjectHandler.eventRemoveQueue;
import static inputs.KeyHandler.hitboxToggle;

public class ChargingPort extends PositionalEvent {

    private int totalPoints = 500;

    public ChargingPort(PositionalEventSpawnInfo positionalEventSpawnInfo) {
        super(positionalEventSpawnInfo.x(), positionalEventSpawnInfo.y(), positionalEventSpawnInfo.width(), positionalEventSpawnInfo.height());
    }

    @Override
    public void runEvent(Player player) {
//        System.out.println("Nah");
    }

    @Override
    public void runEvent(GameCursor cursor) {
        cursor.increaseKillMetre();
        totalPoints--;
        checkComplete();
    }

    private void checkComplete() {
        if(totalPoints ==0) {
            eventRemoveQueue.add(this);
            setComplete();
        }
    }

    @Override
    public void initialEffects() {
        complete = false;
    }

    @Override
    public void reset() {
        totalPoints = 500;
        complete = false;
    }

    public void render(Graphics g, Level level) {
        int entityXPos = (int) (startX - level.getLevelCamera().getxOffset());
        int entityYPos = (int) (startY - level.getLevelCamera().getyOffset());

        g.setColor(Color.YELLOW);
        g.fillRect(entityXPos, entityYPos, width, height);

        if (hitboxToggle) {
            g.setColor(Color.WHITE);
            g.drawRect(entityXPos, entityYPos, width, height);
        }
    }
}
