package main;

import inputs.KeyHandler;
import inputs.MouseHandler;
import states.GameState;
import states.State;
import ui.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import static main.Main.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    //Unscaled pixel size of tiles, they have detail size of 32x32
    public final static int originalTileSize = 32; // 32x32 tile size
    //Scale tiles with monitors
    final static int scale = 2;
    public final static int FPS = 60;
    public final static int UPS = 120;
    public final static int TILE_SIZE = originalTileSize * scale; // 64x64
    final static int maxScreenCol = 16;
    final static int maxScreenRow = 9;
    public final static int SCREEN_WIDTH = TILE_SIZE * maxScreenCol;
    public final static int SCREEN_HEIGHT = TILE_SIZE * maxScreenRow;
    public static int centreX;
    public static int centreY;
    public static int screenEdgeX;
    public static int screenEdgeY;
    public final static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public final static int SCREEN_DPI = toolkit.getScreenResolution();
    KeyHandler keyH = new KeyHandler();
    MouseHandler mouseH = new MouseHandler();
    WindowFocusListener windowFocusListener;
    Robot mouseLocker;
    Thread gameThread;
    private GameState gameState;

    private static State currentState;
    public static Color backGroundColor = Color.black;
    public GamePanel() {
        screenEdgeX = startX + SCREEN_WIDTH;
        screenEdgeY = startY + SCREEN_HEIGHT;
        centreX = startX + SCREEN_WIDTH/2;
        centreY = startY + SCREEN_HEIGHT/2;
        //Set size of window to preferred size
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
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

    private void checkPanelLocation() {
        Point panelLocation = getLocationOnScreen();
        centreY = panelLocation.y + SCREEN_HEIGHT/2;
        centreX = panelLocation.x + SCREEN_WIDTH/2;
        screenEdgeX = panelLocation.x + SCREEN_WIDTH;
        screenEdgeY = panelLocation.y + SCREEN_HEIGHT;

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
        checkPanelLocation();
        currentState.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        currentState.render(g);
    }
}
