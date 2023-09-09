package object;

import entities.Entity;
import entities.player.Player;
import levels.Level;
import ui.UI;
import ui.UITag;

import java.awt.image.BufferedImage;

import static ui.UI.addUITag;

public class CollectableObject extends SuperObject{

    private String name;

    public CollectableObject(int x, int y, BufferedImage objectImage, boolean collided, String name) {
        super(x, y, objectImage, collided);
        this.name = name;
    }

    public CollectableObject(int x, int y, BufferedImage objectImage, boolean collided) {
        super(x,y, objectImage, collided);
    }

    @Override
    public void CollideWithEntity(Entity entity, Level level) {
        //Object vanishes

        System.out.println("Entity");
    }

    public void CollideWithEntity(Player player, Level level) {
        level.getLevelObjects()[x][y] = null;
        UITag uiTag = new UITag("Collected object:"+name,"bla");
        player.getPlayerInventory().addCollectable(this);
        addUITag(uiTag);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

