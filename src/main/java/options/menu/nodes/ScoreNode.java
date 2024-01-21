package options.menu.nodes;

import options.score.ScoreEntry;

import java.awt.*;
import java.util.ArrayList;

public class ScoreNode extends MenuNode {

    ArrayList<ScoreEntry> scoreEntries;
    public ScoreNode() {
        super("SCORE");
    }

    private void loadScoreEntries() {

    }

    @Override
    public void renderNode(Graphics g) {

    }

    @Override
    public void checkCursorCollision(int mouseX, int mouseY) {

    }
}