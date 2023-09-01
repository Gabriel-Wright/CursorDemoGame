package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
}
