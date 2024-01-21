package options.menu.execute.gameEnd;

import options.menu.execute.Executes;

public class EndGame implements Executes {
    @Override
    public void executeChoice() {
        System.out.println("Program closing from main menu");
        System.exit(0);
    }
}
