package options.menu;

public class MenuConstants {

    private MenuNode rootMain;

    public void loadMenus() {
        loadMainMenu();
    }

    private void loadMainMenu() {
        rootMain = new MenuNode("ROOT");
        MenuNode playNode = new MenuNode("PLAY");
        MenuNode scoreNode = new MenuNode("SCORE");
        MenuNode optionNode = new MenuNode("OPTIONS");
        MenuNode quitNode = new MenuNode("QUIT");

        rootMain.addChildNode(playNode);
        rootMain.addChildNode(scoreNode);
        rootMain.addChildNode(optionNode);
        rootMain.addChildNode(quitNode);
    }

    public MenuNode getRootMain() {
        return rootMain;
    }
}
