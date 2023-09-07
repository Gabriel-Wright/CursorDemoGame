package object;

import utils.LoadFiles;

import java.awt.image.BufferedImage;

public class ObjectManager {

    public String objectSpritesPath;
    private BufferedImage objectSprites;
    private SuperObject[] objects;

    public ObjectManager(String objectSpritesPath) {
        this.objectSpritesPath = objectSpritesPath;
        this.objectSprites = LoadFiles.importImg(objectSpritesPath);
    }

    public SuperObject[] getObjects() {
        return objects;
    }

    //This is only for if objects all in one png, which is p stupid
    public void loadObjects() {
        int totalHeight = objectSprites.getHeight();
        int numObjects = totalHeight/32;
        objects = new SuperObject[numObjects];
        for(int i=0; i<numObjects; i++) {
            int y=i*32;
            objects[i] = new CollectableObject(0,0,objectSprites.getSubimage(0,y,32,32), false);
        }
    }
}

