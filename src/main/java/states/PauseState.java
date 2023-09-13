package states;

import static main.GamePanel.SCREEN_HEIGHT;
import static main.GamePanel.SCREEN_WIDTH;

import java.awt.*;

public class PauseState extends State{

    private GameState gameState;
    public PauseState(GameState gameState) {
        this.gameState = gameState;
    }

    //Unsure what is needed
    public void initialiseState() {

    }

    //Logic needed for menus. Pause State does not calculate any internal logic of the game - as it is not running.
    @Override
    public void update() {

    }

    //This renders the background image expected from the gameData that we have, this image will not update as there
    //are no updates to GameState occurring.
    //This could be a
    @Override
    public void render(Graphics g) {
        renderGameStateBackground(g);
        applyPauseFilter(g);
    }

    private void renderGameStateBackground(Graphics g) {
        gameState.getLevelManager().draw(g);
        gameState.getPlayer().render(g);
    }

    //This may become a static utils method?
    private void applyPauseFilter(Graphics g) {
        //Apply a darker color filter to the entire screen
        g.setColor(new Color(0, 0, 0, 128));
        g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }
}
