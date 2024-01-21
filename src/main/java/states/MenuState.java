package states;

import options.menu.MenuConstants;
import options.menu.MenuNavigator;
import options.menu.MenuListRenderer;
import options.sound.SoundSettings;

import java.awt.*;

import static main.GamePanel.TILE_SIZE;
import static main.GamePanel.lockCursor;

public class MenuState extends State{
    private Font font;
    private int fontSize;
    private int xMessagePos;
    private int yMessagePos;
    private SoundSettings soundSettings;
    private MenuConstants menuConstants = new MenuConstants();
    private MenuNavigator menuNavigator;
    private MenuListRenderer menuRenderer;

    public MenuState(SoundSettings soundSettings) {
        this.soundSettings = soundSettings;
    }
    @Override
    public void initialiseState() {

        fontSize = 42;
        font = new Font("Arial", Font.PLAIN, fontSize);
        xMessagePos = TILE_SIZE*20;
        yMessagePos = TILE_SIZE*10;

        menuConstants.loadMenus(soundSettings);
        menuNavigator = new MenuNavigator("TEST", menuConstants.getRootMain());
        menuRenderer = new MenuListRenderer(menuNavigator);
        menuRenderer.loadMenuScaling();
        lockCursor();
    }

    @Override
    public void reloadState() {
        GameState.updateGameBackground(Color.BLACK);
        menuNavigator.setDisplayNode(menuConstants.getRootMain());
        menuRenderer.loadMenuScaling();
        lockCursor();
    }

    @Override
    public void update() {
        menuRenderer.update();
    }

    @Override
    public void render(Graphics g) {
//        g.setFont(font);
//        g.setColor(Color.WHITE);
//        g.drawString("PRESS SPACE TO START GAME OR Q TO QUIT",
//                TILE_SIZE*24,
//                TILE_SIZE*15);
        menuRenderer.renderMenu(g);
    }
}
