package options.menu.execute.gameEnd;

import main.GamePanel;
import options.menu.execute.Executes;

public class ReturnToMenu implements Executes {
    @Override
    public void executeChoice() {
        GamePanel.returnMenu();
    }
}
