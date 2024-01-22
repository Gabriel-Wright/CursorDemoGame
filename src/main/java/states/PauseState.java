package states;

import options.menu.MenuConstants;
import options.menu.MenuListRenderer;
import options.menu.MenuNavigator;
import options.sound.SoundSettings;
import sound.SoundManager;

import static inputs.KeyHandler.*;
import static main.GamePanel.*;
import static options.sound.SoundConstants.PAUSE_RESUME;
import java.awt.*;

public class PauseState extends State{

    private GameState gameState;

    private MenuConstants menuConstants = new MenuConstants();
    private MenuNavigator menuNavigator;
    private MenuListRenderer menuRenderer;

    private SoundManager pauseSounds;
    private int[] soundConstants = {PAUSE_RESUME};
    private SoundSettings soundSettings;


    public PauseState(GameState gameState, SoundSettings soundSettings) {
        this.gameState = gameState;
        this.soundSettings = soundSettings;
        pauseSounds = new SoundManager(soundConstants);
    }

    //Unsure what is needed
    public void initialiseState() {
        menuConstants.loadPauseMenu(soundSettings);
        menuNavigator = new MenuNavigator("Pause", menuConstants.getRootPause());
        menuRenderer = new MenuListRenderer(menuNavigator);
        menuRenderer.loadMenuScaling();
    }

    @Override
    public void reloadState() {
        menuNavigator.setDisplayNode(menuConstants.getRootPause());
        menuRenderer.loadMenuScaling();

        playPauseSound(PAUSE_RESUME);
    }
    public void endState() {
        //play sound & close menus, reset menu data
        playPauseSound(PAUSE_RESUME);
    }

    private void playPauseSound(int i) {
        pauseSounds.play(i);
    }

    private void checkResume() {
        if(!isPaused) {
            togglePause();
            endState();
        }
    }

    @Override
    public void update() {
        menuRenderer.update();
        checkResume();
    }

    //This renders the background image expected from the gameData that we have, this image will not update as there
    //are no updates to GameState occurring.
    //This could be a
    @Override
    public void render(Graphics g) {
        renderGameStateBackground(g);
        applyPauseFilter(g);
        menuRenderer.renderMenu(g);
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
