package ui;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

import gameObjects.entities.player.Player;
import levels.LevelManager;

import static inputs.KeyHandler.playerInventoryInfo;
import static inputs.KeyHandler.playerPosInfo;
import static main.GamePanel.*;


public class UI {
    private Player player;
    private LevelManager levelManager;

    private final Font arial_tileSize = new Font("Arial", Font.PLAIN, TILE_SIZE/2);
    private static ArrayList<UITag> UITagMessages = new ArrayList<>();
    private static boolean UITagMessagesOn = false;
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
        g.drawString(String.format("Pos:(%f, %f)\n. Row: %f. \n Column: %f)",x,y,x/TILE_SIZE,y/TILE_SIZE), 25, 25);
    }

    public void drawEntityInventoryInfo(Graphics g, float x, float y) {
        g.setFont(arial_tileSize);
        g.setColor(Color.WHITE);
        g.drawString(player.getPlayerInventory().toString(),TILE_SIZE,SCREEN_HEIGHT-TILE_SIZE);
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
        if(UITagMessagesOn) {
            drawUITags(g);
        }
    }

}
