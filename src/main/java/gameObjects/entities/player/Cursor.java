package gameObjects.entities.player;

import levels.LevelCamera;

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
    private AlphaComposite cursorTransparency;
    public Cursor(int radius) {
        this.radius = radius;
        mouseX = centreX;
        mouseY = centreY;
        // Create an AlphaComposite with 70% transparency
        cursorTransparency = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f); // 0.7f means 70% transparency

    }
    public void update(float x, float y, LevelCamera levelCamera, int cursorRange) {
        if(WINDOW_IN_FOCUS) {
            //non centred position of osMouse
            int osMouseX = MouseInfo.getPointerInfo().getLocation().x;
            int osMouseY = MouseInfo.getPointerInfo().getLocation().y;

            // Calculate the difference between the OS mouse's previous position and the center of the screen

            deltaX = osMouseX - centreX;
            deltaY = osMouseY - centreY;

            mouseX += deltaX;
            mouseY += deltaY;
            checkScreenEdges();
            checkCursorRange(x,y,levelCamera,cursorRange);
        }
        else {
            mouseX = centreX;
            mouseY = centreY;
        }
        System.out.println(mouseX +"," +mouseY);
    }
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Composite originalComposite = g2d.getComposite();

        // Set the 70% transparency composite
        g2d.setComposite(cursorTransparency);

        g.setColor(Color.GRAY);

        g.fillOval(mouseX - radius, mouseY - radius, radius * 2, radius * 2);

    }
    //Checks distance between playerPos and mousePos
    private float calculateDistance(float playerX, float playerY, LevelCamera levelCamera, int mouseX, int mouseY) {
        float entityPosX = playerX - levelCamera.getxOffset();
        float entityPosY = playerY - levelCamera.getyOffset();
        float dx = mouseX - entityPosX;
        float dy = mouseY - entityPosY;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    //Checks angle difference between playerPos and mousePos
    private double calculateAngle(float playerX, float playerY, LevelCamera levelCamera, int mouseX, int mouseY) {
        float entityPosX = playerX - levelCamera.getxOffset();
        float entityPosY = playerY - levelCamera.getyOffset();
        float dx = mouseX - entityPosX;
        float dy = mouseY - entityPosY;
        return Math.atan2(dy, dx);
    }

    private void checkCursorRange(float x, float y, LevelCamera levelCamera, int cursorRange) {
        float distance = calculateDistance(x, y, levelCamera, mouseX, mouseY);
        // If the distance exceeds the cursor range, limit the mouse position within the range
        if (distance > cursorRange) {
            // Calculate the angle between the player and the mouse
            double angle = calculateAngle(x, y, levelCamera, mouseX, mouseY);

            // Set the mouse position to the edge of the cursor range
            mouseX = (int) (x - levelCamera.getxOffset() + cursorRange * Math.cos(angle));
            mouseY = (int) (y - levelCamera.getyOffset() + cursorRange * Math.sin(angle));
        }
    }

    private void checkCursorRangeX(float x, LevelCamera levelCamera, int cursorRange) {
        float entityPosX = x -levelCamera.getxOffset();
        if(mouseX > entityPosX + cursorRange) {
            mouseX = (int) entityPosX + cursorRange;
        } else if(mouseX < entityPosX - cursorRange) {
            mouseX = (int) entityPosX - cursorRange;
        }
    }

    private void checkCursorRangeY(float y, LevelCamera levelCamera, int cursorRange) {
        float entityPosY = y - levelCamera.getyOffset();
        if(mouseY > entityPosY + cursorRange) {
            mouseY = (int) entityPosY + cursorRange;
        } else if(mouseY < entityPosY - cursorRange) {
            mouseY = (int) entityPosY - cursorRange;
        }
    }

    private void checkScreenEdges() {
        checkScreenEdgeX();
        checkScreenEdgeY();
    }

    private void checkScreenEdgeX() {
        if(mouseX <0) {
            mouseX = 1;
        } else if(mouseX > screenEdgeX) {
            mouseX = screenEdgeX - 1;
        }
    }

    private void checkScreenEdgeY() {
        if(mouseY <0) {
            mouseY = 1;
        } else if(mouseY > screenEdgeY) {
            mouseY = screenEdgeY - 1;
        }
    }
}
