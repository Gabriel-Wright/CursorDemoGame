package tile;

import utils.LoadFiles;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;


public class TileManager {

    private TileConstants tileConstants;
    private Map<Integer, Tile> tiles;

    public TileManager(TileConstants tileConstants) {
        this.tileConstants = tileConstants;
    }

    public void loadTiles(TileConstants.TileType[] tileReferences){
        tiles = new HashMap<>();
        for(TileConstants.TileType tileReference : tileReferences) {
            Tile tempTile = new Tile(tileConstants.getTileImage(tileReference),tileConstants.getTileSolid(tileReference));
            tiles.put(tileReference.id, tempTile);
        }
    }

    public Map<Integer,Tile> getTiles() {
        return tiles;
    }
}
