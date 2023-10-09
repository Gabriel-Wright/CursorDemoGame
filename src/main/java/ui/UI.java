package ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import gameObjects.entities.player.Player;
import levels.LevelManager;

import static inputs.KeyHandler.*;
import static main.GamePanel.*;


public class UI {
    private Player player;
    private LevelManager levelManager;

    private final Font arial_tileSize = new Font("Arial", Font.PLAIN, TILE_SIZE/2);
    private static ArrayList<UITag> UITagMessages = new ArrayList<>();
    private static boolean UITagMessagesOn = false;

    //Static statistic values local to this class - set after every full update
    public static int ECPULOCAL = 0;
    public static int UPSLOCAL = 0;
    public static int FPSLOCAL = 0;
    public UI(LevelManager levelManager) {
        this.player = levelManager.getGameObjectHandler().getPlayer();
        this.levelManager = levelManager;
    }

    public static void addUITag(UITag uiTag) {
        for(UITag tag: UITagMessages) {
           tag.updateDestY();
        }
        UITagMessages.add(uiTag);
        UITagMessagesOn = true;
    }

    public void draw£ntityPosInfo(Graphics g, float x, float y) {
        g.setFont(arial_tileSize);
        g.setColor(Color.WHITE);
        g.drawString(String.format("Pos:(%f, %f)\n. Row: %f. \n Column: %f)",x,y,x/TILE_SIZE,y/TILE_SIZE), TILE_SIZE, TILE_SIZE);
    }

    public void drawEntityInventoryInfo(Graphics g, float x, float y) {
        g.setFont(arial_tileSize);
        g.setColor(Color.WHITE);
        g.drawString(player.getPlayerInventory().toString(),TILE_SIZE,SCREEN_HEIGHT-TILE_SIZE);
    }

    public void drawPerformanceInfo(Graphics g) {
        g.setFont(arial_tileSize);
        g.setColor(Color.WHITE);
        g.drawString(String.format("ECPU: %d\n. UPS: %d. FPS: %d.",ECPULOCAL, UPSLOCAL, FPSLOCAL), 7*TILE_SIZE, TILE_SIZE);
    }
    public void drawUITags(Graphics g) {
        Iterator<UITag> iterator = UITagMessages.iterator();
        while (iterator.hasNext()) {
            UITag uiTag = iterator.next();

            // If some condition is met, you can remove the item
            if (uiTag.drawTag(g, arial_tileSize)) {
                iterator.remove(); // This removes the item safely
            }
        }
    }

    public void render(Graphics g) {
        if(playerPosInfo) {
            draw£ntityPosInfo(g, player.getX(), player.getY());
        }
        if(playerInventoryInfo) {
            drawEntityInventoryInfo(g, player.getX(), player.getY());
        }
        if(performanceInfo) {
            drawPerformanceInfo(g);
        }

        if(UITagMessagesOn) {
            drawUITags(g);
        }
    }

}
