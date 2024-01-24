package options.score;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Properties;

public class ScoreReader {

    private final static String FOLDER_NAME = "config";
    private final static String FILE_NAME = "scores.txt";
    private static String ABSOLUTE_PATH;

    private static File SCORE_FILE;

    public ScoreReader() {
        ABSOLUTE_PATH = System.getProperty("user.dir")+ File.separator + FOLDER_NAME + File.separator + FILE_NAME;
        SCORE_FILE = new File(ABSOLUTE_PATH);
    }

    public ArrayList<ScoreEntry> readScores() {
        ArrayList<ScoreEntry> scores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");

                if (parts.length == 2) {
                    String name = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());

                    ScoreEntry entry = new ScoreEntry(name, score);
                    scores.add(entry);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Sort scores in descending order
        scores.sort(Comparator.comparingInt(ScoreEntry::getScore).reversed());

//        printScores(scores);
        return scores;
    }

    private void printScores(ArrayList<ScoreEntry> scores) {
        int index = 1;
        for(ScoreEntry score: scores) {
//            System.out.println(index + ")" + score.getName() +":"+score.getScore());
            index++;
        }
    }




}
