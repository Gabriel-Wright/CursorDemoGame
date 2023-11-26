package tile;

import java.awt.image.BufferedImage;

import static main.GamePanel.TILE_SIZE;
import static utils.LoadFiles.importImg;

public class TileConstants {

    //Store Tile names with enumerator Tile type - i think it is still easier to
    public enum TileType {
        LIGHT_BLUE, GREY, PURPLE, TRANSPARENT,
        DEMITRANSPARENT, SOLID_WALL,
        SOLID_WALL_EXPOSED_LEFT, SOLID_WALL_EXPOSED_RIGHT, SOLID_WALL_EXPOSED_TOP, SOLID_WALL_EXPOSED_BOTTOM,
        SOLID_WALL_EXPOSED_TOP_LEFT, SOLID_WALL_EXPOSED_TOP_RIGHT, SOLID_WALL_EXPOSED_BOTTOM_RIGHT, SOLID_WALL_EXPOSED_BOTTOM_LEFT,
        SOLID_WALL_UNEXPOSED_BOTTOM, SOLID_WALL_UNEXPOSED_LEFT, SOLID_WALL_UNEXPOSED_TOP, SOLID_WALL_UNEXPOSED_RIGHT
    }

    private BufferedImage[] loadedTileImages = new BufferedImage[TileType.values().length];

    public void loadTileBufferedImageConstants(TileType[] tiles) {
        for (TileType tile : tiles) {
            loadedTileImages[tile.ordinal()] = loadSubImage(tile);
        }
    }

    public boolean getTileSolid(TileType tile) {
        return switch (tile) {
            case TRANSPARENT, DEMITRANSPARENT -> false;
            default -> true;
        };
    }

    private BufferedImage loadSubImage(TileType tile) {
        BufferedImage tempImage = importImg(getTileFilePath(tile));
        return tempImage.getSubimage(getTileImageStartX(tile), getTileImageStartY(tile), getTileWidth(tile), getTileHeight(tile));
    }

    private String getTileFilePath(TileType tile) {
        return switch (tile) {
            case LIGHT_BLUE, GREY, PURPLE, TRANSPARENT -> "/tiles/testTiles.png";
            default -> null;
        };
    }

    public BufferedImage getTileImage(TileType tile) {
        return loadedTileImages[tile.ordinal()];
    }

    private int getTileImageStartX(TileType tile) {
        return switch (tile) {
            case LIGHT_BLUE, GREY, PURPLE, TRANSPARENT -> 0;
            default -> 0;
        };
    }

    private int getTileImageStartY(TileType tile) {
        return switch (tile) {
            case LIGHT_BLUE -> 0;
            case GREY -> 32;
            case PURPLE -> 64;
            case TRANSPARENT -> 96;
            case SOLID_WALL -> 192;
            case SOLID_WALL_UNEXPOSED_TOP -> 176;
            case SOLID_WALL_UNEXPOSED_LEFT -> 160;
            case SOLID_WALL_UNEXPOSED_BOTTOM -> 144;
            case SOLID_WALL_UNEXPOSED_RIGHT -> 128;
            case SOLID_WALL_EXPOSED_BOTTOM_RIGHT -> 112;
            case SOLID_WALL_EXPOSED_TOP_RIGHT -> 96;
            case SOLID_WALL_EXPOSED_TOP_LEFT -> 80;
            case SOLID_WALL_EXPOSED_BOTTOM_LEFT -> 64;
            case SOLID_WALL_EXPOSED_TOP -> 48;
            case SOLID_WALL_EXPOSED_LEFT -> 32;
            case SOLID_WALL_EXPOSED_BOTTOM -> 16;
            case SOLID_WALL_EXPOSED_RIGHT -> 0;
            case DEMITRANSPARENT -> 208;
            default -> 96;
        };
    }

    private int getTileWidth(TileType tile) {
        return switch(tile) {
            case LIGHT_BLUE, GREY, PURPLE, TRANSPARENT -> 32;
            default -> 16;
        };
    }

    private int getTileHeight(TileType tile) {
        return switch(tile) {
            case LIGHT_BLUE, GREY, PURPLE, TRANSPARENT -> 32;
            default -> 16;
        };
    }
}
