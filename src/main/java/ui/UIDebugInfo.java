package ui;

import java.awt.*;

import static inputs.KeyHandler.performanceInfo;
import static inputs.KeyHandler.playerPosInfo;
import static main.GamePanel.TILE_SIZE;
import static ui.UI.*;

public class UIDebugInfo {

    public void drawEntityPosInfo(Graphics g, float x, float y) {

        g.setColor(Color.WHITE);
        g.drawString(String.format("Pos:(%f, %f)\n. Row: %f. \n Column: %f)", x, y, x / TILE_SIZE, y / TILE_SIZE), TILE_SIZE, TILE_SIZE);
    }

    public void drawPerformanceInfo(Graphics g) {

        g.setColor(Color.WHITE);
        g.drawString(String.format("ECPU: %d\n. UPS: %d. FPS: %d.", ECPULOCAL, UPSLOCAL, FPSLOCAL), 7 * TILE_SIZE, TILE_SIZE);
    }
    public void render(Graphics g, Font font, float x, float y) {
        g.setFont(font);
        if(playerPosInfo) {
            drawEntityPosInfo(g, x, y);
        }
        if(performanceInfo) {
            drawPerformanceInfo(g);
        }
    }
}
