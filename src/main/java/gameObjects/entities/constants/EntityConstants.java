package gameObjects.entities.constants;

//Create a constants class for each unique entity -this allows us to store the same values for them that can be passed
//into an Entities object.
public abstract class EntityConstants implements AnimationConstants, HitboxConstants, EventConstants{

    public static final int IDLE = 0;
    public static final int WALKING = 1;
    public static final int DEAD = 2;
    public static final int SHOOTING = 3;
    public abstract float getSpeed();
}
