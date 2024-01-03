package gameObjects.entities;

import animations.EntityAnimations;
import gameObjects.entities.constants.EntityConstants;
import gameObjects.events.entity.EntityEvent;
import gameObjects.handler.Cell;
import gameObjects.handler.GameObjectGrid;
import levels.Level;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static inputs.KeyHandler.hitboxToggle;
import static main.GamePanel.TILE_SIZE;
import static gameObjects.handler.GameObjectHandler.ECPU;
public abstract class Entity {


    protected EntityAnimations animations;
    protected int aniTick = 0, aniIndex = 0, aniSpeed;
    protected int[] animationFlags;
    public boolean renderedThisFrame = false;

    protected int entityWidth, entityHeight;
    protected EntityConstants entityConstants;

    //Entity Position related variables
    protected float x, y;
    protected int newWorldX, newWorldY;

    protected float speed;
    protected float xMove;
    protected float yMove;
    protected boolean moving = false;
    private boolean onPath = true;
    protected int action = EntityConstants.IDLE;
    protected Rectangle bounds; // May need to create own class at some point for hitboxes
    private boolean deleted = false;
    private EntityEvent entityCollideEvents;

    public Entity(int x, int y, EntityConstants entityConstants) {
        this.x = x;
        this.y = y;
        this.entityConstants = entityConstants;
        bounds = new Rectangle(0, 0, entityWidth, entityHeight);
        if(entityConstants!=null) {
            loadEntityValuesFromConstants();
            loadAnimations();
        }
        //Default bounds
    }

    public Entity(EntityConstants entityConstants) {
        this.entityConstants = entityConstants;
        if(entityConstants!=null){
            loadEntityValuesFromConstants();
            loadAnimations();
        }
    }

    public void loadEntityValuesFromConstants() {
        bounds = new Rectangle(0, 0, 0, 0);
        bounds.x = entityConstants.getHitboxXoffset();
        bounds.y = entityConstants.getHitboxYoffset();
        bounds.width = entityConstants.getHitboxWidth();
        bounds.height = entityConstants.getHitboxHeight();

        this.aniSpeed = entityConstants.getAnimationSpeed();
        this.animationFlags = entityConstants.getAnimationFlags();
        this.animations = new EntityAnimations(entityConstants);
        this.entityWidth = entityConstants.getEntityWidth();
        this.entityHeight = entityConstants.getEntityHeight();
        this.speed = entityConstants.getSpeed();
        this.entityCollideEvents = entityConstants.getEntityEvent();
    }

    private void loadAnimations() {
        animations.loadAnimations();
    }



    //Centres the camera on the entities position
    protected void centerCamera(Level level) {
        level.getLevelCamera().centerOnPos(x, y);
    }

    protected void move(Level level) {
        checkLevelCollisions(level);
        x +=xMove;
        y +=yMove;
        //centerCamera(level);
    }

    private void checkLevelCollisions(Level level) {
        checkLevelCollisionXMove(level);
        checkLevelCollisionYMove(level);
    }


    //Adjusting these to change position immediately - we will see how this affects
    private void checkLevelCollisionXMove(Level level) {
        if (xMove > 0) {//Moving right
            //Check right bound of entity
            int tx = (int) ((x + xMove + bounds.x + bounds.width) / TILE_SIZE);
            //If tile overlapping the right bound of the entity is solid then change the xMove value so that the player lines up
            //Maybe adjust this so that the xValue is set here?
            if (level.isSolidTile(tx, (int) (y + bounds.y) / TILE_SIZE) || level.isSolidTile(tx, (int) (y + bounds.y + bounds.height) / TILE_SIZE)) {
                xMove = tx * TILE_SIZE - bounds.x - bounds.width - 1 - x;
            }
        } else if (xMove < 0) { //Moving left
            //Check left bound of entity
            int tx = (int) (x + xMove + bounds.x) / TILE_SIZE;
            //If tile overlapping the left bound of the entity is solid then change the xMove value so that the player lines up
            if (level.isSolidTile(tx, (int) (y + bounds.y) / TILE_SIZE) || level.isSolidTile(tx, (int) (y + bounds.y + bounds.height) / TILE_SIZE)) {
                xMove = tx * TILE_SIZE + TILE_SIZE - bounds.x - x;
            }
        }

    }

