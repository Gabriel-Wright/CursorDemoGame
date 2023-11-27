package tile;

import java.awt.image.BufferedImage;

import static main.GamePanel.TILE_SIZE;
import static utils.LoadFiles.importImg;

public class TileConstants {

    //Store Tile names with enumerator Tile type - i think it is still easier to
    public enum TileType {
        SOLID_WALL_EXPOSED_RIGHT(0),
        SOLID_WALL_EXPOSED_BOTTOM(1),
        SOLID_WALL_EXPOSED_LEFT(2),
        SOLID_WALL_EXPOSED_TOP(3),
        SOLID_WALL_EXPOSED_TOP_RIGHT(4),
        SOLID_WALL_EXPOSED_BOTTOM_RIGHT(5),
        SOLID_WALL_EXPOSED_BOTTOM_LEFT(6),
        SOLID_WALL_EXPOSED_TOP_LEFT(7),
        SOLID_WALL_UNEXPOSED_RIGHT(8),
        SOLID_WALL_UNEXPOSED_BOTTOM(9),
        SOLID_WALL_UNEXPOSED_LEFT(10),
        SOLID_WALL_UNEXPOSED_TOP(11),
        SOLID_WALL(12),
        DEMITRANSPARENT(13),
        //Test level
        LIGHT_BLUE(0),
        GREY(1),
        PURPLE(2),
        TRANSPARENT(3);

        public final int id;
        TileType(int id) {
            this.id = id;
        }
    }

    private BufferedImage[] loadedTileImages = new BufferedImage[TileType.values().length];

    public void loadTileBufferedImageConstants(TileType[] tiles) {
        for (TileType tile : tiles) {
            loadedTileImages[tile.id] = loadSubImage(tile);
        }
    }

    public boolean getTileSolid(TileType tile) {
        return switch (tile) {
            case TRANSPARENT, DEMITRANSPARENT -> false;
            default -> true;
        };
    }

    //Need to make this more efficient - reloads the same tile multiple times
    private BufferedImage loadSubImage(TileType tile) {
        BufferedImage tempImage = importImg(getTileFilePath(tile));
        return tempImage.getSubimage(getTileImageStartX(tile), getTileImageStartY(tile), getTileWidth(tile), getTileHeight(tile));
    }

    private String getTileFilePath(TileType tile) {
        return switch (tile) {
            case LIGHT_BLUE, GREY, PURPLE, TRANSPARENT -> "/tiles/testTiles.png";
            default -> "/tiles/demiChromeTiles.png";
        };
    }

    public BufferedImage getTileImage(TileType tile) {
        return loadedTileImages[tile.id];
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
            case SOLID_WALL_EXPOSED_BOTTOM_RIGHT -> 80;
            case SOLID_WALL_EXPOSED_TOP_RIGHT -> 64;
            case SOLID_WALL_EXPOSED_TOP_LEFT -> 112;
            case SOLID_WALL_EXPOSED_BOTTOM_LEFT -> 96;
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
