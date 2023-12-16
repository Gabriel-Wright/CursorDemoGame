package gameObjects.entities.enemies.GreenDeath;

import gameObjects.entities.Entity;
import gameObjects.entities.constants.EntityConstants;
import main.GamePanel;

import java.util.Random;

import static inputs.KeyHandler.*;
import static inputs.KeyHandler.upPressed;
import static main.GamePanel.UPS;

public class GreenDeath extends Entity {

    private int moveTick = 0;
    private int direction = 0;

    //Need to refactor this whole thing once wave basics are done

    public GreenDeath(int x, int y, GreenDeathConstants greenDeathConstants) {
        super(x, y ,greenDeathConstants);
    }
    public GreenDeath(GreenDeathConstants greenDeathConstants) {super(greenDeathConstants);}

    public GreenDeath(EntityConstants entityConstants) {super(entityConstants);}

//    @Override
//    protected void handleEntityCollision(Entity entity) {
//        if(getCollisionBounds().intersects(entity.getCollisionBounds())) {
//            getEntityCollideEvents().RunEntityEntityCollideEvent(entity,this);
//        }
//    }

    @Override
    protected void updatePos() {
        xMove = 0;
        yMove = 0;
        moveTick++;

        if(moveTick % UPS  == UPS/12) {
            direction = new Random().nextInt(4);
//            System.out.println((direction));
        }

        switch(direction) {
            case 0:
                //Move up
                yMove = -speed;
                break;
            case 1:
                //Move down
                yMove = speed;
                break;
            case 2:
                //Move left
                xMove = -speed;
                break;
            case 3:
                //Move right
                xMove = speed;
                break;
        }
    }

    private void flipDirection() {
        switch(direction) {
            case 0:
                direction = 1;
                break;
            case 1:
                direction = 0;
                break;
            case 2:
                direction = 3;
                break;
            case 3:
                direction = 2;
                break;
        }
    }
    @Override
    protected void updateAction() {

    }
}
