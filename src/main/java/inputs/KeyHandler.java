package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;


public class KeyHandler implements KeyListener {

    public static boolean upPressed, downPressed, leftPressed, rightPressed;
    public static boolean isPaused;
    //Debug toggles
    public static boolean playerPosInfo = false, playerInventoryInfo = false, hitboxToggle = false;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                upPressed = true;
                break;
            case KeyEvent.VK_S:
                downPressed = true;
                break;
            case KeyEvent.VK_A:
                leftPressed = true;
                break;
            case KeyEvent.VK_D:
                rightPressed = true;
                break;
            //Pause the game
            case KeyEvent.VK_P, KeyEvent.VK_ESCAPE:
                isPaused = !isPaused;
                break;
            //Debug info
            case KeyEvent.VK_F3:
                playerPosInfo = !playerPosInfo;
                break;
            case KeyEvent.VK_F4:
                playerInventoryInfo = !playerInventoryInfo;
                break;
            case KeyEvent.VK_F5:
                hitboxToggle = !hitboxToggle;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        switch (code) {
            case KeyEvent.VK_W:
                upPressed = false;
                break;
            case KeyEvent.VK_S:
                downPressed = false;
                break;
            case KeyEvent.VK_A:
                leftPressed = false;
                break;
            case KeyEvent.VK_D:
                rightPressed = false;
                break;
        }

    }
}
