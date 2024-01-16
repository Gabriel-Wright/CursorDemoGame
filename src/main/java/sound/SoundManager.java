package sound;

import javax.sound.sampled.Clip;

import java.util.HashMap;
import java.util.Map;

import static tasks.soundTasks.SoundConstants.getSoundFilePath;
import static utils.LoadFiles.importSound;
public class SoundManager {

    //Use constants class to choose clips
    private Map<Integer, Clip> soundClips;
    private int[] soundConstants;
    public SoundManager(int[] soundConstants) {
        this.soundConstants = soundConstants;
        loadSoundClips(soundConstants);
    }

    private void loadSoundClips(int[] soundConstants) {
        soundClips = new HashMap<>();
        for(int soundConstant: soundConstants) {
            Clip soundClip = importSound(getSoundFilePath(soundConstant));
            soundClips.put(soundConstant, soundClip);
        }
    }

    public void play(int i) {
        Clip clip = soundClips.get(i);
        if (clip != null && !clip.isRunning()) {
            clip.setFramePosition(0);
            clip.start();
        }
        if(clip != null && clip.isRunning()) {
            Clip cloneClip = importSound(getSoundFilePath(i));
            cloneClip.setFramePosition(0);
            cloneClip.start();
        }
    }

    public void stop(int i) {
        if (soundClips.get(i) != null && soundClips.get(i).isRunning()) {
            soundClips.get(i).stop();
        }
    }

    public void close(int i) {
        if (soundClips.get(i) != null) {
            soundClips.get(i).close();
        }
    }

}
