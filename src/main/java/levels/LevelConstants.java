package levels;

import gameObjects.objects.ObjectConstants;
import tile.TileConstants;

import static utils.LoadFiles.readCsvTiles;
import static utils.LoadFiles.readJsonTiles;

public class LevelConstants {

    //Contains these within a LevelConstants object
    private ObjectConstants objectConstants;
    private TileConstants tileConstants;

    //Level integer references
    private static final String MATRIX_ARRAY_NAME = "Tiles";
    public static final int TEST_LEVEL=0;
    public static final int TEST_DEMICHROME=1;

    public int[] getObjectReferences(int i) {
        switch(i) {
            case TEST_LEVEL, TEST_DEMICHROME:
                int[] objectReferences = {ObjectConstants.GUN};
                return objectReferences;
        }
        return null;
    }

    public TileConstants.TileType[] getTileReferences(int i) {
        TileConstants.TileType[] tileReferences;
        return switch (i) {
            case TEST_LEVEL -> {
                tileReferences = new TileConstants.TileType[]{TileConstants.TileType.LIGHT_BLUE, TileConstants.TileType.GREY, TileConstants.TileType.PURPLE, TileConstants.TileType.TRANSPARENT};
                yield tileReferences;
            }
            case TEST_DEMICHROME -> {
                tileReferences = new TileConstants.TileType[]{TileConstants.TileType.DEMITRANSPARENT, TileConstants.TileType.SOLID_WALL,
                        TileConstants.TileType.SOLID_WALL_EXPOSED_LEFT, TileConstants.TileType.SOLID_WALL_EXPOSED_RIGHT, TileConstants.TileType.SOLID_WALL_EXPOSED_BOTTOM, TileConstants.TileType.SOLID_WALL_EXPOSED_TOP,
                        TileConstants.TileType.SOLID_WALL_EXPOSED_TOP_LEFT, TileConstants.TileType.SOLID_WALL_EXPOSED_TOP_RIGHT, TileConstants.TileType.SOLID_WALL_EXPOSED_BOTTOM_RIGHT, TileConstants.TileType.SOLID_WALL_EXPOSED_BOTTOM_LEFT,
                        TileConstants.TileType.SOLID_WALL_UNEXPOSED_BOTTOM, TileConstants.TileType.SOLID_WALL_UNEXPOSED_LEFT, TileConstants.TileType.SOLID_WALL_UNEXPOSED_RIGHT, TileConstants.TileType.SOLID_WALL_UNEXPOSED_TOP};
                yield tileReferences;
            }
            default -> null;
        };
    }

    public void loadLevelConstants(int i) {
        loadTileConstants(i);
        loadObjectConstants(i);
    }
    private void loadTileConstants(int i) {
        tileConstants = new TileConstants();
        tileConstants.loadTileBufferedImageConstants(getTileReferences(i));
    }
    private void loadObjectConstants(int i) {
        objectConstants = new ObjectConstants();
        objectConstants.loadBufferedImageConstants(getObjectReferences(i));
    }

    private String getLevelPath(int i) {
        switch(i) {
            case TEST_LEVEL:
                return "/levelMaps/3219.json";
            case TEST_DEMICHROME:
//                return "/levelMaps/testmap.csv";
//                return "/levelMaps/mazeTest.csv";
                return "/levelMaps/enemySpawns.csv";

        }
        return null;
    }

    public int[][] getLevelData(int i) {
        return switch(i) {
            case TEST_LEVEL -> getLevelJsonData(i);
            case TEST_DEMICHROME -> getCSVData(i);
            default -> getLevelJsonData(TEST_LEVEL);
        };
    }
    public int[][] getLevelJsonData(int i) {
        String jsonPath = getLevelPath(i);
        return readJsonTiles(jsonPath, MATRIX_ARRAY_NAME);
    }

    public int[][] getCSVData(int i) {
        String csvPath = getLevelPath(i);
        return readCsvTiles(csvPath);
    }
    public ObjectConstants getObjectConstants() {
        return objectConstants;
    }

    public TileConstants getTileConstants() {
        return tileConstants;
    }



}
