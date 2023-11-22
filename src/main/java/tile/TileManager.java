package tile;

import utils.LoadFiles;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;


public class TileManager {

    private String tileSpritesPath;
    private BufferedImage tileSprites;
    private TileConstants tileConstants;
    //private Tile[] tiles;
    private Map<Integer, Tile> tiles;
    public TileManager(String tileSpritesPath) {
        this.tileSpritesPath = tileSpritesPath;
        this.tileSprites = LoadFiles.importImg(tileSpritesPath);
    }

    public TileManager(TileConstants tileConstants) {
        this.tileConstants = tileConstants;
    }
//    public void loadTiles() {
//        //Each tile has a uniform size of sprite
//        int totalheight = tileSprites.getHeight();
//        int numTiles = totalheight / 32; // Need to adjust constant used here - essentially this refers to the actual
//        // size of the image
//        tiles = new Tile[numTiles];
//        int startXDim, startYDim, width, height;
//        //No borders for any of our tiles in spritesheet
//        //Unsure of way to read in whether solidOrNot yet
//        for (int i = 0; i < numTiles; i++) {
//            int y = i * 32;
//            tiles[i] = new Tile(tileSprites.getSubimage(0, y, 32, 32), false);
//            if (TileConstants.getSolidMaxIndex(tileSpritesPath) >= i) {
//                tiles[i].setSolid();
//            }
//        }
//    }

    public void loadTiles(int[] tileReferences){
        tiles = new HashMap<>();
        for(int tileReference : tileReferences) {
            Tile tempTile = new Tile(tileConstants.getTileImage(tileReference),tileConstants.getTileSolid(tileReference));
            tiles.put(tileReference, tempTile);
        }
    }

    public Map<Integer,Tile> getTiles() {
        return tiles;
    }
}
