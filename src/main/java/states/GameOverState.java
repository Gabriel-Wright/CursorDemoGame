package states;

import main.GamePanel;

import java.awt.*;

import static inputs.KeyHandler.escapePressed;
import static inputs.KeyHandler.spacePressed;
import static main.GamePanel.TILE_SIZE;
import static main.GamePanel.unlockCursor;

public class GameOverState extends State{

    private GameState gameState;

    private Font font;
    private int fontSize;
    private int xMessagePos;
    private int yMessagePos;

    public GameOverState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void initialiseState() {
        fontSize = 100;
        font = new Font("Arial", Font.PLAIN, fontSize);
        xMessagePos = TILE_SIZE*20;
        yMessagePos = TILE_SIZE*10;
    }

    @Override
    public void reloadState() {
        GameState.updateGameBackground(Color.BLACK);
        unlockCursor();
    }

    @Override
    public void update() {
//        gameState.update();
        if(escapePressed) {
            GamePanel.returnMenu();
        }
    }

    @Override
    public void render(Graphics g) {
        gameState.render(g);
        g.setFont(font);
        g.drawString("GAME OVER",xMessagePos,yMessagePos);
    }
}
