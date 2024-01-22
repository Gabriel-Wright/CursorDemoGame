package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static options.menu.MenuListRenderer.resetOptionClickedFlag;

public class MouseHandler implements MouseListener, MouseMotionListener {

    //We lock the real mouse to the centre of the screen, and use a fake software mouse for
    //in screen calculations
    public static boolean mouseClickHeld = false;
    public static boolean mouseClicked = false;
     @Override
    public void mouseClicked(MouseEvent e) {
        mouseClicked = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseClickHeld = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseClickHeld = false;
        mouseClicked = false;
        resetOptionClickedFlag();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseClickHeld = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}