package entities.constants;

//Create a constants class for each unique entity -this allows us to store the same values for them that can be passed
//into an Entities object.
public abstract class EntityConstants implements AnimationConstants, HitboxConstants{

    public abstract float getSpeed();
}
