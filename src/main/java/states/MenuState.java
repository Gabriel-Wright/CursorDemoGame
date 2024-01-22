package states;

import options.menu.MenuConstants;
import options.menu.MenuNavigator;
import options.menu.MenuListRenderer;
import options.score.ScoreReader;
import options.sound.SoundSettings;

import java.awt.*;

import static main.GamePanel.TILE_SIZE;
import static main.GamePanel.lockCursor;

public class MenuState extends State{
    private SoundSettings soundSettings;
    private ScoreReader scoreReader;
    private MenuConstants menuConstants = new MenuConstants();
    private MenuNavigator menuNavigator;
    private MenuListRenderer menuRenderer;

    public MenuState(SoundSettings soundSettings, ScoreReader scoreReader) {
        this.soundSettings = soundSettings;
        this.scoreReader = scoreReader;
    }
    @Override
    public void initialiseState() {
        menuConstants.loadMainMenu(soundSettings, scoreReader);
        menuNavigator = new MenuNavigator("TEST", menuConstants.getRootMain());
        menuRenderer = new MenuListRenderer(menuNavigator);
        menuRenderer.loadMenuScaling();
        lockCursor();
    }

    @Override
    public void reloadState() {
        GameState.updateGameBackground(Color.BLACK);
        menuNavigator.setDisplayNode(menuConstants.getRootMain());
        menuRenderer.loadMenuScaling();
        lockCursor();
    }

    @Override
    public void update() {
        menuRenderer.update();
    }

    @Override
    public void render(Graphics g) {
        menuRenderer.renderMenu(g);
    }
}