    private void checkLevelCollisionYMove(Level level) {
        if (yMove < 0) { //Moving up
            //Check upper bound of entity
            int ty = (int) (y + yMove + bounds.y) / TILE_SIZE;
            //If tile overlapping the upper bound of the entity is solid then change the yMove value so that the player lines up with that tile
            if (level.isSolidTile((int) (x + bounds.x) / TILE_SIZE, ty) || level.isSolidTile((int) (x + bounds.x + bounds.width) / TILE_SIZE, ty)) {
                yMove = ty * TILE_SIZE + TILE_SIZE - bounds.y - y;
            }
        } else if (yMove > 0) { // Moving down
            //Check lower bound of entity
            int ty = (int) (y + yMove + bounds.y + bounds.height) / TILE_SIZE;
            //If tile overlapping the lower bound of the entity is solid then change the yMove value so that hte player lines up with that tile
            if (level.isSolidTile((int) (x + bounds.x) / TILE_SIZE, ty) || level.isSolidTile((int) (x + bounds.x + bounds.width) / TILE_SIZE, ty)) {
                yMove = ty * TILE_SIZE - bounds.y - bounds.height - 1 - y;
            }
        }
    }
    //This should be improved

    public void update(Level level, GameObjectGrid gameObjectGrid) {
        //Update position calculations- xMove and yMove values (what values the entity will be displaced by in this update)
//        System.out.println(getEntityCellIndexes());

        if(onPath&&getEntityNextAgroIndex(gameObjectGrid)!=null) {
            moveTowardsTile(getEntityNextAgroIndex(gameObjectGrid), level);
        } else {
            updatePos();
        }
        //Verify states/positions based on collisions within level post movement
        move(level);
        //If entity has moved in this update - reassign its grid position
        if (Math.abs(xMove) > 0.001 || Math.abs(yMove) > 0.001) {
            // Do something when xMove or yMove is not close to 0
            gameObjectGrid.reassignEntityCells(this, -xMove, -yMove);
        }

        //Check entity collisions
        handleLocalEntityCollisions(gameObjectGrid);
        //Update action state of the entity
        updateAction();
        //Adjust the entity animation based on its action state
        updateAnimationTick();
    }

