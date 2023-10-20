package gameObjects.objects;

import utils.LoadFiles;

import java.awt.image.BufferedImage;

public class ObjectManager {

    public String objectSpritesPath;
    private BufferedImage objectSprites;
    private ObjectConstants objectConstants;
    private SuperObject[] objects;

    public ObjectManager(String objectSpritesPath) {
        this.objectSpritesPath = objectSpritesPath;
        this.objectSprites = LoadFiles.importImg(objectSpritesPath);
    }

    public ObjectManager(ObjectConstants objectConstants) {
        this.objectConstants = objectConstants;
    }

    public SuperObject[] getCollectableObjects() {
        return objects;
    }


    public void loadObjects() {
        objects = new SuperObject[2];

        objects[0] = new SuperObject(7,5,ObjectConstants.GUN, objectConstants);
        objects[1] = new SuperObject(5,11,ObjectConstants.GUN, objectConstants);
    }
}

