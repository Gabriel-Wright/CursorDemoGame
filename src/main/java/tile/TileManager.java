package tile;

import utils.LoadFiles;

import java.awt.image.BufferedImage;

import static main.GamePanel.TILE_SIZE;
import static main.GamePanel.originalTileSize;

public class TileManager {

    private String tileSpritesPath;
    private BufferedImage tileSprites;
    private Tile[] tiles;

    public TileManager(String tileSpritesPath) {
        this.tileSpritesPath = tileSpritesPath;
        this.tileSprites = LoadFiles.importImg(tileSpritesPath);
    }

    public void loadTiles() {
        //Each tile has a uniform size of sprite
        int totalheight = tileSprites.getHeight();
        int numTiles = totalheight / 32; // Need to adjust constant used here - essentially this refers to the actual
        // size of the image
        tiles = new Tile[numTiles];
        int startXDim, startYDim, width, height;
        //No borders for any of our tiles in spritesheet
        //Unsure of way to read in whether solidOrNot yet
        for (int i = 0; i < numTiles; i++) {
            int y = i * 32;
            tiles[i] = new Tile(tileSprites.getSubimage(0, y, 32, 32), false);
            if (TileConstants.getSolidMaxIndex(tileSpritesPath) >= i) {
                tiles[i].setSolid();
            }
        }
    }

    public Tile[] getTiles() {
        return tiles;
    }
}