    private void moveTowardsTile(Point nextTileIndex, Level level) {

//        if(nextTileIndex ==null) {
//            System.out.println("Stop");
//        }
        int nextX = nextTileIndex.x * TILE_SIZE;
        int nextY = nextTileIndex.y * TILE_SIZE;

        //Entity's solid area boundaries

        float enLeftX = x + bounds.x;
        float enRightX = x + bounds.x + bounds.width;
        float enTopY = y + bounds.y;
        float enBottomY = y + bounds.y + bounds.height;
        xMove = 0.f;
        yMove = 0.f;
        //Checking if entity can go up path
        if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + TILE_SIZE) {
            //Entity moving up
            yMove = -speed;
        }
        else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + TILE_SIZE) {
            //Entity can move down
            yMove = speed;
        }
        else if(enTopY >= nextY && enBottomY < nextY + TILE_SIZE) {
            //Left or right
            if(enLeftX > nextX) {
                xMove = -speed;
            }
            if(enLeftX < nextX) {
                xMove = speed;
            }
        }

        //Have to go up and to the left
        else if(enTopY > nextY && enLeftX > nextX) {
            //up or left direction
            yMove = - speed;
            if(level.isSolidTile((int)enRightX/TILE_SIZE,(int)(enTopY/TILE_SIZE)-1)){
                xMove = -speed;
            }
        }
        //Have to go up and right
        else if(enTopY > nextY && enLeftX < nextX) {
            yMove = -speed;
            if(level.isSolidTile((int)enLeftX/TILE_SIZE,(int)(enTopY/TILE_SIZE)-1)){
                xMove = speed;
            }
        }
        else if(enTopY < nextY && enLeftX > nextX) {
            yMove = speed;
            if(level.isSolidTile((int)enRightX/TILE_SIZE,(int)(enTopY/TILE_SIZE)+1)) {
                xMove = -speed;
            }
        }
        else if(enTopY < nextY && enLeftX < nextX) {
            yMove = speed;
            if(level.isSolidTile((int)enLeftX/TILE_SIZE,(int)(enTopY/TILE_SIZE)+1)) {
                xMove = speed;
            }
        }
    }

    //May need to adjust to not use set?
    protected void handleLocalEntityCollisions(GameObjectGrid gameObjectGrid) {
        Point[] cellIndexes = gameObjectGrid.getAssignedCells(this).toArray(new Point[0]);
        Set<Entity> cellEntities = new HashSet<>();
        //Retrieve all entities
        for(Point cellIndex: cellIndexes) {
            Cell cell = gameObjectGrid.getCell(cellIndex.x, cellIndex.y);
            cellEntities.addAll(cell.getEntities());
        }

        //Complete collisions for those entities
        for(Entity entity: cellEntities) {
            //Check entity is not itself
            if (entity != this) {
                ECPU++;
                handleEntityCollision(entity);
            }
        }
    }

    //Run event for entity - entity (player entity calls cannot be made through this method)
    protected void handleEntityCollision(Entity entity) {
        if(getCollisionBounds().intersects(entity.getCollisionBounds())) {
            entityCollideEvents.RunEntityEntityCollideEvent(this, entity);
        }
    }

    protected abstract void updatePos();

    protected abstract void updateAction();

    public void checkActionChangeAniIndexAniTick(int action) {
        if (this.action != action) {
            aniIndex = 0;
            aniTick = 0;
        }
    }
    protected void updateAnimationTick() {
        renderedThisFrame = false;
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= entityConstants.getNumAnimationFrames(action)) {
                aniIndex = 0;
            }
        }
    }

    public void render(Graphics g, Level level) {
        int entityXPos = (int) (x - level.getLevelCamera().getxOffset());
        int entityYPos = (int) (y - level.getLevelCamera().getyOffset());
        animations.drawImage(g, action, aniIndex, entityXPos, entityYPos, entityWidth, entityHeight);

        if (hitboxToggle) {
            g.setColor(Color.WHITE);
            g.drawRect(entityXPos + bounds.x, entityYPos + bounds.y, bounds.width, bounds.height);
        }
        renderedThisFrame = true;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) +(x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
    public Rectangle getBounds() {
        return bounds;
    }

    public void setX(float x) {
        this. x = x;
    }
    public void setY(float y) {
        this.y = y;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }

    public Rectangle getCollisionBounds() {
        return new Rectangle((int) x+bounds.x,(int) y+bounds.y, bounds.width, bounds.height);
    }

    public void die() {
        deleted = true;
    }
    public boolean isDeleted() {
        return deleted;
    }

    public EntityEvent getEntityCollideEvents() {
        return entityCollideEvents;
    }

    //this is indexed from the centre of the entity's hitbox
    public Point getEntityCellIndexes() {
        return new Point((int) ((x+ bounds.x+bounds.width/2)/TILE_SIZE), (int) ((y+bounds.y+bounds.height/2)/TILE_SIZE));
    }

    public Point getEntityNextAgroIndex(GameObjectGrid gameObjectGrid) {
        Point entityCellIndexes = getEntityCellIndexes();
        Cell gameObjectGridCell = gameObjectGrid.getCell(getEntityCellIndexes().x,getEntityCellIndexes().y);
        if(gameObjectGridCell==null || gameObjectGridCell.getNextAgroPathPoint() ==null) {
            //Null next point?
            return null;
        }
        return gameObjectGrid.getCell(entityCellIndexes.x,entityCellIndexes.y).getNextAgroPathPoint();
    }
}
