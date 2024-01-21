package options.menu;

public class MenuConstants {

    private MenuNode rootMain;

    public void loadMenus() {
        loadMainMenu();
    }

    private void loadMainMenu() {
        rootMain = new MenuNode("ROOT");
        MenuNode playNode = new MenuNode("PLAY");
        MenuNode levelOneNode = new MenuNode("LEVEL 1");
        MenuNode levelTwoNode = new MenuNode("Level 2");
        MenuNode scoreNode = new MenuNode("SCORE");
        MenuNode optionNode = new MenuNode("OPTIONS");
        MenuNode volumeNode = new MenuNode("VOLUME");
        MenuNode controlNode = new MenuNode("CONTROL");
        MenuNode quitNode = new MenuNode("QUIT");
        playNode.addChildNode(levelOneNode);
        playNode.addChildNode(levelTwoNode);
        optionNode.addChildNode(volumeNode);
        optionNode.addChildNode(controlNode);
        rootMain.addChildNode(playNode);
        rootMain.addChildNode(scoreNode);
        rootMain.addChildNode(optionNode);
        rootMain.addChildNode(quitNode);
    }

    public MenuNode getRootMain() {
        return rootMain;
    }
}
