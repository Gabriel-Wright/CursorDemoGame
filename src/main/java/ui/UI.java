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

    private UIDebugInfo uiDebugInfo;
    private UIEntityInfo uiEntityInfo;
    private UITagManager uiTagManager;

    private final Font arial_tileSize = new Font("Arial", Font.PLAIN, TILE_SIZE);

    //Static statistic values local to this class - set after every full update
    public static int ECPULOCAL = 0;
    public static int UPSLOCAL = 0;
    public static int FPSLOCAL = 0;
    public UI(LevelManager levelManager) {
        this.player = levelManager.getGameObjectHandler().getPlayer();
        uiDebugInfo = new UIDebugInfo();
        uiEntityInfo = new UIEntityInfo();
        uiTagManager = new UITagManager();
    }
//    public UI(Player player) {
//        this.player = player;
//        uiTagManager = new UITagManager();
//    }

    //All updates of UI are completed through tasks - or references to static variables, so UI is not internally updated here.

    public void render(Graphics g) {
        g.setFont(arial_tileSize);
        uiDebugInfo.render(g,arial_tileSize, player.getX(), player.getY());
        uiTagManager.drawUITags(g, arial_tileSize);
        uiEntityInfo.drawWarnings(g, arial_tileSize);
    }

}
