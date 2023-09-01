package entities.constants;

public interface AnimationConstants {

    public String getAnimationPath();

    public int[] getAnimationFlags();

    public int getNumAnimationFrames(int playerAction);

    public int getMaxNumAnimationFrames();

    public int getAnimationStartXDimension(int playerAction);

    public int getAnimationStartYDimension(int playerAction);

    public int getAnimationWidth(int playerAction);

    public int getAnimationHeight(int playerAction);

}
