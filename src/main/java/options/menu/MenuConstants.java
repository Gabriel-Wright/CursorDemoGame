package options.menu;

import options.menu.execute.gameEnd.EndGame;
import options.menu.execute.gameEnd.ReturnToMenu;
import options.menu.execute.gameState.LevelOneExecute;
import options.menu.nodes.MenuNode;
import options.menu.nodes.ScoreNode;
import options.menu.nodes.VolumeNode;
import options.score.ScoreReader;
import options.sound.SoundSettings;



public class MenuConstants {

    private MenuNode rootMain;
    private MenuNode rootPause;

    public void loadMainMenu(SoundSettings soundSettings, ScoreReader scoreReader) {
        rootMain = new MenuNode("ROOT");
        MenuNode playNode = new MenuNode("PLAY");
        MenuNode levelOneNode = new MenuNode("BASE LEVEL");
        LevelOneExecute levelOneExecute = new LevelOneExecute();
        levelOneNode.setExecuteChoice(levelOneExecute);
        MenuNode levelTwoNode = new MenuNode("TEST LEVEL");
        MenuNode scores = new MenuNode("SCORES");
        ScoreNode leaderBoard = new ScoreNode(scoreReader);
        MenuNode optionNode = new MenuNode("OPTIONS");
        VolumeNode volumeToggle = new VolumeNode(soundSettings);
        MenuNode quitNode = new MenuNode("QUIT");
        EndGame endGame = new EndGame();
        quitNode.setExecuteChoice(endGame);
        playNode.addChildNode(levelOneNode);
        playNode.addChildNode(levelTwoNode);
        optionNode.addChildNode(volumeToggle);
        scores.addChildNode(leaderBoard);
        rootMain.addChildNode(playNode);
        rootMain.addChildNode(scores);
        rootMain.addChildNode(optionNode);
        rootMain.addChildNode(quitNode);
    }

    public void loadPauseMenu(SoundSettings soundSettings) {
        rootPause = new MenuNode("PAUSE");
        MenuNode optionNode = new MenuNode("OPTIONS");
        MenuNode exitNode = new MenuNode("MAIN MENU");
        VolumeNode volumeNode = new VolumeNode(soundSettings);
        ReturnToMenu returnToMenu = new ReturnToMenu();

        optionNode.addChildNode(volumeNode);
        rootPause.addChildNode(optionNode);
//        exitNode.setExecuteChoice(returnToMenu);
//        rootPause.addChildNode(exitNode);
    }

    public MenuNode getRootMain() {
        return rootMain;
    }

    public MenuNode getRootPause() {
        return rootPause;
    }
}
