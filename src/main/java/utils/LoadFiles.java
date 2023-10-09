package utils;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

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
        try {
            // Read the JSON file from the resources folder
            InputStream inputStream = LoadFiles.class.getResourceAsStream(path);
            InputStreamReader reader = new InputStreamReader(inputStream);
            JSONTokener jsonTokener = new JSONTokener(reader);

            // Parse the JSON data
            JSONObject json = new JSONObject(jsonTokener);

            // Access the labeled grid
            JSONArray myGrid = json.getJSONArray(matrixName);

            // Convert the JSONArray into a 2D int array
            int numRows = myGrid.getJSONArray(0).length();
            int numCols = myGrid.length();
            int[][] gridArray = new int[numRows][numCols];

            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    gridArray[i][j] = myGrid.getJSONArray(j).getInt(i);
                }
            }

            return gridArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle the error appropriately
        }
    }
}
