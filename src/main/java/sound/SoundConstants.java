package sound;

import javax.sound.sampled.Clip;

import static utils.LoadFiles.importSound;

public class SoundConstants {

    public final static int PAUSE_RESUME = 0;
    public final static int OBTAIN = 1;
    public final static int ENTITY_SPAWN = 2;

    private static Clip[] preLoadedAudioClips;

    public SoundConstants() {
        loadAudioClips();
    }

    public static String getSoundFilePath(int i) {
        switch(i) {
            case PAUSE_RESUME:
                return "/soundEffects/options/pause.wav";
            case OBTAIN:
                return "/soundEffects/options/pause.wav";
            case ENTITY_SPAWN:
                return "/soundEffects/options/bong.wav";
        }
        return "";
    }

    private void loadAudioClips() {
        //Have to manually set this for the number of clips unfortunately
        int numAudioClips = 3;
        preLoadedAudioClips = new Clip[numAudioClips];
        int i;
        for(i = 0; i<numAudioClips; i++) {
            String soundFilePath = getSoundFilePath(i);
            preLoadedAudioClips[i] = importSound(soundFilePath);
        }
    }

    public static Clip getAudioClip(int index) {
        return preLoadedAudioClips[index];
    }

    public static void adjustAudioClipVolume(int index) {
        //This will change the volume of the preloaded audio clips here.
    }
}
