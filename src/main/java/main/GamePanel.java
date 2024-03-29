package main;

import inputs.KeyHandler;
import inputs.MouseHandler;
import options.Settings;
import options.score.ScoreEntry;
import options.score.ScoreReader;
import options.score.ScoreWriter;
import options.sound.SoundConstants;
import options.sound.SoundSettings;
import states.*;
import ui.UI;

import javax.swing.*;
import java.awt.*;

import static inputs.KeyHandler.fullScreenToggle;
import static main.Main.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    //Unscaled pixel size of tiles, they have detail size of 32x32
    public final static int TILE_SIZE = 16;
    //Scales used for adjusting window size for monitors
    private double scaleX = 1;
    private double scaleY = 1;

    //Number of frames per seconds and in game updates per second
    public final static int FPS = 60;
    public final static int UPS = 120;

    //Maximum number of tiles to be displayed on screen for each dimension
    final static int maxScreenCol = 48;
    final static int maxScreenRow = 27;

    //Hence target width and height with initial tile size -this is used to calculate scale X and scale Y
    public static int TARGET_SCREEN_WIDTH = TILE_SIZE * maxScreenCol;
    public static int TARGET_SCREEN_HEIGHT = TILE_SIZE * maxScreenRow;

    //Centre of the window - used for centring the mouse
    public static int centreX;
    public static int centreY;

    //borders of the window - used to keep the mouse contained
    public static int screenEdgeX;
    public static int screenEdgeY;

    //Full screen toggle check
    private boolean isFullScreen = false;

    //Input listeners
    private KeyHandler keyH = new KeyHandler();
    private MouseHandler mouseH = new MouseHandler();
    Robot mouseLocker;
    public static boolean LOCK_CURSOR;

    //Local monitor and graphics environment info
    private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    private GraphicsDevice graphicsDevice = ge.getDefaultScreenDevice();
    private DisplayMode originalDisplayMode;

    //NumUpdates per toggle check - how many updates have to pass for toggle check
    private int numUpdatesPerToggleCheck = 1;
    Thread gameThread;

    //GameStates
    public static boolean gameActive;
    private static GameState gameState;
    private static PauseState pauseState;
    private static GameOverState gameOverState;
    private static MenuState menuState;
    private static State currentState;

    //Settings objects
    private Settings settings;
    private static SoundSettings soundSettings;

    //Score writer and reader objects
    private static ScoreWriter scoreWriter;
    private static ScoreReader scoreReader;

    public static Color backGroundColor = Color.black;

    public GamePanel() {
        loadWindow();

        //Initialising input handlers
        initialiseInputHandlers();

        //Initialising settings - checking if settings have been assigned
        loadSettings();

        //Reloading saved score config
        loadScores();

        //Initial menu gameStates - other gameStates are loaded at gameStart
        initialiseMenuState();
        setCurrentState(menuState);

        //Initial display mode
        originalDisplayMode = graphicsDevice.getDisplayMode();
    }

    private void loadWindow() {
        initialiseWindowDimensions();
        //Set size of window to preferred size
        this.setPreferredSize(new Dimension(TARGET_SCREEN_WIDTH, TARGET_SCREEN_HEIGHT));
        //Set background color to black
        this.setBackground(Color.black);
        //Can improve game rendering performance
        this.setDoubleBuffered(true);
    }

    private void initialiseWindowDimensions() {
        screenEdgeX = startX + TARGET_SCREEN_WIDTH;
        screenEdgeY = startY + TARGET_SCREEN_HEIGHT;
        centreX = startX + TARGET_SCREEN_WIDTH / 2;
        centreY = startY + TARGET_SCREEN_HEIGHT / 2;
    }

    private void initialiseInputHandlers() {
        initialiseRobot();
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
        this.setFocusable(true); //sets KeyListener to be focusable within gamePanel
    }

    private void initialiseRobot() {
        try {
            mouseLocker = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadSettings() {
        settings = new Settings();
        soundSettings = new SoundSettings(settings);
        soundSettings.loadSoundConstants();
        soundSettings.adjustSoundConstants();
        soundSettings.setVolume(0.9f);
    }

    private void loadScores() {
        scoreWriter= new ScoreWriter();
        scoreReader = new ScoreReader();
        scoreReader.readScores();
    }

    private void initialiseMenuState() {
        menuState = new MenuState(soundSettings, scoreReader);
        menuState.initialiseState();
    }


    /*
    * These methods below are used for toggling different game states, e.g. starting the game - pausing, gameOver etc.
     */
    public static void startGame() {
        gameState = new GameState();
        gameState.initialiseState();
        pauseState = new PauseState(gameState, soundSettings);
        pauseState.initialiseState();
        gameOverState = new GameOverState(gameState, scoreWriter, scoreReader);
        gameActive = true;
        setCurrentState(gameState);
    }

    public static void returnMenu() {
        setCurrentState(menuState);
    }

    public static void togglePause() {
        if(currentState == gameState) {
            gameActive = false;
            setCurrentState(pauseState);
            return;
        }
        if(currentState == pauseState) {
            gameActive = true;
            setCurrentState(gameState);
        }
    }

    public static void gameOver() {
        gameActive = false;
        setCurrentState(gameOverState);
    }

    public static void setCurrentState(State state) {
        currentState = state;
        currentState.reloadState();
    }

    /*
    * These methods are used for adjusting in game tile dimensions based on the size of the window
     */
    private void enterFullscreen() {
        if (graphicsDevice.isFullScreenSupported()) {
            // Set full screen mode
            graphicsDevice.setFullScreenWindow(SwingUtilities.getWindowAncestor(this));
            isFullScreen = true;
        }
    }

    private void exitFullScreen() {
        //Restore original display mode
        graphicsDevice.setDisplayMode(originalDisplayMode);


        //Exit full screen mode
        graphicsDevice.setFullScreenWindow(null);
        isFullScreen = false;
    }

    public void toggleFullScreen() {
        if (isFullScreen) {
            exitFullScreen();
        } else {
            enterFullscreen();
        }
    }

    private void updateScale() {
        scaleX = (double) getWidth() / TARGET_SCREEN_WIDTH;
        scaleY = (double) getHeight() / TARGET_SCREEN_HEIGHT;
    }

    private void checkPanelDimension() {
        if(WINDOW_IN_FOCUS) {
            Point panelLocation = getLocationOnScreen();
            centreY = panelLocation.y + getHeight() / 2;
            centreX = panelLocation.x + getWidth() / 2;
            screenEdgeX = panelLocation.x + TARGET_SCREEN_WIDTH;
            screenEdgeY = panelLocation.y + TARGET_SCREEN_HEIGHT;
        }
    }

    private void checkFullScreenToggle() {
        if (isFullScreen != fullScreenToggle) {
            toggleFullScreen();
        }
    }

    public static void lockCursor() {
        LOCK_CURSOR = true;
    }

    public static void unlockCursor() {
        LOCK_CURSOR = false;
    }


    private void lockCursorToCentre() {
        if (WINDOW_IN_FOCUS) {
            mouseLocker.mouseMove(centreX, centreY);
        }
    }

    /**
     * Initialises startGameThread which creates begins run method, which implements our game loop
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        lockCursorToCentre();

    }

    //When an object implementing Runnable is used to create a thread, starting the thread
    // causes the object's run method to be called in that separately executing thread.
    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS;
        double timePerUpdate = 1000000000.0 / UPS;

        long previousTime = System.nanoTime();        //checks time of activation of run method in nanoseconds
        long lastCheck = System.currentTimeMillis();   //checks time of activation of run method in milliseconds
        int updates = 0;
        int frames = 0;

        double deltaU = 0; // used for checking if Update ticks pass threshold of timePerUpdate
        double deltaF = 0; // used for checking if time has passed enough for a frame to update


        while (gameThread != null) {

            long currentTime = System.nanoTime();

            //delta U will be 1 or more when the duration since last update is equal
            // OR more than timePerUpdate
            deltaU += (currentTime - previousTime) / timePerUpdate;
            //1 UPDATE: If enough time has passed for update - update information such as entity positions, states etc.
            if (deltaU >= 1) {
                update();
                updates++;
                //Add variable here instead of 2
                if (updates % numUpdatesPerToggleCheck == 0 && LOCK_CURSOR) {
                    lockCursorToCentre();
                }
                //Doesn't reset to 0, take away difference above 1.
                deltaU--;
            }

            deltaF += (currentTime - previousTime) / timePerFrame;

            //2 DRAW: render entities and background tiles to the screen
            if (deltaF >= 1) {
                updateBackGroundColor();
                repaint();
                frames++;
                deltaF--;
            }

            previousTime = currentTime;

            //Checks FPS & UPS every second
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
//                System.out.println("FPS: " + frames + "| UPS: " + updates);
                UI.UPSLOCAL = updates;
                UI.FPSLOCAL = frames;
                frames = 0;
                updates = 0;
            }
        }
    }

    public void updateBackGroundColor() {
        setBackground(backGroundColor);
    }

    public void update() {
        checkPanelDimension();
        currentState.update();
    }

    //Replacing this method to enable resizing of the screen - there is a better method to approach this,
    //but I have integrated using TILE_SIZE too much - I should've had TILE_WIDTH and TILE_HEIGHT :(
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        checkFullScreenToggle();
        updateScale();
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(scaleX, scaleY);
        currentState.render(g);
    }

}
