package gameObjects.objects;

import gameObjects.events.CollectItem;
import gameObjects.events.generic.Event;

import java.awt.image.BufferedImage;

import static utils.LoadFiles.importImg;

public class ObjectConstants {

    //OBJECT reference integers
    public final static int GUN = 0;


    //OBJECT reference images, initialised to nothing
    private BufferedImage GUN_IMAGE = null;
    //Need to make decision of how Objects are loaded to bufferedImages. I can have the constants load them here at choice.
    //Want to be able to be loaded just by a constant? Similar to how sound is handled?
    //Then load all the assets needed for a given level by just the integer.

    //Loads relevant GUN_IMAGE
    public void loadBufferedImageConstants(int[] objects) {
        for (int object : objects) {
            switch (object) {
                case GUN:
                    GUN_IMAGE = importImg(getObjectImageFilePath(object));
                    break;
                // Add cases for other objects here
            }
        }
    }
    public String getObjectName(int i) {
        switch(i) {
            case GUN:
                return "Gun";
        } return "Null Item";
    }
    private String getObjectImageFilePath(int i) {
        switch(i) {
            case GUN:
                return "";
        } //Replace this with empty
        return "";
    }

    private int getObjectImageStartX(int i) {
        return 0;
    }

    private int getObjectImageStartY(int i) {
        return 0;
    }

    public int getObjectWidth(int i) {
        return 0;
    }
    public int getObjectHeight(int i) {
        return 0;
    }
    public BufferedImage getObjectImage(int i) {
        switch(i) {
            case GUN:
                return GUN_IMAGE;
        }
        return null;
    }

    public Event getObjectEvent(int i) {
        switch(i) {
            case GUN:
                Event gunEvent = new CollectItem();
                return gunEvent;
        }
        return null;
    }
}
