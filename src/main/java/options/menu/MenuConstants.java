package options.menu;

import options.menu.execute.gameEnd.EndGame;
import options.menu.execute.gameEnd.ReturnToMenu;
import options.menu.execute.gameState.LevelOneExecute;
import options.menu.nodes.MenuNode;
import options.menu.nodes.VolumeNode;
import options.sound.SoundSettings;



public class MenuConstants {

    private MenuNode rootMain;
    private MenuNode rootPause;

    public void loadMenus(SoundSettings soundSettings) {
        loadMainMenu(soundSettings);
        loadPauseMenu(soundSettings);
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
        VolumeNode volumeToggle = new VolumeNode(soundSettings);
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

    private void loadPauseMenu(SoundSettings soundSettings) {
        rootPause = new MenuNode("PAUSE");
        MenuNode optionNode = new MenuNode("OPTIONS");
        MenuNode exitNode = new MenuNode("MAIN MENU");
        VolumeNode volumeNode = new VolumeNode(soundSettings);
        ReturnToMenu returnToMenu = new ReturnToMenu();

        optionNode.addChildNode(volumeNode);
        rootPause.addChildNode(optionNode);
        exitNode.setExecuteChoice(returnToMenu);
        rootPause.addChildNode(exitNode);
    }

    public MenuNode getRootMain() {
        return rootMain;
    }

    public MenuNode getRootPause() {
        return rootPause;
    }
}
