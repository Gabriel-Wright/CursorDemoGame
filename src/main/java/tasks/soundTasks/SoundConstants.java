package tasks.soundTasks;

public class SoundConstants {

    public final static int PAUSE_RESUME = 0;
    public final static int OBTAIN = 1;
    public final static int ENTITY_SPAWN = 2;

    public static String getSoundFilePath(int i) {
        switch(i) {
            case PAUSE_RESUME:
                return "/soundEffects/options/pause.wav";
            case OBTAIN:
                return "";
            case ENTITY_SPAWN:
                return "/soundEffects/options/bong.wav";
        }
        return "";
    }
}
