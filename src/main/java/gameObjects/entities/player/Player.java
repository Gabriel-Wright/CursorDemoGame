package gameObjects.entities.player;

import gameObjects.entities.Entity;
import gameObjects.events.generic.PositionalEvent;
import gameObjects.handler.Cell;
import gameObjects.handler.GameObjectGrid;
import gameObjects.objects.SuperObject;
import levels.Level;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.HashSet;
import java.util.Set;

import static inputs.KeyHandler.*;
import static main.GamePanel.TILE_SIZE;

public class Player extends Entity {

//    private Inventory playerInventory = new Inventory();
    private GameCursor cursor;
    private double rotationAngle=0;
    public static int SCORE;
    public Player(int x, int y, PlayerConstants playerConstants) {
        super(x, y, playerConstants);
        cursor = new GameCursor(TILE_SIZE/4, TILE_SIZE/4);
        bounds.x -= entityWidth/2;
        bounds.y -= entityHeight/2;
    }


    @Override
    public void update(Level level, GameObjectGrid gameObjectGrid) {
        //Update position calculations
        updatePos();
        //Verify states/positions based on collisions within level post movement
        move(level);
        //Handle local event triggers
        handleLocalEventTriggers(gameObjectGrid);
        //Check entity collisions
        handleLocalEntityCollisions(gameObjectGrid);
        //Check object collisions
        handleLocalObjectCollisions(gameObjectGrid);
        //Centre camera on player
//        centerCamera(level);
        //Update action state of the entity
        updateAction();
        //Adjust the entity animation based on its action state
        updateAnimationTick();
        //Adjust player rotation
//        updatePlayerRotation(level);

        //If entity has moved in this update - reassign its grid position
        if (Math.abs(xMove) > 0.001 || Math.abs(yMove) > 0.001) {
            // Do something when xMove or yMove is not close to 0
            gameObjectGrid.reassignPlayerCells(this, -xMove, -yMove);
        }
        cursor.update(x, y, level, gameObjectGrid);
    }

    //Entity - player collision is only calculated from player, entities only run
    //entity - entity events within their own classes.
    @Override
    protected void handleEntityCollision(Entity entity) {
        if(getCollisionBounds().intersects(entity.getCollisionBounds())) {
            entity.getEntityCollideEvents().RunEntityPlayerCollideEvent(entity,this);
        }
    }

    private void handleLocalObjectCollisions(GameObjectGrid gameObjectGrid) {
        Point[] cellIndexes = gameObjectGrid.getAssignedCells(this).toArray(new Point[0]);
        Set<SuperObject> cellObjects = new HashSet<>();
        //Retrieve all entities
        for(Point cellIndex: cellIndexes) {
            Cell cell = gameObjectGrid.getCell(cellIndex.x, cellIndex.y);
            cellObjects.addAll(cell.getObjects());
        }

        //Complete collisions for those entities
        for(SuperObject cellObject: cellObjects) {
            //Check entity is not itself
            handleLocalCollision(cellObject);
        }
    }
    private void updatePlayerRotation(Level level) {
        rotationAngle = cursor.calculateAngle(x,y,level.getLevelCamera()) + Math.PI/2;
    }

    private void handleLocalCollision(SuperObject cellObject) {
        cellObject.getEvent().runEvent(this);
    }

    //This is intended for only single trigger in a small space
    private void handleLocalEventTriggers(GameObjectGrid gameObjectGrid) {
        Point[] cellIndexes = gameObjectGrid.getAssignedCells(this).toArray(new Point[0]);
        Set<PositionalEvent> cellEvents = new HashSet<>();

        //Retrieve all events
        for(Point cellIndex: cellIndexes) {
            Cell cell = gameObjectGrid.getCell(cellIndex.x, cellIndex.y);
            cellEvents.addAll(cell.getPositionalEvents());
        }

        //Complete collisions for those entities
        for(PositionalEvent cellEvent: cellEvents) {
            //Check entity is not itself
            handleLocalEventTrigger(cellEvent);
        }
    }

    private void handleLocalEventTrigger(PositionalEvent positionalEvent) {
        if(getCollisionBounds().intersects(positionalEvent.getTriggerBox())) {
            positionalEvent.runEvent(this);
        }
    }
    @Override
    protected void updatePos() {
        xMove = 0;
        yMove = 0;
        moving = false;
        if (leftPressed) {
            moving = true;
            xMove -= speed;
        }
        if (rightPressed) {
            moving = true;
            xMove += speed;
        }
        if (downPressed) {
            moving = true;
            yMove += speed;
        }
        if (upPressed) {
            moving = true;
            yMove -= speed;
        }
    }


    @Override
    //May need to make some changes to this e.g. if changed action make sure that animationIndex is reset to 0, or something like that.
    //Through this method I could end up entering an animation for a different action too far in e.g. start at animation
    protected void updateAction() {
        if (moving) {
            checkActionChangeAniIndexAniTick(PlayerConstants.WALKING);
            action = PlayerConstants.WALKING;
        } else {
            checkActionChangeAniIndexAniTick(PlayerConstants.IDLE);
            action = PlayerConstants.IDLE;
        }
    }
    public void render(Graphics g, Level level) {
        int entityXPos = (int) (x - level.getLevelCamera().getxOffset());
        int entityYPos = (int) (y -  level.getLevelCamera().getyOffset());
        int displacedXPos = (int) (x - entityWidth/2 - level.getLevelCamera().getxOffset());
        int displacedYPos = (int) (y - entityHeight/2 - level.getLevelCamera().getyOffset());

        // Cast Graphics to Graphics2D to enable transformations
        Graphics2D g2d = (Graphics2D) g.create();

        animations.drawImage(g2d, action, aniIndex, displacedXPos, displacedYPos, entityWidth, entityHeight);

        g2d.dispose();
        if (hitboxToggle) {
            g.setColor(Color.WHITE);
            g.drawRect(entityXPos + bounds.x, entityYPos + bounds.y, bounds.width, bounds.height);
        }
        renderedThisFrame = true;

        cursor.render(g);
    }
}
