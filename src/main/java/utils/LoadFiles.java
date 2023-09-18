package utils;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONObject;

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

    public static int[][] readJsonTiles(String path, String matrixName) {
        JSONObject json = new JSONObject(path);

        // Access the labeled grid
        JSONArray myGrid = json.getJSONArray(matrixName);

        // Convert the JSONArray into a 2D int array
        int numRows = myGrid.length();
        int numCols = myGrid.getJSONArray(0).length();
        int[][] gridArray = new int[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            JSONArray row = myGrid.getJSONArray(i);
            for (int j = 0; j < numCols; j++) {
                gridArray[i][j] = row.getInt(j);
            }
        }

        return gridArray;
    }
}
