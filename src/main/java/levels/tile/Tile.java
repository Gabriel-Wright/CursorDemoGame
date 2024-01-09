package levels.tile;

import animations.EntityAnimations;

import java.awt.image.BufferedImage;

public class Tile {

    // Leave as BufferedImage for now - in case of animated Tiles, create a separate class for this
    protected BufferedImage tileImage;
    protected boolean isSolid = false;

    public Tile(BufferedImage tileImage, boolean isSolid) {
        this.tileImage = tileImage;
        this.isSolid = isSolid;
    }

    public boolean isSolid(){ return isSolid;}

    public BufferedImage getTileImage() {
        return tileImage;
    }

    public void setSolid() {
        isSolid = true;
    }
}
