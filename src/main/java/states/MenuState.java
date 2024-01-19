package states;

import main.GamePanel;
import options.menu.MenuConstants;
import options.menu.MenuNavigator;
import options.menu.MenuRenderer;

import java.awt.*;

import static inputs.KeyHandler.*;
import static main.GamePanel.TILE_SIZE;
import static main.GamePanel.lockCursor;

public class MenuState extends State{
    private Font font;
    private int fontSize;
    private int xMessagePos;
    private int yMessagePos;

    private MenuConstants menuConstants = new MenuConstants();
    private MenuNavigator menuNavigator;
    private MenuRenderer menuRenderer;
    @Override
    public void initialiseState() {

        fontSize = 42;
        font = new Font("Arial", Font.PLAIN, fontSize);
        xMessagePos = TILE_SIZE*20;
        yMessagePos = TILE_SIZE*10;

        menuConstants.loadMenus();
        menuNavigator = new MenuNavigator("TEST", menuConstants.getRootMain());
        menuRenderer = new MenuRenderer(menuNavigator);
        menuRenderer.loadMenuScaling();
        lockCursor();
    }

    @Override
    public void reloadState() {
        GameState.updateGameBackground(Color.BLACK);
        lockCursor();
    }

    @Override
    public void update() {
        menuRenderer.update();
        if(spacePressed) {
            GamePanel.startGame();
        }
        if(qPressed) {
            System.out.println("Program closing from main menu");
            System.exit(0);

        }
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
