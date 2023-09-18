package levels;

import object.ObjectConstants;
import tile.Tile;
import tile.TileConstants;

import java.sql.Struct;

import static utils.LoadFiles.readJsonTiles;

public class LevelConstants {

    //Contains these within a LevelConstants object
    private ObjectConstants objectConstants;
    private TileConstants tileConstants;

    //Level integer references
    private static final String MATRIX_ARRAY_NAME = "Tiles";
    public static final int TEST_LEVEL=0;
    private int[][] levelData;
    private int[] getObjectReferences(int i) {
        switch(i) {
            case TEST_LEVEL:
                int[] objectReferences = {ObjectConstants.GUN};
                return objectReferences;
        }
        return null;
    }

    public void loadObjectConstants(int i) {
        objectConstants = new ObjectConstants();
        objectConstants.loadBufferedImageConstants(getObjectReferences(i));
    }
    private String getLevelJsonPath(int i) {
        switch(i) {
            case TEST_LEVEL:
                return "";
        }
        return null;
    }

    public int[][] getLevelJsonData(int i) {
        return readJsonTiles(getLevelJsonPath(i), MATRIX_ARRAY_NAME);
    }

    public ObjectConstants getObjectConstants() {
        return objectConstants;
    }

    public TileConstants getTileConstants() {
        return tileConstants;
    }



}
