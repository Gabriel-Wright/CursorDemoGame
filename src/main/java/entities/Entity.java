package entities;

import animations.EntityAnimations;
import entities.constants.EntityConstants;

public class Entity {

    protected float x , y;
    protected EntityAnimations animations;
    protected int aniTick, aniIndex, aniSpeed;
    protected int entityWidth, entityHeight;
    private EntityConstants entityConstants;

    public Entity(EntityConstants entityConstants) {
        this.entityConstants = entityConstants;
    }

}
