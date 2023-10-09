package states;

import main.GamePanel;

import java.awt.*;

public abstract class State {

    public static GamePanel gamePanel;

    public State(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public abstract void initialiseState();
    public abstract void update();

    public abstract void render(Graphics g);
}
