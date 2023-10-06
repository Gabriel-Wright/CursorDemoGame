package gameObjects.entities.enemies.GreenDeath;

import gameObjects.entities.Entity;

public class GreenDeath extends Entity {

    public GreenDeath(int x, int y, GreenDeathConstants greenDeathConstants) {
        super(x, y ,greenDeathConstants);
        action = GreenDeathConstants.IDLE;
    }
    @Override
    protected void updatePos() {

    }

    @Override
    protected void updateAction() {

    }
}
