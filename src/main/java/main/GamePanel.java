package main;

import inputs.KeyHandler;
import inputs.MouseHandler;
import states.GameState;
import states.State;
import ui.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowFocusListener;

import static main.Main.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    //Unscaled pixel size of tiles, they have detail size of 32x32
    public final static int originalTileSize = 32; // 32x32 tile size
    //Scale tiles with monitors
    final static int scale = 1;
    private double scaleX = 1;
    private double scaleY = 1;
    public final static int FPS = 60;
    public final static int UPS = 120;
    public final static int TILE_SIZE = originalTileSize * scale; // 64x64
    final static int maxScreenCol = 32;
    final static int maxScreenRow = 18;
    public static int TARGET_SCREEN_WIDTH = TILE_SIZE * maxScreenCol;
    public static int TARGET_SCREEN_HEIGHT = TILE_SIZE * maxScreenRow;
    public static int centreX;
    public static int centreY;
    public static int screenEdgeX;
    public static int screenEdgeY;
    //FOR FULL SCREEN
    private boolean isFullScreen = false;

    public final static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public final static int SCREEN_DPI = toolkit.getScreenResolution();
    private KeyHandler keyH = new KeyHandler(this);
    private MouseHandler mouseH = new MouseHandler();
    private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    private GraphicsDevice graphicsDevice = ge.getDefaultScreenDevice();
    private DisplayMode originalDisplayMode;

    Robot mouseLocker;
    Thread gameThread;
    private GameState gameState;

    private static State currentState;
    public static Color backGroundColor = Color.black;
    public GamePanel() {
        screenEdgeX = startX + TARGET_SCREEN_WIDTH;
        screenEdgeY = startY + TARGET_SCREEN_HEIGHT;
        centreX = startX + TARGET_SCREEN_WIDTH /2;
        centreY = startY + TARGET_SCREEN_HEIGHT /2;
        //Set size of window to preferred size
        this.setPreferredSize(new Dimension(TARGET_SCREEN_WIDTH, TARGET_SCREEN_HEIGHT));
        //Set background color to black
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //Can improve game rendering performance
        initialiseRobot();
        this.addKeyListener(keyH);
        this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
        this.setFocusable(true); //sets KeyListener to be focusable within gamePanel
        gameState = new GameState();
        setCurrentState(gameState);
        originalDisplayMode = graphicsDevice.getDisplayMode();
    }
    private void updateScale() {
        scaleX = (double) getWidth() / TARGET_SCREEN_WIDTH;
        scaleY = (double) getHeight() / TARGET_SCREEN_HEIGHT;
    }

    private void enterFullscreen() {
        if (graphicsDevice.isFullScreenSupported()) {
            // Set full screen mode
            graphicsDevice.setFullScreenWindow((JFrame) SwingUtilities.getWindowAncestor(this));
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
        if(isFullScreen) {
            exitFullScreen();
        } else {
            enterFullscreen();
        }
    }
    public static void setCurrentState(State state) {
        currentState = state;
        currentState.initialiseState();
    }

    private void initialiseRobot() {
        try {
            mouseLocker = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkPanelDimension() {
        Point panelLocation = getLocationOnScreen();
        centreY = panelLocation.y + TARGET_SCREEN_HEIGHT /2;
        centreX = panelLocation.x + TARGET_SCREEN_WIDTH /2;
        screenEdgeX = panelLocation.x + TARGET_SCREEN_WIDTH;
        screenEdgeY = panelLocation.y + TARGET_SCREEN_HEIGHT;
    }

    private void lockCursorToCentre() {
        if(WINDOW_IN_FOCUS) {
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
                if(updates%2 ==0) {
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
                System.out.println("FPS: " + frames + "| UPS: " + updates);
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
        updateScale();
        Graphics2D g2d = (Graphics2D) g;
        g2d.scale(scaleX, scaleY);
        currentState.render(g);
    }
}
