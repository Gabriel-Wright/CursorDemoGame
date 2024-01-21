package options.menu;

import options.menu.execute.gameEnd.EndGame;
import options.menu.execute.gameState.LevelOneExecute;
import options.sound.SoundSettings;



public class MenuConstants {

    private MenuNode rootMain;

    public void loadMenus(SoundSettings soundSettings) {
        loadMainMenu(soundSettings);
    }

    private void loadMainMenu(SoundSettings soundSettings) {
        rootMain = new MenuNode("ROOT");
        MenuNode playNode = new MenuNode("PLAY");
        MenuNode levelOneNode = new MenuNode("LEVEL 1");
        LevelOneExecute levelOneExecute = new LevelOneExecute();
        levelOneNode.setExecuteChoice(levelOneExecute);
        MenuNode levelTwoNode = new MenuNode("Level 2");
        MenuNode scoreNode = new MenuNode("SCORE");
        MenuNode optionNode = new MenuNode("OPTIONS");
        OptionNode volumeToggle = new OptionNode("slider", soundSettings);
        MenuNode controlNode = new MenuNode("CONTROL");
        MenuNode quitNode = new MenuNode("QUIT");
        EndGame endGame = new EndGame();
        quitNode.setExecuteChoice(endGame);
        playNode.addChildNode(levelOneNode);
        playNode.addChildNode(levelTwoNode);
        optionNode.addChildNode(volumeToggle);
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
