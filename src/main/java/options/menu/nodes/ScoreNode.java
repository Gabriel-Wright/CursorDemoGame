package options.menu.nodes;

import options.score.ScoreEntry;
import options.score.ScoreReader;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import static main.GamePanel.*;

public class ScoreNode extends MenuNode {

    private ScoreReader scoreReader;
    private ArrayList<ScoreEntry> scores;
    private Font scoreFont;

    private int startX = TARGET_SCREEN_WIDTH/6;
    private int startY = TARGET_SCREEN_HEIGHT/8;

    private Rectangle scoreBox = new Rectangle(startX, startY, 2*TARGET_SCREEN_WIDTH/3, 3*TARGET_SCREEN_HEIGHT/4);
    private final int maxDisplayedScores = 4;
    private int numDisplayedScores;

    public ScoreNode(ScoreReader scoreReader) {
        super("SCORE");
        this.scoreReader = scoreReader;
        loadScoreEntries();
        loadFont();
    }

    public void reloadScores() {
        loadScoreEntries();;
        loadFont();
    }
    @Override
    public void reloadNode() {
        loadScoreEntries();;
        loadFont();
    }

    private void loadScoreEntries() {
        scores = scoreReader.readScores();
        int numScoreEntires = scores.size();
        numDisplayedScores = Math.min(numScoreEntires, maxDisplayedScores);
    }

    private void loadFont() {
        int intervalHeight = (int) ((float) scoreBox.height/(float) numDisplayedScores);
        int fontSize = intervalHeight / 2;
        scoreFont = new Font("Arial", Font.ITALIC,fontSize);
    }

    @Override
    public void renderNode(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(scoreBox.x, scoreBox.y, scoreBox.width, scoreBox.height);
        int i;
        g.setFont(scoreFont);
        g.setColor(Color.WHITE);
        for(i=1; i<=numDisplayedScores; i++) {
            int textHeight = scoreBox.y - scoreBox.height/(numDisplayedScores*2) + (i)*scoreBox.height/numDisplayedScores;
            g.drawString(i+")"+scores.get(i-1).getScoreDisplay(),scoreBox.x,textHeight);
        }
    }

    @Override
    public void checkCursorCollision(int mouseX, int mouseY) {

    }
}