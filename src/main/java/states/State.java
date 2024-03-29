package states;

import main.GamePanel;

import java.awt.*;

public abstract class State {

    public abstract void initialiseState();
    public abstract void reloadState();
    public abstract void update();
    public abstract void render(Graphics g);
}
