package options.sound;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import static utils.LoadFiles.importSound;

public class SoundConstants {

    public final static int PAUSE_RESUME = 0;
    public final static int OBTAIN = 1;
    public final static int ENTITY_SPAWN = 2;

    public final static int numAudioClips = 3;

    private static Clip[] preLoadedAudioClips;

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

    public void loadAudioClips() {
        //Have to manually set this for the number of clips unfortunately

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

    //Adjusting volume

    public void adjustVolume(float volume) {
        int i;
        if(preLoadedAudioClips.length != numAudioClips) {
            System.out.println("Could not adjust volume, mismatch of number of clips");
            return;
        }
        for(i=0; i<numAudioClips; i++) {
            adjustAudioClipVolume(preLoadedAudioClips[i], volume);
        }
    }

    private static void adjustAudioClipVolume(Clip clip, float volume) {
        if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = gainControl.getMaximum() - gainControl.getMinimum();
            float gain = (range * volume) + gainControl.getMinimum();
            gainControl.setValue(gain);
        }
    }
}
