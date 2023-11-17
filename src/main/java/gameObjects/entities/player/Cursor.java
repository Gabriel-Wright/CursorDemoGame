package gameObjects.entities.player;

import levels.Level;
import levels.LevelCamera;

import java.awt.*;
import java.awt.MouseInfo;
import java.awt.Point;

import static main.GamePanel.*;
import static main.Main.WINDOW_IN_FOCUS;

public class Cursor {

    //Max world distance that software cursor can move in a single update
    private final int maxDelta = TILE_SIZE/8;
    private static int SENSITIVITY_FACTOR = 1;
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
    public void update(float x, float y, Level level, int cursorRange) {
        if(WINDOW_IN_FOCUS) {
            displaceSoftwareMouse();
            checkScreenEdges();
            checkLevelCollision(level);
            mouseY += deltaY;
            mouseX += deltaX;
            //Temporarily double check
            checkScreenEdges();

//            checkCursorRange(x,y,level.getLevelCamera(),cursorRange);
        }
        else {
            mouseX = centreX;
            mouseY = centreY;
        }
    }
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Composite originalComposite = g2d.getComposite();

        // Set the 70% transparency composite
        g2d.setComposite(cursorTransparency);

        g.setColor(Color.GRAY);

        g.fillOval(mouseX - radius, mouseY - radius, radius * 2, radius * 2);

    }

    private void displaceSoftwareMouse() {

        //non centred position of osMouse
        int osMouseX = MouseInfo.getPointerInfo().getLocation().x;
        int osMouseY = MouseInfo.getPointerInfo().getLocation().y;

        // Calculate the difference between the OS mouse's previous position and the center of the screen
        //Don't want the mouse to move too quickly
        //Have sensitivty facto as setting later to deal with playe sensitivty customisation
        int displaceX = (osMouseX - centreX)/SENSITIVITY_FACTOR;
//        System.out.println(displaceX+" x");
        int displaceY = (osMouseY - centreY)/SENSITIVITY_FACTOR;
//        System.out.println(displaceY+" y");
        //Don't to limit mouse's movement in line with sensitivity so use Max and Min to bound

        //This is syntax for is else statement where you assign in one line
        deltaX = (displaceX>0) ? Math.min(displaceX, maxDelta) : Math.max(displaceX, -maxDelta);
        deltaY = (displaceY>0) ? Math.min(displaceY, maxDelta) : Math.max(displaceY, -maxDelta);
    }
    //Checks distance between playerPos and mousePos
    private float calculateDistance(float playerX, float playerY, LevelCamera levelCamera) {
        float entityPosX = playerX - levelCamera.getxOffset();
        float entityPosY = playerY - levelCamera.getyOffset();
        float dx = mouseX - entityPosX;
        float dy = mouseY - entityPosY;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    //Checks angle difference between playerPos and mousePos
    public double calculateAngle(float playerX, float playerY, LevelCamera levelCamera) {
        float entityPosX = playerX - levelCamera.getxOffset();
        float entityPosY = playerY - levelCamera.getyOffset();
        float dx = mouseX - entityPosX;
        float dy = mouseY - entityPosY;
        return Math.atan2(dy, dx);
    }

    private void checkCursorRange(float x, float y, LevelCamera levelCamera, int cursorRange) {
        float distance = calculateDistance(x, y, levelCamera);
        // If the distance exceeds the cursor range, limit the mouse position within the range
        if (distance > cursorRange) {
            // Calculate the angle between the player and the mouse
            double angle = calculateAngle(x, y, levelCamera);

            // Set the mouse position to the edge of the cursor range
            mouseX = (int) (x - levelCamera.getxOffset() + cursorRange * Math.cos(angle));
            mouseY = (int) (y - levelCamera.getyOffset() + cursorRange * Math.sin(angle));
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

    private void checkLevelCollision(Level level) {
        checkLevelCollisionX(level);
        checkLevelCollisionY(level);
    }

    private void checkLevelCollisionX(Level level) {
        if (deltaX > 0) {//Moving right
            //Check right bound of entity
            int tx = (int) ((mouseX + level.getLevelCamera().getxOffset() + deltaX + radius) / TILE_SIZE);
            //If tile overlapping the right bound of the entity is solid then change the xMove value so that the player lines up
            //Maybe adjust this so that the xValue is set here?
            if (level.isSolidTile(tx, (int) (mouseY + level.getLevelCamera().getyOffset() - radius) / TILE_SIZE) || level.isSolidTile(tx, (int) (mouseY + level.getLevelCamera().getyOffset() - radius) / TILE_SIZE)) {
                deltaX = (int) (tx * TILE_SIZE - level.getLevelCamera().getxOffset() - radius - 1 - mouseX);
            }
        } else if (deltaX < 0) { //Moving left
            //Check left bound of entity
            int tx = (int) ((mouseX + level.getLevelCamera().getxOffset() + deltaX -radius) / TILE_SIZE);
            //If tile overlapping the left bound of the entity is solid then change the xMove value so that the player lines up
            if (level.isSolidTile(tx,  (int) (mouseY +level.getLevelCamera().getyOffset() - radius) / TILE_SIZE) || level.isSolidTile(tx,  (int) (mouseY +level.getLevelCamera().getyOffset() + radius) / TILE_SIZE)) {
                deltaX = (int) (tx * TILE_SIZE + TILE_SIZE - level.getLevelCamera().getxOffset() + radius - mouseX);
            }
        }
    }

    private void checkLevelCollisionY(Level level) {
        if (deltaY < 0) { //Moving up
            //Check upper bound of entity
            int ty = (int) (mouseY + deltaY + level.getLevelCamera().getyOffset() - radius) / TILE_SIZE;
            //If tile overlapping the upper bound of the entity is solid then change the yMove value so that the player lines up with that tile
            if (level.isSolidTile((int) (mouseX + level.getLevelCamera().getxOffset() - radius) / TILE_SIZE, ty) || level.isSolidTile((int) (mouseX + level.getLevelCamera().getxOffset() + radius) / TILE_SIZE, ty)) {
                deltaY = (int) (ty * TILE_SIZE + TILE_SIZE - level.getLevelCamera().getyOffset() + radius - mouseY);
            }
        } else if (deltaY > 0) { // Moving down
            //Check lower bound of entity
            int ty = (int) (mouseY + deltaY +level.getLevelCamera().getyOffset() + radius) / TILE_SIZE;
            //If tile overlapping the lower bound of the entity is solid then change the yMove value so that hte player lines up with that tile
            if (level.isSolidTile((int) (mouseX + level.getLevelCamera().getxOffset() - radius) / TILE_SIZE, ty) || level.isSolidTile((int) (mouseX + level.getLevelCamera().getxOffset() + radius) / TILE_SIZE, ty)) {
                deltaY = (int) (ty * TILE_SIZE -level.getLevelCamera().getyOffset() - radius - 1 - mouseY);
            }
        }
    }

    public static void UPDATE_SENSITIVITY(int sensitivity) {
        SENSITIVITY_FACTOR = sensitivity;
    }
}
