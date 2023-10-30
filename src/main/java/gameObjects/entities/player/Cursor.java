package gameObjects.entities.player;

import java.awt.*;
import java.awt.MouseInfo;
import java.awt.Point;

import static inputs.MouseHandler.IN_FOCUS;
import static main.GamePanel.*;
import static main.Main.WINDOW_IN_FOCUS;

public class Cursor {

    //Software positions of mouse calculated each update using the difference in distance from in game :O
    private int mouseX;
    private int mouseY;
    private int deltaX;
    private int deltaY;
    private int radius;
    public Cursor(int radius) {
        this.radius = radius;
        mouseX = centreX;
        mouseY = centreY;
    }
    public void update() {
        if(WINDOW_IN_FOCUS) {
            //non centred position of osMouse
            int osMouseX = MouseInfo.getPointerInfo().getLocation().x;
            int osMouseY = MouseInfo.getPointerInfo().getLocation().y;

            // Calculate the difference between the OS mouse's previous position and the center of the screen

            deltaX = osMouseX - centreX;
            deltaY = osMouseY - centreY;

            mouseX += deltaX;
            mouseY += deltaY;
        }
        else {
            mouseX = centreX;
            mouseY = centreY;
        }
        System.out.println(mouseX +"," +mouseY);
    }
    public void render(Graphics g) {
        g.setColor(Color.GRAY);

        g.drawOval(mouseX - radius, mouseY - radius, radius * 2, radius * 2);
    }
}
