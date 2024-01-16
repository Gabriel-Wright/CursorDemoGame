package states;

import sound.SoundManager;

import static main.GamePanel.*;
import static tasks.soundTasks.SoundConstants.PAUSE_RESUME;
import java.awt.*;

public class PauseState extends State{

    private GameState gameState;
    private SoundManager pauseSounds;
    private int[] soundConstants = {PAUSE_RESUME};
    public PauseState(GameState gameState) {
        this.gameState = gameState;
        pauseSounds = new SoundManager(soundConstants);
    }

    //Unsure what is needed
    public void initialiseState() {

    }

    @Override
    public void reloadState() {
        unlockCursor();
        //play sound - load menus?
        playPauseSound(PAUSE_RESUME);
    }
    public void endState() {
        //play sound & close menus, reset menu data
        playPauseSound(PAUSE_RESUME);
    }

    private void playPauseSound(int i) {
        pauseSounds.play(i);
    }

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
    }

    //This may become a static utils method?
    private void applyPauseFilter(Graphics g) {
        //Apply a darker color filter to the entire screen
        g.setColor(new Color(0, 0, 0, 128));
        g.fillRect(0, 0, TARGET_SCREEN_WIDTH, TARGET_SCREEN_HEIGHT);
    }
}
