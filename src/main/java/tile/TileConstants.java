package tile;

public class TileConstants {

    public static int getSolidMaxIndex(String tileSpritePath) {
        switch(tileSpritePath) {
            case "/testTileMap.png":
                return 2;
        }
        return 0;
    }
}
