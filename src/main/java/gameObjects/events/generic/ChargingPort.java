package gameObjects.events.generic;

import gameObjects.entities.player.Cursor;
import gameObjects.entities.player.Player;
import levels.Level;

import java.awt.*;

import static inputs.KeyHandler.hitboxToggle;
import static main.GamePanel.TILE_SIZE;

public class ChargingPort extends PositionalEvent{
    public ChargingPort(int startCol, int startRow, int numCols, int numRows) {
        super(startCol, startRow, numCols, numRows);
    }

    @Override
    public void runEvent(Player player) {
        System.out.println("Nah");
    }

    @Override
    public void runEvent(Cursor cursor) {
        cursor.increaseKillMetre();
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