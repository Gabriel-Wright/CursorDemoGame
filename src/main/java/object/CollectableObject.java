package object;

import entities.Entity;
import entities.player.Player;
import levels.Level;
import ui.UI;
import ui.UITag;

import java.awt.image.BufferedImage;

import static ui.UI.addUITag;

public class CollectableObject extends SuperObject{

    public CollectableObject(int x, int y, int objectID, ObjectConstants objectConstants) {
        super(x, y, objectID, objectConstants);
    }

    @Override
    public void CollideWithEntity(Entity entity, Level level) {
        //Object vanishes
        System.out.println("Entity");
    }

    public void CollideWithEntity(Player player, Level level) {
        level.getLevelObjects()[x][y] = null;
        UITag uiTag = new UITag("Collected object:"+objectName,"bla");
        player.getPlayerInventory().addCollectable(this);
        addUITag(uiTag);
    }

}

