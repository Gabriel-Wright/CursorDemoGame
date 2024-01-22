package states;

import main.GamePanel;
import options.menu.MenuListRenderer;
import options.menu.MenuNodeRenderer;
import options.menu.nodes.ScoreNode;
import options.score.ScoreReader;

import java.awt.*;

import static inputs.KeyHandler.escapePressed;
import static inputs.KeyHandler.spacePressed;
import static main.GamePanel.*;
import static main.GamePanel.TARGET_SCREEN_HEIGHT;

public class GameOverState extends State{

    private GameState gameState;

    private Font font;
    private int fontSize;

     final Color gameOverColor = new Color(255,0,0,128);
    private ScoreNode scoreDisplay;
    public GameOverState(GameState gameState, ScoreReader scoreReader) {
        this.gameState = gameState;
        scoreDisplay = new ScoreNode(scoreReader);
    }

    @Override
    public void initialiseState() {

    }

    @Override
    public void reloadState() {
        scoreDisplay.reloadScores();
        GameState.updateGameBackground(Color.BLACK);
        unlockCursor();
    }

    @Override
    public void update() {
        if(escapePressed) {
            GamePanel.returnMenu();
        }
    }

    @Override
    public void render(Graphics g) {
        gameState.render(g);
        applyGameOverFilter(g);
        scoreDisplay.renderNode(g);
    }

    private void applyGameOverFilter(Graphics g) {
        g.setColor(gameOverColor);
        g.fillRect(0, 0, TARGET_SCREEN_WIDTH, TARGET_SCREEN_HEIGHT);
    }
}
