package object;

import entities.Entity;
import entities.player.Player;
import levels.Level;
import utils.UI;

import java.awt.image.BufferedImage;

import static utils.UI.addMessage;

public class CollectableObject extends SuperObject{

    private String name;

    public CollectableObject(int x, int y, BufferedImage objectImage, boolean collided) {
        super(x, y, objectImage, collided);
    }

    @Override
    public void CollideWithEntity(Entity entity, Level level) {
        //Object vanishes

        System.out.println("Entity");
    }

    public void CollideWithEntity(Player player, Level level) {
        level.getLevelObjects()[x][y] = null;
        addMessage("Collected object!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

