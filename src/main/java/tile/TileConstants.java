package tile;

import java.awt.image.BufferedImage;

import static main.GamePanel.TILE_SIZE;
import static utils.LoadFiles.importImg;

public class TileConstants {


    public final static int LIGHT_BLUE = 0;
    public final static int GREY = 1;
    public final static int PURPLE = 2;
    public final static int TRANSPARENT = 3;

    private BufferedImage LIGHT_BLUE_IMAGE;
    private BufferedImage GREY_IMAGE;
    private BufferedImage PURPLE_IMAGE;
    private BufferedImage TRANSPARENT_IMAGE;

    public void loadTileBufferedImageConstants(int[] tiles) {
        for (int i : tiles) {
            switch (i) {
                case LIGHT_BLUE:
                    LIGHT_BLUE_IMAGE = loadSubImage(i);
                    break;
                case GREY:
                    GREY_IMAGE = loadSubImage(i);
                    break;
                case PURPLE:
                    PURPLE_IMAGE = loadSubImage(i);
                    break;
                case TRANSPARENT:
                    TRANSPARENT_IMAGE = loadSubImage(i);
            }
        }
    }

    public boolean getTileSolid(int i) {
        switch(i) {
            case LIGHT_BLUE, GREY, PURPLE:
                return true;
            case TRANSPARENT:
                return false;
        } return false;
    }

    private BufferedImage loadSubImage(int i) {
        BufferedImage tempImage = importImg(getTileFilePath(i));
        return tempImage.getSubimage(getTileImageStartX(i), getTileImageStartY(i), getTileWidth(i), getTileHeight(i));
    }
    private String getTileFilePath(int i) {
        switch (i) {
            case LIGHT_BLUE, GREY, PURPLE, TRANSPARENT:
                return "/tiles/testTiles.png";
        }
        return null;
    }

    public BufferedImage getTileImage(int i) {
        switch(i) {
            case LIGHT_BLUE:
                return LIGHT_BLUE_IMAGE;
            case GREY:
                return GREY_IMAGE;
            case PURPLE:
                return PURPLE_IMAGE;
            case TRANSPARENT:
                return TRANSPARENT_IMAGE;
        } return TRANSPARENT_IMAGE;
    }

    private int getTileImageStartX(int i) {
        switch(i) {
            case LIGHT_BLUE, GREY, PURPLE, TRANSPARENT:
                return 0;
        } return 0;
    }

    private int getTileImageStartY(int i) {
        switch(i) {
            case LIGHT_BLUE:
                return 0;
            case GREY:
                return 32;
            case PURPLE:
                return 64;
            case TRANSPARENT:
                return 96;
        } return 96;
    }

    private int getTileWidth(int i) {
        return 32;
    }

    private int getTileHeight(int i) {
        return 32;
    }
}
