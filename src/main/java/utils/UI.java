package utils;

import java.awt.*;
import java.util.ArrayList;

import entities.player.Player;
import levels.LevelManager;

import static inputs.KeyHandler.playerDebugInfo;
import static main.GamePanel.*;


public class UI {
    private Player player;
    private LevelManager levelManager;

    private final Font arial_40 = new Font("Arial", Font.PLAIN, 32);
    private final int halfScreenHeight = SCREEN_HEIGHT/2;
    private static boolean collectMessageOn = false;
    private static ArrayList<String> messages = new ArrayList<>();
    private static ArrayList<Integer> messageTicks = new ArrayList<>();
    private static int messageIndex = 0;
    private final int numSecondsMessage = 3;
    private final int messageTotalFrames = FPS*numSecondsMessage;
    public UI(Player player, LevelManager levelManager) {
        this.player = player;
        this.levelManager = levelManager;
    }
//    public static void collectMessage(String text) {
//        collectMessage = text;
//        collectMessageOn = true;
//        numCollections++;
//    }

    public static void addMessage(String text) {
        messages.add(text);
        messageTicks.add(0);
        collectMessageOn = true;
    }

    public void render(Graphics g) {
        if(playerDebugInfo) {
            draw£ntityDebugInfo(g, player.getX(), player.getY());
        }

        if(collectMessageOn) {
            drawMessage(g);
        }
    }
    public void draw£ntityDebugInfo(Graphics g, float x, float y) {
        g.setFont(arial_40);
        g.setColor(Color.WHITE);
        g.drawString(String.format("Pos:(%f, %f)\n. Row: %f. \n Column: %f)",x,y,x/TILE_SIZE,y/TILE_SIZE), 25, 25);
    }

    public void drawMessage(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(arial_40);
        g2d.setColor(Color.WHITE);
        String message = messages.get(messageIndex);
        int messageTick = messageTicks.get(messageIndex);
        int messageX = TILE_SIZE;
        int messageY = halfScreenHeight;
        float transparency = 1.0f;
        if (messageTick > FPS) {
            int scaledMessageTick = messageTick - FPS; //resets back to 0 to account
            messageY = (halfScreenHeight) + (((scaledMessageTick) * halfScreenHeight) / messageTotalFrames); //Scale to go down screen as time passes
            transparency = 1 - (float) (scaledMessageTick) / messageTotalFrames;
        }
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency);
        g2d.setComposite(alphaComposite);

        g2d.drawString(message, messageX, messageY);
        tickCollectionMessage(g2d, messageTick);
    }

    private void tickCollectionMessage(Graphics g,int messageTick) {
        messageTick++;
        messageTicks.set(messageIndex,messageTick);
        if(messageTick > messageTotalFrames) {
            messages.remove(messageIndex);
            messageTicks.remove(messageIndex);
            if(messages.size() > messageIndex) {
                drawMessage(g);
            } else {
                collectMessageOn = false;
            }
        }

        if(messageTick > 1.2*FPS) {
            messageIndex++;
            if(messages.size()>messageIndex) {
                drawMessage(g);
            }
        }
        messageIndex = 0;
    }

}
