package gameObjects.entities.enemies.GreenDeath;

import gameObjects.entities.Entity;

public class GreenDeath extends Entity {

    public GreenDeath(int x, int y, GreenDeathConstants greenDeathConstants) {
        super(x, y ,greenDeathConstants);
    }

    @Override
    protected void handleEntityCollision(Entity entity) {
//        System.out.println("Overlap??");

    }

    @Override
    protected void updatePos() {

    }

    @Override
    protected void updateAction() {

    }
}
