package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;

public class Main {
    public static boolean WINDOW_IN_FOCUS;
    public static int startX;
    public static int startY;
    public static void main(String[] args) {
        startX = 100;
        startY = 100;
        JFrame window = new JFrame();
        //Can close window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Cannot resize window
        window.setTitle("2D Adventure");
        window.setLocation(startX,startY);
        window.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                System.out.println("IN FOCUS");
                WINDOW_IN_FOCUS = true;
            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                System.out.println("LOST FOCUS");
                WINDOW_IN_FOCUS = false;
            }
        });

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.setResizable(false);
        window.pack();

        // Create a blank image for a custom cursor
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blankCursor");

        // Set the cursor to the blank cursor
        window.getContentPane().setCursor(blankCursor);

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
    }
}
