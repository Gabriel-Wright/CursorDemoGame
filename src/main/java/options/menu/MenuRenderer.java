package options.menu;

import gameObjects.entities.player.GameCursor;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static inputs.MouseHandler.mouseClickHeld;
import static inputs.MouseHandler.mouseClicked;
import static main.GamePanel.*;

public class MenuRenderer {

    private MenuNavigator menuNavigator;

    //Height first menu option is printed
    private int startX = TARGET_SCREEN_WIDTH / 6;
    private int width = TARGET_SCREEN_WIDTH / 2;
    private int startY;
    //Height second menu option is printed
    private int intervalY;
    private int fontSize;

    public static boolean optionClicked = false;

    private GameCursor cursor = new GameCursor(TILE_SIZE, TILE_SIZE*2);

    public MenuRenderer(MenuNavigator menuNavigator) {
        this.menuNavigator = menuNavigator;
    }

    public void loadMenuScaling() {
        int numMenuOptions = menuNavigator.getCurrentMenuOptions().size();
        if(numMenuOptions == 0) {
            return;
        }
        int menuStart = TARGET_SCREEN_HEIGHT / 8;
        int menuEnd = TARGET_SCREEN_HEIGHT - TARGET_SCREEN_HEIGHT/8;
        int menuHeight = menuEnd - menuStart;

        startY = menuStart;
        intervalY = menuHeight/numMenuOptions;
        adjustFontSize(numMenuOptions);

        assignTriggerBoxes();
    }

    private void adjustFontSize(int numMenuOptions) {
        switch(numMenuOptions) {
            case 5,4  -> fontSize = 3*intervalY/4;
            case 3,2 -> fontSize = intervalY/2;
        }
    }

    private void assignTriggerBoxes() {
        ArrayList<MenuNode> currentMenuOptions = menuNavigator.getCurrentMenuOptions();
        int index = 0;
        for(MenuNode menuNode: currentMenuOptions) {
            int optionHeight = startY + index * intervalY;
            menuNode.setTriggerBox(new Rectangle(startX, optionHeight, width, intervalY));
            index++;
        }
    }

    private ArrayList<MenuNode> getCurrentMenuOptions() {
        return menuNavigator.getCurrentMenuOptions();
    }

    private String getCurrentMenuOptionsString() {
        ArrayList<MenuNode> menuOptions = getCurrentMenuOptions();
        StringBuilder printedOptions = new StringBuilder();
        for(MenuNode menuNode: menuOptions) {
            printedOptions.append(menuNode.getLabel()).append("\n");
        }

        return printedOptions.toString();
    }

    private void checkSelect() {
        ArrayList<MenuNode> menuNodes = getCurrentMenuOptions();
        if(mouseClicked &&!optionClicked) {
            for (MenuNode menuNode: menuNodes) {
                if(menuNode.isFocused()) {
                    menuNavigator.pickOption(menuNode);
                    loadMenuScaling();
                    optionClicked = true;
                }
            }
        }
    }

    public void update() {
//        loadMenuScaling();
        cursor.menuUpdate(getCurrentMenuOptions());
        checkSelect();
    }

    public void renderMenu(Graphics g){
        Font font = new Font("Arial", Font.PLAIN, fontSize);
        g.setFont(font);
        g.setColor(Color.WHITE);
        ArrayList<MenuNode> menuNodes = getCurrentMenuOptions();
        if (menuNodes.isEmpty()) {
            return;
        }
        for(MenuNode menuNode: menuNodes) {
            menuNode.renderNode(g);
        }

        cursor.render(g);
    }

    public static void resetOptionClickedFlag() {
        optionClicked = false; // Clear the flag when needed (e.g., after performing the action)
    }
}
