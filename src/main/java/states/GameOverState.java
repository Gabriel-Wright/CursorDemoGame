package states;

import gameObjects.entities.player.Player;
import main.GamePanel;
import options.menu.nodes.ScoreNode;
import options.score.ScoreEntry;
import options.score.ScoreReader;
import options.score.ScoreWriter;

import java.awt.*;

import static gameObjects.entities.player.Player.getSCORE;
import static inputs.KeyHandler.*;
import static main.GamePanel.*;
import static main.GamePanel.TARGET_SCREEN_HEIGHT;

public class GameOverState extends State{

    private GameState gameState;


    private final Color gameOverColor = new Color(188,0,0,128);
    private ScoreNode scoreDisplay;
    private ScoreWriter scoreWriter;
    private String nameString ="";
    private boolean localNameInputted;

    public GameOverState(GameState gameState, ScoreWriter scoreWriter, ScoreReader scoreReader) {
        this.gameState = gameState;
        scoreDisplay = new ScoreNode(scoreReader);
        this.scoreWriter = scoreWriter;
    }

    @Override
    public void initialiseState() {
    }

    @Override
    public void reloadState() {
        localNameInputted = false;
        nameInputted = false;
        nameString = "";
        reloadTypedText();
        GameState.updateGameBackground(Color.BLACK);
        unlockCursor();
    }

    @Override
    public void update() {
        if(!localNameInputted) {
            retrieveName();
            return;
        }
        if(escapePressed) {
            GamePanel.returnMenu();
        }
    }

    private void retrieveName() {
        nameString = String.valueOf(typedText);
        if(nameInputted) {
            scoreWriter.saveScore(new ScoreEntry(nameString, getSCORE()));
            scoreDisplay.reloadScores();
            localNameInputted = true;
        }
    }

    private void renderNameInput(Graphics g) {
        g.setFont(new Font("Arial",Font.PLAIN, TILE_SIZE*2));
        g.setColor(Color.WHITE);
        g.drawString("Please enter your name. SCORE: "+getSCORE(),TILE_SIZE*4, (TARGET_SCREEN_HEIGHT/2)- TILE_SIZE*3);
        g.drawString(nameString, (TARGET_SCREEN_WIDTH/2) - TILE_SIZE*4, TARGET_SCREEN_HEIGHT/2);
    }

    @Override
    public void render(Graphics g) {
        gameState.render(g);
        applyGameOverFilter(g);
        if(!localNameInputted) {
            renderNameInput(g);
        } else {
            scoreDisplay.renderNode(g);
        }
    }

    private void applyGameOverFilter(Graphics g) {
        g.setColor(gameOverColor);
        g.fillRect(0, 0, TARGET_SCREEN_WIDTH, TARGET_SCREEN_HEIGHT);
    }
}
