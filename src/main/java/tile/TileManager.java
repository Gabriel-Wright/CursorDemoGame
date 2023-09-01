package tile;

import utils.LoadFiles;

import java.awt.image.BufferedImage;
import static main.GamePanel.tileSize;
public class TileManager {

    private String tileSpritesPath;
    private BufferedImage tileSprites;
    private Tile[] tiles;
    public TileManager(String tileSpritesPath){
        this.tileSpritesPath = tileSpritesPath;
        this.tileSprites = LoadFiles.importImg(tileSpritesPath);
    }

    public void loadTiles() {
        //Each tile has a uniform size of sprite
        int totalheight = tileSprites.getHeight();
        int numTiles = totalheight/tileSize;
        tiles = new Tile[numTiles];
        int startXDim, startYDim, width, height;
        //No borders for any of our tiles in spritesheet
        //Unsure of way to read in whether solidOrNot yet
        for(int y=0,i=0; y<=totalheight; y+=32,i++){
            tiles[i] = new Tile(tileSprites.getSubimage(0,y,tileSize,tileSize),false);
            if(TileConstants.getSolidMaxIndex(tileSpritesPath) == i) {
                tiles[i].setSolid();
            }
        }
    }

    public Tile[] getTiles() {
        return tiles;
    }
}
