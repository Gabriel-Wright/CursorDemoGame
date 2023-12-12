package states;

import main.GamePanel;

import java.awt.*;

import static inputs.KeyHandler.escapePressed;
import static inputs.KeyHandler.spacePressed;
import static main.GamePanel.TILE_SIZE;

public class MenuState extends State{
    private Font font;
    private int fontSize;
    private int xMessagePos;
    private int yMessagePos;

    @Override
    public void initialiseState() {
        fontSize = 42;
        font = new Font("Arial", Font.PLAIN, fontSize);
        xMessagePos = TILE_SIZE*20;
        yMessagePos = TILE_SIZE*10;
    }

    @Override
    public void reloadState() {

    }

    @Override
    public void update() {
        if(spacePressed) {
            GamePanel.startGame();
        }
    }

    @Override
    public void render(Graphics g) {
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("PRESS SPACE TO START GAME",
                TILE_SIZE*24,
                TILE_SIZE*15);
    }
}
