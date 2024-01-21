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
//        System.out.println("Mouse pressed");
        mouseClickHeld = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        System.out.println("Mouse released");
        mouseClickHeld = false;
        mouseClicked = false;
        resetOptionClickedFlag();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        System.out.println("Mouse entered?");
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        System.out.println("mouse exit");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseClickHeld = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

//    @Override
//    public void mouseDragged(MouseEvent e) {
////        System.out.println("mouse drag");
//        MOUSE_X = e.getX(); // Update mouse X position
//        MOUSE_Y = e.getY(); // Update mouse Y position
////        System.out.println(MOUSE_X +","+ MOUSE_Y);
//    }

//    @Override
//    public void mouseMoved(MouseEvent e) {
////        System.out.println("mouse move");
//        MOUSE_X = e.getX(); // Update mouse X position
//        MOUSE_Y = e.getY(); // Update mouse Y position
//        System.out.println(MOUSE_X +","+ MOUSE_Y);
//    }
}