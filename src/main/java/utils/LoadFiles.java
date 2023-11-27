package utils;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

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
            int numCol = myGrid.getJSONArray(0).length();
            int numRows = myGrid.length();
            int[][] gridArray = new int[numCol][numRows];

            for (int i = 0; i < numCol; i++) {
                for (int j = 0; j < numRows; j++) {
                    gridArray[i][j] = myGrid.getJSONArray(j).getInt(i);
                }
            }

            return gridArray;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle the error appropriately
        }
    }

    public static int[][] readCsvTiles(String path) {
        try {
            // Read the CSV file from the resources folder
            Reader reader = new InputStreamReader(LoadFiles.class.getResourceAsStream(path));
            CSVReader csvReader = new CSVReader(reader);

            // Read all lines from the CSV
            List<String[]> csvLines = csvReader.readAll();

            // Convert the List<String[]> into a 2D int array
            int numRows = csvLines.size();
            int numCols = csvLines.get(0).length;
            int[][] gridArray = new int[numCols][numRows];

            for (int i = 0; i < numCols; i++) {
                for (int j = 0; j < numRows; j++) {
                    gridArray[i][j] = Integer.parseInt(csvLines.get(j)[i]);
                }
            }

            return gridArray;
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return null; // Handle the error appropriately
        }
    }

}
