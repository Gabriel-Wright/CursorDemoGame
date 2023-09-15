package utils;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

public class SoundPlayer {

    public static void PlaySound(Clip soundClip) {
        soundClip.addLineListener(new LineListener() {
            @Override
            public void update(LineEvent event) {
                if (event.getType() == LineEvent.Type.STOP) {
                    soundClip.close();
                    soundClip.setFramePosition(0); // Reset frame position to the beginning
                    soundClip.start();
                }
            }
        });

        Thread soundThread = new Thread(() -> {
            try {
                soundClip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        soundThread.start();
    }
}
