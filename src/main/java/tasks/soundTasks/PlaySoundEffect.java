package tasks.soundTasks;

import tasks.Task;

import javax.sound.sampled.Clip;

import static tasks.soundTasks.SoundConstants.getSoundFilePath;
import static utils.LoadFiles.importSound;

public class PlaySoundEffect extends Task {

    private Clip sound;
    private int soundID;
    public PlaySoundEffect(int soundID) {
        sound = importSound(getSoundFilePath(soundID));
        this.soundID = soundID;
    }

    public void playSound() {
        complete = false;
        if (sound != null && !sound.isRunning()) {
            sound.setFramePosition(0);
            sound.start();
        }
        //If sound is already playing
        if(sound != null && sound.isRunning()) {
            Clip cloneClip = importSound(getSoundFilePath(soundID));
            cloneClip.setFramePosition(0);
            cloneClip.start();
        }

    }

    //Checks whether sound has finished playing - if so task is removed
    @Override
    public void runTask() {
        if(!sound.isRunning()) {
            setComplete();
        }
    }
}
