package main;

import entities.player.Player;
import entities.player.PlayerConstants;
import inputs.KeyHandler;
import levels.LevelManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    //Unscaled pixel size of tiles, they have detail size of 32x32
    public final static int originalTileSize = 32; // 32x32 tile size
    //Scale tiles with monitors
    final static int scale = 3;
    final static int FPS = 60;
    public final static int UPS = 120;
    public final static int TILE_SIZE = originalTileSize * scale; // 64x64
    final static int maxScreenCol = 16;
    final static int maxScreenRow = 12;
    public final static int SCREEN_WIDTH = TILE_SIZE * maxScreenCol;
    public final static int SCREEN_HEIGHT = TILE_SIZE * maxScreenRow;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;


    private Player player;
    private PlayerConstants playerConstants;
    private LevelManager levelManager;

    public GamePanel() {
        //Set size of window to preferred size
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        //Set background color to black
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //Can improve game rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true); //sets KeyListener to be focusable within gamePanel
        loadPlayerInfo();
        loadLevelManager();
    }

    private void loadPlayerInfo() {
        playerConstants = new PlayerConstants();
        player = new Player(150, 150, playerConstants);
    }

    private void loadLevelManager() {
        levelManager = new LevelManager();
        levelManager.loadNewLevel("/tiles/testTiles.png","/levelMaps/testMap2.png");
    }

    /**
     * Initialises startGameThread which creates begins run method, which implements our game loop
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
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
                //Doesn't reset to 0, take away difference above 1.
                deltaU--;
            }

            deltaF += (currentTime - previousTime) / timePerFrame;

            //2 DRAW: render entities and background tiles to the screen
            if (deltaF >= 1) {
                repaint();
                frames++;
                deltaF--;
            }

            previousTime = currentTime;

            //Checks FPS & UPS every second
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + "| UPS: " + updates);
                System.out.println(player.getWorldX()+ "," + player.getWorldY()+"." + player.getScreenX() +"," +player.getScreenY());
                frames = 0;
                updates = 0;
            }
        }
    }

    public void update() {
        player.update();
        levelManager.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        levelManager.draw(g,player.getWorldX(), player.getWorldY(), player.getScreenX(), player.getScreenY());
        player.render(g);
    }
}
