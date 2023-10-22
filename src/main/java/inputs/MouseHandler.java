package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;

public class MouseHandler implements MouseListener, MouseMotionListener {

    //We lock the real mouse to the centre of the screen, and use a fake software mouse for
    //in screen calculations

    public static int MOUSE_X; // Position of in software mouse
    public static int MOUSE_Y; // Mouse Y position
        @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse click");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        System.out.println("Mouse entered?");
        MOUSE_X = e.getX(); // Update mouse X position
        MOUSE_Y = e.getY(); // Update mouse Y position
//        System.out.println(MOUSE_X +","+ MOUSE_Y);

    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouse exit");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
//        System.out.println("mouse drag");
        MOUSE_X = e.getX(); // Update mouse X position
        MOUSE_Y = e.getY(); // Update mouse Y position
//        System.out.println(MOUSE_X +","+ MOUSE_Y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
//        System.out.println("mouse move");
        MOUSE_X = e.getX(); // Update mouse X position
        MOUSE_Y = e.getY(); // Update mouse Y position
        System.out.println(MOUSE_X +","+ MOUSE_Y);
    }
}