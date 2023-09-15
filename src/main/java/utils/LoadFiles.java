package utils;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LoadFiles {

    public static BufferedImage importImg(String path) {
        BufferedImage spriteSheet = null;
        InputStream is = LoadFiles.class.getResourceAsStream(path);
        try {
            spriteSheet = ImageIO.read(is);
        } catch (IOException e) {
            //Switch this to logs?
            System.out.println("Failed to load sprite ");
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch(IOException e) {
                //Switch this to logs
                System.out.println("Failed to close inputStream");
                e.printStackTrace();
            }
        }
        return spriteSheet;
    }

    public static Clip importSound(String path) {
        Clip soundClip = null;
        InputStream is = LoadFiles.class.getResourceAsStream(path);

        try{
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
            // Get a clip resource
            soundClip = AudioSystem.getClip();

            // Open the audio clip and load samples from the audio input stream
            soundClip.open(inputStream);

        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return soundClip;
    }
}
