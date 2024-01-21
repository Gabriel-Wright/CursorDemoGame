package options.score;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ScoreWriter {

    private final static String FOLDER_NAME = "config";
    private final static String FILE_NAME = "scores.txt";
    private static String RELATIVE_PATH;
    public ScoreWriter() {
        RELATIVE_PATH = FOLDER_NAME + File.separator + FILE_NAME;
        checkFolderFile();
    }

    private void checkFolderFile() {
        if (!checkFolder()) {
//            logger.info("No config folder found - attempting to create");
            createFolder();
        }
        if (!checkFile()) {
//            logger.info("No config file found - attempting to create");
            boolean fileCreated = false;
            while (!fileCreated) {
                fileCreated = createFile();
            }
        }
        // assign config file
//        logger.info("config.properties file found: " + CONFIG_FILE_PATH.toString());

    }

    private boolean checkFolder() {
        String absolutePath = System.getProperty("user.dir") + File.separator + FOLDER_NAME;
        File file = new File(absolutePath);
        ;
        return file.exists();
    }

    private boolean checkFile() {
        String absolutePath = System.getProperty("user.dir") + File.separator + RELATIVE_PATH;
        File file = new File(absolutePath);
        return file.exists();
    }

    private void createFolder() {
        String absolutePath = System.getProperty("user.dir") + File.separator + FOLDER_NAME;
        File folder = new File(absolutePath);
        folder.mkdir();
    }

    private boolean createFile() {
        String absolutePath = System.getProperty("user.dir") + File.separator + RELATIVE_PATH;

        try (FileOutputStream fileOutputStream = new FileOutputStream(absolutePath)) {
            String log = String.format("Config file created at %s", absolutePath);
//            logger.info(log);
            // Store an empty properties entry to format .properties file.
            Properties properties = new Properties();
            properties.store(fileOutputStream, null);
            // Return true as file created
            return true;
        } catch (IOException e) {
            String log = String.format("Failed to create properties file %s", e.getMessage());
//            logger.error(log);
            return false;
        }
    }


    public void saveScore(ScoreEntry scoreEntry) {
        try {
            String absolutePath = System.getProperty("user.dir") + File.separator + RELATIVE_PATH;
            String name = scoreEntry.getName();
            int score = scoreEntry.getScore();

            // Loading pre-existing properties within config file
            Properties properties = new Properties();
            FileInputStream inputStream = new FileInputStream(absolutePath);
            properties.load(inputStream);

            // Set boolean property
            properties.setProperty(name, score+"");

            // Save updated properties
            FileOutputStream outputStream = new FileOutputStream(absolutePath);
            properties.store(outputStream, null);
            outputStream.close();
//            logger.info(String.format("Property %s updated: %s", propertyName, property));
        } catch (IOException e) {
//            logger.error(String.format("Failed to update property %s: %s. %s", propertyName, property, e.getMessage()));
        }
    }

}
